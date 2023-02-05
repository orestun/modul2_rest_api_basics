package com.epam.esm.giftCertificate;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GiftCertificateMapper implements RowMapper<GiftCertificate> {

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
