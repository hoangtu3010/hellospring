package aptech.t2008m.hellospring.student;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class Student {
    @Id
    private String rollNumber;
    private String fullName;
    private int gender;
    private int status;
}
