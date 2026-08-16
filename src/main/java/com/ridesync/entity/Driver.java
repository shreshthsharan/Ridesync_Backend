package com.ridesync.entity;

import java.time.Duration;
import java.time.LocalDateTime;

import com.ridesync.enums.DriverStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="drivers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Driver {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OneToOne
	@JoinColumn(name="user_id")
	private User user;
	
	@Column
	private Double currentLat;
	
	@Column
	private Double currentLng;

	@Enumerated(EnumType.STRING)
	private DriverStatus status;
	
	@Column
	private Double rating;
	
	@Column
	private Double acceptanceRate;
	
	@Column
	private LocalDateTime lastLocationUpdateAt;
	
	public boolean isAvailableForMatch() {
		
		boolean isOnline =this.status==DriverStatus.ONLINE;
		boolean isLocationFresh = lastLocationUpdateAt !=null && Duration.between(lastLocationUpdateAt, LocalDateTime.now()).getSeconds()<=30;
		return isOnline && isLocationFresh;
	}
}
