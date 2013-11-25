/**
 * 
 */
package com.netflexity.qflex.business.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.ReplicationMode;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * Implements generic data access operations
 * 
 * @author FedorovM
 * 
 */
public class AbstractGenericsDao<T, ID extends Serializable> extends HibernateDaoSupport{

	protected Log logger = LogFactory.getLog(this.getClass());
	private Class<T> persistentClass;
	
    /**
     * Default constructor.
     */
    @SuppressWarnings("unchecked")
	public AbstractGenericsDao() {
		this.persistentClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}
    
    /**
     * @return
     */
    public Class<T> getPersistentClass() {
        return persistentClass;
    }
    
    /**
     * @param entity
     * @return
     */
    @SuppressWarnings("unchecked")
    public T save(T entity) {
        getHibernateTemplate().saveOrUpdate(entity);
        return entity;
    }

    @SuppressWarnings("unchecked")
    public T merge(T entity) {
        getHibernateTemplate().merge(entity);
        return entity;
    }

    @SuppressWarnings("unchecked")
    public void flush() {
        getHibernateTemplate().flush();
    }


    @SuppressWarnings("unchecked")
    public T replicate(T entity) {
        getHibernateTemplate().replicate(entity, ReplicationMode.OVERWRITE);
        return entity;
    }


    /**
     * @param entity
     */
    public void delete(T entity) {
        getHibernateTemplate().delete(entity);
    }

    /**
	 * @param id
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public T find(ID id) {
        return (T)getHibernateTemplate().get(persistentClass, id);
    }

	/**
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<T> findAll(String... orderBy) {
	    DetachedCriteria criteria = DetachedCriteria.forClass(persistentClass);
	    for (String col : orderBy) {
            criteria.addOrder(Order.asc(col));
        }
		return (List<T>) getHibernateTemplate().findByCriteria(criteria);
    }

	/**
	 * @param propertyName
	 * @param value
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<T> find(String propertyName, Object value) {
		DetachedCriteria criteria = DetachedCriteria.forEntityName(persistentClass.getName());
		criteria.add(Property.forName(propertyName).eq(value));
		return getHibernateTemplate().findByCriteria(criteria);
	}

	/**
	 * @param propertyNames
	 * @param values
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<T> find(String propertyNames[], Object values[]) {
		DetachedCriteria criteria = DetachedCriteria.forEntityName(persistentClass.getName());
		for(int i=0; i<propertyNames.length; ++i){
			criteria.add(Property.forName(propertyNames[i]).eq(values[i]));
		}
		return getHibernateTemplate().findByCriteria(criteria);
	}

	/**
	 * @param exampleInstance
	 * @param excludeProperty
	 * @return
	 */
	@SuppressWarnings("unchecked")
    public List<T> findByExample(T exampleInstance, String... excludeProperty) {
        DetachedCriteria crit = DetachedCriteria.forEntityName(persistentClass.getName());
        Example example =  Example.create(exampleInstance);
        for (String exclude : excludeProperty) {
            example.excludeProperty(exclude);
        }
        crit.add(example);
        return getHibernateTemplate().findByCriteria(crit);
    }

	/**
     * Use this inside subclasses as a convenience method.
     * @param criterion
     * @return
     */
    @SuppressWarnings("unchecked")
    protected List<T> findByCriteria(Order orders[], Criterion... criterion) {
        return findByCriteria(0, orders, criterion);
    }
    
    /**
     * Use this inside subclasses as a convenience method.
     * @param criterion
     * @return
     */
    @SuppressWarnings("unchecked")
    protected List<T> findByCriteria(Criterion... criterion) {
        return findByCriteria(0, null, criterion);
    }
    
    /**
     * @param rowLimit
     * @param orders
     * @param criterion
     * @return
     */
    @SuppressWarnings("unchecked")
    protected List<T> findByCriteria(int rowLimit, Order orders[], Criterion... criterion) {
        DetachedCriteria crit = DetachedCriteria.forEntityName(persistentClass.getName());
        for (Criterion c : criterion) {
            crit.add(c);
        }
        if(orders != null){
            for (Order order : orders) {
                crit.addOrder(order);
            }
        }
        return getHibernateTemplate().findByCriteria(crit, 0, rowLimit);
    }
    
    /**
     * @param criterion
     * @return
     */
    protected int countByCriteria(Criterion... criterion) {
        return (Integer)getProjection(Projections.rowCount(), criterion);
    }
   
    /**
     * @param projection
     * @param criterion
     * @return
     */
    protected Object getProjection(Projection projection, Criterion... criterion) {
        DetachedCriteria crit = DetachedCriteria.forEntityName(persistentClass.getName());
        for (Criterion c : criterion) {
            crit.add(c);
        }
        crit.setProjection(projection);
        return getHibernateTemplate().findByCriteria(crit).get(0);
    }
    
    /**
     * @param criterion
     * @return
     */
    protected T findSingleByCriteria(Criterion... criterion) {
        List<T> list = findByCriteria(1, null, criterion);
        if(list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }
    
    /**
     * @param queryName
     * @param paramName
     * @param value
     * @return
     */
    protected List<T> findByNamedQueryAndNamedParam(String queryName, String paramName, Object value){
        List<T> list = getHibernateTemplate().findByNamedQueryAndNamedParam(queryName, paramName, value);
        return list;
    }
    
    /**
     * @param queryName
     * @return
     */
    protected List<T> findByNamedQuery(String queryName){
        List<T> list = getHibernateTemplate().findByNamedQuery(queryName);
        return list;
    }
}
