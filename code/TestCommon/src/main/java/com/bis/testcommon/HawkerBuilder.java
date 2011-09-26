package com.bis.testcommon;

import com.bis.domain.Hawker;

public class HawkerBuilder {
    private Hawker hawker = new Hawker();

    public Hawker build() {
        return this.hawker;
    }

    public HawkerBuilder withDefaults() {
        hawker.setHawkerName("hawkerName");
        hawker.setHawkerDiscount(10.0F);
        hawker.setBillingCycle('W');
        hawker.setAddress("Address");
        hawker.setPhoneNumber("phone");
        hawker.setAlternatePhone("alternatePhone");
        return this;
    }

    public HawkerBuilder setName(String name) {
        hawker.setHawkerName(name);
        return this;
    }

    public HawkerBuilder setHawkerId(int hawkerId) {
        hawker.setHawkerId(hawkerId);
        return this;
    }
}
