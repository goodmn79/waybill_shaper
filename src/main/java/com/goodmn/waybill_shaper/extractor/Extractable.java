package com.goodmn.waybill_shaper.extractor;

public interface Extractable<T> {
    T extractData();

    boolean isPresent(T t);
}
