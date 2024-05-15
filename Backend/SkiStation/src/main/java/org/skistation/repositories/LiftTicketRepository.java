package org.skistation.repositories;

import org.skistation.models.LiftTicket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LiftTicketRepository extends JpaRepository<LiftTicket, Integer>
{
    List<LiftTicket> findByLiftId(int liftId);

    List<LiftTicket> findByTicketId(int ticketId);
}
