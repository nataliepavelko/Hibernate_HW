package entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.*;

@Data
@Entity
@Table (name = "projects")
@NoArgsConstructor
public class Project {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = "id")
    private long id;

    @Column (name = "name", length = 300)
    private String name;

    @Column (name = "date", length = 300)
    private String date;

    @Column(name = "cost")
    private double cost;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable (name = "developers_projects",
            joinColumns = @JoinColumn(name = "id_project"),
            inverseJoinColumns = @JoinColumn(name = "id_developer"))
    private Set<Developer> developers = new HashSet<>();


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinTable(name = "companies_projects",
        joinColumns = @JoinColumn (name = "id_project"),
            inverseJoinColumns = @JoinColumn(name = "id_companies"))
    private Company company;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinTable (name = "customers_projects",
    joinColumns = @JoinColumn(name = "id_project"),
    inverseJoinColumns = @JoinColumn(name = "id_customer"))
    private Customer customer;

    @Override
    public String toString() {
        return "Project" +
                " id = " + id +
                ", name = " + name +
                ", date = " + date +
                ", cost = " + cost;
    }

    // get all developers from project
    public List <Developer> getDevelopers(){
        List <Developer> developerList = new ArrayList<>();
        for (Developer developer : developers){
            developerList.add(developer);
        }
        return developerList;
    }

    // sum salary of developers
    public int getSumDevelopersSalary() {
        int sum = 0;
        if (developers != null) {
            for (Developer developer : developers) {
                sum += developer.getSalary();
            }
        } else {
            System.out.println("No developers in project");
        }
        return sum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Project project = (Project) o;
        return id == project.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
