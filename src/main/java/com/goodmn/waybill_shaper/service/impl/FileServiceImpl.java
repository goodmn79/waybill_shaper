package com.goodmn.waybill_shaper.service.impl;

import com.goodmn.waybill_shaper.service.FileService;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static com.goodmn.waybill_shaper.constant.Constant.FILE_NAME;

@Slf4j
@Service
public class FileServiceImpl implements FileService {
    private static final File file = new File(FILE_NAME);

    @Override
    public File saveToFile(Workbook workbook) {

        try (FileOutputStream out = new FileOutputStream(file)) {
            workbook.write(out);
        } catch (IOException e) {
            log.error(e.getMessage());
        }
        return file;
    }

    @Override
    public void deleteFile() {
        Path path = file.toPath();

        try {
            Files.delete(path);
            log.info("File was successfully deleted.");
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }
}
