package avd.inf.demoapicourseregistration.domain;

import javax.persistence.Entity;
import java.time.LocalDate;

@Entity
public class FulltimeStudent extends Student {
    private String studentCoach;

    public FulltimeStudent(String name, LocalDate dayOfBirth, String studentCoach) {
        super(name, dayOfBirth);
        this.studentCoach = studentCoach;
    }

    public FulltimeStudent() {
    }
}
