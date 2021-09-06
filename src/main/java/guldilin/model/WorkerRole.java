package guldilin.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Data
@NoArgsConstructor
public class WorkerRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    @NotNull
    private String title;

    @NotNull
    private Integer level;

    @OneToMany(fetch = FetchType.LAZY)
    private Collection<Worker> workerList = new ArrayList<>();


}
