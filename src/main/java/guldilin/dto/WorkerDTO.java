package guldilin.dto;

import guldilin.model.Worker;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class WorkerDTO implements Serializable {
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private Long role;

    private String phone;

    private String email;

    WorkerDTO() {}

    public WorkerDTO(Worker worker) {
        this.id = worker.getId();
        this.name = worker.getName();
        this.role = worker.getRole().getId();
        this.phone = worker.getPhone();
        this.email = worker.getEmail();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getRole() {
        return role;
    }

    public void setRole(Long role) {
        this.role = role;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
