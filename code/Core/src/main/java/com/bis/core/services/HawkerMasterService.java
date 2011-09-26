package com.bis.core.services;


import com.bis.domain.Hawker;

import java.util.List;

public interface HawkerMasterService {

    void create(Hawker hawker);
    void update(Hawker hawker);
    Hawker get(int hawkerId);
    List<Hawker> getAll();
}
