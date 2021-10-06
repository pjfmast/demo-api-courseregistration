package avd.inf.demoapicourseregistration.repository;

import avd.inf.demoapicourseregistration.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    // By extending JpaRepository we already get a lot of 'find'-functionality

    // Here we put our custom finder methods:
    // Some magic happens: https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods.query-creation
    List<Student> findByNameContainingIgnoringCase(String name);
    List<Student> findByDayOfBirthBetween(LocalDate fromDate, LocalDate toDate);
}
