package guldilin.dto;

import guldilin.model.ProcessApprove;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Date;

public class ProcessApproveDTO {

    private Long id;

    @NotNull(message = "workerBy cannot be null")
    @Min(value = 1)
    private Integer level;

    @NotNull(message = "workerBy cannot be null")
    private Long workerBy;

    @NotNull(message = "workerTo cannot be null")
    private Long workerTo;

    @NotNull(message = "process cannot be null")
    private Long process;

    private Date created;

    private Date updated;

    private Boolean approved;

    ProcessApproveDTO() {}

    public ProcessApproveDTO(ProcessApprove processApprove) {
        this.id = processApprove.getId();
        this.level = processApprove.getLevel();
        if (processApprove.getWorkerBy() != null) {
            this.workerBy = processApprove.getWorkerBy().getId();
        }
        if (processApprove.getWorkerTo() != null) {
            this.workerTo = processApprove.getWorkerTo().getId();
        }
        if (processApprove.getProcess() != null) {
            this.process = processApprove.getProcess().getId();
        }
        this.created = processApprove.getCreated();
        this.updated = processApprove.getUpdated();
        this.approved = processApprove.getApproved();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Long getWorkerBy() {
        return workerBy;
    }

    public void setWorkerBy(Long workerBy) {
        this.workerBy = workerBy;
    }

    public Long getWorkerTo() {
        return workerTo;
    }

    public void setWorkerTo(Long workerTo) {
        this.workerTo = workerTo;
    }

    public Long getProcess() {
        return process;
    }

    public void setProcess(Long process) {
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
