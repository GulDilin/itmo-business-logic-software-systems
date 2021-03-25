package guldilin.dto;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class WorkerRoleDTO implements Serializable {

    private Integer id;

    @NotNull
    private String title;

    @NotNull
    private Integer level;

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

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }
}
