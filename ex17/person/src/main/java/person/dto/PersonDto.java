package person.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PersonDto {

    private String name;
    private String surname;
    private String patronymic;
    //private Integer age;
    private LocalDate birthday;//ex.17

    @JsonProperty("passport")
    private PassportDto passportDto;
}