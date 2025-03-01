package com.goodmn.waybill_shaper.configuration;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStream;

@Configuration
@Scope("prototype")
public class WaybillTemplateConfig {

    @Bean
    public Workbook waybillTemplate() throws IOException {
        ClassPathResource resource = new ClassPathResource("templates/waybill_template.xlsm");
        try (InputStream is = resource.getInputStream()) {
            return new XSSFWorkbook(is);
        }
    }
}
