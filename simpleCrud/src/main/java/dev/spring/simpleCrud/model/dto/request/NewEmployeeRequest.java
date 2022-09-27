package dev.spring.simpleCrud.model.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;


@Getter
@Setter
public class NewEmployeeRequest {

    @JsonProperty("email")
    @Email(message="Invalid email format.")
    @NotBlank(message = "Email field should not be empty.")
    private String email;

    @JsonProperty("first_name")
    @NotBlank(message = "First name is required field.")
    private String firstName;

    @JsonProperty("last_name")
    @NotBlank(message = "Last name is required field.")
    private String lastName;

    @JsonProperty("department_name")
    private String departmentName;

}
