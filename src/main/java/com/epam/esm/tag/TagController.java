package com.epam.esm.tag;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

/**
 * @author orest uzhytchak
 * */
@RestController
@RequestMapping(value = "/tag", produces = MediaType.APPLICATION_JSON_VALUE)
public class TagController {

    private final TagService tagService;

    public TagController( TagService tagService) {
        this.tagService = tagService;
    }

    /**
     * A controller post method for creating a new tag
     * in db, by calling a method of service layer
     * @param tag the Tag object that will be added in db,
     *            in case of passing validation
     * @see TagService#createTag(Tag)
     * */
    @PostMapping()
    public ResponseEntity<?> createTag(@RequestBody Tag tag){
        tagService.createTag(tag);
        return ResponseEntity.ok(Map.of("Status", HttpStatus.CREATED));
    }

    /**
     * A controller get method for getting all tags
     * that are stored in db, by calling a method of service layer
     * @see TagService#getTag()
     * */
    @GetMapping()
    public ResponseEntity<?> getAllTags(){
        return ResponseEntity.ok(Map.of("All tags", tagService.getTag()));
    }


    /**
     * A controller delete method for deleting a tag
     * from db by id and by calling a method of service layer
     * @param id the id of object that are going to be deleted
     * @see TagService#deleteTag(long)
     * */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTagById(@PathVariable("id") long id){
        tagService.deleteTag(id);
        return ResponseEntity.ok(Map.of("Status",HttpStatus.OK));
    }

}
