package com.epam.esm.tag;


import com.epam.esm.exceptionHandler.ItemNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping(value = "/tag", produces = MediaType.APPLICATION_JSON_VALUE)
public class TagController {

    private final TagService tagService;

    public TagController( TagService tagService) {
        this.tagService = tagService;
    }

    @PostMapping()
    public ResponseEntity<?> createTag(@RequestBody Tag tag){
        tagService.createTag(tag);
        return ResponseEntity.ok(Map.of("Status", HttpStatus.CREATED));
    }

    @GetMapping()
    public ResponseEntity<?> getAllTags(){
        return ResponseEntity.ok(Map.of("All tags", tagService.readTag()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTagById(@PathVariable("id") long id){
        tagService.deleteTag(id);
        return ResponseEntity.ok(Map.of("Status",HttpStatus.OK));
    }

}
