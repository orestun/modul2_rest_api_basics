package com.epam.esm.giftCertificate.repo;

import com.epam.esm.giftCertificate.GiftCertificate;
import com.epam.esm.giftCertificate.GiftCertificateRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class GiftCertificateRepoTest {

    private EmbeddedDatabase testDatabase;
    private GiftCertificateRepository giftCertificateRepository;

    private final List<GiftCertificate> giftCertificateList = List.of(
            new GiftCertificate(1,
                    "exhibitions",
                    "gift certificate for picture exhibition",
                    new BigDecimal("500.00"),
                    180,
                    new Date(2023-1900,0,28),
                    new Date(2023-1900,0,29)),
            new GiftCertificate(2,
                    "car",
                    "gift certificate for buying a car",
                    new BigDecimal("10000.00"),
                    360,
                    new Date(2023-1900,0,30),
                    new Date(2023-1900,0,30)),
            new GiftCertificate(3,
                    "Computer equipment",
                    "gift certificate for buying any computer equipment",
                    new BigDecimal("1000.00"),
                    90,
                    new Date(2023-1900,0,10),
                    new Date(2023-1900,0,12))
    );

    @BeforeEach
    void setup(){
        this.testDatabase = new EmbeddedDatabaseBuilder().
                setType(EmbeddedDatabaseType.H2).
                addScripts("db/sql/create-db.sql").
                addScripts("db/sql/giftcertificate/insert-gift-certificates.sql").
                build();
        this.giftCertificateRepository = new GiftCertificateRepository(new NamedParameterJdbcTemplate(testDatabase).getJdbcTemplate());
    }

    @AfterEach
    void tearDown(){
        testDatabase.shutdown();
    }

    @Test
    void getAllGiftCertificates(){
        List<GiftCertificate> giftCertificateListFromRepo = giftCertificateRepository.getAllGiftCertificates();
        assertEquals(giftCertificateList,giftCertificateListFromRepo);
    }

    @ParameterizedTest
    @CsvSource({
            " Phone, gift certificate for buying any phone in store , 1200, 90, true",
            " Laptop, gift certificate for buying any laptop in store , 1500, 120, true",
    })
    void createGiftCertificate(String name, String description, BigDecimal price, int duration, boolean expected){
        GiftCertificate giftCertificate = new GiftCertificate(name, description, price, duration);
        assertEquals(expected, giftCertificateRepository.createGiftCertificate(giftCertificate));
    }

    @ParameterizedTest
    @CsvSource({
            " 45, true",
            " 8, true",
            " 3, false"
    })
    void isNotGiftCertificateById(long id, boolean expected){
        assertEquals(expected, giftCertificateRepository.isNotGiftCertificateById(id));
    }

    @Test
    void deleteGiftCertificateById(){
        List<GiftCertificate> giftCertificateListWithoutLastObject = giftCertificateList.subList(0,2);
        giftCertificateRepository.deleteGiftCertificate(3);
        assertEquals(giftCertificateListWithoutLastObject, giftCertificateRepository.getAllGiftCertificates());
    }

    @ParameterizedTest
    @CsvSource({
            "1, exhibitions, gift certificate for picture exhibition , 500.00, 180"
    })
    void updateGiftCertificateById(int id, String name, String description, BigDecimal price, int duration){
        long millis = System.currentTimeMillis();
        Date currentDate = new Date(millis);
        GiftCertificate newGiftCertificate = new GiftCertificate(name, description, price, duration);
        giftCertificateRepository.updateGiftCertificate(id, newGiftCertificate);
        assertNotEquals(giftCertificateList, giftCertificateRepository.getAllGiftCertificates());
        giftCertificateList.get(0).setDuration(180);
        giftCertificateList.get(0).setLastUpdateDate(currentDate);
        assertEquals(giftCertificateList,  giftCertificateRepository.getAllGiftCertificates());
    }
}
