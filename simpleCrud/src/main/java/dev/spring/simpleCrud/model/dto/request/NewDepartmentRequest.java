package dev.spring.simpleCrud.model.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class NewDepartmentRequest {

    @NotBlank(message = "Department name field cannot be blank.")
    @JsonProperty("department_name")
    private String departmentName;

    @NotBlank(message = "Department code field cannot be blank.")
    @JsonProperty("department_code")
    private String departmentCode;

}
