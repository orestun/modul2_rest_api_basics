package com.epam.esm.giftCertificate;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author orest uzhytchak
 * */
@RestController
@RequestMapping(value="/certificate", produces = MediaType.APPLICATION_JSON_VALUE)
public class GiftCertificateController {

    private final GiftCertificateService giftCertificateService;

    public GiftCertificateController(GiftCertificateService giftCertificateService) {
        this.giftCertificateService = giftCertificateService;
    }

    /**
     * A controller get method for getting all gift certificates
     * that are stored in db, by calling a method of service layer
     * @see GiftCertificateService#getAllGiftCertificates()
     * */
    @GetMapping()
    public ResponseEntity<?> getAllGiftCertificates(){
        return ResponseEntity.ok(Map.of("All gift certificates",giftCertificateService.getAllGiftCertificates()));
    }

    /**
     * A controller post method for creating a new gift certificate
     * , by calling a method of service layer
     * @param giftCertificate the object that will be added in db,
     *                        in case of passing validation
     * @see GiftCertificateService#createGiftCertificate(GiftCertificate)
     * */
    @PostMapping()
    public ResponseEntity<?> createNewGiftCertificate(@RequestBody GiftCertificate giftCertificate){
        giftCertificateService.createGiftCertificate(giftCertificate);
        return ResponseEntity.ok(Map.of("Status", HttpStatus.CREATED));
    }

    /**
     * A controller patch method for updating a gift certificate
     * , by calling a method of service layer
     * @param id the id of object that are going to be updated
     * @param giftCertificate the object that will change current object by id
     *
     * @see GiftCertificateService#updateGiftCertificate(long, GiftCertificate)
     * */
    @PatchMapping("/{id}")
    public ResponseEntity<?> updateGiftCertificate(@PathVariable("id") long id,
                                                   @RequestBody GiftCertificate giftCertificate){
        giftCertificateService.updateGiftCertificate( id, giftCertificate);
        return ResponseEntity.ok(Map.of("Status", HttpStatus.OK));
    }

    /**
     * A controller delete method for deleting a gift certificate
     * , by calling a method of service layer with id
     * @param id the id of object that are going to be deleted
     *
     * @see GiftCertificateService#deleteGiftCertificate(long)
     * */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteGiftCertificate(@PathVariable("id") long id){
        giftCertificateService.deleteGiftCertificate(id);
        return ResponseEntity.ok(Map.of("Status",HttpStatus.OK));
    }
}
