package guldilin.service.implementation;

import com.rabbitmq.jms.client.message.RMQBytesMessage;
import guldilin.dto.MedicamentDTO;
import guldilin.dto.MedicamentVendorInfoDTO;
import guldilin.dto.MedicamentlVendorInfoQueueTaskDTO;
import guldilin.dto.VendorDTO;
import guldilin.model.Medicament;
import guldilin.model.MedicamentVendorInfo;
import guldilin.model.Vendor;
import guldilin.repository.MedicamentRepository;
import guldilin.repository.MedicamentVendorInfoRepository;
import guldilin.repository.VendorRepository;
import guldilin.service.interfaces.JmsConsumer;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.jms.JMSException;
import javax.jms.Message;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class JmsConsumerImpl implements JmsConsumer {
    MedicamentVendorInfoRepository medicamentVendorInfoRepository;
    MedicamentRepository medicamentRepository;
    VendorRepository vendorRepository;
    ModelMapper modelMapper;

    @Override
    public void onMessage(Message message) {
        byte[] data = new byte[1024];
        try {
            ((RMQBytesMessage) message).readBytes(data);
        } catch (JMSException e) {
            System.err.println("Failed to read JMS message");
            e.printStackTrace();
            return;
        }

        try (
                ByteArrayInputStream in = new ByteArrayInputStream(data);
                ObjectInputStream is = new ObjectInputStream(in);
        ) {
            MedicamentlVendorInfoQueueTaskDTO task = (MedicamentlVendorInfoQueueTaskDTO) is.readObject();
            Medicament medicament = medicamentRepository.findById(task.getMedicamentId()).orElseThrow(
                    () -> new IllegalArgumentException("No such medicament")
            );
            System.out.println("Got new QUEUE task");

            List<Vendor> vendors = vendorRepository.findAll();
            vendors.forEach( vendor -> {
                MedicamentVendorInfo medicamentVendorInfo = new MedicamentVendorInfo();
                medicamentVendorInfo.setVendor(vendor);
                medicamentVendorInfo.setMedicament(medicament);
                medicamentVendorInfoRepository.save(medicamentVendorInfo);
            });
            Thread.sleep(300);
            System.out.println(task);

        } catch (IOException | ClassNotFoundException | IllegalArgumentException | InterruptedException e) {
            System.out.println("Failed to process medicament");
            e.printStackTrace();
        }
    }
}
