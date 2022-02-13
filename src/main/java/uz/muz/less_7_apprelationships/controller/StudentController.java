package uz.muz.less_7_apprelationships.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import uz.muz.less_7_apprelationships.entity.Student;
import uz.muz.less_7_apprelationships.repository.StudentRepository;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    StudentRepository studentRepository;

    @GetMapping("/forMinistry")
    public Page<Student> getAllStudentsForMinistry(@RequestParam int page) {
        Pageable pageable = PageRequest.of(page, 10);
        return studentRepository.findAll(pageable);
    }

    @GetMapping("forUniversity/{university_id}")
    public Page<Student> getAllStudentsForUniversity(@PathVariable Integer university_id, @RequestParam int page) {
        Pageable pageable = PageRequest.of(page, 10);
        return studentRepository.findAllByGroup_Faculty_UniversityId(university_id, pageable);
    }

    @GetMapping("/faculty/{faculty_id}")
    public Page<Student> getAllForFaculty(@PathVariable Integer faculty_id, @RequestParam int page) {
        Pageable pageable = PageRequest.of(page, 10);
        return studentRepository.findAllByGroup_FacultyId(faculty_id, pageable);
    }
    @GetMapping("/group/{group_id}")
    public Page<Student> getAllForGroup(@PathVariable Integer group_id, @RequestParam int page) {
        Pageable pageable = PageRequest.of(page, 10);
        return studentRepository.findAllByGroupId(group_id, pageable);
    }



}
