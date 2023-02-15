package com.epam.esm.repositories;


import com.epam.esm.mappers.GiftCertificateMapper;
import com.epam.esm.models.GiftCertificate;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;
import java.util.ResourceBundle;

/**
 * @author orest uzhytchak
 * */
@Repository
public class GiftCertificateRepository {


    private final JdbcTemplate jdbcTemplate;

    public GiftCertificateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    ResourceBundle sql = ResourceBundle.getBundle("sql-queries");

    /**
     * A repository method for getting a list of all gift certificates
     * that are stored in db, by calling a query from properties file with sql queries
     * and using jdbcTemplate
     * @return list with all gift certificates that are stored in db
     * */
    public List<GiftCertificate> getAllGiftCertificates(){
        return jdbcTemplate.query(
                sql.getString("query.certificate.GET_ALL_GIFT_CERTIFICATES"),
                new GiftCertificateMapper());
    }

    public GiftCertificate getGiftCertificateById(long id){
        return jdbcTemplate.query(
                sql.getString("query.certificate.GET_GIFT_CERTIFICATE_BY_ID"),
                        new Object[]{id},
                        new GiftCertificateMapper()).
                stream().
                findAny().
                get();
    }

    /**
     * A repository method that check checks if a certificate exists
     * with such id, by calling a query from properties file with sql queries
     * and using jdbcTemplate
     * @param id an id to check for existence
     * @return true if gift certificate with given id is exists
     * */
    public boolean isNotGiftCertificateById(long id){
        return jdbcTemplate.query(
                sql.getString("query.certificate.GET_GIFT_CERTIFICATE_BY_ID"),
                new Object[]{id},
                new GiftCertificateMapper()).isEmpty();
    }

    /**
     * A repository method that create a new gift certificate in db
     * , by calling a query from properties file with sql queries
     * and using jdbcTemplate
     * @param giftCertificate a <strong>GiftCertificate</strong> object that are going to be added in db
     * */
    public GiftCertificate createGiftCertificate(GiftCertificate giftCertificate){
        long millis = System.currentTimeMillis();
        Date currentDate = new Date(millis);
        jdbcTemplate.update(
                sql.getString("query.certificate.CREATE_GIFT_CERTIFICATE"),
                giftCertificate.getName(),
                giftCertificate.getDescription(),
                giftCertificate.getPrice(),
                giftCertificate.getDuration(),
                currentDate,
                currentDate);
        return giftCertificate;
    }

    /**
     * A repository method that update a gift certificate from db
     * received by id, by calling a query from properties file with sql queries
     * and using jdbcTemplate
     * @param id the id of object that are going to be updated
     * @param giftCertificate the object that will change current object by id
     * */
    public boolean updateGiftCertificate(long id, GiftCertificate giftCertificate){
        long millis = System.currentTimeMillis();
        Date currentDate = new Date(millis);
        return jdbcTemplate.update(
                sql.getString("query.certificate.UPDATE_GIFT_CERTIFICATE"),
                giftCertificate.getName(),
                giftCertificate.getDescription(),
                giftCertificate.getPrice(),
                giftCertificate.getDuration(),
                currentDate,
                id)==1;
    }

    /**
     * A repository method that delete a gift certificate from db
     * by id,and by calling a query from properties file with sql queries
     * and using jdbcTemplate
     * @param id the id of object that are going to be deleted
     * */
    public boolean deleteGiftCertificate(long id){
        return jdbcTemplate.update(
                sql.getString("query.certificate.DELETE_GIFT_CERTIFICATE"),
                id) == 1;
    }

}
