package server.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Table(name="flowuser")
public class FlowUser {

    public FlowUser() {

    }

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column
    private String username;
    private LocalDate dateJoined;
    private String mainRole;
    private String mainClass;

    @ManyToOne
    private FlowTeam mainTeam;

    @OneToMany
    private List<FlowUser> altUsers;

}
