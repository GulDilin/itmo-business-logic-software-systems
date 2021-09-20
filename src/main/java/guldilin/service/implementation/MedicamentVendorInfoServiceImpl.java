package guldilin.service.implementation;

import guldilin.dto.MedicamentVendorInfoDTO;
import guldilin.dto.MedicamentlVendorInfoQueueTaskDTO;
import guldilin.exceptions.NoSuchObject;
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
    private final AmqpTemplate template;
    private final Queue queue;

    @Autowired
    public MedicamentVendorInfoServiceImpl(
            MedicamentVendorInfoRepository medicamentVendorInfoRepository,
            MedicamentRepository medicamentRepository,
            AmqpTemplate template,
            Queue queue
    ) {
        this.medicamentVendorInfoRepository = medicamentVendorInfoRepository;
        this.medicamentRepository = medicamentRepository;
        this.template = template;
        this.queue = queue;
    }

    public MedicamentVendorInfoDTO get(Long medicamentId) throws NoSuchObject {
        medicamentRepository.findById(medicamentId)
                .orElseThrow(() -> new NoSuchObject(MedicamentVendorInfo.class.getName()));
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
