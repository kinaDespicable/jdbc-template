package dev.spring.simpleCrud.model.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@RequiredArgsConstructor
@Entity
@Table(name="Department")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonProperty("department_name")
    @Column(name="department_name")
    private String departmentName;

    @JsonProperty("department_code")
    @Column(name = "department_code")
    private String departmentCode;


    public static class Builder{
        private final Department department = new Department();

        public Builder name(String name){
            department.departmentName = name;
            return this;
        }

        public Builder code(String code){
            department.departmentCode = code;
            return this;
        }
        public Department build(){
            return department;
        }
    }
    public static Builder builder(){
        return new Builder();
    }
}
