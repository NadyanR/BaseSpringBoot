package person.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonDto {

    private String name;
    private String surname;
    private String patronymic;
    private Integer age;

    @JsonProperty("passport")
    private PassportDto passportDto;

}