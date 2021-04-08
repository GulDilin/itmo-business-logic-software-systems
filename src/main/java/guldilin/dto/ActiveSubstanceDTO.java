package guldilin.dto;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ActiveSubstanceDTO {

    private Integer id;

    @NotNull
    @NotBlank
    private String title;

    private String description;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
