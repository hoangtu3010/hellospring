package aptech.t2008m.hellospring.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/students")
public class StudentApi {
    @Autowired
    StudentService studentService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Student>> findAll(@RequestParam(defaultValue = "1") int page,
                                                 @RequestParam(defaultValue = "10") int limit) {
        return ResponseEntity.ok(studentService.findAll());
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Student> save(@RequestBody Student student) {
        return ResponseEntity.status(HttpStatus.CREATED).body(studentService.save(student));
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/{rollNumber}")
    public ResponseEntity<Student> update(@PathVariable String rollNumber, @RequestBody Student student) {
        Optional<Student> optionalStudent = studentService.findById(rollNumber);

        if (!optionalStudent.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        Student existStudent = optionalStudent.get();
        existStudent.setFullName(student.getFullName());
        existStudent.setGender(student.getGender());
        existStudent.setStatus(student.getStatus());

        return ResponseEntity.ok(studentService.save(existStudent));
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{rollNumber}")
    public ResponseEntity<Student> findById(@PathVariable String rollNumber) {
        Optional<Student> optionalStudent = studentService.findById(rollNumber);

        if (!optionalStudent.isPresent()) {
            ResponseEntity.notFound();
        }

        return ResponseEntity.ok(optionalStudent.get());
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/{rollNumber}")
    public ResponseEntity<Boolean> delete(@PathVariable String rollNumber) {
        Optional<Student> optionalStudent = studentService.findById(rollNumber);

        if (!optionalStudent.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(false);
        }

        studentService.deleteById(rollNumber);
        return ResponseEntity.status(HttpStatus.OK).body(true);
    }
}
