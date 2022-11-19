package person.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import person.model.Passport;
import person.model.Person;

import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {
    //List <Person> findByName(String name);
    List<Person> findByName(String name);
    Person findByNameAndAge(String name, Integer age);
    List<Person> findByAge(Integer age);

    boolean existsByPassport(Passport passport);

    //ex.11 метод, возвращающий всех людей старше 30 лет (PersonDto)
    //List<Person> findByAgeGreaterThan(Integer age);
    @Query(value = "SELECT u FROM Person u WHERE u.age > :age" )
    List<Person> findAllPersons(@Param("age")Integer age);

    //ex.16.1 вывод все сотрудников в одном отделе (запрос GET в Department)
    List<Person> findPersonsByDepartmentId(Integer department_id);
}
