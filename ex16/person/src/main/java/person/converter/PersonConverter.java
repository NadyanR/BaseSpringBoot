package person.converter;

import org.springframework.stereotype.Component;
import person.dto.PassportDto;
import person.dto.PersonDto;
import person.model.Passport;
import person.model.Person;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PersonConverter {

    public PersonDto entityToDto(Person person){
        PersonDto dto = new PersonDto();
        dto.setName(person.getName());
        dto.setSurname(person.getSurname());
        dto.setPatronymic(person.getPatronymic());
        dto.setAge(person.getAge());
        dto.setPassportDto(new PassportDto(person.getPassport().getSeries(),
                                person.getPassport().getNumber(),
                                person.getPassport().getDateOfIssue()));
        return dto;
    }

    public List<PersonDto> entityToDto(List<Person> person){
        return person.stream().map(x->entityToDto(x)).collect(Collectors.toList());
    }

    public Person dtoToEntity(PersonDto dto){
        Passport newPassport = new Passport();
        newPassport.setNumber(dto.getPassportDto().getNumber());
        newPassport.setSeries(dto.getPassportDto().getSeries());
        newPassport.setDateOfIssue(dto.getPassportDto().getDateOfIssue());

        Person newPerson = new Person();
        newPerson.setName(dto.getName());
        newPerson.setSurname(dto.getSurname());
        newPerson.setPatronymic(dto.getPatronymic());
        newPerson.setAge(dto.getAge());
        return newPerson;
    }

    public List<Person> dtoToEntity(List<PersonDto> dto){
        return dto.stream().map(x->dtoToEntity(x)).collect(Collectors.toList());
    }
}