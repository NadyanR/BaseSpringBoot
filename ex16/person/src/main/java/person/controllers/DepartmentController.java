package person.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
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

    //ex16.1 - добавление нового отдела в БД (уникальный номер отдела)
    @PostMapping("/department")
    public Department createNewDepartment(@RequestBody Department newDepartment) {
            return departmentRepository.save(newDepartment);
    }

    //ex.16.1 - вывести перечень сотрудников в отделе
    @GetMapping("/department/{id}")
    public List<Person> personsInDepartment(@PathVariable Integer id) {
        return personRepository.findPersonsByDepartmentId(id);
    }

}
//    Department department = departmentRepository.findByName(newDepartment.getDepName());
//        if (department = )
//                .map(department -> {
//                department.setAge(newDepartment.getAge());
//                department.setDepartment(newDepartment.getDepartment());
//                return departmentRepository.save(person);})
//                .orElseGet(() -> {
//                newDepartment.setId(id);
//                return departmentRepository.save(newDepartment);});