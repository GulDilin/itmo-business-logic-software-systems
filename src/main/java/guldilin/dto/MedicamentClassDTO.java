package guldilin.dto;

import javax.validation.constraints.NotNull;

public class MedicamentClassDTO {


    private Long id;

    private Long parentGroup;

    @NotNull
    private String title;

    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getParentGroup() {
        return parentGroup;
    }

    public void setParentGroup(Long parentGroup) {
        this.parentGroup = parentGroup;
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
