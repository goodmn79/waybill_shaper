package com.goodmn.waybill_shaper.extractor;

import com.goodmn.waybill_shaper.model.Time;
import com.goodmn.waybill_shaper.service.DataExtractionUtility;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

import static com.goodmn.waybill_shaper.constant.Constant.*;

@Component
@RequiredArgsConstructor
public class TimeExtractor implements Extractable<Time> {
    private final DataExtractionUtility dataExtractionUtility;

    private final Logger log = LoggerFactory.getLogger(TimeExtractor.class);

    @Override
    public Time extractData() {
        log.debug("Извлечение данных о времени заказа...");

        List<String> orderTime = extractTime(dataExtractionUtility.getOrderElement(TIME));
        log.debug("КОЛИЧЕСТВО ЭЛЕМЕНТОВ СПИСКА ТАЙМИНГОВ: {}", orderTime.size());
        Time time = new Time();

        if (orderTime.isEmpty()) {
            return time
                    .setDepartureTime(EMPTY_STRING)
                    .setStartBreak(EMPTY_STRING)
                    .setEndBreak(EMPTY_STRING)
                    .setArrivalTime(EMPTY_STRING);
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

    private List<String> extractTime(List<String> orderDataList) {
        log.debug("СПИСОК С ДАННЫМИ О ВРЕМЕНИ ЗАКАЗА: '{}'.", orderDataList);

        List<String> orderTime = orderDataList
                .stream()
                .map(t -> t.replaceAll(TIME, SPACE).trim())
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
