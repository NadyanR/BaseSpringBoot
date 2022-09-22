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


    @GetMapping("/first-request/person")
    public Person getPerson(@RequestParam("name") String name) {
        for (Person person : persons) {
            if (person.getName().equals(name)) {
                return new Person(person.getName(), person.getAge());
            }
        }
        return null;
    }

    @GetMapping("/second-request/person/{name}")
    public Person showPerson(@PathVariable("name") String name) {
        for (Person person : persons) {
            if (person.getName().equals(name)) {
                return new Person(person.getName(), person.getAge());
            }
        }
        return null;
    }

//    @GetMapping("/third-request/person")
//    @ResponseBody
//    public Person showPerson1(@RequestBody Person person) {
//            if (persons.contains(person)) {
//                return new Person(person.getName(), person.getAge());
//            }
//        return null;
//    }
}
