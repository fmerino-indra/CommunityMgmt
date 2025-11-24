package org.fmm.communitymgmt.common.repository.groups;

import java.time.OffsetDateTime;
import java.util.List;

import org.fmm.communitymgmt.common.model.groups.Raffle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by felix.merino.
 * @author Félix merino
 */
@Repository
public interface RaffleRepository extends JpaRepository<Raffle, Integer> {
    @Query("SELECT r FROM Raffle r"
            + " WHERE r.date >= :from"
            + " AND r.date <= :to"
            + " ORDER BY r.date ASC, r.id ASC")
    List<Raffle> selectByDates(@Param("from")OffsetDateTime from, @Param("to")OffsetDateTime to);

}
