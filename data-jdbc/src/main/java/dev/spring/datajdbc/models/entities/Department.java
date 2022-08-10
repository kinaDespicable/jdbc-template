package dev.spring.datajdbc.models.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Table("Departments")
public class Department {
    @Id
    private Long id;
    private String departmentName;
    private String departmentCode;

    public Department(String departmentName, String departmentCode){
        this.departmentName = departmentName;
        this.departmentCode = departmentCode;
    }
}
