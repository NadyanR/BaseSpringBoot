package person.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import person.model.Person;
import person.repository.PersonRepository;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PersonController {

    private final PersonRepository personRepository;

    @GetMapping("/person/{name}")
    public Person getPerson(@PathVariable("name") String name) {
        return personRepository.findByName(name);
    }

    //ex.10 Принимает имя и возраст, возвращает одного совпадающего человека
    @GetMapping("/person")
    public Person getPerson(@RequestParam("name") String name, @RequestParam("age") Integer age) {
        return personRepository.findByNameAndAge(name, age);
    }

    //ex.10 Принимает возраст, возвращает всех совпадающих людей
    @GetMapping("/persons/{age}")
    public List<Person> getPersons(@PathVariable("age") Integer age) {
        return personRepository.findByAge(age);
    }

//    private List<Person> persons = new ArrayList<>();
//    {
//        persons.add(new Person("John", 20));
//        persons.add(new Person("Joanna", 24));
//        persons.add(new Person("Bred", 50));
//        persons.add(new Person("Kate", 33));
//    }

//    @GetMapping("/person")
//    public Person getPerson(@RequestParam("name") String name) {
//        for (Person person : persons) {
//            if (person.getName().equals(name)) {
//                //return new Person(person.getName(), person.getAge());}
//                return person;}
//        }
//        return null;
//    }

//    @GetMapping("/person/{name}")
//    public Person showPerson(@PathVariable("name") String name) {
//        for (Person person : persons) {
//            if (person.getName().equals(name)) {
//                return person;}
//        }
//        return null;
//    }
//    @PostMapping("/person")
//    public Person showPerson(@RequestBody Person person) {
//        for (Person person1 : persons) {
//            if (person1.getName().equals(person.getName())) {
//                return person1;}
//                //return String.format("Person: %s %d", person.getName(), person.getAge());
//        }
//            return null;
//        }
//
@PutMapping("/person/{id}")
    public Person changePerson(@RequestBody Person newPerson, @PathVariable Integer id) {
        return personRepository.findById(id)
                .map(person -> {
                    person.setAge(newPerson.getAge());
                    return personRepository.save(person);
                })
                .orElseGet(() -> {
                    newPerson.setId(id);
                    return personRepository.save(newPerson);
                });
    }

    //ex.10 Принимает в теле json, сохраняет в БД нового человека с паспортом
    @PostMapping("/person")
    public Person addPerson(@RequestBody Person newPerson) {
        if (personRepository.existsByPassport(newPerson.getPassport())) {
            System.out.println("Запись существует в БД");
        } else {
            return personRepository.save(newPerson);
        }
        return null;
    }

//      @PatchMapping ("/person/{id}")
//      public Person changePerson(@PathVariable Integer personId, Person newPerson) {
//          persons.setPerson(personId, newPerson);
//       }
//
//      @DeleteMapping("/person")
//       public void deleteAllPersons() {
//          persons.clear();
//       }
//
        @DeleteMapping("/person/{id}")
        public void deletePerson(@PathVariable Integer id) {
    personRepository.deleteById(id);
}
//       }
}