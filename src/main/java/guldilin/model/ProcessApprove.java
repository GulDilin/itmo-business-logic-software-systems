package guldilin.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class ProcessApprove {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String level;

    @ManyToOne(fetch = FetchType.LAZY)
    private Worker workerBy;

    @ManyToOne(fetch = FetchType.LAZY)
    private Worker workerTo;

    @ManyToOne(fetch = FetchType.LAZY)
    private Process process;

    private Date created;

    private Date updated;

    private Boolean approved;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public Worker getWorkerBy() {
        return workerBy;
    }

    public void setWorkerBy(Worker workerBy) {
        this.workerBy = workerBy;
    }

    public Worker getWorkerTo() {
        return workerTo;
    }

    public void setWorkerTo(Worker workerTo) {
        this.workerTo = workerTo;
    }

    public Process getProcess() {
        return process;
    }

    public void setProcess(Process process) {
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
