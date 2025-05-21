package com.goodmn.waybill_shaper.extractor;

public interface Extractor<T> {

    T extract(String messageText);
}
