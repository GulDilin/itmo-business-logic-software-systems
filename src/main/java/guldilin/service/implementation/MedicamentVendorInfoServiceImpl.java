package guldilin.service.implementation;

import guldilin.dto.MedicamentVendorInfoDTO;
import guldilin.dto.MedicamentlVendorInfoQueueTaskDTO;
import guldilin.model.MedicamentVendorInfo;
import guldilin.repository.MedicamentRepository;
import guldilin.repository.MedicamentVendorInfoRepository;
import guldilin.service.interfaces.MedicamentVendorInfoService;
import org.modelmapper.ModelMapper;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MedicamentVendorInfoServiceImpl implements MedicamentVendorInfoService {
    private final MedicamentVendorInfoRepository medicamentVendorInfoRepository;
    private final MedicamentRepository medicamentRepository;
    private final ModelMapper modelMapper;
    private final AmqpTemplate template;
    private final Queue queue;

    @Autowired
    public MedicamentVendorInfoServiceImpl(
            MedicamentVendorInfoRepository medicamentVendorInfoRepository,
            MedicamentRepository medicamentRepository,
            ModelMapper modelMapper,
            AmqpTemplate template,
            Queue queue
    ) {
        this.medicamentVendorInfoRepository = medicamentVendorInfoRepository;
        this.medicamentRepository = medicamentRepository;
        this.modelMapper = modelMapper;
        this.template = template;
        this.queue = queue;
    }

    public MedicamentVendorInfoDTO get(Long medicamentId) {
        medicamentRepository.findById(medicamentId).orElseThrow(() -> new IllegalArgumentException("No such medicament"));
        Optional<MedicamentVendorInfo> medicamentVendorInfo = medicamentVendorInfoRepository.findByMedicamentId(medicamentId);
        if (medicamentVendorInfo.isPresent()) return mapToDTO(medicamentVendorInfo.get());

        MedicamentlVendorInfoQueueTaskDTO message = new MedicamentlVendorInfoQueueTaskDTO();
        message.setMedicamentId(medicamentId);
        template.convertAndSend(queue.getName(), message);

        throw new IllegalArgumentException("Preparing in process");
    }

    private MedicamentVendorInfoDTO mapToDTO(MedicamentVendorInfo medicamentVendorInfo) {
        return new MedicamentVendorInfoDTO(medicamentVendorInfo);
    }
}
