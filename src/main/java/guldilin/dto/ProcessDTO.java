package guldilin.dto;

import javax.validation.constraints.NotNull;

public class ProcessDTO {

    private Long id;

    @NotNull
    private String status;

    private Integer medicament;

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

    public Integer getMedicament() {
        return medicament;
    }

    public void setMedicament(Integer medicament) {
        this.medicament = medicament;
    }
}
