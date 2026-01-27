package server.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.Instant;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name="flowitem")
public class FlowItem {

    public FlowItem() {

    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;
    private String description;
    private Status status;
    private LocalDateTime deadline;
    private Instant createdTimestamp;
    private Instant lastupdatedTimestamp;

    @ManyToOne
    private FlowTeam team;

}
