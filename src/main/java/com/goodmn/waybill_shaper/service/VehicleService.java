package com.goodmn.waybill_shaper.service;

import com.goodmn.waybill_shaper.model.Vehicle;
import com.goodmn.waybill_shaper.repository.VehicleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.goodmn.waybill_shaper.extractor.Constant.EMPTY_STRING;

@Service
@RequiredArgsConstructor
public class VehicleService {
    private final VehicleRepository vehicleRepository;

    public Vehicle findById(String registrationMark) {
        return vehicleRepository.findByRegistrationMark(registrationMark).orElse(new Vehicle()
                .setRegistrationMark(EMPTY_STRING)
                .setMark(EMPTY_STRING)
                .setType(EMPTY_STRING));
    }
}
