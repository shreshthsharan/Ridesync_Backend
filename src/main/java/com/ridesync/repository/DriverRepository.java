package com.ridesync.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ridesync.entity.Driver;
import com.ridesync.enums.DriverStatus;

@Repository
public interface DriverRepository extends JpaRepository<Driver, Long> {

	Optional<Driver> findByUserId(Long userId);
	List<Driver> findByStatus(DriverStatus status);
}
