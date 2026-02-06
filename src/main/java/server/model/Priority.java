package server.model;

import lombok.Getter;

@Getter
public enum Priority {

    CRITICAL(1),
    HIGH(2),
    MEDIUM(3),
    LOW(4);

    private int number;

    private Priority(int number) {
        this.number = number;
    }

}
