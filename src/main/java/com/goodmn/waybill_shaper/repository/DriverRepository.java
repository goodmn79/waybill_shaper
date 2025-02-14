package com.goodmn.waybill_shaper.repository;

import com.goodmn.waybill_shaper.model.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DriverRepository extends JpaRepository<Driver, Long> {
}
