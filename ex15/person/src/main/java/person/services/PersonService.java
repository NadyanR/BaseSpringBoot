package person.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import person.converter.PersonConverter;
import person.dto.PersonDto;
import person.exception_handling.ErrorCodes;
import person.exception_handling.NoSuchPersonException;
import person.model.Person;
import person.repository.PersonRepository;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PersonService {
    private final PersonConverter converter;
    private final PersonRepository personRepository;

    public List<PersonDto> convertToDto(String name){
        List<Person> person = personRepository.findByName(name);
        if (person == null){
            throw new NoSuchPersonException(ErrorCodes.VALIDATION_PARSE_ERROR);
        }
      return (List<PersonDto>) converter.entityToDto(person);
    }
}
