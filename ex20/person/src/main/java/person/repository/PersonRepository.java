package person.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import person.model.Passport;
import person.model.Person;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {
    //List <Person> findByName(String name);
    List<Person> findByName(String name);

    //Person findByNameAndAge(String name, Integer age);
    Person findByNameAndBirthday(String name, LocalDate birthday);//ex.17 замена age на birthday

    List<Person> findByBirthday(LocalDate birthday);//ex.17 замена age на birthday

    boolean existsByPassport(Passport passport);

    //ex.11 метод, возвращающий всех людей старше даты (PersonDto)
    //List<Person> findByBirthdayGreaterThan(LocalDate birthday);
    @Query(value = "SELECT u FROM Person u WHERE u.birthday > :birthday" )
    List<Person> findAllPersons(@Param("birthday")LocalDate birthday);

    //ex.16.1 вывод все сотрудников в одном отделе (запрос GET в Department)
    List<Person> findPersonsByDepartmentId(Integer department_id);

    //ex.19 поиск в БД всех людей без отчества
    List<Person> findPersonsByPatronymicIsNull();

    //ex.20 подсчет кол-ва записей, кот. возвращает запрос
    @Query(value = "SELECT COUNT(u) FROM Person u WHERE u.birthday = ?1")
    Long recordsNumber(LocalDate birthday);

    @Query(value = "SELECT COUNT(u) FROM Person u WHERE u.name = ?1")
    Long recordsNumber2(String name);
}