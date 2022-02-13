package uz.muz.less_7_apprelationships.repository;

import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import uz.muz.less_7_apprelationships.entity.Address;
import uz.muz.less_7_apprelationships.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Integer> {
    Page<Student> findAllByGroup_Faculty_UniversityId(Integer group_faculty_university_id, Pageable pageable);

    Page<Student> findAllByGroup_FacultyId(Integer group_faculty_id, Pageable pageable);

    Page<Student> findAllByGroupId(Integer group_id, Pageable pageable);

    Boolean existsByFirstNameAndLastNameAndAddress(String firstName, String lastName, Address address);


}
