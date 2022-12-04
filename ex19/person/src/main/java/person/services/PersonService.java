package person.services;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import person.converter.PersonConverter;
import person.dto.PersonDto;
import person.exception_handling.ErrorCodes;
import person.exception_handling.NoSuchPersonException;
import person.model.Person;
import person.repository.PersonRepository;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
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

    //GET2 Принимает имя и Дата рождения (ex.17), возвращает одного совпадающего человека
    public PersonDto convertToDto(String name, LocalDate birthday){
        Person person = personRepository.findByNameAndBirthday(name, birthday);
        if (person == null){
            throw new NoSuchPersonException(ErrorCodes.VALIDATION_PARSE_ERROR);
        }
        return converter.entityToDto(person);
    }

    //GET3 Принимает Дата рождения (ex.17), возвращает список совпадающих людей
    public List<PersonDto> convertToDto(LocalDate birthday){
        List<Person> persons = personRepository.findByBirthday(birthday);
        if (persons.isEmpty()){
            throw new NoSuchPersonException(ErrorCodes.VALIDATION_PARSE_ERROR);
        }
        return converter.entityToDto(persons);
    }

    //GET4 Принимает Дату рождения (ex.17), возвращает список людей, кто старше 30 лет
    public List<PersonDto> convertToDto2(LocalDate birthday){
        List<Person> persons = personRepository.findAllPersons(birthday);
        if (personRepository.findAllPersons(birthday).isEmpty()){
            throw new NoSuchPersonException(ErrorCodes.VALIDATION_PARSE_ERROR);
        }
        return converter.entityToDto(persons);
    }

    //PUT изменение данных человека
    public PersonDto convertToDto(Person newPerson, Integer id){
        Person personDto = personRepository.findById(id)
                .map(person -> {
                    //person.setAge(newPerson.getAge());
                    person.setBirthday(newPerson.getBirthday());
                    return personRepository.save(person);})
                .orElseGet(() -> {
                    newPerson.setId(id);
                    return personRepository.save(newPerson);});
        return converter.entityToDto(personDto);
    }

    //ex19 Вывод всех людей без отчетсва (=null),проверка каждые 10 сек
    @Scheduled(fixedRate = 10000)
    public void checkPersonPatronymic(){
        List<Person> persons = personRepository.findPersonsByPatronymicIsNull();
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        System.out.println(dateFormat.format(new Date()) + " " + persons);
    }
}