package com.school.dashboard.repository;

import com.school.dashboard.model.DashboardData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DashboardDataRepository extends JpaRepository<DashboardData, Long> {
    
    DashboardData findFirstByOrderByLastUpdatedDesc();
}