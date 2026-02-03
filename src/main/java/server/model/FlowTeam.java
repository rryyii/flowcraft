package server.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="flowteam")
public class FlowTeam {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String teamName;
    private LocalDate dateCreated;

    @OneToMany
    private List<FlowItem> items;

    @PrePersist
    protected void onCreate() {
        dateCreated = LocalDate.now();
    }

}
