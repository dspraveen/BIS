package com.bis.reporting.repository;

import com.bis.domain.Alert;
import com.bis.domain.AlertAssociation;
import com.bis.domain.AlertConfig;
import com.bis.domain.AlertType;
import com.bis.testcommon.BaseIntTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class AlertsIntTest extends BaseIntTest {

    @Autowired
    private AlertTypeRepository alertTypeRepository;

    @Autowired
    private AlertConfigRepository alertConfigRepository;

    @Autowired
    private AlertAssociationRepository alertAssociationRepository;

    @Autowired
    private AlertsRepository alertRepository;

    @Test
    public void shouldPerformCRUDOnAlertAssociation() {

        AlertAssociation alertAssociation = null;
        AlertConfig alertConfig = null;
        AlertType alertType = null;
        try {
            alertType = new AlertType();
            alertType.setAlertMessage("test message");
            alertType.setAlertName("test");
            alertTypeRepository.save(alertType);

            alertConfig = new AlertConfig();
            alertConfig.setAlertParameters("key=value");
            alertConfig.setAlertType(alertType);
            alertConfig.setDefaultConfig('Y');
            alertConfig.setAlertConfigName("default");
            alertConfigRepository.save(alertConfig);

            alertAssociation = new AlertAssociation();
            alertAssociation.setAlertConfig(alertConfig);
            alertAssociation.setItemId(1);
            alertAssociationRepository.save(alertAssociation);
        } catch(Exception e){
            e.printStackTrace();
        }finally {
            if (alertAssociation != null) alertAssociationRepository.delete(alertAssociation);
            if (alertConfig != null) alertConfigRepository.delete(alertConfig);
            if (alertType != null) alertTypeRepository.delete(alertType);
        }

    }

    @Test
    public void shouldPerformCRUDOnAlert() {
        Alert alert = null;
        AlertType alertType = null;
        try {
            alertType = new AlertType();
            alertType.setAlertMessage("test message");
            alertType.setAlertName("test");
            alertTypeRepository.save(alertType);

            alert = new Alert();
            alert.setAlertType(alertType);
            alert.setAlertStatus('A');
            alert.setAlertText("224234");
            alert.setAlertText("224234");
            alertRepository.save(alert);
        } finally {
            if (alert != null) alertRepository.delete(alert);
            if (alertType != null) alertTypeRepository.delete(alertType);
        }

    }

}
