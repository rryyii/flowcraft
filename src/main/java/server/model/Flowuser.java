package server.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name="flowuser")
public class Flowuser {

    public Flowuser() {

    }

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column
    private String username;
    private Date dateJoined;
    private String mainRole;
    private String mainClass;

}
