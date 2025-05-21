package com.goodmn.waybill_shaper.writer;


import org.apache.poi.ss.usermodel.Workbook;

public interface Writeable {
    void writeData(Workbook workbook);

    void setNext(Writeable next);
}