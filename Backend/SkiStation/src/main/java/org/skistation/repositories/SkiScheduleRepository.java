package org.skistation.repositories;

import org.skistation.models.SkiSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SkiScheduleRepository extends JpaRepository<SkiSchedule, Long>
{
}
