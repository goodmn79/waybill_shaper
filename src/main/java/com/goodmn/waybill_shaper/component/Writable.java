package com.goodmn.waybill_shaper.component;


import com.pengrad.telegrambot.model.Message;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

public interface Writable {
    void writeData(Message message, Workbook workbook);

    default Cell getCell(Workbook workbook, int sheetNum, int rowNum, int columnNum) {
        Sheet sheet = workbook.getSheetAt(sheetNum);
        Row row = sheet.getRow(rowNum);
        return row.getCell(columnNum);
    }
}