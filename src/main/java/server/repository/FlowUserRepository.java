package server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import server.model.FlowUser;

@Repository
public interface FlowUserRepository extends JpaRepository<FlowUser, Long> {
}
