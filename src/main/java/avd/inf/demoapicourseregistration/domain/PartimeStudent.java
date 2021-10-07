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

    public String getWorkingEnvironment() {
        return workingEnvironment;
    }

    public void setWorkingEnvironment(String workingEnvironment) {
        this.workingEnvironment = workingEnvironment;
    }
}
