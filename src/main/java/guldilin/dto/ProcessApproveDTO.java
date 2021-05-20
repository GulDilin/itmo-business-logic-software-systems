package guldilin.dto;

import guldilin.model.ProcessApprove;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
public class ProcessApproveDTO implements Serializable {

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
}
