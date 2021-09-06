package guldilin.config;

import guldilin.model.Worker;
import guldilin.repository.WorkerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class CustomUserDetailsService implements UserDetailsService {

    private final WorkerRepository workerRepository;

    @Autowired
    public CustomUserDetailsService(WorkerRepository workerRepository) {
        this.workerRepository = workerRepository;
    }

    @Override
    public CustomUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Worker worker = workerRepository.findByLogin(username)
                .orElseThrow(() -> new UsernameNotFoundException("No such user"));

        return CustomUserDetails.fromUserEntityToCustomUserDetails(worker);
    }
}