package aptech.t2008m.hellospring.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    public Optional<Student> findById(String id) {
        return studentRepository.findById(id);
    }

    public Student save(Student account) {
        return studentRepository.save(account);
    }

    public void deleteById(String id) {
        studentRepository.deleteById(id);
    }
}
