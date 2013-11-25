package com.netflexity.qflex.business.dao;

import com.netflexity.qflex.domain.Qmanager;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Property;

public class QmanagerDaoImpl extends AbstractGenericsDao<Qmanager, Long> {

    protected static final Log log = LogFactory.getLog(QmanagerDaoImpl.class);
    public static final String QMANAGER_NM = "qmanagerNm";
    public static final String COMPANY_ID = "company.companyId";

    @SuppressWarnings("unchecked")
    public List<Qmanager> findQmanagers(String qmname) {
        DetachedCriteria criteria = DetachedCriteria.forEntityName(Qmanager.class.getName());
        if (qmname != null) {
            criteria.add(Property.forName(QMANAGER_NM).eq(qmname));
        }
        return getHibernateTemplate().findByCriteria(criteria);
//        if (qmname != null) {
//            DetachedCriteria criteria = DetachedCriteria.forEntityName(Qmanager.class.getName());
//            criteria.add(Property.forName(QMANAGER_NM).eq(qmname));
//            return getHibernateTemplate().findByCriteria(criteria);
//        }
//        return findAll(QMANAGER_NM);
}

}
