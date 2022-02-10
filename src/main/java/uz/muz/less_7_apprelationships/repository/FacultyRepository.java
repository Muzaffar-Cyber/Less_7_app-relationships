package uz.muz.less_7_apprelationships.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.muz.less_7_apprelationships.entity.Address;
import uz.muz.less_7_apprelationships.entity.Faculty;

public interface FacultyRepository extends JpaRepository<Faculty,Integer> {
    Boolean existsByNameAndUniversityId(String name, Integer university_id);
}
