package com.ok100.greendao.gen;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import com.ok100.weather.bean.AllCityGreenBean;
import com.ok100.weather.bean.CityGreenDaoBean;

import com.ok100.greendao.gen.AllCityGreenBeanDao;
import com.ok100.greendao.gen.CityGreenDaoBeanDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig allCityGreenBeanDaoConfig;
    private final DaoConfig cityGreenDaoBeanDaoConfig;

    private final AllCityGreenBeanDao allCityGreenBeanDao;
    private final CityGreenDaoBeanDao cityGreenDaoBeanDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        allCityGreenBeanDaoConfig = daoConfigMap.get(AllCityGreenBeanDao.class).clone();
        allCityGreenBeanDaoConfig.initIdentityScope(type);

        cityGreenDaoBeanDaoConfig = daoConfigMap.get(CityGreenDaoBeanDao.class).clone();
        cityGreenDaoBeanDaoConfig.initIdentityScope(type);

        allCityGreenBeanDao = new AllCityGreenBeanDao(allCityGreenBeanDaoConfig, this);
        cityGreenDaoBeanDao = new CityGreenDaoBeanDao(cityGreenDaoBeanDaoConfig, this);

        registerDao(AllCityGreenBean.class, allCityGreenBeanDao);
        registerDao(CityGreenDaoBean.class, cityGreenDaoBeanDao);
    }
    
    public void clear() {
        allCityGreenBeanDaoConfig.clearIdentityScope();
        cityGreenDaoBeanDaoConfig.clearIdentityScope();
    }

    public AllCityGreenBeanDao getAllCityGreenBeanDao() {
        return allCityGreenBeanDao;
    }

    public CityGreenDaoBeanDao getCityGreenDaoBeanDao() {
        return cityGreenDaoBeanDao;
    }

}