package com.epam.esm.giftCertificate;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping(value="/certificate", produces = MediaType.APPLICATION_JSON_VALUE)
public class GiftCertificateController {

    private final GiftCertificateService giftCertificateService;

    public GiftCertificateController(GiftCertificateService giftCertificateService) {
        this.giftCertificateService = giftCertificateService;
    }

    @GetMapping()
    public ResponseEntity<?> getAllGiftCertificates(){
        return ResponseEntity.ok(Map.of("All gift certificates",giftCertificateService.readAllGiftCertificates()));
    }

    @PostMapping()
    public ResponseEntity<?> createNewGiftCertificate(@RequestBody GiftCertificate giftCertificate){
        giftCertificateService.createGiftCertificate(giftCertificate);
        return ResponseEntity.ok(Map.of("Status", HttpStatus.CREATED));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updateGiftCertificate(@PathVariable("id") long id,
                                                   @RequestBody GiftCertificate giftCertificate){
        giftCertificateService.updateGiftCertificate( id, giftCertificate);
        return ResponseEntity.ok(Map.of("Status", HttpStatus.OK));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteGiftCertificate(@PathVariable("id") long id){
        giftCertificateService.deleteGiftCertificate(id);
        return ResponseEntity.ok(Map.of("Status",HttpStatus.OK));
    }
}
