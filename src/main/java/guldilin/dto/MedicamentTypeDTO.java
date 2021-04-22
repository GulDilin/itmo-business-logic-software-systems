package guldilin.dto;

import guldilin.model.MedicamentType;

import javax.validation.constraints.NotBlank;

public class MedicamentTypeDTO {

    private Long id;

    private Long parentClass;

    @NotBlank(message = "Title cannot be null")
    private String title;

    private String description;

    public MedicamentTypeDTO() {
    }

    public MedicamentTypeDTO(MedicamentType medicamentType) {
        this.id = medicamentType.getId();
        if (medicamentType.getParentClass() != null) {
            this.parentClass = medicamentType.getParentClass().getId();
        }
        this.title = medicamentType.getTitle();
        this.description = medicamentType.getDescription();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getParentClass() {
        return parentClass;
    }

    public void setParentClass(Long parentGroup) {
        this.parentClass = parentGroup;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
