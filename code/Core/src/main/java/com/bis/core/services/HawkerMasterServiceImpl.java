package com.bis.core.services;

import com.bis.core.repository.HawkerRepository;
import com.bis.domain.Hawker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HawkerMasterServiceImpl implements HawkerMasterService{

    private HawkerRepository hawkerRepository;

    @Autowired
    public HawkerMasterServiceImpl(HawkerRepository hawkerRepository) {
        this.hawkerRepository = hawkerRepository;
    }

    @Override
    public void create(Hawker hawker) {
        hawkerRepository.save(hawker);
    }

    @Override
    public void update(Hawker hawker) {
        hawkerRepository.update(hawker);
    }

    @Override
    public Hawker get(int hawkerId) {
        return hawkerRepository.get(hawkerId);
    }

    @Override
    public List<Hawker> getAll() {
        return hawkerRepository.getAll();
    }
}
