package guldilin.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class WorkerRoleDTO implements Serializable {

    private Long id;

    @NotBlank(message = "Title cannot be blank")
    private String title;

    @NotNull(message = "Level cannot be null")
    @Min(value = 0, message = "Level should not be less than 0")
    private Integer level;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }
}
