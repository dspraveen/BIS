package com.bis.reporting.repository;

import com.bis.domain.*;
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

    @Autowired
    private GroupRepository groupRepository;



    @Test
    public void shouldPerformCRUDOnAlertAssociation() {


        Group group = new Group();
        group.setGroupName("test");
        group.setGroupText("test text");
        group.getGroupItems().add(new GroupItem() {{
            setHawkerId(1);
            setVendorId(1);
            setItemId(1);
        }});
        group.getGroupItems().add(new GroupItem() {{
            setHawkerId(2);
            setVendorId(2);
            setItemId(2);
        }});
        groupRepository.save(group);


        AlertType alertType = new AlertType();
        alertType.setAlertMessage("test message");
        alertType.setAlertName("test");
        alertTypeRepository.save(alertType);

        AlertConfig alertConfig = new AlertConfig();
        alertConfig.setAlertParameters("key=value");
        alertConfig.setAlertType(alertType);
        alertConfig.setDefaultConfig('Y');
        alertConfig.setAlertConfigName("default");
        alertConfigRepository.save(alertConfig);

        AlertAssociation alertAssociation = new AlertAssociation();
        alertAssociation.setAlertConfig(alertConfig);
        alertAssociation.setGroup(group);
        alertAssociationRepository.save(alertAssociation);
    }

    @Test
    public void shouldPerformCRUDOnAlert() {
        AlertType alertType = new AlertType();
        alertType.setAlertMessage("test message");
        alertType.setAlertName("test");
        alertTypeRepository.save(alertType);

        Alert alert = new Alert();
        alert.setAlertType(alertType);
        alert.setAlertStatus('A');
        alert.setAlertText("224234");
        alert.setAlertText("224234");
        alertRepository.save(alert);
    }

}
