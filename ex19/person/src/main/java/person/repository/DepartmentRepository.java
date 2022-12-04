package person.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import person.model.Department;

import java.util.List;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer> {
       //@Query(value = "SELECT u FROM Department u WHERE u.department_name" )
       List<Department> findAll();

       @Query(value = "SELECT SUM(square) FROM Department_operations WHERE department_id = :id", nativeQuery = true )
       Integer sumSquareDepartment(@Param("id")Integer id);
}