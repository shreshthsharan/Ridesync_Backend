package com.ridesync.dto;

import java.math.BigDecimal;

import com.ridesync.enums.RideStatus;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RideResponseDTO {

	private Long rideId;
	private RideStatus status;
	private String driverName;
	private Double driverRating;
	private Integer estimatedArrivalMinutes;
	private BigDecimal fare;
}
