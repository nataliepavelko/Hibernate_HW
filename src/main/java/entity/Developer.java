package entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.*;

@Entity
@Data
@Table (name = "developers")
@NoArgsConstructor
public class Developer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column (name = "name", length = 300)
    private String name;

    @Column (name = "surname", length = 300)
    private String surname;

    @Column(name = "sex")
    private String sex;

    @Column (name = "salary", length = 300)
    private int salary;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "developers_skills",
            joinColumns = @JoinColumn(name = "id_developer"),
            inverseJoinColumns = @JoinColumn(name = "id_skill"))
    private Set<Skill> skills = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable (name = "developers_projects",
    joinColumns = @JoinColumn(name = "id_developer"),
    inverseJoinColumns = @JoinColumn(name = "id_project"))
    private Set<Project> projects = new HashSet<>();


    public List <Skill> getSkillsOfDeveloper (){
        List <Skill> skillList = new ArrayList<>();
        for (Skill skill : skills){
            skillList.add(skill);
        }
        return skillList;
    }


    @Override
    public String toString() {
        return "Developer " +
                "id = " + id +
                ", name = " + name +
                ", surname = " + surname +
                ", sex = " + sex +
                ", salary = " + salary ;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Developer developer = (Developer) o;
        return id == developer.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
