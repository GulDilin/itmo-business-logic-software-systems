package guldilin.dto;

import guldilin.model.Process;

import javax.validation.constraints.NotNull;

public class ProcessDTO {

    private Long id;

    @NotNull
    private String status;

    private Long medicament;

    ProcessDTO() {}

    public ProcessDTO(Process process) {
        this.id = process.getId();
        this.status = process.getStatus();
        this.medicament = process.getMedicament().getId();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getMedicament() {
        return medicament;
    }

    public void setMedicament(Long medicament) {
        this.medicament = medicament;
    }
}
