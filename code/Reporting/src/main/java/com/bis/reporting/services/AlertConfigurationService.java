package com.bis.reporting.services;


import com.bis.domain.AlertConfig;
import com.bis.domain.AlertType;
import com.bis.domain.Group;

import java.util.List;

public interface AlertConfigurationService {

    void save(AlertConfig alertConfig);
    void update(AlertConfig alertConfig);
    void delete(AlertConfig alertConfig);
    List<AlertConfig> getAll();
    List<AlertType> getAlertTypes();
    List<AlertConfig> getAlertConfigs(int alertTypeId);
    AlertType getAlertType(int alertTypeId);
    AlertConfig get(int alertConfigId);
}
