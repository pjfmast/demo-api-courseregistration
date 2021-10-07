package avd.inf.demoapicourseregistration.domain;

import javax.persistence.Entity;
import java.time.LocalDate;

@Entity
public class PartimeStudent extends Student {
    private String workingEnvironment;

    public PartimeStudent(String name, LocalDate dayOfBirth, String workingEnvironment) {
        super(name, dayOfBirth);
        this.workingEnvironment = workingEnvironment;
    }

    public PartimeStudent() {
    }
}
