package server.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="item_history")
public class ItemHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="item_id", nullable = false)
    private Long itemId;

    @Column
    private String name;
    private String description;
    private Long ownerId;

    @Enumerated(value = EnumType.ORDINAL)
    private Instant timestamp;

    @OneToOne
    @JoinColumn(name="changed_by_user")
    private FlowUser changedBy;


}
