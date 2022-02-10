package uz.muz.less_7_apprelationships.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.muz.less_7_apprelationships.entity.Address;
import uz.muz.less_7_apprelationships.entity.University;
import uz.muz.less_7_apprelationships.payload.UniversityDto;
import uz.muz.less_7_apprelationships.repository.AddressRepository;
import uz.muz.less_7_apprelationships.repository.UniversityRepository;

import java.util.List;
import java.util.Optional;

@RestController
public class UniversityController {
    @Autowired
    UniversityRepository universityRepository;
    @Autowired
    AddressRepository addressRepository;

    @RequestMapping(value = "/university", method = RequestMethod.GET)
    public List<University> getAllUniversity() {
        List<University> all = universityRepository.findAll();
        return all;
    }

    @RequestMapping(value = "/university/{id}", method = RequestMethod.GET)
    public University getOneUniversity(@PathVariable Integer id) {
        Optional<University> university = universityRepository.findById(id);
        return university.orElse(null);
    }

    @RequestMapping(value = "/university", method = RequestMethod.POST)
    public String addUniversity(@RequestBody UniversityDto universityDto) {
        Address address = new Address();
        address.setCity(universityDto.getCity());
        address.setDistrict(universityDto.getDistrict());
        address.setStreet(universityDto.getStreet());
        Address address1 = addressRepository.save(address);
        University university = new University();
        university.setAddress(address1);
        university.setName(universityDto.getName());
        University university1 = universityRepository.save(university);

        return "Successfully added";
    }

    @RequestMapping(value = "/university/{id}")
    public String deleteUniversity(@PathVariable Integer id) {
        Optional<University> byId = universityRepository.findById(id);
        if (byId.isPresent()) {
            universityRepository.deleteById(id);
            return "Successfully deleted";
        } else return "Not found";
    }


}
