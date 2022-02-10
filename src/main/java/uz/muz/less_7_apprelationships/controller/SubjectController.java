package uz.muz.less_7_apprelationships.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.muz.less_7_apprelationships.entity.Address;
import uz.muz.less_7_apprelationships.entity.Subject;
import uz.muz.less_7_apprelationships.entity.University;
import uz.muz.less_7_apprelationships.payload.UniversityDto;
import uz.muz.less_7_apprelationships.repository.AddressRepository;
import uz.muz.less_7_apprelationships.repository.SubjectRepository;
import uz.muz.less_7_apprelationships.repository.UniversityRepository;

import java.util.List;
import java.util.Optional;

@RestController
public class SubjectController {
    @Autowired
    SubjectRepository subjectRepository;

    @RequestMapping(value = "/Subject", method = RequestMethod.GET)
    public List<Subject> getSubject() {
        List<Subject> all = subjectRepository.findAll();
        return all;
    }

    @RequestMapping(value = "/subject/{id}", method = RequestMethod.GET)
    public Subject getOneSubject(@PathVariable Integer id) {
        Optional<Subject> subject = subjectRepository
                .findById(id);
        return subject.orElse(null);
    }

    @RequestMapping(value = "/subject", method = RequestMethod.POST)
    public String addSubject( Subject subject) {
        Subject subject1 = new Subject();
        subject1.setName(subject.getName());
        return "Successfully added";
    }

    @RequestMapping(value = "/subject/{id}",method = RequestMethod.DELETE)
    public String deleteSubject( @PathVariable Integer id) {
        Optional<Subject> byId = subjectRepository.findById(id);
        if (byId.isPresent()) {
            subjectRepository.deleteById(id);
            return "Successfully deleted";
        } else return "Not found";
    }


}
