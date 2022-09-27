package dev.spring.simpleCrud.exception;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.Instant;

@Getter
@Setter
public class ErrorMessage {

    @JsonProperty("timestamp")
    private Instant timestamp;

    @JsonProperty("status")
    private HttpStatus status;

    @JsonProperty("statusCode")
    private int statusCode;

    @JsonProperty("message")
    private String message;

    public static class Builder{

        private final ErrorMessage errorMessage = new ErrorMessage();

        public Builder timestamp(Instant i){
            errorMessage.timestamp = i;
            return this;
        }
        public Builder status(HttpStatus status){
            errorMessage.status = status;
            return this;
        }
        public Builder statusCode(int code){
            errorMessage.statusCode = code;
            return this;
        }
        public Builder message(String message){
            errorMessage.message = message;
            return this;
        }
        public ErrorMessage build(){
            return errorMessage;
        }
    }

    public static Builder builder(){
        return new Builder();
    }
}
