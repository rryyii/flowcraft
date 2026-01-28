package server.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name="flowitem")
public class FlowItem {

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
