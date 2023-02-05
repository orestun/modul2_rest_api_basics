package com.epam.esm.giftCertificate;


import com.epam.esm.db.SQLQueries;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public class GiftCertificateRepository {


    private final JdbcTemplate jdbcTemplate;

    public GiftCertificateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<GiftCertificate> readAllGiftCertificates(){
        return jdbcTemplate.query(SQLQueries.GiftCertificate.READ_ALL_GIFT_CERTIFICATES, new GiftCertificateMapper());
    }

    public boolean isNotGiftCertificateById(long id){
        return jdbcTemplate.query(SQLQueries.GiftCertificate.GET_GIFT_CERTIFICATE_BY_ID,
                new Object[]{id}, new GiftCertificateMapper()).isEmpty();
    }

    public boolean createGiftCertificate(GiftCertificate giftCertificate){
        long millis = System.currentTimeMillis();
        Date currentDate = new Date(millis);
        jdbcTemplate.update(SQLQueries.GiftCertificate.CREATE_GIFT_CERTIFICATE,
                giftCertificate.getName(),
                giftCertificate.getDescription(),
                giftCertificate.getPrice(),
                giftCertificate.getDuration(),
                currentDate,
                currentDate);
        return true;
    }

    public boolean updateGiftCertificate(long id, GiftCertificate giftCertificate){
        long millis = System.currentTimeMillis();
        Date currentDate = new Date(millis);
        return jdbcTemplate.update(SQLQueries.GiftCertificate.UPDATE_GIFT_CERTIFICATE,
                giftCertificate.getName(),
                giftCertificate.getDescription(),
                giftCertificate.getPrice(),
                giftCertificate.getDuration(),
                currentDate,
                id) == 1;
    }

    public boolean deleteGiftCertificate(long id){
        return jdbcTemplate.update(SQLQueries.GiftCertificate.DELETE_GIFT_CERTIFICATE,id) == 1;
    }

}
