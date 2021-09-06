package guldilin.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class WorkerAuthResponseDTO extends WorkerDTO {
    private String token;

    public WorkerAuthResponseDTO(WorkerDTO workerDTO) {
        this.setId(workerDTO.getId());
        this.setEmail(workerDTO.getEmail());
        this.setLogin(workerDTO.getLogin());
        this.setName(workerDTO.getName());
        this.setPhone(workerDTO.getPhone());
        this.setRole(workerDTO.getRole());
    }
}
