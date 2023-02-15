package com.epam.esm.giftCertificate.service;

import com.epam.esm.exceptionHandler.ItemNotFoundException;
import com.epam.esm.exceptionHandler.ServerException;
import com.epam.esm.giftCertificate.GiftCertificate;
import com.epam.esm.giftCertificate.GiftCertificateRepository;
import com.epam.esm.giftCertificate.GiftCertificateService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.web.server.ResponseStatusException;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


import java.math.BigDecimal;
import java.util.List;


@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class GiftCertificateServiceTest {

    @Mock
    GiftCertificateRepository giftCertificateRepository;

    @InjectMocks
    GiftCertificateService giftCertificateService;

    @BeforeEach
    public void setup(){
        giftCertificateService = new GiftCertificateService(giftCertificateRepository);
    }


    @Test
    void getAllGiftCertificates(){
        List<GiftCertificate> giftCertificateListFromService = giftCertificateService.getAllGiftCertificates();
        List<GiftCertificate> giftCertificateListFromRepo = giftCertificateRepository.getAllGiftCertificates();
        assertEquals(giftCertificateListFromService, giftCertificateListFromRepo);
    }

    @ParameterizedTest
    @CsvSource({
            " , porsche - gift certificate, 1000, 90",
            "    , porsche - gift certificate, 300, 10"
    })
    void dataValidationForNullValueInNameField(String name, String description, BigDecimal price, int duration) {
        GiftCertificate giftCertificate = new GiftCertificate(name, description, price, duration);
        ResponseStatusException responseStatusException = assertThrows(ResponseStatusException.class,() -> giftCertificateService.createGiftCertificate(giftCertificate));
        assertEquals("400 BAD_REQUEST \"Name field can't be null; \"", responseStatusException.getMessage());
    }


    @ParameterizedTest
    @CsvSource({
            " 'g', porsche - gift certificate, 1000, 90",
            " 'PORSCHE gift certificate for car', porsche - gift certificate, 300, 10",
            " '1', porsche - gift certificate, 1000, 90"
    })
    void dataValidationForShortOrLargeValueInNameField(String name, String description, BigDecimal price, int duration) {
        GiftCertificate giftCertificate = new GiftCertificate(name, description, price, duration);
        ResponseStatusException responseStatusException = assertThrows(ResponseStatusException.class,() -> giftCertificateService.createGiftCertificate(giftCertificate));
        assertEquals("400 BAD_REQUEST \"You should input name with length of characters from 2 to 30; \"", responseStatusException.getMessage());
    }

    @ParameterizedTest
    @CsvSource({
            " porsche, porsche - gift certificate, -1000, 90",
            " 911, porsche 911 - gift certificate, -300, 10",
            " cars, cars - gift certificate, -1000, 120"
    })
    void dataValidationForNegativePriceValue(String name, String description, BigDecimal price, int duration) {
        GiftCertificate giftCertificate = new GiftCertificate(name, description, price, duration);
        ResponseStatusException responseStatusException = assertThrows(ResponseStatusException.class,() -> giftCertificateService.createGiftCertificate(giftCertificate));
        assertEquals("400 BAD_REQUEST \"You input a negative price number; \"", responseStatusException.getMessage());
    }

    @ParameterizedTest
    @CsvSource({
            " porsche, porsche - gift certificate, 1000, -90",
            " 911, porsche 911 - gift certificate, 300, -10"
    })
    void dataValidationForNegativeDurationValue(String name, String description, BigDecimal price, int duration) {
        GiftCertificate giftCertificate = new GiftCertificate(name, description, price, duration);
        ResponseStatusException responseStatusException = assertThrows(ResponseStatusException.class,() -> giftCertificateService.createGiftCertificate(giftCertificate));
        assertEquals("400 BAD_REQUEST \"You input a negative duration number; \"", responseStatusException.getMessage());
    }

    @ParameterizedTest
    @CsvSource({
            " 12, porsche, porsche - gift certificate, 1000, 90",
            " 13, 911, porsche 911 - gift certificate, 300, 10"
    })
    void updateGiftCertificateById(long id ,String name, String description, BigDecimal price, int duration){
        GiftCertificate giftCertificate = new GiftCertificate(name, description, price, duration);
        when(giftCertificateRepository.isNotGiftCertificateById(id)).thenReturn(false);
        when(giftCertificateRepository.updateGiftCertificate(id, giftCertificate)).thenReturn(true);
        assertTrue(giftCertificateService.updateGiftCertificate(id, giftCertificate));
    }

    @ParameterizedTest
    @CsvSource({
            " 54 ",
            " 9 "
    })
    void deleteGiftCertificateById(long id){
        when(giftCertificateRepository.isNotGiftCertificateById(id)).thenReturn(false);
        when(giftCertificateRepository.deleteGiftCertificate(id)).thenReturn(true);
        assertTrue(giftCertificateService.deleteGiftCertificate(id));
    }


    @ParameterizedTest
    @CsvSource({
            " 54 "
    })
    void deleteGiftCertificateByNonExistedId(long id){
        when(giftCertificateRepository.isNotGiftCertificateById(id)).thenReturn(true);
        when(giftCertificateRepository.deleteGiftCertificate(id)).thenReturn(true);
        ResponseStatusException responseStatusException = assertThrows(ResponseStatusException.class,() -> giftCertificateService.deleteGiftCertificate(id));
        assertEquals(String.format("404 NOT_FOUND \"There is not such gift certificate with (id=%d)\"",id), responseStatusException.getMessage());
    }

}
