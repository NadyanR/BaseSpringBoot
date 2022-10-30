package person.converter;

import org.springframework.stereotype.Component;
import person.dto.PersonDto;
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
        return dto;
    }

    public List<PersonDto> entityToDto(List<Person> person){
        return person.stream().map(x->entityToDto(x)).collect(Collectors.toList());
    }

//    public Person dtoToEntity(PersonDto dto){
//        Person person1 = new Person();
//        person1.setName(dto.getName());
//        person1.setSurname(dto.getSurname());
//        person1.setPatronymic(dto.getPatronymic());
//        person1.setAge(dto.getAge());
//        return person1;
//    }
//
//    public List<Person> dtoToEntity(List<PersonDto> dto){
//        return dto.stream().map(x->dtoToEntity(x)).collect(Collectors.toList());
//    }
}
