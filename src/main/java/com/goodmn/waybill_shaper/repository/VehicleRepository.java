package com.goodmn.waybill_shaper.repository;

import com.goodmn.waybill_shaper.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, String> {
    Optional<Vehicle> findByRegistrationMark(String registrationMark);
}
