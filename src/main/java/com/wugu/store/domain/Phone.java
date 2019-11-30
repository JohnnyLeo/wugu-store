package com.wugu.store.domain;

import java.util.UUID;

public class Phone {
    private String phoneName;
    private String phonePrice;
    private int phoneQuantity;
    private String phoneDescription;

    public String getPhoneName() {
        return phoneName;
    }

    public void setPhoneName(String phoneName) {
        this.phoneName = phoneName;
    }

    public String getPhonePrice() {
        return phonePrice;
    }

    public void setPhonePrice(String phonePrice) {
        this.phonePrice = phonePrice;
    }

    public int getPhoneQuantity() {
        return phoneQuantity;
    }

    public void setPhoneQuantity(int phoneQuantity) {
        this.phoneQuantity = phoneQuantity;
    }

    public String getPhoneDescription() {
        return phoneDescription;
    }

    public void setPhoneDescription(String phoneDescription) {
        this.phoneDescription = phoneDescription;
    }
}
