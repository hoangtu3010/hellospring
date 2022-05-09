package aptech.t2008m.hellospring.grade;

import aptech.t2008m.hellospring.student.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/grades")
public class GradeApi {
    @Autowired
    private GradeService gradeService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Grade>> findAll(){
        return ResponseEntity.ok(gradeService.findAll());
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{id}")
    public ResponseEntity<Grade> findById(int id){
        Optional<Grade> optionalGrade = gradeService.findById(id);

        if (!optionalGrade.isPresent()) {
            ResponseEntity.notFound();
        }

        return ResponseEntity.ok(optionalGrade.get());
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Grade> save(@RequestBody Grade grade){
        return ResponseEntity.status(HttpStatus.CREATED).body(gradeService.save(grade));
    }
}
