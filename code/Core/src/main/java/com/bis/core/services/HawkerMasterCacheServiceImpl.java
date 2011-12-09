package com.bis.core.services;

import com.bis.common.BisCacheManager;
import com.bis.core.repository.HawkerRepository;
import com.bis.domain.Hawker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HawkerMasterCacheServiceImpl extends HawkerMasterServiceImpl {

    public static final String HAWKERS = "hawkers";
    private BisCacheManager bisCacheManager;

    @Autowired
    public HawkerMasterCacheServiceImpl(HawkerRepository hawkerRepository, BisCacheManager bisCacheManager) {
        super(hawkerRepository);
        this.bisCacheManager = bisCacheManager;
    }

    @Override
    public void create(Hawker hawker) {
        super.create(hawker);
        bisCacheManager.remove(HAWKERS);
    }

    @Override
    public void update(Hawker hawker) {
        super.update(hawker);
        bisCacheManager.remove(HAWKERS);
    }

    @Override
    public Hawker get(int hawkerId) {
        if (bisCacheManager.cached(HAWKERS)) {
            List<Hawker> hawkers = (List<Hawker>) bisCacheManager.get(HAWKERS);
            for (Hawker hawker : hawkers) {
                if (hawkerId == hawker.getHawkerId()) return hawker;
            }
        }
        return super.get(hawkerId);
    }

    @Override
    public List<Hawker> getAll() {
        if (bisCacheManager.notCached(HAWKERS)) {
            bisCacheManager.cache(HAWKERS, super.getAll());
        }
        return (List<Hawker>) bisCacheManager.get(HAWKERS);
    }
}
