package uz.muz.less_7_apprelationships.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.muz.less_7_apprelationships.entity.Group;

public interface GroupRepository extends JpaRepository<Group,Integer> {
    Boolean existsByNameAndFacultyId(String name,Integer faculty_id);
}
