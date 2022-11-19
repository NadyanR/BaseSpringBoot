package person.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "person")
@AllArgsConstructor
@NoArgsConstructor//(access= AccessLevel.PRIVATE, force = true)
@Data
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
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

    //private String passport;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "passport_id", referencedColumnName = "id")
    private Passport passport;

    private String address;

    private String mobile;

    @JsonIgnore
    @ManyToOne(cascade = {CascadeType.ALL, CascadeType.PERSIST,
            CascadeType.DETACH, CascadeType.REFRESH, CascadeType.MERGE})
    @JoinColumn(name = "department_id", referencedColumnName = "id")
    private Department department;


//    public Person (String name){
//        this.name = name;}
//
//    public Person (String name, Integer age){
//        this.name = name;
//        this.age = age;
//    }
}
