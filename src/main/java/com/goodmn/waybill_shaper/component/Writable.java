package com.goodmn.waybill_shaper.component;


import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

public interface Writable {
    void writeData(Workbook workbook);

    default Cell cell(Workbook workbook, int rowNum, int columnNum) {
        Sheet sheet = workbook.getSheetAt(0);
        Row row = sheet.getRow(rowNum);
        return row.getCell(columnNum);
    }
}