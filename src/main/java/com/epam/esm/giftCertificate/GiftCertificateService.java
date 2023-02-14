package com.epam.esm.giftCertificate;

import com.epam.esm.exceptionHandler.DataValidationHandler;
import com.epam.esm.exceptionHandler.ItemNotFoundException;
import com.epam.esm.exceptionHandler.ServerException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.*;
import java.util.List;


/**
 * @author orest uzhytchak
 * */
@Service
@EnableTransactionManagement
public class GiftCertificateService {


    private final GiftCertificateRepository giftCertificateRepository;
    
    public GiftCertificateService(GiftCertificateRepository giftCertificateRepository) {
        this.giftCertificateRepository = giftCertificateRepository;
    }

    /**
     * A service method for getting a list of all gift certificates
     * that are stored in db, by calling a method of repository layer
     * @see GiftCertificateRepository#createGiftCertificate(GiftCertificate)
     * */
    public List<GiftCertificate> getAllGiftCertificates(){
        return giftCertificateRepository.getAllGiftCertificates();
    }

    /**
     * A service method for creating a new gift certificate
     * , by calling a method of service layer
     * @param giftCertificate the object that will be added in db,
     *                        in case of passing validation
     * @see GiftCertificateRepository#createGiftCertificate(GiftCertificate) 
     * */
    @Transactional
    public boolean createGiftCertificate(@Valid GiftCertificate giftCertificate){
        DataValidationHandler<GiftCertificate> dataValidationHandler = new DataValidationHandler<>();
        String errors = dataValidationHandler.errorsRepresentation(giftCertificate);
        if(!errors.isEmpty()){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    String.format(errors)
            );
        }
        return giftCertificateRepository.createGiftCertificate(giftCertificate);
    }

    /**
     * A service method for updating a gift certificate
     * , by calling a method of repository layer and id of gift certificate
     * @param id the id of object that are going to be updated
     * @param giftCertificate the object that will change current object by id
     *
     * @see GiftCertificateRepository#updateGiftCertificate(long, GiftCertificate) 
     * */
    @Transactional
    public boolean updateGiftCertificate(long id,
                                       @Valid GiftCertificate giftCertificate){
        DataValidationHandler<GiftCertificate> dataValidationHandler = new DataValidationHandler<>();
        String errors = dataValidationHandler.errorsRepresentation(giftCertificate);
        if (giftCertificateRepository.isNotGiftCertificateById(id)){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    String.format("There is not such gift certificate with (id=%d)",id)
            );
        }
        if(!errors.isEmpty()){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    String.format(errors)
            );
        }
        return giftCertificateRepository.updateGiftCertificate(id,giftCertificate);
    }

    /**
     * A service delete method for deleting a gift certificate
     * , by calling a method of repository layer with id
     * @param id the id of object that are going to be deleted
     *
     * @see GiftCertificateRepository#deleteGiftCertificate(long) 
     * */
    public boolean deleteGiftCertificate(long id){
        if (giftCertificateRepository.isNotGiftCertificateById(id)){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    String.format("There is not such gift certificate with (id=%d)",id)
            );
        }
        return giftCertificateRepository.deleteGiftCertificate(id);
    }


}
