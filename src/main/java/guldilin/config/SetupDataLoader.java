package guldilin.config;

import guldilin.model.Worker;
import guldilin.model.WorkerRole;
import guldilin.repository.WorkerRepository;
import guldilin.repository.WorkerRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {

    boolean alreadySetup = false;
    WorkerRoleRepository workerRoleRepository;
    WorkerRepository workerRepository;
    PasswordEncoder passwordEncoder;

    @Autowired
    public SetupDataLoader(
            WorkerRoleRepository workerRoleRepository,
            WorkerRepository workerRepository,
            PasswordEncoder passwordEncoder
    ) {
        this.workerRoleRepository = workerRoleRepository;
        this.workerRepository = workerRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (alreadySetup) return;

        WorkerRole roleAdmin = createRoleIfNotFound("ADMIN", 0);
        createRoleIfNotFound("MANAGER", 1);
        createRoleIfNotFound("MAIN_EDITOR", 2);
        createRoleIfNotFound("EDITOR", 3);
        createRoleIfNotFound("FARM", 4);

        Optional<Worker> optionalWorker = this.workerRepository.findByLogin("admin");
        if (!optionalWorker.isPresent()) {
            Worker worker = new Worker();
            worker.setLogin("admin");
            worker.setPassword(passwordEncoder.encode("root-blps"));
            worker.setRole(roleAdmin);
            worker.setName("Admin");
            workerRepository.save(worker);
        }

        alreadySetup = true;
    }

    @Transactional
    WorkerRole createRoleIfNotFound(String title, Integer level) {
        Optional<WorkerRole> roleOptional = workerRoleRepository.findByTitle(title);
        if (roleOptional.isPresent()) return roleOptional.get();
        WorkerRole role = new WorkerRole();
        role.setTitle(title);
        role.setLevel(level);
        workerRoleRepository.save(role);
        return role;
    }
}
