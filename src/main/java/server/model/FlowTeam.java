package server.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
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
    private String teamDescription;
    private LocalDate dateCreated;

    @OneToMany(mappedBy = "team")
    private List<FlowItem> items = new ArrayList<>();

    @PrePersist
    protected void onCreate() {
        dateCreated = LocalDate.now();
    }

}
