package com.epam.esm.giftCertificateTag;

import com.epam.esm.db.SQLQueries;
import com.epam.esm.giftCertificate.GiftCertificate;
import com.epam.esm.giftCertificate.GiftCertificateMapper;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GiftCertificateTagService {
    private final GiftCertificateTagRepository giftCertificateTagRepository;

    public GiftCertificateTagService(GiftCertificateTagRepository giftCertificateTagRepository) {
        this.giftCertificateTagRepository = giftCertificateTagRepository;
    }

    public int createGiftCertificateTag(GiftCertificateTag giftCertificateTag){
        return giftCertificateTagRepository.createGiftCertificateTag(giftCertificateTag);
    }

    public List<GiftCertificate> getGiftCertificateByName(String name){
        return  giftCertificateTagRepository.getGiftCertificateByName(name);
    }
    public List<GiftCertificate> getGiftCertificateByDescription(String description){
        return  giftCertificateTagRepository.getGiftCertificateByDescription(description);
    }
    public List<GiftCertificate> getSortedGiftCertificateByName(boolean asc){
        return giftCertificateTagRepository.getSortedGiftCertificateByName(asc);
    }
    public List<GiftCertificate> getSortedGiftCertificateByCreateDate(boolean asc){
        return giftCertificateTagRepository.getSortedGiftCertificateByCreateDate(asc);
    }

    public List<GiftCertificate> getSortedGiftCertificateTagByNameAndCreateDate(boolean nameIsAsc, boolean createDateIsAsc){
        return giftCertificateTagRepository.getSortedGiftCertificateByNameAndCreateDate(nameIsAsc, createDateIsAsc);
    }
}
