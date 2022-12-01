package person.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PersonDto {

    @NotNull
    @Size(min =2, message = "Не указано имя")
    private String name;
    private String surname;
    private String patronymic;
    //private Integer age;
    @JsonFormat(pattern = "dd.MM.yyyy")
    private LocalDate birthday;//ex.17

    @JsonProperty("passport")
    private PassportDto passportDto;
}