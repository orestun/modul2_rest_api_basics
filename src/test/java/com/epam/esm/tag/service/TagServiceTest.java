package com.epam.esm.tag.service;

import com.epam.esm.exceptionHandler.ItemNotFoundException;
import com.epam.esm.exceptionHandler.ServerException;
import com.epam.esm.tag.Tag;
import com.epam.esm.tag.TagRepository;
import com.epam.esm.tag.TagService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

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
        List<Tag> tagListFromService = tagService.readTag();
        List<Tag> tagListFromRepo = tagRepository.readTag();
        assertEquals(tagListFromRepo, tagListFromService);
    }

    @Test
    void dataValidationForNullValueInNameField(){
        Tag tag = new Tag(null);
        when(tagRepository.createTag(tag)).thenReturn(true);
        ServerException serverException = assertThrows(ServerException.class,() -> tagService.createTag(tag));
        assertEquals("Name field can't be null; ", serverException.getMessage());
    }

    @ParameterizedTest
    @CsvSource({
            "h",
            "ThisIsAPersonNamForMoreThen30Chars"
    })
    void dataValidationForShortOrLargeValueInNameField(String name){
        Tag tag = new Tag(name);
        when(tagRepository.createTag(tag)).thenReturn(true);
        ServerException serverException = assertThrows(ServerException.class,() -> tagService.createTag(tag));
        assertEquals("You should input name with length of characters from 2 to 30; ", serverException.getMessage());
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
        ItemNotFoundException itemNotFoundException = assertThrows(ItemNotFoundException.class,() -> tagService.deleteTag(id));
        assertEquals(String.format("There is not such tag with (id = %d)",id), itemNotFoundException.getMessage());
    }
}
