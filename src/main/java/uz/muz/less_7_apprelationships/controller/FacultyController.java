package uz.muz.less_7_apprelationships.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.muz.less_7_apprelationships.entity.Faculty;
import uz.muz.less_7_apprelationships.entity.University;
import uz.muz.less_7_apprelationships.payload.FacultyDto;
import uz.muz.less_7_apprelationships.repository.FacultyRepository;
import uz.muz.less_7_apprelationships.repository.UniversityRepository;

import java.util.List;
import java.util.Optional;

@RestController
public class FacultyController {
    @Autowired
    FacultyRepository facultyRepository;
    @Autowired
    UniversityRepository universityRepository;

    @RequestMapping(value = "/faculty",method = RequestMethod.GET)
    public List<Faculty> getAllFaculty() {
        List<Faculty> all = facultyRepository.findAll();
        if (all.isEmpty())
            return null;
        return all;
    }

    @RequestMapping(value = "/faculty/{id}",method = RequestMethod.GET)
    public Faculty getOneFaculty( Integer id) {
        Optional<Faculty> faculty = facultyRepository
                .findById(id);
        return faculty.orElse(null);
    }

    @RequestMapping(value = "/faculty", method = RequestMethod.POST)
    public String addFaculty(@RequestBody FacultyDto facultyDto) {
        Optional<University> byId = universityRepository.findById(facultyDto.getUniversityId());
        if (!byId.isPresent())
            return "University not found";
        Boolean aBoolean = facultyRepository.existsByNameAndUniversityId(facultyDto.getName(), facultyDto.getUniversityId());
        if (aBoolean)
            return "this couple is already exist";
        Faculty faculty1 = new Faculty();
        faculty1.setName(facultyDto.getName());
        faculty1.setUniversity(byId.get());
        facultyRepository.save(faculty1);
        return "Successfully added";
    }

    @RequestMapping(value = "/faculty/{id}",method = RequestMethod.DELETE)
    public String deleteFaculty(@PathVariable Integer id) {
        Optional<Faculty> byId = facultyRepository.findById(id);
        if (byId.isPresent()) {
            facultyRepository.delete(byId.get());
            return "Successfully deleted";
        } else return "Faculty not found";
    }

}
