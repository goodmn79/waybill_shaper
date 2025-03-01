package com.goodmn.waybill_shaper.extractor;

import com.goodmn.waybill_shaper.model.Time;
import com.goodmn.waybill_shaper.service.DataExtractionUtility;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

import static com.goodmn.waybill_shaper.constant.Constant.EMPTY_STRING;
import static com.goodmn.waybill_shaper.constant.Constant.SPACE;
import static com.goodmn.waybill_shaper.constant.DataType.TIME;

@Component
@RequiredArgsConstructor
public class TimeExtractor implements Extractable<Time> {
    private final DataExtractionUtility dataExtractionUtility;

    private final Logger log = LoggerFactory.getLogger(TimeExtractor.class);

    @Override
    public Time extractData() {
        log.debug("Извлечение данных о времени заказа...");

        List<String> orderTime = extractTime(dataExtractionUtility.getOrderElement(TIME));
        log.debug("Количество элементов списка данных о времени заказа: {}", orderTime.size());
        Time time = new Time();

        if (orderTime.isEmpty()) {
            return Time.getDefault();
        } else if (orderTime.size() == 2) {
            return time
                    .setDepartureTime(orderTime.get(0))
                    .setStartBreak(EMPTY_STRING)
                    .setEndBreak(EMPTY_STRING)
                    .setArrivalTime(orderTime.get(1));
        } else {
            return time
                    .setDepartureTime(orderTime.get(0))
                    .setStartBreak(orderTime.get(1))
                    .setEndBreak(orderTime.get(2))
                    .setArrivalTime(orderTime.get(3));
        }
    }

    @Override
    public boolean isPresent(Time time) {
        return !Time.getDefault().equals(time);
    }

    private List<String> extractTime(List<String> orderDataList) {
        log.debug("Данные о времени заказа: '{}'.", orderDataList);

        List<String> orderTime = orderDataList
                .stream()
                .map(t -> t.replaceAll(TIME.getValue(), SPACE).trim())
                .flatMap(t -> Arrays.stream(t.split("-")))
                .toList();

        if (orderTime.isEmpty()) {
            log.debug("Данные о времени заказа не получены!");
        } else {
            log.debug("Данные о времени заказа получены успешно.");
        }
        return orderTime;
    }
}
