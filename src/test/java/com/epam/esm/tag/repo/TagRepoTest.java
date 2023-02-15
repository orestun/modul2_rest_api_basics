package com.epam.esm.tag.repo;

import com.epam.esm.models.Tag;
import com.epam.esm.repositories.TagRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TagRepoTest {
    private EmbeddedDatabase testDatabase;
    private TagRepository tagRepository;

    private final List<Tag> tagList = List.of(
            new Tag(1,"testTag1"),
            new Tag(2,"testTag2"),
            new Tag(3,"testTag3")
    );

    @BeforeEach
    void setup(){
        this.testDatabase = new EmbeddedDatabaseBuilder().
                setType(EmbeddedDatabaseType.H2).
                addScript("db/sql/create-db.sql").
                addScript("db/sql/tag/insert-tag.sql").
                build();
        this.tagRepository = new TagRepository(new NamedParameterJdbcTemplate(testDatabase).getJdbcTemplate());
    }

    @AfterEach
    void tearDown(){
        this.testDatabase.shutdown();
    }

    @Test
    void getAllTagsTest(){
        List<Tag> tagListFromDatabase = tagRepository.getTag();
        assertEquals(tagList, tagListFromDatabase);
    }

    @Test
    void createTagTest(){
        List<Tag> listWithNewTags = new java.util.ArrayList<>(tagList);
        listWithNewTags.add(new Tag(4,"testTag4"));
        tagRepository.createTag(new Tag("testTag4"));
        assertEquals(listWithNewTags, tagRepository.getTag());
        tagRepository.createTag(new Tag("testTag5"));
        assertNotEquals(listWithNewTags, tagRepository.getTag());
        listWithNewTags.add(new Tag(5, "testTag5"));
        assertEquals(listWithNewTags, tagRepository.getTag());
    }

    @Test
    void deleteTagTest(){
        List<Tag> listWitTags = new java.util.ArrayList<>(tagList);
        tagRepository.deleteTag(3);
        assertNotEquals(listWitTags, tagRepository.getTag());
        assertEquals(listWitTags.subList(0,2),tagRepository.getTag());
    }

    @ParameterizedTest
    @CsvSource({
            "34, true",
            "4, true",
            "1, false"
    })
    void isNotTagBySuchId(long id,boolean expected){
        assertEquals(expected, tagRepository.isNotTagBySuchId(id));
    }
}
