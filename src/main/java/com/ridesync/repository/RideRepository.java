package com.ridesync.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ridesync.entity.Ride;
import com.ridesync.enums.RideStatus;

@Repository
public interface RideRepository extends JpaRepository<Ride, Long> {

	List<Ride> findByRiderIdAndStatusIn(Long riderId, List<RideStatus> statuses);
	Optional<Ride> findByDriverIdAndStatus(Long driverId, RideStatus status);
}
