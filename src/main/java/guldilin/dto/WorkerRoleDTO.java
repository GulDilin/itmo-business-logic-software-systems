package guldilin.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@NoArgsConstructor
public class WorkerRoleDTO implements Serializable {

    private Long id;

    @NotBlank(message = "Title cannot be blank")
    private String title;

    @NotNull(message = "Level cannot be null")
    @Min(value = 0, message = "Level should not be less than 0")
    private Integer level;
}
