package person.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

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

    private String surname;

    private String patronymic;

    @Column(name = "creation_date")
    private LocalDate creationDate;

    private String password;

    private String passport;

    private String address;

    private String mobile;


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

    public String getSurname() {return surname;}

    public void setSurname(String surname) {this.surname = surname;}

    public String getPatronymic() { return patronymic;}

    public void setPatronymic(String patronymic) {this.patronymic = patronymic;}

    public LocalDate getCreationDate() { return creationDate; }

    public void setCreationDate(LocalDate creationDate) {this.creationDate = creationDate;}

    public String getPassword() {return password; }

    public void setPassword(String password) {this.password = password;}

    public String getPassport() {return passport;}

    public void setPassport(String passport) {this.passport = passport; }

    public String getAddress() { return address;}

    public void setAddress(String address) {this.address = address;}

    public String getMobile() { return mobile;}

    public void setMobile(String mobile) {this.mobile = mobile;}

}
