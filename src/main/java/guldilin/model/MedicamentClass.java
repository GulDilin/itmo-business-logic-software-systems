package guldilin.model;

import javax.persistence.*;

@Entity
public class MedicamentClass {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private MedicamentGroup parentGroup;

    private String title;

    private String description;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public MedicamentGroup getParentGroup() {
        return parentGroup;
    }

    public void setParentGroup(MedicamentGroup parentGroup) {
        this.parentGroup = parentGroup;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}