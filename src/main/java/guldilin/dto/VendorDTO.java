package guldilin.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
@NoArgsConstructor
@JsonSerialize
public class VendorDTO implements Serializable {

    private Long id;

    @NotBlank(message = "Title cannot be blank")
    private String title;

    private String address;

    private String contact;
}
