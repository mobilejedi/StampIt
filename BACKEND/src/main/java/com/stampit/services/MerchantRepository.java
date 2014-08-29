package com.stampit.services;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.jpa.repository.Query;

import com.stampit.model.Merchant;

import javax.persistence.*;

@RepositoryRestResource(collectionResourceRel ="merchant", path="merchants")
public interface MerchantRepository extends PagingAndSortingRepository<Merchant, Long> {
	@Query(value = "select m.*, st_distance(m.GPS_COORDINATES, geomFromText(:gpsCoordinates)) as calcDistance "
				+ "from merchants as m "
				+ "having calcDistance < :distance "
				+ "order by calcDistance asc", nativeQuery = true)
	List<Merchant> findByGpsCoordinates(@Param("gpsCoordinates")String gpsCoordinates, @Param("distance") float distance);
}


