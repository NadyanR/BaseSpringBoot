package person.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import person.model.Person;

import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {
    //List <Person> findByName(String name);
    Person findByName(String name);
    Person findByNameAndAge(String name, Integer age);
    List<Person> findByAge(Integer age);

}
