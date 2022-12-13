package person.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "department")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "department_name")
    private String depName;

    @Column(name = "department_number")
    private String depNumber;

    @Column(name = "person_amount")
    private Integer personAmount;

    @PrePersist//позволяет заполнить поле значением перед сохранием в БД
    private void prePersist() {
        personAmount = 0;}

    @OneToMany(cascade = {CascadeType.PERSIST,
                CascadeType.DETACH, CascadeType.REFRESH, CascadeType.MERGE},
                mappedBy = "department")
    //@JoinColumn (name = "department_id")
    private List<Person> persons;

    public void addPersonToDepartment(Person person){
        if(persons == null){
            persons = new ArrayList<>();
        }
        persons.add(person);
        person.setDepartment(this);
    }
    @ElementCollection
    @CollectionTable(name = "department_operations",
                    joinColumns = @JoinColumn(name="department_id"))
    private Set<Operation> lines;

}
