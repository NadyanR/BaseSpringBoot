package person.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import person.converter.PersonConverter;
import person.dto.PersonDto;
import person.model.Person;
import person.repository.PersonRepository;
import person.services.PersonService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PersonController {

    private final PersonRepository personRepository;
    private final PersonConverter converter;
    private final PersonService personService;

    @GetMapping("/person/{name}")
    public List<PersonDto> getPerson1(@PathVariable("name") String name) {
        // ex.15* Логика формирования PersonDto вынесена в отдельный класс PersonService
        return personService.convertToDto(name);
    }

    @GetMapping("/person")
    public PersonDto getPerson(@RequestParam("name") String name, @RequestParam("age") Integer age) {
        // ex.15* Логика формирования PersonDto вынесена в отдельный класс PersonService
        return personService.convertToDto(name, age);
    }

    @GetMapping("/persons/{age}")
    public List<PersonDto> getPersons(@PathVariable("age") Integer age) {
        // ex.15* Логика формирования PersonDto вынесена в отдельный класс PersonService
        return personService.convertToDto(age);
    }

    @GetMapping("/persons1/{age}")
    public List<PersonDto> getPersons30(@PathVariable("age") Integer age) {
        // ex.15* Логика формирования PersonDto вынесена в отдельный класс PersonService
        return personService.convertToDto2(age);
    }

    @PutMapping("/person/{id}")
    public PersonDto changePerson(@RequestBody Person newPerson, @PathVariable Integer id) {
        // ex.15* Логика формирования PersonDto вынесена в отдельный класс PersonService
        return personService.convertToDto(newPerson, id);
    }

//    @PostMapping("/person")
//    //ex.13 Принимает в теле json, сохраняет в БД нового человека с паспортом
//    public Person createPerson(@RequestBody Person newPerson) {
//        return personRepository.save(newPerson);
//    }

    @PostMapping("/person")
    //ex.13* Принимает в теле json несколько человек с паспортами, сохраняет в БД людей и паспорта за один раз
    public List<Person> addPerson(@RequestBody List<Person> newPerson) {
            return personRepository.saveAll(newPerson);}

    @PostMapping("/person/dto")
    // ex.14* передаем DTO -> получаем Объект Person (Принимай дто, и создавай сама объект персон из этой дто)
    public Person savePerson(@RequestBody PersonDto newPersonDto) {
        Person person = converter.dtoToEntity(newPersonDto);
        return personRepository.save(person);}

//    @PostMapping("/person/dto")
//    // ex.14* передаем DTO (перечень) -> получаем Объекты Persons
//    public List<Person> savePerson(@RequestBody List<PersonDto> newPersonDto) {
//        List<Person> persons = converter.dtoToEntity(newPersonDto);
//        return personRepository.saveAll(persons); }

    @DeleteMapping("/person/{id}")
    public void deletePerson(@PathVariable Integer id) {
       personRepository.deleteById(id);
    }
}