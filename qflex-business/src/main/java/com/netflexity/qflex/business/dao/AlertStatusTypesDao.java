package com.netflexity.qflex.business.dao;

import com.netflexity.qflex.domain.AlertStatusTypes;
import java.util.List;

public class AlertStatusTypesDao extends AbstractGenericsDao<AlertStatusTypes, Long> {
    @SuppressWarnings("unchecked")
    public List<AlertStatusTypes> findAllAlertStatusTypes() {
        return findAll();
    }
}
