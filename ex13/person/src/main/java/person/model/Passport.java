package person.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "passport")
@AllArgsConstructor
@NoArgsConstructor(access= AccessLevel.PRIVATE, force = true)
@Data
public class Passport {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    private String series;

    private String number;

    @Column(name = "issue_date")
    @JsonFormat(pattern = "dd.MM.yyyy")
    private LocalDate dateOfIssue;
}