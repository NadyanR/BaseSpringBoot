package person.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import person.converter.PersonConverter;
import person.dto.PersonDto;
import person.exception_handling.ErrorCodes;
import person.exception_handling.NoSuchPersonException;
import person.model.Person;
import person.repository.PersonRepository;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PersonController {

    private final PersonRepository personRepository;
    private final PersonConverter converter;

    @GetMapping("/person/{name}")
    //public Person getPerson(@PathVariable("name") String name) {
    //return personRepository.findByName(name);

    //ex.11 возврат PersonDto
    public List<PersonDto> getPerson(@PathVariable("name") String name) {
        List<Person> person = personRepository.findByName(name);
    //ex.12 Исключения
        if (person == null){
            //throw new NoSuchPersonException("There is no person with name = " + name + " in DataBase");
            throw new NoSuchPersonException(ErrorCodes.VALIDATION_PARSE_ERROR);
        }
        return converter.entityToDto(person);

    }

    @GetMapping("/person")
    //ex.10 Принимает имя и возраст, возвращает одного совпадающего человека
    //public Person getPerson(@RequestParam("name") String name, @RequestParam("age") Integer age) {
    //return personRepository.findByNameAndAge(name, age);

    //ex.11 возврат PersonDto
    public PersonDto getPerson(@RequestParam("name") String name, @RequestParam("age") Integer age) {
        Person person = personRepository.findByNameAndAge(name, age);
        //ex.12 Исключения
        if (person == null){
            //throw new NoSuchPersonException("There is no person with name = " + name + " and age = "+ age + " in DataBase");
            throw new NoSuchPersonException(ErrorCodes.VALIDATION_PARSE_ERROR);
        }
        return converter.entityToDto(person);
    }

    @GetMapping("/persons/{age}")
    //ex.10 Принимает возраст, возвращает всех совпадающих людей
    //public List<Person> getPersons(@PathVariable("age") Integer age) {return personRepository.findByAge(age);

    //ex.11 возврат список PersonDto
    public List<PersonDto> getPersons(@PathVariable("age") Integer age) {
        List<Person> persons = personRepository.findByAge(age);
        //ex.12 Исключения
        if (persons.isEmpty()){
            //throw new NoSuchPersonException("There are no persons with age = " + age + " in DataBase");
            throw new NoSuchPersonException(ErrorCodes.VALIDATION_PARSE_ERROR);
        }
        return converter.entityToDto(persons);
    }

    @GetMapping("/persons1/{age}")
   //ex.11 возврат список PersonDto, кто старше 30 лет
    public List<PersonDto> getPersons30(@PathVariable("age") Integer age) {
        //return converter.entityToDto(personRepository.findByAgeGreaterThan(age));
        //ex.12 Исключения
        if (personRepository.findAllPersons(age).isEmpty()){
            //throw new NoSuchPersonException("There are no persons older than " + age + " years old in DataBase");
            throw new NoSuchPersonException(ErrorCodes.VALIDATION_PARSE_ERROR);
        }
        return converter.entityToDto(personRepository.findAllPersons(age));
    }

    @PutMapping("/person/{id}")
    // ex.11 возврат PersonDto (изменение только age)
    public PersonDto changePerson(@RequestBody Person newPerson, @PathVariable Integer id) {
        Person personDto = personRepository.findById(id)
                .map(person -> {
                    person.setAge(newPerson.getAge());
                    return personRepository.save(person);
                })
                .orElseGet(() -> {
                    newPerson.setId(id);
                    return personRepository.save(newPerson);
                });
        return converter.entityToDto(personDto);
    }

    @PostMapping("/person")
    //ex.10 Принимает в теле json, сохраняет в БД нового человека с паспортом
    public PersonDto addPerson(@RequestBody Person newPerson) {
        if (personRepository.existsByPassport(newPerson.getPassport())) {
            System.out.println("Запись существует в БД");
        } else {
//            return personRepository.save(newPerson)
            // ex.11 возврат PersonDto
            Person person = personRepository.save(newPerson);
            return converter.entityToDto(person);
        }
        return null;
    }

    @DeleteMapping("/person/{id}")
    public void deletePerson(@PathVariable Integer id) {
       personRepository.deleteById(id);
    }
}