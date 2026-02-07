package server.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.time.LocalDateTime;

/**
 * Data model for a FlowItem with relevant properties through JPA.
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="flowitem")
public class FlowItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;
    private String description;
    @Enumerated(value = EnumType.STRING)
    private Status status;
    @Enumerated(value = EnumType.ORDINAL)
    private Priority priority;
    private LocalDateTime deadline;
    private Instant createdTimestamp;
    private Instant lastupdatedTimestamp;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private FlowTeam team;

    @PrePersist
    protected void onCreate() {
        this.createdTimestamp = Instant.now();
    }

    @PostUpdate
    protected void onUpdate() {
        this.lastupdatedTimestamp = Instant.now();
    }

}
