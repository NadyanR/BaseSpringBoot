package person.person;

import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
public class PersonController {
    private List<Person> persons = new ArrayList<>();
    {
        persons.add(new Person("John", 20));
        persons.add(new Person("Joanna", 24));
        persons.add(new Person("Bred", 50));
        persons.add(new Person("Kate", 33));
    }

    @GetMapping("/person")
    public Person getPerson(@RequestParam("name") String name) {
        for (Person person : persons) {
            if (person.getName().equals(name)) {
                //return new Person(person.getName(), person.getAge());}
                return person;}
        }
        return null;
    }

    @GetMapping("/person/{name}")
    public Person showPerson(@PathVariable("name") String name) {
        for (Person person : persons) {
            if (person.getName().equals(name)) {
                return person;}
        }
        return null;
    }

    @PostMapping("/person")
    public Person showPerson(@RequestBody Person person) {
        for (Person person1 : persons) {
            if (person1.getName().equals(person.getName())) {
                return person1;}
                //return String.format("Person: %s %d", person.getName(), person.getAge());
        }
            return null;
        }
//
//      @PutMapping ("/person/{id}")
//      public Person addPerson(@PathVariable Integer personId, Person newPerson) {
//          persons.setPerson(personId, newPerson);
//       }
//
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
//       @DeleteMapping("/person/{id}")
//       public void deletePerson(@PathVariable Integer personId) {
//           persons.remove(personId);
//       }
}