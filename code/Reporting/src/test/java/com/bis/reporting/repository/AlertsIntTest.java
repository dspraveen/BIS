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

     /*
        Group group = new Group();
        group.setGroupName("test");
        group.setGroupText("test text");
        group.getGroupItems().add(new GroupItem() {{
            Hawker hawker = new Hawker();
            hawker.setHawkerId(1);
            Vendor vendor = new Vendor();
            vendor.setVendorId(3);
            Item item = new Item();
            item.setItemCode(1);
            setHawker(hawker);
            setVendor(vendor);
            setItem(item);
        }});
        group.getGroupItems().add(new GroupItem() {{
            Hawker hawker = new Hawker();
            hawker.setHawkerId(2);
            Vendor vendor = new Vendor();
            vendor.setVendorId(2);
            Item item = new Item();
            item.setItemCode(2);
            setHawker(hawker);
            setVendor(vendor);
            setItem(item);
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
        alertAssociationRepository.save(alertAssociation);  */
    }

    @Test
    public void shouldPerformCRUDOnAlert() {
        /*AlertType alertType = new AlertType();
        alertType.setAlertMessage("test message");
        alertType.setAlertName("test");
        alertTypeRepository.save(alertType);

        Alert alert = new Alert();
        alert.setAlertType(alertType);
        alert.setAlertStatus('A');
        alert.setAlertText("224234");
        alert.setAlertText("224234");
        alertRepository.save(alert);*/
    }

}
