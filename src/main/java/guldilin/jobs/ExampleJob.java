package guldilin.jobs;

import guldilin.model.MedicamentVendorInfo;
import guldilin.repository.MedicamentVendorInfoRepository;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.beans.XMLEncoder;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Component
public class ExampleJob implements Job {

    @Autowired
    MedicamentVendorInfoRepository medicamentVendorInfoRepository;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("I am scheduled job");
        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        String dateString = dateFormat.format(date);
        File file = new File("./xml/" + dateString + ".xml");
        try {
            write(medicamentVendorInfoRepository.findAll(), file);
        } catch (IOException e) {
            System.err.println("Failed to save file");
        }

    }

    private void write(List<MedicamentVendorInfo> medicamentVendorInfoList, File file) throws IOException {
        if (!file.exists()) file.createNewFile();
        XMLEncoder encoder = new XMLEncoder(new FileOutputStream(file));
        encoder.writeObject(medicamentVendorInfoList);
        encoder.close();
    }
}
