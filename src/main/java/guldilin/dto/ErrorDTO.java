package guldilin.dto;

import guldilin.config.ErrorCodes;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ErrorDTO {
    private String model;
    private String message;
    private ErrorCodes error;
}
