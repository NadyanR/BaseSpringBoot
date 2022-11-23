package person.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import person.model.Passport;

@Repository
public interface PassportRepository extends JpaRepository<Passport, Integer> {
}