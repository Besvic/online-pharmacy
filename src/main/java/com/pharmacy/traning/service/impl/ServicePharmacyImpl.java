package com.pharmacy.traning.service.impl;

import com.pharmacy.traning.exception.DaoException;
import com.pharmacy.traning.exception.ServiceException;
import com.pharmacy.traning.model.dao.OrderDao;
import com.pharmacy.traning.model.dao.PharmacyDao;
import com.pharmacy.traning.model.dao.impl.OrderDaoImpl;
import com.pharmacy.traning.model.dao.impl.PharmacyDaoImpl;
import com.pharmacy.traning.model.entity.Pharmacy;
import com.pharmacy.traning.service.ServiceOrder;
import com.pharmacy.traning.service.ServicePharmacy;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class ServicePharmacyImpl implements ServicePharmacy {

    private static final Logger logger = LogManager.getLogger();
    private static ServicePharmacy instance;
    private static final PharmacyDao pharmacyDao = PharmacyDaoImpl.getInstance();

    public static ServicePharmacy getInstance() {
        if (instance == null){
            instance = new ServicePharmacyImpl();
        }
        return instance;
    }

    @Override
    public boolean createPharmacy(Pharmacy pharmacy) throws ServiceException {
        return false;
    }

    @Override
    public boolean deletePharmacy(long id) throws ServiceException {
        return false;
    }

    @Override
    public List<Pharmacy> findAllActualPharmacy() throws ServiceException, DaoException {
        List<Pharmacy> pharmacyList = pharmacyDao.findAllActualPharmacy();
        if (pharmacyList.isEmpty()){
            logger.error("Pharmacy for issuing an order isn't added by administrator.");
            throw new ServiceException("Pharmacy for issuing an order isn't added by administrator.");
        }
        return pharmacyList;
    }
}
