package guldilin.repository;

import guldilin.model.Worker;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class WorkerRepository {

    private final String crutchPath = "./xml/users";
    private Long nextId;

    WorkerRepository() {
        File file = new File(crutchPath);
        file.mkdirs();
        if (!file.isDirectory()) throw new IllegalArgumentException(crutchPath + " is not a directory");
        this.nextId = getWorkerStream().map(Worker::getId).mapToLong(Long::new).max().orElse(1L);
    }

    private File provideFile(Worker worker) {
        return new File(String.format("%s/%d.xml", crutchPath, worker.getId()));
    }

    private File provideFile(Long id) {
        return new File(String.format("%s/%d.xml", crutchPath, id));
    }

    private void writeWorker(Worker worker, File file) throws IOException {
        if (!file.exists()) file.createNewFile();
        XMLEncoder encoder = new XMLEncoder(new FileOutputStream(file));
        encoder.writeObject(worker);
        encoder.close();
    }

    private Optional<Worker> readWorker(File file) {
        if (!file.exists()) throw new IllegalArgumentException("No such file");
        try(XMLDecoder decoder = new XMLDecoder(new FileInputStream(file))) {
            return Optional.ofNullable((Worker) decoder.readObject());
        } catch (FileNotFoundException e) {
            return Optional.empty();
        }
    }

    private Stream<File> getFilesStream() {
        return Arrays.stream(Objects.requireNonNull(new File(crutchPath).listFiles()));
    }

    private Stream<Worker> getWorkerStream() {
        return getFilesStream().map(this::readWorker).filter(Optional::isPresent).map(Optional::get);
    }

    public Worker save(Worker worker)  {
        try {
            File file = provideFile(worker);
            if (worker.getId() != null && file.exists() && file.delete()) {
                writeWorker(worker, file);
                return worker;
            }
            worker.setId(++nextId);
            writeWorker(worker, provideFile(worker));
            return worker;
        } catch (IOException e) {
            throw new DataAccessException("Save error") {};
        }
    }

    public List<Worker> findAll() {
      return getWorkerStream().collect(Collectors.toList());
    }

    public Optional<Worker> findById(Long id) {
        File file = provideFile(id);
        return readWorker(file);
    }

    public Optional<Worker> findByLogin(String login) {
        return getWorkerStream().filter(it -> it.getLogin().equals(login)).findFirst();
    }

    public List<Worker> findAllByEmail(String email) {
        return getWorkerStream().filter(it -> it.getEmail().equals(email)).collect(Collectors.toList());
    }

    public List<Worker> findAllByRoleId(Long id) {
        return getWorkerStream().filter(it -> it.getRole().getId().equals(id)).collect(Collectors.toList());
    }

    public List<Worker> findAllByRoleTitle(String role) {
        return getWorkerStream().filter(it -> it.getRole().getTitle().equals(role)).collect(Collectors.toList());
    }

    public List<Worker> findAllByName(String name) {
        return getWorkerStream().filter(it -> it.getName().equals(name)).collect(Collectors.toList());
    }
}
