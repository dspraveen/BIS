package com.bis.reporting.services;

import com.bis.domain.AlertConfig;
import com.bis.domain.AlertType;
import com.bis.domain.Group;
import com.bis.reporting.repository.AlertConfigRepository;
import com.bis.reporting.repository.AlertTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlertConfigurationServiceImpl implements AlertConfigurationService {

    private AlertConfigRepository alertConfigRepository;
    private AlertTypeRepository alertTypeRepository;

    @Autowired
    public AlertConfigurationServiceImpl(AlertTypeRepository alertTypeRepository, AlertConfigRepository alertConfigRepository) {
        this.alertTypeRepository = alertTypeRepository;
        this.alertConfigRepository = alertConfigRepository;
    }

    @Override
    public void save(AlertConfig alertConfig) {
        alertConfigRepository.save(alertConfig);
    }

    @Override
    public void update(AlertConfig alertConfig) {
        alertConfigRepository.update(alertConfig);
    }

    @Override
    public void delete(AlertConfig alertConfig) {
        alertConfigRepository.delete(alertConfig);
    }

    @Override
    public List<AlertConfig> getAll() {
        return alertConfigRepository.getAll();
    }

    @Override
    public List<AlertType> getAlertTypes() {
        return alertTypeRepository.getAll();
    }

    @Override
    public List<AlertConfig> getAlertConfigs(int alertTypeId) {
        AlertType alertType = alertTypeRepository.get(alertTypeId);
        return alertConfigRepository.getAlertConfigs(alertType);
    }

    @Override
    public AlertType getAlertType(int alertTypeId) {
        return alertTypeRepository.get(alertTypeId);
    }

    @Override
    public AlertConfig get(int alertConfigId) {
        return alertConfigRepository.get(alertConfigId);
    }
}
