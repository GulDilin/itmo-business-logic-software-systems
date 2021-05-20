package guldilin.dto;

import guldilin.model.Worker;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@NoArgsConstructor
public class WorkerDTO implements Serializable {
    private Long id;

    @NotBlank(message = "Name cannot be blank")
    private String name;

    @NotNull(message = "Role cannot be null")
    private Long role;

    private String phone;

    private String email;

    @NotBlank(message = "login cannot be blank")
    private String login;

    public WorkerDTO(Worker worker) {
        this.id = worker.getId();
        this.name = worker.getName();
        if (worker.getRole() != null) {
            this.role = worker.getRole().getId();
        }
        this.phone = worker.getPhone();
        this.email = worker.getEmail();
        this.login = worker.getLogin();
    }
}
