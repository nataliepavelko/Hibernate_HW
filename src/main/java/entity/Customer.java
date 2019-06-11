package entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Data
@Entity
@Table (name = "customers")
@NoArgsConstructor
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id")
    private long id;

    @Column (name = "name", length = 300)
    private String name;

    @Column (name = "phone", length = 300)
    private String phone;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "customers_projects",
            joinColumns = @JoinColumn (name = "id_customer"),
            inverseJoinColumns = @JoinColumn(name = "id_project"))
    private Set<Project> projects = new HashSet<>();

    @Override
    public String toString() {
        return "Customer" +
                " id = " + id +
                ", name = " + name +
                ", phone = " + phone ;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return id == customer.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
