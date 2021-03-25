package guldilin.dto;

import guldilin.model.WorkerRole;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class WorkerDTO implements Serializable {
    private Integer id;

    @NotNull
    private String name;

    @NotNull
    private WorkerRole role;

    private String phone;

    private String email;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public WorkerRole getRole() {
        return role;
    }

    public void setRole(WorkerRole role) {
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
