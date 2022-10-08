package person.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "person")
@AllArgsConstructor
@NoArgsConstructor(access= AccessLevel.PRIVATE, force = true)
@Data
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    //@Column(name = "id")
    private Integer id;
    private String name;
    private Integer age;

    private String surname;

    private String patronymic;

    @Column(name = "creation_date")
    @JsonFormat(pattern = "dd.MM.yyyy")
    private LocalDate creationDate;

    private String password;

    private String passport;

    private String address;

    private String mobile;


//    public Person (String name){
//        this.name = name;}
//
//    public Person (String name, Integer age){
//        this.name = name;
//        this.age = age;
//    }

}
