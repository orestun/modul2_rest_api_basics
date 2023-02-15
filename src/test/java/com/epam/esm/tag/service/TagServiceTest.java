package com.epam.esm.tag.service;

import com.epam.esm.models.Tag;
import com.epam.esm.repositories.TagRepository;
import com.epam.esm.services.TagService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class TagServiceTest {

    @Mock
    TagRepository tagRepository;

    TagService tagService;

    @BeforeEach
    void setup(){
        tagService = new TagService(tagRepository);
    }

    @Test
    void getAllTag(){
        List<Tag> tagListFromService = tagService.getTag();
        List<Tag> tagListFromRepo = tagRepository.getTag();
        assertEquals(tagListFromRepo, tagListFromService);
    }

    @Test
    void dataValidationForNullValueInNameField(){
        Tag tag = new Tag(null);
        when(tagRepository.createTag(tag)).thenReturn(true);
        ResponseStatusException responseStatusException = assertThrows(ResponseStatusException.class,() -> tagService.createTag(tag));
        assertEquals("400 BAD_REQUEST \"Name field can't be null; \"", responseStatusException.getMessage());
    }

    @ParameterizedTest
    @CsvSource({
            "h",
            "ThisIsAPersonNamForMoreThen30Chars"
    })
    void dataValidationForShortOrLargeValueInNameField(String name){
        Tag tag = new Tag(name);
        when(tagRepository.createTag(tag)).thenReturn(true);
        ResponseStatusException responseStatusException = assertThrows(ResponseStatusException.class,() -> tagService.createTag(tag));
        assertEquals("400 BAD_REQUEST \"You should input name with length of characters from 2 to 30; \"", responseStatusException.getMessage());
    }


    @ParameterizedTest
    @CsvSource({
            "TestName1",
            "TestName2"
    })
    void successCreateTag(String name){
        Tag tag = new Tag(name);
        when(tagRepository.createTag(tag)).thenReturn(true);
        assertTrue(tagService.createTag(tag));
    }

    @ParameterizedTest
    @CsvSource({
            "32",
            "9"
    })
    void successDeleteTag(long id){
        when(tagRepository.deleteTag(id)).thenReturn(true);
        when(tagRepository.isNotTagBySuchId(id)).thenReturn(false);
        assertTrue(tagService.deleteTag(id));
    }


    @ParameterizedTest
    @CsvSource({
            "2094",
            "65",
            "543"
    })
    void deleteGiftCertificateByNonExistedId(long id){
        when(tagRepository.deleteTag(id)).thenReturn(true);
        when(tagRepository.isNotTagBySuchId(id)).thenReturn(true);
        ResponseStatusException responseStatusException = assertThrows(ResponseStatusException.class,() -> tagService.deleteTag(id));
        assertEquals(String.format("404 NOT_FOUND \"There is not such tag with (id=%d)\"",id), responseStatusException.getMessage());
    }
}
