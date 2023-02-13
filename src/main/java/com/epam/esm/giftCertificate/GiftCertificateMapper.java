package com.epam.esm.giftCertificate;

import com.epam.esm.db.SQLQueries;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author orest uzhytchak
 * */
public class GiftCertificateMapper implements RowMapper<GiftCertificate> {

    /**
     * A mapping method that get gift certificate from database
     * and return gift certificate object
     * @return gift certificate object taken from db
     * */
    @Override
    public GiftCertificate mapRow(ResultSet rs, int rowNum) throws SQLException {
        GiftCertificate giftCertificate = new GiftCertificate();
        giftCertificate.setId(rs.getInt("id"));
        giftCertificate.setName(rs.getString("name"));
        giftCertificate.setDescription(rs.getString("description"));
        giftCertificate.setPrice(rs.getBigDecimal("price"));
        giftCertificate.setDuration(rs.getInt("duration"));
        giftCertificate.setCreateDate(rs.getDate("create_date"));
        giftCertificate.setLastUpdateDate(rs.getDate("last_update_date"));
        return giftCertificate;
    }
}
