package person.person.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import person.person.repository.PersonRepository;

@RestController
@RequiredArgsConstructor
public class PersonDeleteController {

    private final PersonRepository personRepository;

    @DeleteMapping("/person/{id}")
    public void deletePerson(@PathVariable Integer id) {
        personRepository.deleteById(id);
    }
}
