package com.goodmn.waybill_shaper.configuration;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@Configuration
@Scope("prototype")
public class WaybillTemplateConfig {
    Logger log = LoggerFactory.getLogger(WaybillTemplateConfig.class);

    @Value("${server.port}")
    private int port;

    @Value("${application.waybill_template.path}")
    private String waybillTemplatePath;

    @Bean
    public Workbook waybillTemplate() throws IOException {
        log.info("Приложение запущено на порту: {}", port);

        try (InputStream is = new FileInputStream(waybillTemplatePath)) {
            return new XSSFWorkbook(is);
        }
    }
}
