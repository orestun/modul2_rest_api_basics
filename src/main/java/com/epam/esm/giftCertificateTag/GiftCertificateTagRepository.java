package com.epam.esm.giftCertificateTag;

import com.epam.esm.giftCertificate.GiftCertificate;
import com.epam.esm.db.SQLQueries;
import com.epam.esm.giftCertificate.GiftCertificateMapper;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author orest uzhytchak
 * */
@Repository
public class GiftCertificateTagRepository {

    private final JdbcTemplate jdbcTemplate;

    public GiftCertificateTagRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * A repository method that create a new giftCertificateTag in db
     * , by calling a query from {@link SQLQueries} class
     * and using jdbcTemplate
     * @param giftCertificateTag a <strong>GiftCertificateTag</strong> object that are going to be added in db
     * */
    public int createGiftCertificateTag(GiftCertificateTag giftCertificateTag){
        return jdbcTemplate.update(SQLQueries.GiftCertificateTag.CREATE_GIFT_CERTIFICATE_TAG,
                giftCertificateTag.getGiftCertificateId(),
                giftCertificateTag.getTagId());
    }

    /**
     * A repository method for getting all gift certificates by name
     * , by calling a query from {@link SQLQueries} class
     * @param name a name or part of name for searching in db
     * */
    public List<GiftCertificate> getGiftCertificateByName(String name){
        String paramName = "%"+name+"%";
        return  jdbcTemplate.query(SQLQueries.GiftCertificateTag.GET_GIFT_CERTIFICATES_BY_NAME,new Object[]{paramName}, new BeanPropertyRowMapper<>(GiftCertificate.class));
    }

    /**
     * A repository method for getting all gift certificates by description
     * , by calling a query from {@link SQLQueries} class
     * @param description a name or part of name for searching in db
     * */
    public List<GiftCertificate> getGiftCertificateByDescription(String description){
        String paramName = "%"+description+"%";
        return  jdbcTemplate.query(SQLQueries.GiftCertificateTag.GET_GIFT_CERTIFICATES_BY_DESCRIPTION,new Object[]{paramName}, new BeanPropertyRowMapper<>(GiftCertificate.class));
    }

    /**
     * A repository method for getting sorted gift certificates list
     * by <strong>name</strong> and by calling a query from {@link SQLQueries} class
     * @param asc a boolean variable that in case of true
     *           will return gift certificate list in ascending row, and
     *            in case of false in descending row
     * */
    public List<GiftCertificate> getSortedGiftCertificateByName(boolean asc){
        if(asc){
            return jdbcTemplate.query(SQLQueries.GiftCertificateTag.GET_SORTED_GIFT_CERTIFICATE_BY_NAME + " ASC",new GiftCertificateMapper());
        }else{
            return jdbcTemplate.query(SQLQueries.GiftCertificateTag.GET_SORTED_GIFT_CERTIFICATE_BY_NAME + " DESC",new GiftCertificateMapper());
        }
    }

    /**
     * A repository method for getting sorted gift certificates list
     * by <strong>creation date</strong> and by calling a query from {@link SQLQueries} class
     * @param asc a boolean variable that in case of true
     *           will return gift certificate list in ascending row, and
     *            in case of false in descending row
     * */
    public List<GiftCertificate> getSortedGiftCertificateByCreateDate(boolean asc){
        if(asc){
            return jdbcTemplate.query(SQLQueries.GiftCertificateTag.GET_SORTED_GIFT_CERTIFICATE_BY_CREATE_DATE + " ASC",new GiftCertificateMapper());
        }else{
            return jdbcTemplate.query(SQLQueries.GiftCertificateTag.GET_SORTED_GIFT_CERTIFICATE_BY_CREATE_DATE + " DESC",new GiftCertificateMapper());
        }
    }

    /**
     * A repository method for getting sorted gift certificates list
     * by <strong>name</strong> and <strong>creation date</strong> in the time
     * and by calling a query from {@link SQLQueries} class
     * @param nameIsAsc boolean variable that in case of true
     *                  will return a sorted gift certificate list <strong>by name</strong>
     *                  in ascending row, and
     *                  in case of false in descending row
     * @param createDateIsAsc boolean variable that in case of true
     *                        will return a sorted gift certificate list <strong>by name</strong>
     *                        in ascending row, and
     *                        in case of false in descending row
     * */
    public List<GiftCertificate> getSortedGiftCertificateByNameAndCreateDate(boolean nameIsAsc, boolean createDateIsAsc){
        String nameParam;
        String createDateParam;
        if(nameIsAsc){
            nameParam = "ASC";
        }else{
            nameParam = "DESC";
        }
        if(createDateIsAsc){
            createDateParam = "ASC";
        }else{
            createDateParam = "DESC";
        }
        return jdbcTemplate.query(String.format(SQLQueries.GiftCertificateTag.GET_SORTED_GIFT_CERTIFICATE_BY_NAME_AND_CREATE_DATE,
                nameParam,createDateParam),new GiftCertificateMapper());
    }
}
