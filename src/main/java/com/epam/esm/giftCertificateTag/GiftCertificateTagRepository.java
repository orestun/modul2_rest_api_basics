package com.epam.esm.giftCertificateTag;

import com.epam.esm.giftCertificate.GiftCertificate;
import com.epam.esm.db.SQLQueries;
import com.epam.esm.giftCertificate.GiftCertificateMapper;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class GiftCertificateTagRepository {

    private final JdbcTemplate jdbcTemplate;

    public GiftCertificateTagRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int createGiftCertificateTag(GiftCertificateTag giftCertificateTag){
        return jdbcTemplate.update(SQLQueries.GiftCertificateTag.CREATE_GIFT_CERTIFICATE_TAG,
                giftCertificateTag.getGiftCertificateId(),
                giftCertificateTag.getTagId());
    }

    public List<GiftCertificate> getGiftCertificateByName(String name){
        String paramName = "%"+name+"%";
        return  jdbcTemplate.query(SQLQueries.GiftCertificateTag.GET_GIFT_CERTIFICATES_BY_NAME,new Object[]{paramName}, new BeanPropertyRowMapper<>(GiftCertificate.class));
    }
    public List<GiftCertificate> getGiftCertificateByDescription(String description){
        String paramName = "%"+description+"%";
        return  jdbcTemplate.query(SQLQueries.GiftCertificateTag.GET_GIFT_CERTIFICATES_BY_DESCRIPTION,new Object[]{paramName}, new BeanPropertyRowMapper<>(GiftCertificate.class));
    }
    public List<GiftCertificate> getSortedGiftCertificateByName(boolean asc){
        if(asc){
            return jdbcTemplate.query(SQLQueries.GiftCertificateTag.GET_SORTED_GIFT_CERTIFICATE_BY_NAME + " ASC",new GiftCertificateMapper());
        }else{
            return jdbcTemplate.query(SQLQueries.GiftCertificateTag.GET_SORTED_GIFT_CERTIFICATE_BY_NAME + " DESC",new GiftCertificateMapper());
        }
    }
    public List<GiftCertificate> getSortedGiftCertificateByCreateDate(boolean asc){
        if(asc){
            return jdbcTemplate.query(SQLQueries.GiftCertificateTag.GET_SORTED_GIFT_CERTIFICATE_BY_CREATE_DATE + " ASC",new GiftCertificateMapper());
        }else{
            return jdbcTemplate.query(SQLQueries.GiftCertificateTag.GET_SORTED_GIFT_CERTIFICATE_BY_CREATE_DATE + " DESC",new GiftCertificateMapper());
        }
    }

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
