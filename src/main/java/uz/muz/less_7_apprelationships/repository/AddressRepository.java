package uz.muz.less_7_apprelationships.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.muz.less_7_apprelationships.entity.Address;
import uz.muz.less_7_apprelationships.entity.University;

public interface AddressRepository extends JpaRepository<Address,Integer> {
}
