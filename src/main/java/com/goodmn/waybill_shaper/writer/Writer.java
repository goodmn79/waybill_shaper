package com.goodmn.waybill_shaper.writer;


import com.goodmn.waybill_shaper.storage.DataStorage;
import org.apache.poi.ss.usermodel.Workbook;

public interface Writer {
    void writeData(Workbook workbook, DataStorage storage);

    void setNext(Writer next);
}