package com.bis.reporting.services;


import com.bis.domain.AlertConfig;
import com.bis.domain.AlertType;
import com.bis.domain.Group;

import java.util.List;

public interface AlertConfigurationService {

    List<AlertConfig> getAll();

    List<AlertType> getAlertTypes();

    List<AlertConfig> getAlertConfigs(int alertTypeId);

    AlertType getAlertType(int alertTypeId);

    AlertConfig get(int alertConfigId);
}
