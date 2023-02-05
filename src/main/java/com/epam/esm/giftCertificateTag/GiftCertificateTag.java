package com.epam.esm.giftCertificateTag;

public class GiftCertificateTag {

    private int giftCertificateId;
    private int tagId;

    public GiftCertificateTag(int giftCertificateId, int tagId) {
        this.giftCertificateId = giftCertificateId;
        this.tagId = tagId;
    }

    public GiftCertificateTag() {
    }

    public int getGiftCertificateId() {
        return giftCertificateId;
    }

    public void setGiftCertificateId(int giftCertificateId) {
        this.giftCertificateId = giftCertificateId;
    }

    public int getTagId() {
        return tagId;
    }

    public void setTagId(int tagId) {
        this.tagId = tagId;
    }
}
