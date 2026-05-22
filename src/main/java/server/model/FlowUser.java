package server.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Data model for a FlowUser with relevant properties through JPA.
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="flowuser")
public class FlowUser {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column
    private String username;
    private LocalDate dateJoined;
    private String mainRole;
    private String mainClass;
    @Enumerated(value = EnumType.ORDINAL)
    private Title title;

    @ManyToMany(mappedBy = "users")
    private List<FlowTeam> teams = new ArrayList<>();

    @PrePersist
    protected void onCreate() {
        this.dateJoined = LocalDate.now();
    }

}
