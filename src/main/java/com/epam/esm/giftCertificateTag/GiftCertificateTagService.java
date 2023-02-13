package com.epam.esm.giftCertificateTag;

import com.epam.esm.db.SQLQueries;
import com.epam.esm.giftCertificate.GiftCertificate;
import com.epam.esm.giftCertificate.GiftCertificateMapper;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author orest uzhytchak
 * */
@Service
@EnableTransactionManagement
public class GiftCertificateTagService {
    private final GiftCertificateTagRepository giftCertificateTagRepository;

    public GiftCertificateTagService(GiftCertificateTagRepository giftCertificateTagRepository) {
        this.giftCertificateTagRepository = giftCertificateTagRepository;
    }

    /**
     * A service method for creating a new giftCertificateTag
     * , by calling a method of repository layer
     * @param giftCertificateTag the object that will be added in db
     * @see GiftCertificateTagRepository#createGiftCertificateTag(GiftCertificateTag)
     * */
    @Transactional
    public int createGiftCertificateTag(GiftCertificateTag giftCertificateTag){
        return giftCertificateTagRepository.createGiftCertificateTag(giftCertificateTag);
    }

    /**
     * A service method for getting all gift certificates by name
     * , by calling a method of repository layer
     * @param name a name or part of name for searching in db
     * @see GiftCertificateTagRepository#getGiftCertificateByName(String)
     * */
    public List<GiftCertificate> getGiftCertificateByName(String name){
        return  giftCertificateTagRepository.getGiftCertificateByName(name);
    }

    /**
     * A service method for getting all gift certificates by description
     * , by calling a method of repository layer
     * @param description a name or part of name for searching in db
     * @see GiftCertificateTagRepository#getGiftCertificateByDescription(String)
     * */
    public List<GiftCertificate> getGiftCertificateByDescription(String description){
        return  giftCertificateTagRepository.getGiftCertificateByDescription(description);
    }

    /**
     * A service method for getting sorted gift certificates list
     * by <strong>name</strong> and by calling a method of repository layer
     * @param asc a boolean variable that in case of true
     *           will return gift certificate list in ascending row, and
     *            in case of false in descending row
     * @see GiftCertificateTagRepository#getSortedGiftCertificateByName(boolean)
     * */
    public List<GiftCertificate> getSortedGiftCertificateByName(boolean asc){
        return giftCertificateTagRepository.getSortedGiftCertificateByName(asc);
    }

    /**
     * A service method for getting sorted gift certificates list
     * by <strong>creation date</strong> and by calling a method of repository layer
     * @param asc a boolean variable that in case of true
     *           will return gift certificate list in ascending row, and
     *            in case of false in descending row
     * @see GiftCertificateTagRepository#getSortedGiftCertificateByCreateDate(boolean)
     * */
    public List<GiftCertificate> getSortedGiftCertificateByCreateDate(boolean asc){
        return giftCertificateTagRepository.getSortedGiftCertificateByCreateDate(asc);
    }

    /**
     * A service method for getting sorted gift certificates list
     * by <strong>name</strong> and <strong>creation date</strong> in the time
     * and by calling a method of service layer
     * @param nameIsAsc boolean variable that in case of true
     *                  will return a sorted gift certificate list <strong>by name</strong>
     *                  in ascending row, and
     *                  in case of false in descending row
     * @param createDateIsAsc boolean variable that in case of true
     *                        will return a sorted gift certificate list <strong>by name</strong>
     *                        in ascending row, and
     *                        in case of false in descending row
     * @see GiftCertificateTagRepository#getSortedGiftCertificateByNameAndCreateDate(boolean, boolean)
     * */
    public List<GiftCertificate> getSortedGiftCertificateTagByNameAndCreateDate(boolean nameIsAsc, boolean createDateIsAsc){
        return giftCertificateTagRepository.getSortedGiftCertificateByNameAndCreateDate(nameIsAsc, createDateIsAsc);
    }
}
