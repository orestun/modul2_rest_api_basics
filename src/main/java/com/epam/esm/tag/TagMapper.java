package com.epam.esm.tag;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author orest uzhytchak
 * */
public class TagMapper implements RowMapper<Tag> {

    /**
     * A mapping method that get tag from database
     * and return tag object
     * @return tag object taken from db
     * */
    @Override
    public Tag mapRow(ResultSet resultSet, int i) throws SQLException {
        Tag tag = new Tag();
        tag.setId(resultSet.getInt("id"));
        tag.setName(resultSet.getString("name"));
        return tag;
    }
}
