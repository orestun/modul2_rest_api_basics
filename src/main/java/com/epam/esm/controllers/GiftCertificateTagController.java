package com.epam.esm.controllers;

import com.epam.esm.services.GiftCertificateTagService;
import com.epam.esm.models.GiftCertificateTag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author orest uzhytchak
 * */
@RestController
@RequestMapping(value = "/certificate_tag", produces = MediaType.APPLICATION_JSON_VALUE)
public class GiftCertificateTagController {

    private final GiftCertificateTagService giftCertificateTagService;

    public GiftCertificateTagController(GiftCertificateTagService giftCertificateTagService) {
        this.giftCertificateTagService = giftCertificateTagService;
    }

    /**
     * A controller post method for creating a new giftCertificateTag
     * , by calling a method of service layer
     * @param giftCertificateTag the object that will be added in db
     * @see GiftCertificateTagService#createGiftCertificateTag(GiftCertificateTag)
     * */
    @PostMapping()
    public ResponseEntity<?> createGiftCertificateTag(@RequestBody GiftCertificateTag giftCertificateTag){
        giftCertificateTagService.createGiftCertificateTag(giftCertificateTag);
        return ResponseEntity.ok(Map.of("Status", HttpStatus.CREATED));
    }

    /**
     * A controller get method for getting all gift certificates by name
     * , by calling a method of service layer
     * @param name a name or part of name for searching in db
     * @see GiftCertificateTagService#getGiftCertificateByName(String)
     * */
    @GetMapping("/all_by_name/{name}")
    public ResponseEntity<?> getGiftCertificateByName(@PathVariable String name){
        return ResponseEntity.ok(Map.of("Gift certificates with name like: '"+name+"'",
                giftCertificateTagService.getGiftCertificateByName(name)));
    }

    /**
     * A controller get method for getting all gift certificates by description
     * , by calling a method of service layer
     * @param description a description or part of description for searching in db
     * @see GiftCertificateTagService#getGiftCertificateByDescription(String)
     * */
    @GetMapping("/all_by_description/{description}")
    public ResponseEntity<?> getGiftCertificateByDescription(@PathVariable String description){
        return ResponseEntity.ok(Map.of("Gift certificates with name like: '"+description+"'",
                giftCertificateTagService.getGiftCertificateByDescription(description)));
    }

    /**
     * A controller get method for getting sorted gift certificates list
     * by <strong>name</strong> and by calling a method of service layer
     * @param asc a boolean variable that in case of true
     *           will return gift certificate list in ascending row, and
     *            in case of false in descending row
     * @see GiftCertificateTagService#getSortedGiftCertificateByName(boolean)
     * */
    @GetMapping("/sorting/name/{asc}")
    public ResponseEntity<?> getSortedGiftCertificateByName(@PathVariable boolean asc){
        if(asc){
            return ResponseEntity.ok(Map.of("Sorted gift certificates by name in ascending row",
                    giftCertificateTagService.getSortedGiftCertificateByName(true)));
        }else{
            return ResponseEntity.ok(Map.of("Sorted gift certificates by name in descending row",
                    giftCertificateTagService.getSortedGiftCertificateByName(false)));
        }
    }

    /**
     * A controller get method for getting sorted gift certificates list
     * by <strong>creation date</strong> and by calling a method of service layer
     * @param asc a boolean variable that in case of true
     *           will return gift certificate list in ascending row, and
     *            in case of false in descending row
     * @see GiftCertificateTagService#getSortedGiftCertificateByCreateDate(boolean)
     * */
    @GetMapping("/sorting/create_date/{asc}")
    public ResponseEntity<?> getSortedGiftCertificateByCreateDate(@PathVariable boolean asc){
        if(asc){
            return ResponseEntity.ok(Map.of("Sorted gift certificates by create date in ascending row",
                    giftCertificateTagService.getSortedGiftCertificateByCreateDate(true)));
        }else{
            return ResponseEntity.ok(Map.of("Sorted gift certificates by create date in descending row",
                    giftCertificateTagService.getSortedGiftCertificateByCreateDate(false)));
        }
    }

    /**
     * A controller get method for getting sorted gift certificates list
     * by <strong>name</strong> and <strong>creation date</strong> in the time
     * and by calling a method of service layer
     * @param nameIsAsc boolean variable that in case of true
     *                  will return a sorted gift certificate list <strong>by name</strong>
     *                  in ascending row, and
     *                  in case of false in descending row
     * @param createDateIsAsc boolean variable that in case of true
     *                        will return a sorted gift certificate list <strong>by name</strong>
     *                        in ascending row, and
     *                        in case of false in descending row
     * @see GiftCertificateTagService#getSortedGiftCertificateTagByNameAndCreateDate(boolean, boolean)
     * */
    @GetMapping("/sorting")
    public ResponseEntity<?> getSortedGiftCertificateTagByNameAndCreateDate(@RequestParam("name_asc") boolean nameIsAsc, @RequestParam("create_date_asc") boolean createDateIsAsc){
        String massage = "Sorted gift certificates by name in %s row, and create date in %s row";
        String nameParam;
        String createDateParam;
        if(nameIsAsc){
            nameParam = "ASC";
        }else{
            nameParam = "DESC";
        }
        if(createDateIsAsc){
            createDateParam = "ASC";
        }else{
            createDateParam = "DESC";
        }
        return ResponseEntity.ok(Map.of(String.format(massage,nameParam,createDateParam),
                giftCertificateTagService.
                        getSortedGiftCertificateTagByNameAndCreateDate(nameIsAsc, createDateIsAsc)));
    }

}
