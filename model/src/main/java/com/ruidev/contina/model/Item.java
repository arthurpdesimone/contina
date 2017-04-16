package com.ruidev.contina.model;

/**
 * Created by ruiri on 21/07/2016.
 */
public class Item {

    String id;
    /** Item description*/
    String itemWhat;
    /** Item locale*/
    String itemWhere;
    /** Item Price */
    String price;
    /** Item date*/
    String date;
    /** Item category*/
    String category;
    /** Payment type*/
    String paymentType;
    /** Public constructor*/

    public Item(String id, String itemWhat, String itemWhere, String price, String date, String category, String paymentType) {
        this.id = id;
        this.itemWhat = itemWhat;
        this.itemWhere = itemWhere;
        this.price = price;
        this.date = date;
        this.category = category;
        this.paymentType = paymentType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getItemWhat() {
        return itemWhat;
    }

    public void setItemWhat(String itemWhat) {
        this.itemWhat = itemWhat;
    }

    public String getItemWhere() {
        return itemWhere;
    }

    public void setItemWhere(String itemWhere) {
        this.itemWhere = itemWhere;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public Item(){}

}
