package com.bis.web.viewmodel;

import com.bis.domain.Hawker;

import java.util.List;

public class HawkerList {
    private int selectedHawkerId;
    private List<Hawker> hawkers;

    public HawkerList(int selectedHawkerId, List<Hawker> hawkers) {
        this.selectedHawkerId = selectedHawkerId;
        this.hawkers = hawkers;
    }

    public int getSelectedHawkerId() {
        return selectedHawkerId;
    }

    public void setSelectedHawkerId(int selectedHawkerId) {
        this.selectedHawkerId = selectedHawkerId;
    }

    public List<Hawker> getHawkers() {
        return hawkers;
    }

    public Hawker getSelectedHawker() {
        for (Hawker hawker : hawkers) {
            if (hawker.getHawkerId().equals(selectedHawkerId)) {
                return hawker;
            }
        }
        return hawkers.get(0);
    }

    public int getCount() {
        return hawkers.size();
    }
}
