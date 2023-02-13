package com.epam.esm.tag;

import com.epam.esm.db.SQLQueries;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author orest uzhytchak
 * */
@Repository
public class TagRepository {

    private final JdbcTemplate jdbcTemplate;

    public TagRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * A repository method for creating a new tag
     * in db, by calling a query from {@link SQLQueries} class
     * and using jdbcTemplate
     * @param tag a <strong>Tag</strong> object that are going to be added in db
     * */
    public boolean createTag(Tag tag){
        return jdbcTemplate.update(SQLQueries.Tag.CREATE_TAG, tag.getName()) == 1;
    }

    /**
     * A repository method for getting a list of all tags
     * that are stored in db, by calling a query from {@link SQLQueries} class
     * and using jdbcTemplate
     * @return list with all tags that are stored in db
     * */
    public List<Tag> getTag(){
        return jdbcTemplate.query(SQLQueries.Tag.READ_TAG,new TagMapper());
    }

    /**
     * A repository method for deleting a tag
     * from db by id and by calling a query from {@link SQLQueries} class
     * and using jdbcTemplate
     * @param id the id of object that are going to be updated
     * */
    public boolean deleteTag(long id){
        return jdbcTemplate.update(SQLQueries.Tag.DELETE_TAG,id) == 1;
    }


    /**
     * A repository method that check checks if the tag exists
     * with such id, by calling a query from {@link SQLQueries} class
     * and using jdbcTemplate
     * @param id an id to check for existence
     * @return true if tag with given id is exists
     * */
    public boolean isNotTagBySuchId(long id){
        return jdbcTemplate.query(SQLQueries.Tag.GET_GIFT_TAG_BY_ID,new Object[]{id},new TagMapper()).isEmpty();
    }
}
