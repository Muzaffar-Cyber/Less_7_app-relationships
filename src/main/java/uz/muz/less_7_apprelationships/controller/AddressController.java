package uz.muz.less_7_apprelationships.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.muz.less_7_apprelationships.entity.Address;
import uz.muz.less_7_apprelationships.repository.AddressRepository;

import java.util.List;
import java.util.Optional;

@RestController
public class AddressController {
    @Autowired
    AddressRepository addressRepository;

    @RequestMapping(value = "/address",method =  RequestMethod.GET)
    public List<Address> getAllAddress() {
        List<Address> all = addressRepository.findAll();
        return all;
    }

    @RequestMapping(value = "/address/{id}",method = RequestMethod.GET)
    public Address getOneAddress(@PathVariable Integer id) {
        Optional<Address> address = addressRepository
                .findById(id);
        return address.orElse(null);
    }

    @RequestMapping(value = "/address", method = RequestMethod.POST)
    public String addAddress( Address address) {
        Address address1 = new Address();
        address1.setDistrict(address.getDistrict());
        address1.setDistrict(address.getStreet());
        address1.setDistrict(address.getCity());
        addressRepository.save(address1);
        return "Successfully added";
    }

    @RequestMapping(value = "/address/{id}",method = RequestMethod.DELETE)
    public String deleteAddress(@PathVariable Integer id) {
        Optional<Address> byId = addressRepository
                .findById(id);
        if (byId.isPresent()) {
            addressRepository.deleteById(id);
            return "Successfully deleted";
        } else return "Not found";
    }


}
