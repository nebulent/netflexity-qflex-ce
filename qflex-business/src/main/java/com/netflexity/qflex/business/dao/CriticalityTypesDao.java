package com.netflexity.qflex.business.dao;

import com.netflexity.qflex.domain.CriticalityTypes;
import java.util.List;

public class CriticalityTypesDao extends AbstractGenericsDao<CriticalityTypes, Long> {

    @SuppressWarnings("unchecked")
    public List<CriticalityTypes> findAllCriticalityTypes() {
        return findAll();
    }
}
