package guldilin.dto;

import javax.validation.constraints.NotNull;
import java.util.Date;

public class ProcessApproveDTO {

    private Long id;

    private String level;

    @NotNull
    private Integer workerBy;

    @NotNull
    private Integer workerTo;

    @NotNull
    private Integer process;

    private Date created;

    private Date updated;

    private Boolean approved;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public Integer getWorkerBy() {
        return workerBy;
    }

    public void setWorkerBy(Integer workerBy) {
        this.workerBy = workerBy;
    }

    public Integer getWorkerTo() {
        return workerTo;
    }

    public void setWorkerTo(Integer workerTo) {
        this.workerTo = workerTo;
    }

    public Integer getProcess() {
        return process;
    }

    public void setProcess(Integer process) {
        this.process = process;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public Boolean getApproved() {
        return approved;
    }

    public void setApproved(Boolean approved) {
        this.approved = approved;
    }
}
