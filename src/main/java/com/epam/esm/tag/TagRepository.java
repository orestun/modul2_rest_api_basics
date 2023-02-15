package com.epam.esm.tag;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.ResourceBundle;

/**
 * @author orest uzhytchak
 * */
@Repository
public class TagRepository {

    private final JdbcTemplate jdbcTemplate;

    ResourceBundle sql = ResourceBundle.getBundle("sql-queries");

    public TagRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * A repository method for creating a new tag
     * in db, by calling a query from properties file with sql queries
     * and using jdbcTemplate
     * @param tag a <strong>Tag</strong> object that are going to be added in db
     * */
    public boolean createTag(Tag tag){
        return jdbcTemplate.update(
                sql.getString("query.tag.CREATE_TAG"),
                tag.getName()) == 1;
    }

    /**
     * A repository method for getting a list of all tags
     * that are stored in db, by calling a query from properties file with sql queries
     * and using jdbcTemplate
     * @return list with all tags that are stored in db
     * */
    public List<Tag> getTag(){
        return jdbcTemplate.query(
                sql.getString("query.tag.GET_TAG"),
                new TagMapper());
    }

    /**
     * A repository method for deleting a tag
     * from db by id and by calling a query from properties file with sql queries
     * and using jdbcTemplate
     * @param id the id of object that are going to be updated
     * */
    public boolean deleteTag(long id){
        return jdbcTemplate.update(
                sql.getString("query.tag.DELETE_TAG"),
                id) == 1;
    }


    /**
     * A repository method that check checks if the tag exists
     * with such id, by calling a query from properties file with sql queries
     * and using jdbcTemplate
     * @param id an id to check for existence
     * @return true if tag with given id is exists
     * */
    public boolean isNotTagBySuchId(long id){
        return jdbcTemplate.query(
                sql.getString("query.tag.GET_TAG_BY_ID"),
                new Object[]{id},
                new TagMapper()).isEmpty();
    }
}
