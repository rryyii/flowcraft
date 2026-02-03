package server.model;

import lombok.Getter;

@Getter
public enum Status {
    IN_PROGRESS("In Progress"),
    COMPLETED("Completed"),
    CANCELLED("Cancelled"),
    NEW("New");

    private String description;

    private Status(String description) {
        this.description = description;
    }

}
