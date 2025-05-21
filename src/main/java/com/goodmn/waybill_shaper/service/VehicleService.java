package com.goodmn.waybill_shaper.service;

import com.goodmn.waybill_shaper.dto.Vehicle;
import com.goodmn.waybill_shaper.repository.VehicleRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VehicleService {
    private static final Logger log = LoggerFactory.getLogger(VehicleService.class);
    private final VehicleRepository vehicleRepository;

    public Vehicle getByRegistrationMark(String registrationMark) {
        log.info("Получение данных о транспортном средстве с государственным регистрационным знаком: \"{}\"", registrationMark);

        Optional<Vehicle> vehicle = vehicleRepository.findByRegistrationMark(registrationMark);

        if (vehicle.isPresent()) {
            log.info("Данные транспортного средства успешно получены.");
            return vehicle.get();
        } else {
            log.error("Данные транспортного средства не получены!");
            return Vehicle.getDefault();
        }
    }
}
