package kjistik.backend.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import kjistik.backend.Models.Subject;
import kjistik.backend.Repository.StudyRepository;

@Component
public class SubjectServiceImp implements SubjectService {

    @Autowired
    StudyRepository repo;

    @Override
    public List<Subject> all() {
        return repo.findAll();
    }

    @Override
    public Subject one(Long id) {

        return repo.findById(id).orElseThrow();
    }

    @Override
    public Subject newSubject(Subject nuevo) {

        return repo.save(nuevo);
    }

    @Override
    public Subject Change(Subject newSubject, Long id) {
        return repo.findById(id)
                .map(subject -> {
                    subject.setSubject_name(newSubject.getSubject_name());
                    return subject;
                })
                .orElseGet(() -> {
                    newSubject.setIdSubject(id);
                    return repo.save(newSubject);
                });
    }

    /*
     * @Override
     * public void delete(Long id) {
     * repo.deleteById(id);
     * 
     * }
     */
}
