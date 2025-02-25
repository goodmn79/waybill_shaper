package com.goodmn.waybill_shaper.configuration;

import com.goodmn.waybill_shaper.extractor.Extractable;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class ExtractorConfiguration {
    private List<Extractable> extractors;
}
