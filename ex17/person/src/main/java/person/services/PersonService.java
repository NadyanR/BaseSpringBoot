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

    //GET1 Принимает имя, возвращает список совпадающих людей
    public List<PersonDto> convertToDto(String name){
        List<Person> person = personRepository.findByName(name);
        if (person == null){
            throw new NoSuchPersonException(ErrorCodes.VALIDATION_PARSE_ERROR);
        }
      return converter.entityToDto(person);
    }

    //GET2 Принимает имя и возраст, возвращает одного совпадающего человека
    public PersonDto convertToDto(String name, Integer age){
        Person person = personRepository.findByNameAndAge(name, age);
        if (person == null){
            throw new NoSuchPersonException(ErrorCodes.VALIDATION_PARSE_ERROR);
        }
        return converter.entityToDto(person);
    }

    //GET3 Принимает возраст, возвращает возвращает список совпадающих людей
    public List<PersonDto> convertToDto(Integer age){
        List<Person> persons = personRepository.findByAge(age);
        if (persons.isEmpty()){
            throw new NoSuchPersonException(ErrorCodes.VALIDATION_PARSE_ERROR);
        }
        return converter.entityToDto(persons);
    }

    //GET4 Принимает возраст, возвращает список людей, кто старше 30 лет
    public List<PersonDto> convertToDto2(Integer age){
        List<Person> persons = personRepository.findAllPersons(age);
        if (personRepository.findAllPersons(age).isEmpty()){
            throw new NoSuchPersonException(ErrorCodes.VALIDATION_PARSE_ERROR);
        }
        return converter.entityToDto(persons);
    }

    //PUT изменение данных человека
    public PersonDto convertToDto(Person newPerson, Integer id){
        Person personDto = personRepository.findById(id)
                .map(person -> {
                    person.setAge(newPerson.getAge());
                    return personRepository.save(person);})
                .orElseGet(() -> {
                    newPerson.setId(id);
                    return personRepository.save(newPerson);});
        return converter.entityToDto(personDto);
    }
}
