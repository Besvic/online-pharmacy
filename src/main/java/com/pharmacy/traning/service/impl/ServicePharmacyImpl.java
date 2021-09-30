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
import com.pharmacy.traning.validator.ValidatorPharmacy;
import com.pharmacy.traning.validator.impl.ValidatorPharmacyImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

public class ServicePharmacyImpl implements ServicePharmacy {

    private static final Logger logger = LogManager.getLogger();
    private static ServicePharmacy instance;
    private static final PharmacyDao pharmacyDao = PharmacyDaoImpl.getInstance();
    private static final ValidatorPharmacy validatorPharmacy = ValidatorPharmacyImpl.getInstance();

    public static ServicePharmacy getInstance() {
        if (instance == null){
            instance = new ServicePharmacyImpl();
        }
        return instance;
    }

    @Override
    public boolean createPharmacy(Optional<Pharmacy> pharmacy) throws ServiceException, DaoException {
        if (pharmacy.isPresent() && validatorPharmacy.isOnlyLetter(pharmacy.get().getCity())
                && validatorPharmacy.isOnlyLetter(pharmacy.get().getStreet())){
            return pharmacyDao.createPharmacy(pharmacy.get());
        }
        logger.error("Incorrect input data!");
        throw new ServiceException("Incorrect input data!");
    }

    @Override
    public boolean deletePharmacy(long id) throws ServiceException, DaoException {
        return pharmacyDao.deletePharmacy(id);
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

    @Override
    public List<Pharmacy> findAllPharmacy() throws ServiceException, DaoException {
        return pharmacyDao.findAllPharmacy();
    }
}
