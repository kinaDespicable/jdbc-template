package dev.spring.datajdbc.exceptions;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorMessage {

    @JsonProperty("status_code")
    private int statusCode;

    @JsonProperty("status")
    private HttpStatus status;

    @JsonProperty("message")
    private String message;

}
