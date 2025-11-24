package org.fmm.communitymgmt.common.repository.groups;

import org.fmm.communitymgmt.common.model.groups.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by felix.merino.
 * @author Félix merino
 */
@Repository
public interface ServiceRepository extends JpaRepository<Service, Integer> {
	/*
    @Query("SELECT DISTINCT s FROM Service s"
            + " INNER JOIN FETCH s.serviceTypeBean st")
    List<Service> listServices();

    @Query("SELECT DISTINCT s FROM Service s"
            + " LEFT JOIN FETCH s.serviceTypeBean st"
            + " ORDER BY s.serviceDate")
    List<Service> listOrderedServices();

    @Query("SELECT DISTINCT s FROM Service s "
            + " WHERE s.serviceDate >= :from AND s.serviceDate <= :to"
            + " ORDER BY s.serviceDate ASC")
    List<Service> listServicesFromTo(@Param("from")ZonedDateTime from, @Param("to")ZonedDateTime to);
*/    
}
