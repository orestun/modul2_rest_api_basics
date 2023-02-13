package com.epam.esm.db;

/**
 * @author orest uzhytchak
 * Class that store the static nested classes
 * that store final static fields with SQL queries
 * that a used by repositories
 * */
public class SQLQueries {
    public static class GiftCertificate{
        public final static String CREATE_GIFT_CERTIFICATE = "INSERT INTO gift_certificate(name, description, price, duration, create_date, last_update_date) " +
            "VALUES (?, ?, ?, ?, ? ,?)";
        public final static String READ_ALL_GIFT_CERTIFICATES = "SELECT * FROM gift_certificate";
        public final static String UPDATE_GIFT_CERTIFICATE = "UPDATE gift_certificate " +
                "SET name= ?,description = ?, price = ?,duration = ?, last_update_date =? " +
                "WHERE id= ?";
        public final static String DELETE_GIFT_CERTIFICATE = "DELETE FROM gift_certificate " +
                "WHERE id=?";
        public final static String GET_GIFT_CERTIFICATE_BY_ID = "SELECT * FROM gift_certificate WHERE id=?";
    }

    public static class Tag{
        public final static String CREATE_TAG = "INSERT INTO tag(name) VALUES (?)";
        public final static String READ_TAG = "SELECT * FROM tag";
        public final static String DELETE_TAG =  "DELETE FROM tag WHERE id=?";
        public final static String GET_GIFT_TAG_BY_ID = "SELECT * FROM tag WHERE id=?";
    }

    public static class GiftCertificateTag{
        public final static String CREATE_GIFT_CERTIFICATE_TAG = "INSERT INTO gift_certificate_tag(gift_certificate_id, tag_id) VALUES (?, ?)";
        public final static String GET_GIFT_CERTIFICATES_BY_TAG_NAME = "SELECT gc.id, gc.name, gc.description, gc.price, gc.duration, gc.create_date, gc.last_update_date " +
                "FROM gift_certificate AS gc " +
                "JOIN gift_certificate_tag AS gct " +
                "JOIN tag AS t " +
                "ON gc.id = gct.gift_certificate_id AND t.id = gct.tag_id AND t.name = ?";
        public final static String GET_GIFT_CERTIFICATES_BY_NAME = "SELECT gc.id, gc.name, gc.description, gc.price, gc.duration, gc.create_date, gc.last_update_date " +
                "FROM gift_certificate AS gc " +
                "JOIN gift_certificate_tag AS gct " +
                "JOIN tag AS t " +
                "ON gc.id = gct.gift_certificate_id AND t.id = gct.tag_id AND gc.name LIKE ?";
        public final static String GET_GIFT_CERTIFICATES_BY_DESCRIPTION = "SELECT gc.id, gc.name, gc.description, gc.price, gc.duration, gc.create_date, gc.last_update_date " +
                "FROM gift_certificate AS gc " +
                "JOIN gift_certificate_tag AS gct " +
                "JOIN tag AS t " +
                "ON gc.id = gct.gift_certificate_id AND t.id = gct.tag_id AND gc.description LIKE ?";
        public final static String GET_SORTED_GIFT_CERTIFICATE_BY_NAME = "SELECT gc.id, gc.name, gc.description, gc.price, gc.duration, gc.create_date, gc.last_update_date " +
                "FROM gift_certificate AS gc " +
                "JOIN gift_certificate_tag AS gct " +
                "ON gc.id = gct.gift_certificate_id " +
                "order by gc.name";
        public final static String GET_SORTED_GIFT_CERTIFICATE_BY_CREATE_DATE = "SELECT gc.id, gc.name, gc.description, gc.price, gc.duration, gc.create_date, gc.last_update_date " +
                "FROM gift_certificate AS gc " +
                "JOIN gift_certificate_tag AS gct " +
                "ON gc.id = gct.gift_certificate_id " +
                "order by gc.create_date";

        public final static String GET_SORTED_GIFT_CERTIFICATE_BY_NAME_AND_CREATE_DATE = "SELECT gc.id, gc.name, gc.description, gc.price, gc.duration, gc.create_date, gc.last_update_date " +
                "FROM gift_certificate AS gc " +
                "JOIN gift_certificate_tag AS gct " +
                "ON gc.id = gct.gift_certificate_id " +
                "order by gc.name %s, gc.create_date %s";

    }
}
