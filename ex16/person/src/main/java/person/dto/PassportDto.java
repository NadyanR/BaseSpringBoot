package person.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PassportDto {

    private String series;
    private String number;
    @JsonFormat(pattern = "dd.MM.yyyy")
    private LocalDate dateOfIssue;
}
