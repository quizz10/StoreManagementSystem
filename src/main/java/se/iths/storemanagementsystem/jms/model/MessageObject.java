package se.iths.storemanagementsystem.jms.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;
import java.util.UUID;

public class MessageObject {

    private UUID id;
    private String message;
    private String email;
    @JsonFormat(pattern = "yyyy-MM-dd HH-mm:ss")
    private LocalDateTime localDatetime;

    public MessageObject(UUID id, String message, String email, LocalDateTime localDatetime) {
        this.id = id;
        this.message = message;
        this.email = email;
        this.localDatetime = localDatetime;
    }

    public MessageObject() {}

    public UUID getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

    public LocalDateTime getLocalDatetime() {
        return localDatetime;
    }


    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "MessageObject{" +
                "id=" + id +
                ", message='" + message + '\'' +
                ", email='" + email + '\'' +
                ", localDatetime=" + localDatetime +
                '}';
    }
}
