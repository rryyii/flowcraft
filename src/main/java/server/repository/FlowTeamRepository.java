package server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import server.model.FlowTeam;

@Repository
public interface FlowTeamRepository extends JpaRepository<FlowTeam, Long> {
}
