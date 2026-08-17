package com.ridesync.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class DriverLocationUpdateDTO {

	@NotNull
	private Double lat;
	@NotNull
	private Double lng;
}
