package dev.spring.datajdbc.models.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table("Employees")
public class Employee {
    @Id
    private Long id;
    private String firstName;
    private String lastName;
    private Long departmentId;

    public Employee(String firstName, String lastName, Long departmentId){
        this.firstName = firstName;
        this.lastName = lastName;
        this.departmentId = departmentId;
    }
}
