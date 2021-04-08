package guldilin.dto;

import guldilin.model.Medicament;

import javax.validation.constraints.NotNull;

public class ProcessDTO {

    private Integer id;

    @NotNull
    private String status;

    private Integer medicament;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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
