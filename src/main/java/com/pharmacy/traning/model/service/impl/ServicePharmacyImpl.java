package com.pharmacy.traning.model.service.impl;

import com.pharmacy.traning.exception.DaoException;
import com.pharmacy.traning.exception.ServiceException;
import com.pharmacy.traning.model.dao.PharmacyDao;
import com.pharmacy.traning.model.dao.impl.PharmacyDaoImpl;
import com.pharmacy.traning.model.entity.Pharmacy;
import com.pharmacy.traning.model.service.ServicePharmacy;
import com.pharmacy.traning.model.validator.Validator;
import com.pharmacy.traning.model.validator.impl.ValidatorImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

/**
 * The type Service pharmacy.
 */
public class ServicePharmacyImpl implements ServicePharmacy {

    /**
     * The Logger.
     */
    private static final Logger logger = LogManager.getLogger();
    /**
     * The Instance.
     */
    private static ServicePharmacy instance;
    /**
     * The Pharmacy dao.
     */
    private static final PharmacyDao pharmacyDao = PharmacyDaoImpl.getInstance();
    /**
     * The Validator pharmacy.
     */
    private static final Validator validatorPharmacy = ValidatorImpl.getInstance();

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static ServicePharmacy getInstance() {
        if (instance == null){
            instance = new ServicePharmacyImpl();
        }
        return instance;
    }

    /**
     * Create pharmacy boolean.
     *
     * @param pharmacy the pharmacy
     * @param number   the number
     * @return the boolean
     * @throws ServiceException the service exception
     * @throws DaoException     the dao exception
     */
    @Override
    public boolean createPharmacy(Pharmacy pharmacy, String number) throws ServiceException, DaoException {
        if (validatorPharmacy.isName(pharmacy.getCity()) && validatorPharmacy.isName(pharmacy.getStreet())
                && validatorPharmacy.isOnlyNumber(number)){
            pharmacy.setNumber(Integer.parseInt(number));
            return pharmacyDao.createPharmacy(pharmacy);
        }
        logger.error("Incorrect input data!");
        throw new ServiceException("Incorrect input data!");
    }

    /**
     * Delete pharmacy boolean.
     *
     * @param id the id
     * @return the boolean
     * @throws ServiceException the service exception
     * @throws DaoException     the dao exception
     */
    @Override
    public boolean deletePharmacy(long id) throws ServiceException, DaoException {
        return pharmacyDao.deletePharmacy(id);
    }

    /**
     * Restore pharmacy boolean.
     *
     * @param id the id
     * @return the boolean
     * @throws ServiceException the service exception
     * @throws DaoException     the dao exception
     */
    @Override
    public boolean restorePharmacy(long id) throws ServiceException, DaoException {
        return pharmacyDao.restorePharmacy(id);
    }

    /**
     * Find all actual pharmacy list.
     *
     * @return the list
     * @throws ServiceException the service exception
     * @throws DaoException     the dao exception
     */
    @Override
    public List<Pharmacy> findAllActualPharmacy() throws ServiceException, DaoException {
        List<Pharmacy> pharmacyList = pharmacyDao.findAllActualPharmacy();
        if (pharmacyList.isEmpty()){
            logger.error("Pharmacy for issuing an order isn't added by administrator.");
            throw new ServiceException("Pharmacy for issuing an order isn't added by administrator.");
        }
        return pharmacyList;
    }

    /**
     * Find all pharmacy list.
     *
     * @return the list
     * @throws ServiceException the service exception
     * @throws DaoException     the dao exception
     */
    @Override
    public List<Pharmacy> findAllPharmacy() throws ServiceException, DaoException {
        return pharmacyDao.findAllPharmacy();
    }
}
