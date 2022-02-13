package uz.muz.less_7_apprelationships.payload;

import lombok.Data;

import java.util.List;

@Data
public class StudentDto {
    private String firstName;
    private String lastName;
    private Integer addressId;
    private Integer groupId;
    private Integer universityId;
    private List<Integer> subjectIds;
}
