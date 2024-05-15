package org.skistation.repositories;

import org.skistation.models.LiftPass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LiftPassRepository extends JpaRepository<LiftPass, Integer>
{
    List<LiftPass> findByLiftId(Integer liftId);

    List<LiftPass> findByPassId(Integer passId);
}
