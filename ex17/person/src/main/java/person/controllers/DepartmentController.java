package person.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import person.exception_handling.ErrorCodes;
import person.exception_handling.NoSuchPersonException;
import person.model.Department;
import person.model.Person;
import person.repository.DepartmentRepository;
import person.repository.PersonRepository;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class DepartmentController {

    private final DepartmentRepository departmentRepository;
    private final PersonRepository personRepository;


    //ex16.1 - отобразить список всех отделов
    @GetMapping("/departments/all")
    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    //ex16.3 (п.2) - добавление нового отдела в БД (уникальный номер отдела)
    @PostMapping("/department")
    public Department createNewDepartment(@RequestBody Department newDepartment) {
            return departmentRepository.save(newDepartment);    }

    //ex16.3 (п.3) - назначить сотрудника в отдел
    @PostMapping("/department/{dep_id}/person/{p_id}")
    public Department addPersonToDepartment1(@PathVariable(name ="dep_id") Integer idDep,
                                             @PathVariable(name ="p_id") Integer idPer) {
            Department department = departmentRepository.findById(idDep)
                .map(dep -> {
                    dep.addPersonToDepartment(personRepository.findById(idPer)
                                    .orElseThrow(() -> new NoSuchPersonException(ErrorCodes.VALIDATION_PERSON_ERROR)));
                    dep.setPersonAmount(dep.getPersonAmount() + 1);
                    return departmentRepository.save(dep);})
                            .orElseThrow(() -> new NoSuchPersonException(ErrorCodes.VALIDATION_DEPARTMENT_ERROR));
        return department;

    }

    //ex.16.1 - вывести перечень сотрудников в отделе
    @GetMapping("/department/{id}/all_person")
    public List<Person> personsInDepartment(@PathVariable Integer id) {
        return personRepository.findPersonsByDepartmentId(id);
    }

    //ex16.3 (п. 4) - расчет кол-ва площади,кот. занимает все оборудование департамента=id
    @GetMapping("/department/{id}")
    public String getSquareDepartment(@PathVariable Integer id) {
        return "Площадь, которое занимает оборудование подразделения " + id +
                " равна " + departmentRepository.sumSquareDepartment(id) + " кв.ед.";
    }
}