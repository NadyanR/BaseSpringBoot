package person.person.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import person.person.model.Person;
import person.person.repository.PersonRepository;

@RestController
@RequiredArgsConstructor
public class PersonChangeController {

    private final PersonRepository personRepository;

    @PutMapping("/person/{id}")
      public Person changePerson(@RequestBody Person newPerson, @PathVariable Integer id) {
          return personRepository.findById(id)
                  .map(person -> {person.setAge(newPerson.getAge());
                  return personRepository.save(person);
                  })
                  .orElseGet(()->{
                      newPerson.setId(id);
                      return personRepository.save(newPerson);
                  });
       }
}
