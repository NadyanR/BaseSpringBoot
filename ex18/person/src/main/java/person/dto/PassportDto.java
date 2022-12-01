package person.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Digits;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PassportDto {

    @Digits(integer = 4, fraction = 0,
            message = "Серия паспорта содержит недопустимые символы")
    private String series;
    @Digits(integer = 6, fraction = 0,
            message = "Серия паспорта содержит недопустимые символы")
    private String number;
    @JsonFormat(pattern = "dd.MM.yyyy")
    private LocalDate dateOfIssue;
}
