package avd.inf.demoapicourseregistration;



import avd.inf.demoapicourseregistration.domain.Student;
import avd.inf.demoapicourseregistration.repository.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;

// Annotating a class:
// - with the @Configuration indicates that the class can be used by the Spring IoC container as a source of bean definitions.
// - The @Bean annotation tells Spring that a method annotated with @Bean will return an object that should be registered as a bean in the Spring application context.
@Configuration
public class LoadDummyData {
    private static final Logger log = LoggerFactory.getLogger(LoadDummyData.class);

    @Bean
    CommandLineRunner initDatabase(StudentRepository repository) {
        return args -> {
            log.info("Preloading " + repository.save(new Student("Henk", LocalDate.of(2001,9,1))));
            log.info("Preloading " + repository.save(new Student("Marie", LocalDate.of(2004,5,10))));
            log.info("Preloading " + repository.save(new Student("Sophie", LocalDate.of(1998,7,8))));
            log.info("Preloading " + repository.save(new Student("Ivo", LocalDate.of(1994,3,12))));
        };

    }

}
