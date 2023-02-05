package com.epam.esm.giftCertificateTag;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(value = "/certificate_tag", produces = MediaType.APPLICATION_JSON_VALUE)
public class GiftCertificateTagController {

    private final GiftCertificateTagService giftCertificateTagService;

    public GiftCertificateTagController(GiftCertificateTagService giftCertificateTagService) {
        this.giftCertificateTagService = giftCertificateTagService;
    }


    @PostMapping()
    public ResponseEntity<?> createGiftCertificateTag(@RequestBody GiftCertificateTag giftCertificateTag){
        giftCertificateTagService.createGiftCertificateTag(giftCertificateTag);
        return ResponseEntity.ok(Map.of("Status", HttpStatus.CREATED));
    }

    @GetMapping("/all_by_name/{name}")
    public ResponseEntity<?> getGiftCertificateByName(@PathVariable String name){
        return ResponseEntity.ok(Map.of("Gift certificates with name like: '"+name+"'",
                giftCertificateTagService.getGiftCertificateByName(name)));
    }
    @GetMapping("/all_by_description/{description}")
    public ResponseEntity<?> getGiftCertificateByDescription(@PathVariable String description){
        return ResponseEntity.ok(Map.of("Gift certificates with name like: '"+description+"'",
                giftCertificateTagService.getGiftCertificateByDescription(description)));
    }

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
