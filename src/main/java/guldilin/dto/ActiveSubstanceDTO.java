package guldilin.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
@NoArgsConstructor
public class ActiveSubstanceDTO implements Serializable {
    private Long id;

    @NotBlank(message = "Title cannot be blank")
    private String title;

    private String description;
}
