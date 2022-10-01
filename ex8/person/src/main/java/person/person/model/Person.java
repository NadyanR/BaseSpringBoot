package person.person.model;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "person")
@AllArgsConstructor
@NoArgsConstructor(access= AccessLevel.PRIVATE, force = true)
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    //@Column(name = "id")
    private Integer id;
    private String name;
    private Integer age;

    public Person (String name){
        this.name = name;}

    public Person (String name, Integer age){
        this.name = name;
        this.age = age;
    }

    public Integer getPersonId() {return id;}

    public void setId(Integer personId) { this.id = personId;}

    public String getName() {return name;}

    public void setName(String name) {this.name = name;}

    public Integer getAge() {return age;}

    public void setAge(Integer age) {this.age = age;}
}
