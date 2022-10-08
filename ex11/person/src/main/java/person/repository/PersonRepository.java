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

    boolean existsByPassport(String passport);

    //ex.11 метод, возвращающий всех людей старше 30 лет (PersonDto)
    List<Person> findByAgeGreaterThan(Integer age);
//    @Query(value = "SELECT u FROM User u WHERE u.age > 30")
//    List<Person> findAllPersons(Sort sort);
}
