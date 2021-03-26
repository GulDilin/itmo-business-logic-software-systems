package guldilin.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Collection;

@Entity
public class WorkerRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true)
    @NotNull
    private String title;

    @NotNull
    private Integer level;

    @OneToMany(fetch = FetchType.LAZY)
    private Collection<Worker> workerList = new ArrayList<>();

    public Collection<Worker> getWorkerList() {
        return workerList;
    }

    public void setWorkerList(Collection<Worker> workerList) {
        this.workerList = workerList;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }
}
