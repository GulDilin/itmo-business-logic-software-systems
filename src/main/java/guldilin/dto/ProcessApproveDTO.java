package guldilin.dto;

import guldilin.model.Process;
import guldilin.model.ProcessApprove;

import javax.validation.constraints.NotNull;
import java.util.Date;

public class ProcessApproveDTO {

    private Long id;

    private String level;

    @NotNull
    private Long workerBy;

    @NotNull
    private Long workerTo;

    @NotNull
    private Long process;

    private Date created;

    private Date updated;

    private Boolean approved;

    ProcessApproveDTO() {}

    public ProcessApproveDTO(ProcessApprove processApprove) {
        this.id = processApprove.getId();
        this.level = processApprove.getLevel();
        this.workerBy = processApprove.getWorkerBy().getId();
        this.workerTo = processApprove.getWorkerTo().getId();
        this.process = processApprove.getProcess().getId();
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

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
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
