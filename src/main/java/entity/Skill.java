package entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.*;

@Data
@Entity
@Table (name = "skills")
@NoArgsConstructor
public class Skill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column (name = "name", length = 300)
    private String name;

    @Column(name = "level", length = 300)
    private String level;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "developers_skills",
            joinColumns = @JoinColumn(name = "id_skill"),
            inverseJoinColumns = @JoinColumn(name = "id_developer"))
    private Set<Developer> developers = new HashSet<>();

    @Override
    public String toString() {
        return "Skill" +
                " id = " + id +
                ", name = " + name +
                ", level = " + level;

    }
    public List<Developer> getDevelopersBySkillName() {
        List<Developer> developersList = new ArrayList<>();
        for (Developer developer : developers) {
            developersList.add(developer);
        }
        return  developersList;
    }
    public List<Developer> getDevelopersBySkillLevel() {
        List<Developer> developersList = new ArrayList<>();
        for (Developer developer : developers) {
            developersList.add(developer);
        }
        return  developersList;
    }
     @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Skill skill = (Skill) o;
        return id == skill.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
