package server.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name="flowteam")
public class FlowTeam {

    public FlowTeam() {

    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String teamName;
    private LocalDate dateCreated;

    @OneToMany
    private FlowItem currentItem;

}
