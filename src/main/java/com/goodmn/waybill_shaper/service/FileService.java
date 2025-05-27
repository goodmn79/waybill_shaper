package com.goodmn.waybill_shaper.service;

import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public interface FileService {
    File saveToFile(Workbook workbook);

    void deleteFile();
}
