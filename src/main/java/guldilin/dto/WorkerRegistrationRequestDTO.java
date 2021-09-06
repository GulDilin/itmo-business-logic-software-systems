package guldilin.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@Data
public class WorkerRegistrationRequestDTO extends WorkerDTO {
    @NotBlank(message = "password cannot be blank")
    private String password;
}
