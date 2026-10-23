
package com.newsagent.model;

public class DeliveryArea {

    private int areaNumber;
    private String areaName;
    private int deliveryPersonId;

    public DeliveryArea() {
    }

    public DeliveryArea(int areaNumber, String areaName,
                        int deliveryPersonId) {
        if (!createDeliveryArea(areaNumber, areaName, deliveryPersonId)) {
            throw new IllegalArgumentException("Invalid Delivery Area");
        }
    }

    public boolean verifyAreaNumber(int number) {
        return number >= 1 && number <= 24;
    }

    public boolean verifyAreaName(String name) {
        return name != null && !name.trim().isEmpty();
    }

    public boolean verifyDeliveryPersonId(int id) {
        return id >= 1;
    }

    public boolean createDeliveryArea(int number, String name,
                                      int personId) {

        if (!verifyAreaNumber(number)) {
            return false;
        }

        if (!verifyAreaName(name)) {
            return false;
        }

        if (!verifyDeliveryPersonId(personId)) {
            return false;
        }

        this.areaNumber = number;
        this.areaName = name.trim();
        this.deliveryPersonId = personId;

        return true;
    }

    public int getAreaNumber() {
        return areaNumber;
    }

    public String getAreaName() {
        return areaName;
    }

    public int getDeliveryPersonId() {
        return deliveryPersonId;
    }

    public boolean setAreaNumber(int number) {
        if (!verifyAreaNumber(number)) {
            return false;
        }
        this.areaNumber = number;
        return true;
    }

    public boolean setAreaName(String name) {
        if (!verifyAreaName(name)) {
            return false;
        }
        this.areaName = name.trim();
        return true;
    }

    public boolean setDeliveryPersonId(int id) {
        if (!verifyDeliveryPersonId(id)) {
            return false;
        }
        this.deliveryPersonId = id;
        return true;
    }

    @Override
    public String toString() {
        return "Area Number: " + areaNumber +
               "\nArea Name: " + areaName +
               "\nDelivery Person ID: " + deliveryPersonId;
    }
}
