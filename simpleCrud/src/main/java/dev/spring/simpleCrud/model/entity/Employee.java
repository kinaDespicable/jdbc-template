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
@Table(name="Employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="email", nullable = false, unique = true)
    private String email;

    @Column(name="first_name", nullable = false)
    @JsonProperty("first_name")
    private String firstName;

    @Column(name="last_name", nullable = false)
    @JsonProperty("last_name")
    private String lastName;

    @JoinColumn(name="department_id", nullable = true)
    @OneToOne
    private Department department;

    public static class Builder{
        private final Employee employee = new Employee();

        public Builder email(String email){
            employee.email = email;
            return this;
        }

        public Builder firstName(String firstName){
            employee.firstName = firstName;
            return this;
        }

        public Builder lastName(String lastName){
            employee.lastName = lastName;
            return this;
        }

        public Builder department(Department department){
            employee.department = department;
            return this;
        }

        public Employee build(){
            return employee;
        }

    }

    public static Builder builder(){
        return new Builder();
    }

}
