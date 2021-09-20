package guldilin.service.implementation;

import guldilin.dto.*;
import guldilin.exceptions.NoSuchObject;
import guldilin.model.*;
import guldilin.model.Process;
import guldilin.repository.*;
import guldilin.service.interfaces.MedicamentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MedicamentServiceImpl implements MedicamentService {
    private final MedicamentRepository medicamentRepository;
    private final MedicamentFormulaRepository medicamentFormulaRepository;
    private final MedicamentGroupRepository medicamentGroupRepository;
    private final MedicamentTypeRepository medicamentTypeRepository;
    private final ProcessRepository processRepository;
    private final ProcessApproveRepository processApproveRepository;
    private final ModelMapper modelMapper;
    private final WorkerRoleRepository workerRoleRepository;

    @Autowired
    public MedicamentServiceImpl(
            MedicamentRepository medicamentRepository,
            MedicamentFormulaRepository medicamentFormulaRepository,
            MedicamentGroupRepository medicamentGroupRepository,
            MedicamentTypeRepository medicamentTypeRepository,
            ProcessRepository processRepository,
            ProcessApproveRepository processApproveRepository,
            ModelMapper modelMapper,
            WorkerRoleRepository workerRoleRepository
    ) {
        this.medicamentRepository = medicamentRepository;
        this.medicamentFormulaRepository = medicamentFormulaRepository;
        this.medicamentGroupRepository = medicamentGroupRepository;
        this.medicamentTypeRepository = medicamentTypeRepository;
        this.processRepository = processRepository;
        this.processApproveRepository = processApproveRepository;
        this.modelMapper = modelMapper;
        this.workerRoleRepository = workerRoleRepository;
    }

    @Override
    public List<MedicamentDTO> getAll(String title, Long groupId, Long formulaId, Long activeSubstanceId) {
        return medicamentRepository.findAll()
                .stream()
                .filter(e -> title == null || e.getTitle().equals(title))
                .filter(e -> groupId == null || e.getGroup().getId().equals(groupId))
                .filter(e -> formulaId == null || e.getFormula().getId().equals(formulaId))
                .filter(e -> activeSubstanceId == null || e.getActiveSubstances()
                        .stream()
                        .map(ActiveSubstance::getId)
                        .anyMatch(l -> l.equals(formulaId))
                )
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public MedicamentDTO get(Long id) {
        return mapToDTO(medicamentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("No such medicament")));
    }

    @Override
    public MedicamentFormulaDTO getFormula(Long id) throws NoSuchObject {
        Medicament medicament = medicamentRepository.findById(id)
                .orElseThrow(() -> new NoSuchObject(Medicament.class.getName()));
        if (medicament.getFormula() == null) {
            throw new NoSuchObject(MedicamentFormula.class.getName());
        }
        return new MedicamentFormulaDTO(medicament.getFormula());
    }

    @Override
    public List<ProcessDTO> getProcesses(Long id) throws NoSuchObject {
        medicamentRepository.findById(id)
                .orElseThrow(() -> new NoSuchObject(Medicament.class.getName()));
        return processRepository.findAllByMedicamentId(id)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProcessApproveDTO> getProcessesApproves(Long id, Long processId) throws NoSuchObject {
        medicamentRepository.findById(id)
                .orElseThrow(() -> new NoSuchObject(Medicament.class.getName()));
        getProcesses(id)
                .stream()
                .filter(it -> it.getId().equals(processId))
                .findFirst()
                .orElseThrow(() -> new NoSuchObject(Process.class.getName()));
        return processApproveRepository.findAllByProcessId(processId)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public MedicamentFormulaDTO createFormula(Long id, MedicamentFormulaDTO medicamentFormulaDTO) throws NoSuchObject {
        medicamentFormulaDTO.setId(null);
        Medicament medicament = medicamentRepository.findById(id)
                .orElseThrow(() -> new NoSuchObject(Medicament.class.getName()));
        MedicamentFormula medicamentFormula = mapToEntity(medicamentFormulaDTO);
        medicamentFormulaRepository.save(medicamentFormula);
        medicament.setFormula(medicamentFormula);
        medicamentRepository.save(medicament);
        return mapToDTO(medicamentFormula);
    }

    @Override
    @Transactional
    public MedicamentDTO create(MedicamentDTO medicamentDTO) throws NoSuchObject {
        medicamentDTO.setId(null);
        if (medicamentRepository.findAllByTitle(medicamentDTO.getTitle()).size() > 0) {
            throw new IllegalArgumentException("Medicament with title already exists");
        }
        Medicament medicament = mapToEntity(medicamentDTO);
        medicament = medicamentRepository.save(medicament);
        Process process = new Process();
        process.setMedicament(medicament);
        process.setStatus("started");
        process = processRepository.save(process);
        Process finalProcess = process;
        workerRoleRepository.findAll().forEach(role -> {
            ProcessApprove processApprove = new ProcessApprove();
            processApprove.setProcess(finalProcess);
            processApproveRepository.save(processApprove);
        });
        return mapToDTO(medicament);
    }

    @Override
    public MedicamentDTO update(UpdateMedicamentDTO updateMedicamentDTO) throws NoSuchObject {
        Medicament medicament = medicamentRepository.findById(updateMedicamentDTO.getId())
                .orElseThrow(() -> new NoSuchObject(Medicament.class.getName()));

        Medicament newMedicament = mapToEntity(updateMedicamentDTO);
        if (updateMedicamentDTO.getTitle() != null) {
            medicament.setTitle(updateMedicamentDTO.getTitle());
        }
        if (newMedicament.getMedicamentType() != null) {
            medicament.setMedicamentType(newMedicament.getMedicamentType());
        }
        if (newMedicament.getFormula() != null) {
            medicament.setFormula(newMedicament.getFormula());
        }
        if (newMedicament.getGroup() != null) {
            medicament.setGroup(newMedicament.getGroup());
        }
        return mapToDTO(medicamentRepository.save(medicament));
    }

    private MedicamentDTO mapToDTO(Medicament medicament) {
        return new MedicamentDTO(medicament);
    }

    private ProcessDTO mapToDTO(Process process) {
        return new ProcessDTO(process);
    }

    private ProcessApproveDTO mapToDTO(ProcessApprove processApprove) {
        return new ProcessApproveDTO(processApprove);
    }

    private MedicamentFormulaDTO mapToDTO(MedicamentFormula medicamentFormula) {
        return modelMapper.map(medicamentFormula, MedicamentFormulaDTO.class);
    }

    private Medicament mapToEntity(MedicamentDTO medicamentDTO) throws NoSuchObject {
        Medicament medicament = modelMapper.map(medicamentDTO, Medicament.class);
        if (medicamentDTO.getGroup() != null) {
            medicament.setGroup(
                    medicamentGroupRepository.findById(medicamentDTO.getGroup())
                            .orElseThrow(() -> new NoSuchObject(MedicamentGroup.class.getName())));
        }
        if (medicamentDTO.getMedicamentClass() != null) {
            medicament.setMedicamentType(
                    medicamentTypeRepository.findById(medicamentDTO.getMedicamentClass())
                            .orElseThrow(() -> new NoSuchObject(Medicament.class.getName())));
        }
        return medicament;
    }

    private MedicamentFormula mapToEntity(MedicamentFormulaDTO medicamentFormulaDTO) {
        return modelMapper.map(medicamentFormulaDTO, MedicamentFormula.class);
    }
}
