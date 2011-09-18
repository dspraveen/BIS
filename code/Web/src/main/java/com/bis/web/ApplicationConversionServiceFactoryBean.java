package com.bis.web;

import org.springframework.format.FormatterRegistry;
import org.springframework.format.support.FormattingConversionServiceFactoryBean;

public class ApplicationConversionServiceFactoryBean extends FormattingConversionServiceFactoryBean {

    @Override
	protected void installFormatters(FormatterRegistry registry) {
		super.installFormatters(registry);
	}
}
