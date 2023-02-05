package com.epam.esm.giftCertificate;

import com.epam.esm.exceptionHandler.DataValidationHandler;
import com.epam.esm.exceptionHandler.ItemNotFoundException;
import com.epam.esm.exceptionHandler.ServerException;
import org.springframework.stereotype.Service;

import javax.validation.*;
import java.util.List;
import java.util.Set;

@Service
public class GiftCertificateService {


    private final GiftCertificateRepository giftCertificateRepository;

    public GiftCertificateService(GiftCertificateRepository giftCertificateRepository) {
        this.giftCertificateRepository = giftCertificateRepository;
    }


    public List<GiftCertificate> readAllGiftCertificates(){
        return giftCertificateRepository.readAllGiftCertificates();
    }

    public boolean createGiftCertificate(@Valid GiftCertificate giftCertificate){
        DataValidationHandler<GiftCertificate> dataValidationHandler = new DataValidationHandler<>();
        String errors = dataValidationHandler.errorsRepresentation(giftCertificate);
        if(!errors.isEmpty()){
            throw new ServerException(errors);
        }
        return giftCertificateRepository.createGiftCertificate(giftCertificate);
    }

    public boolean updateGiftCertificate(long id,
                                       @Valid GiftCertificate giftCertificate){
        DataValidationHandler<GiftCertificate> dataValidationHandler = new DataValidationHandler<>();
        String errors = dataValidationHandler.errorsRepresentation(giftCertificate);
        if(!errors.isEmpty()){
            throw new ServerException(errors);
        }
        return giftCertificateRepository.updateGiftCertificate(id,giftCertificate);
    }

    public boolean deleteGiftCertificate(long id){
        if (giftCertificateRepository.isNotGiftCertificateById(id)){
            throw new ItemNotFoundException(String.format("There is not such gift certificate with (id=%d)",id));
        }
        return giftCertificateRepository.deleteGiftCertificate(id);
    }


}
