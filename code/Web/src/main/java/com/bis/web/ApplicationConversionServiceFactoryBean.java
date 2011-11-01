package com.bis.web;

import org.springframework.core.convert.converter.Converter;
import org.springframework.format.FormatterRegistry;
import org.springframework.format.support.FormattingConversionServiceFactoryBean;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ApplicationConversionServiceFactoryBean extends FormattingConversionServiceFactoryBean {

    @Override
    protected void installFormatters(FormatterRegistry registry) {
        super.installFormatters(registry);
    }

    public void afterPropertiesSet() {
        super.afterPropertiesSet();
        installLabelConverters(getObject());
    }

    public void installLabelConverters(FormatterRegistry registry) {
        registry.addConverter(new Converter<String, Date>() {
            @Override
            public Date convert(String source) {
                try {
                    return new SimpleDateFormat("dd-MM-yyyy").parse(source);
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        registry.addConverter(new Converter<Date, String>() {
            @Override
            public String convert(Date source) {
                return new SimpleDateFormat("dd-MM-yyyy").format(source);

            }
        });
    }
}
