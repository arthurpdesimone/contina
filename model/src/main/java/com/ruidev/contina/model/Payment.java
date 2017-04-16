package com.ruidev.contina.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by ruiri on 21/07/2016.
 *
 */
public class Payment extends RealmObject {
    /** Primary key int*/
    @PrimaryKey
    int id;
    /** String containing type*/
    String type;
    /** Color hex*/
    String color;
    /** Color name*/
    String colorName;
    public Payment(){}
    /** Constructor
     * @param id
     * @param color
     * @param colorName
     * @param type */
    public Payment(int id, String colorName, String color, String type) {
        this.id = id;
        this.colorName = colorName;
        this.color = color;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public String getColor() {
        return color;
    }

    public String getType() {
        return type;
    }

    public String getColorName() {
        return colorName;
    }
}
