package com.school.dashboard.repository;

import com.school.dashboard.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    
    @Query("SELECT e FROM Event e ORDER BY e.displayOrder ASC, e.id ASC")
    List<Event> findAllOrderedByDisplayOrder();
}