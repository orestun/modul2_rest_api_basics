package com.epam.esm.giftCertificate;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

public class GiftCertificate {

    private int id;

    @NotNull(message = "Name field can't be null")
    @Size(min = 2, max = 30, message = "You should input name with length of characters from 2 to 30")
    private String name;

    @NotNull(message = "Description field can't be null")
    @Size(min = 1, max = 200, message = "You should input description with length of characters from 1 to 200")
    private String description;

    @NotNull(message = "Price field can't be null")
    @Min(value = 0, message = "You input a negative price number")
    private BigDecimal price;

    @Min(value = 0, message = "You input a negative duration number")
    private int duration;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    private Date createDate;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    private Date lastUpdateDate;

    public GiftCertificate() {
    }

    public GiftCertificate(String name, String description, BigDecimal price, int duration) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.duration = duration;
    }

    public GiftCertificate(int id, String name, String description, BigDecimal price, int duration, Date createDate, Date lastUpdateDate) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.duration = duration;
        this.createDate = createDate;
        this.lastUpdateDate = lastUpdateDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public void setId(int id) {
        this.id = id;
    }


    @Override
    public String toString() {
        return "{\n" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", duration=" + duration +
                ", createDate=" + createDate +
                ", lastUpdateDate=" + lastUpdateDate + '\n'+
                '}'+'\n';
    }

    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GiftCertificate that = (GiftCertificate) o;
        return id == that.id && duration == that.duration && name.equals(that.name) && description.equals(that.description) && price.equals(that.price) && Objects.equals(createDate.toString(), that.createDate.toString()) && Objects.equals(lastUpdateDate.toString(), that.lastUpdateDate.toString());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, price, duration, createDate, lastUpdateDate);
    }
}
