package server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import server.model.FlowItem;

import java.util.List;

@Repository
public interface FlowItemRepository extends JpaRepository<FlowItem, Long> {

    public List<FlowItem> findItemsByOwnerId(Long ownerId);
}
