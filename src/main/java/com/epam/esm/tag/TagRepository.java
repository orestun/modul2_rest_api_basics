package com.epam.esm.tag;

import com.epam.esm.db.SQLQueries;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TagRepository {

    private final JdbcTemplate jdbcTemplate;

    public TagRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public boolean createTag(Tag tag){
        return jdbcTemplate.update(SQLQueries.Tag.CREATE_TAG, tag.getName()) == 1;
    }

    public List<Tag> readTag(){
        return jdbcTemplate.query(SQLQueries.Tag.READ_TAG,new TagMapper());
    }

    public boolean deleteTag(long id){
        return jdbcTemplate.update(SQLQueries.Tag.DELETE_TAG,id) == 1;
    }

    public boolean isNotTagBySuchId(long id){
        return jdbcTemplate.query(SQLQueries.Tag.GET_GIFT_TAG_BY_ID,new Object[]{id},new TagMapper()).isEmpty();
    }
}
