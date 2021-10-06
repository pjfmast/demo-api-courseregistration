package avd.inf.demoapicourseregistration.controller;

import avd.inf.demoapicourseregistration.domain.Student;
import avd.inf.demoapicourseregistration.repository.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/students")
public class StudentController {
    private final StudentRepository studentRepository;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public StudentController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @GetMapping
    public ResponseEntity<List<Student>> getAll(@RequestParam(required = false) String name) {
        List<Student> found = new ArrayList<>();

        if (name == null) {
            found.addAll(studentRepository.findAll());
        } else {
            found.addAll(studentRepository.findByNameContainingIgnoringCase(name));
        }
        if (found.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(found);
    }

    @GetMapping("/immature")
    public ResponseEntity<List<Student>> upComingCourses() {
        List<Student> found = new ArrayList<>();
        LocalDate immatureDate = LocalDate.now().minusYears(18).plusDays(1);
        found.addAll(studentRepository.findByDayOfBirthBetween(immatureDate,LocalDate.now()));

        if (found.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(found);
    }

    @PostMapping
    ResponseEntity<Student> newCourse(@RequestBody Student newCourse) {

        try {
            Student student = studentRepository.save(newCourse);
            return new ResponseEntity<>(student, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            logger.error("Error during creation of: " + newCourse, e);
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteById(@PathVariable Long id) {
        if ((!studentRepository.existsById(id))) {
            // HTTP 404 Not Found response is for (deleting) a resource does not exist
            return ResponseEntity.notFound().build();
        }
        studentRepository.deleteById(id);
        // HTTP 204 No Content: The server successfully processed the request, but is not returning any content
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    ResponseEntity<Student> update(@PathVariable Long id, @RequestBody Student studentNew) {
        Optional<Student> optionalCourse = studentRepository.findById(id);

        if (optionalCourse.isPresent()) {
            Student studentOld = optionalCourse.get();
            studentOld.setName(studentNew.getName());
            studentOld.setDayOfBirth(studentNew.getDayOfBirth());

            return ResponseEntity.ok(studentRepository.save(studentOld));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
