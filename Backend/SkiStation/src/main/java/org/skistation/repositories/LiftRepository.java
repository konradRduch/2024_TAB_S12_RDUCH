package org.skistation.repositories;

import org.skistation.models.Lift;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LiftRepository extends JpaRepository<Lift, Long>
{
}
