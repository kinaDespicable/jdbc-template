package dev.spring.simpleCrud.model.dto.response;

import dev.spring.simpleCrud.model.enums.Deleted;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class DeleteResponse {

    private Instant timestamp;
    private boolean deleted;
    private String deletedPhrase;
    private Object deletedObject;


    public static class Builder{
        private final DeleteResponse response = new DeleteResponse();

        public Builder timestamp(Instant i){
            response.timestamp = i;
            return this;
        }

        public Builder deleted(boolean d){
            response.deleted = d;
            return this;
        }

        public Builder deletedPhrase(String phrase){
            response.deletedPhrase = phrase;
            return this;
        }

        public Builder deletedObject(Object o){
            response.deletedObject = o;
            return this;
        }

        public DeleteResponse build(){
            return response;
        }
    }
    public static Builder builder(){
        return new Builder();
    }
}
