package uz.muz.less_7_apprelationships.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import uz.muz.less_7_apprelationships.entity.Address;
import uz.muz.less_7_apprelationships.entity.Group;
import uz.muz.less_7_apprelationships.entity.Student;
import uz.muz.less_7_apprelationships.entity.Subject;
import uz.muz.less_7_apprelationships.payload.StudentDto;
import uz.muz.less_7_apprelationships.repository.AddressRepository;
import uz.muz.less_7_apprelationships.repository.GroupRepository;
import uz.muz.less_7_apprelationships.repository.StudentRepository;
import uz.muz.less_7_apprelationships.repository.SubjectRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    AddressRepository addressRepository;
    @Autowired
    GroupRepository groupRepository;
    @Autowired
    SubjectRepository subjectRepository;

    @GetMapping("/byMinistry")
    public Page<Student> getAllStudentsForMinistry(@RequestParam int page) {
        Pageable pageable = PageRequest.of(page, 10);
        return studentRepository.findAll(pageable);
    }

    @GetMapping("byUniversity/{university_id}")
    public Page<Student> getAllStudentsForUniversity(@PathVariable Integer university_id, @RequestParam int page) {
        Pageable pageable = PageRequest.of(page, 10);
        return studentRepository.findAllByGroup_Faculty_UniversityId(university_id, pageable);
    }

    @GetMapping("/byFaculty/{faculty_id}")
    public Page<Student> getAllForFaculty(@PathVariable Integer faculty_id, @RequestParam int page) {
        Pageable pageable = PageRequest.of(page, 10);
        return studentRepository.findAllByGroup_FacultyId(faculty_id, pageable);
    }

    @GetMapping("/byGroup/{group_id}")
    public Page<Student> getAllForGroup(@PathVariable Integer group_id, @RequestParam int page) {
        Pageable pageable = PageRequest.of(page, 10);
        return studentRepository.findAllByGroupId(group_id, pageable);
    }

    @PostMapping("/add")
    public String addStudent(StudentDto studentDto) {
        Optional<Address> byId = addressRepository.findById(studentDto.getAddressId());
        if (byId.isPresent()) {
            Address address = byId.get();
            Boolean aBoolean = studentRepository.existsByFirstNameAndLastNameAndAddress(studentDto.getFirstName(), studentDto.getLastName(), byId.get());
            if (!aBoolean) {
                Student student = new Student();
                student.setAddress(address);

                student.setFirstName(studentDto.getFirstName());
                student.setLastName(studentDto.getLastName());

                Optional<Group> group = groupRepository.findById(studentDto.getGroupId());
                group.ifPresent(student::setGroup);

                List<Integer> subjectIds = studentDto.getSubjectIds();
                List<Subject> subjectList = subjectRepository.findAllById(subjectIds);
                student.setSubject(subjectList);

                studentRepository.save(student);
            } else return "Already exist this student";

        } else
            return "Address not found";
        return "Successfully added";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteStudent(@PathVariable Integer id) {
        Optional<Student> byId = studentRepository.findById(id);
        if (byId.isPresent()) {
            studentRepository.delete(byId.get());
            return "Successfully added";
        } else return "Student not found";
    }

}
