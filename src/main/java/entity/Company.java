package entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Data
@Entity
@Table (name = "companies")
@NoArgsConstructor
public class Company {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = "id")
    private long id;

    @Column (name = "name", length = 300)
    private String name;

    @Column (name = "address", length = 300)
    private String address;


    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "companies_projects",
            joinColumns = @JoinColumn (name = "id_companies"),
            inverseJoinColumns = @JoinColumn(name = "id_project"))
    private Set<Project> projects = new HashSet<>();

    @Override
    public String toString() {
        return "Company" +
                " id = " + id +
                ", name = " + name +
                ", address = " + address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Company company = (Company) o;
        return id == company.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
