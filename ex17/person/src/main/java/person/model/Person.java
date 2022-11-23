package person.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

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

    @NotNull
    @Size(min =2, message = "Не указано имя")
    private String name;
    private Integer age;
    //private LocalDate birthday;//ex.17

    private String surname;

    private String patronymic;

    @Column(name = "creation_date")
    @JsonFormat(pattern = "dd.MM.yyyy")
    private LocalDateTime creationDate;

    @PrePersist//заполняет поле значением времени внесения записи о персон перед сохранием в БД
    private void prePersist() {
        creationDate = LocalDateTime.now();}

    @Pattern(regexp="^([A-Z][A-z0-9]{7,9})$",
            message = "Пароль не соответсвует допустимому формату")
    private String password;

    //private String passport;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "passport_id", referencedColumnName = "id")
    @NotNull
    @Valid
    private Passport passport;

    private String address;

    @Pattern(regexp="^(\\+7|8)(\\d{10})$",
            message = "Номер телефона не соответсвует допустимому формату")
    private String mobile;

    @JsonIgnore
    @ManyToOne(cascade = {CascadeType.ALL, CascadeType.PERSIST,
            CascadeType.DETACH, CascadeType.REFRESH, CascadeType.MERGE})
    @JoinColumn(name = "department_id", referencedColumnName = "id")
    private Department department;
}
