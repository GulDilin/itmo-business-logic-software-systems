package guldilin.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
public class ProcessApprove {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer level;

    private Long workerBy;

    private Long workerTo;

    @ManyToOne(fetch = FetchType.LAZY)
    private Process process;

    private Date created;

    private Date updated;

    private Boolean approved;


}
