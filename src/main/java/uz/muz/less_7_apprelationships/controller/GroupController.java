package uz.muz.less_7_apprelationships.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.muz.less_7_apprelationships.entity.Address;
import uz.muz.less_7_apprelationships.entity.Faculty;
import uz.muz.less_7_apprelationships.entity.Group;
import uz.muz.less_7_apprelationships.payload.GroupDto;
import uz.muz.less_7_apprelationships.repository.AddressRepository;
import uz.muz.less_7_apprelationships.repository.FacultyRepository;
import uz.muz.less_7_apprelationships.repository.GroupRepository;

import java.util.List;
import java.util.Optional;

@RequestMapping("/group")
@RestController
public class GroupController {

    @Autowired
    GroupRepository groupRepository;
    @Autowired
    FacultyRepository facultyRepository;

    @GetMapping
    public List<Group> getAllGroups() {
        List<Group> all = groupRepository.findAll();
        if (all.isEmpty())
            return null;
        return all;
    }

    @GetMapping("/{id}")
    public Group getOneGroup(@PathVariable Integer id) {
        Optional<Group> byId = groupRepository.findById(id);
        return byId.orElse(null);
    }

    @PostMapping
    public String addGroup( GroupDto groupDto) {
        Optional<Faculty> byId = facultyRepository.findById(groupDto.getFacultyId());
        if (!byId.isPresent())
            return "Faculty not found";
        Group group = new Group();
        group.setName(groupDto.getName());
        Boolean aBoolean = groupRepository.existsByNameAndFacultyId(groupDto.getName(), groupDto.getFacultyId());
        if (aBoolean)
            return "This couple of data already is exist";
        group.setFaculty(byId.get());
        groupRepository.save(group);
        return "Successfully added";
    }

    @DeleteMapping("/{id}")
    public String deleteGroup( Integer id) {
        Optional<Group> byId = groupRepository.findById(id);
        if (byId.isPresent()) {
            groupRepository.delete(byId.get());
            return "Successfully deleted";
        }
         else return "Not found";
    }


}
