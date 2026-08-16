package com.ridesync.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.ridesync.enums.RideStatus;
import com.ridesync.exception.InvalidStateTransitionException;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "rides")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Ride {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "rider_id")
	private Rider rider;
	
	@ManyToOne
	@JoinColumn(name="driver_id", nullable = true)
	private Driver driver;
	
	@Column(nullable =false)
	private Double pickupLat;
	
	@Column(nullable = false)
	private Double pickupLng;
	
	@Column(nullable = false)
	private Double dropLat;
	
	@Column(nullable = false)
	private Double dropLng;
	
	@Enumerated(EnumType.STRING)
	private RideStatus status;
	
	@Column(precision = 10,scale = 2)
	private BigDecimal fare;
	
	@Column
	private LocalDateTime requestedAt;
	@Column
	private LocalDateTime matchedAt;
	@Column
	private LocalDateTime completedAt;
	@Version
	private Long version;
	
	public void transitionTo(RideStatus newStatus) {
		boolean isValid = switch(this.status) {
		case REQUESTED ->newStatus == RideStatus.MATCHED ||newStatus == RideStatus.CANCELLED;
		case MATCHED -> newStatus == RideStatus.DRIVER_ARRIVED || newStatus == RideStatus.CANCELLED;
		case DRIVER_ARRIVED -> newStatus == RideStatus.IN_PROGRESS || newStatus == RideStatus.CANCELLED;
		case IN_PROGRESS -> newStatus == RideStatus.COMPLETED;
		case COMPLETED, CANCELLED ->false;	
				
		};
		
		if(!isValid) {
			throw new InvalidStateTransitionException("Invalid transition: "+ this.status +" -> " +newStatus
					);
		}
		
		this.status = newStatus;
	}
}
