package guldilin.dto;

import guldilin.model.Process;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
@NoArgsConstructor
public class ProcessDTO implements Serializable {

    private Long id;

    @NotBlank(message = "Status cannot be blank")
    private String status;

    private Long medicament;

    public ProcessDTO(Process process) {
        this.id = process.getId();
        this.status = process.getStatus();
        if (process.getMedicament() != null) {
            this.medicament = process.getMedicament().getId();
        }
    }
}
