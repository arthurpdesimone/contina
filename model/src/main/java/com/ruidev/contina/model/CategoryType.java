package com.ruidev.contina.model;

/**
 * Created by ruiri on 24/02/2017.
 */

public enum CategoryType {

    HEALTH(R.drawable.ic_local_hospital_white_18dp, 0, "Health", R.color.red_active_element, R.color.red_accent, R.color.red_lighter_element, R.color.red_active_element, R.color.red_lighter_background),
    FOOD(R.drawable.ic_restaurant_white_18dp, 1, "Food", R.color.indigo_active_element, R.color.indigo_accent, R.color.indigo_lighter_element, R.color.indigo_active_element, R.color.indigo_lighter_background),
    STORE(R.drawable.ic_local_grocery_store_white_18dp, 2, "Store", R.color.green_active_element, R.color.green_accent, R.color.green_lighter_element, R.color.green_active_element, R.color.green_lighter_background),
    PETS(R.drawable.ic_pets_white_18dp, 3, "Pets", R.color.brown_active_element, R.color.brown_accent, R.color.brown_lighter_element, R.color.brown_active_element, R.color.brown_lighter_background),
    FUN(R.drawable.ic_gamepad_variant_white_18dp, 4, "Fun", R.color.purple_active_element, R.color.purple_accent, R.color.purple_lighter_element, R.color.purple_active_element, R.color.purple_lighter_background),
    HOME(R.drawable.ic_home_white_18dp, 5, "Home", R.color.teal_active_element, R.color.teal_accent, R.color.teal_lighter_element, R.color.teal_active_element, R.color.teal_lighter_background),
    ELECTRONIC(R.drawable.ic_computer_white_18dp, 6, "Electronic", R.color.blue_grey_active_element, R.color.blue_grey_accent, R.color.blue_grey_lighter_element, R.color.blue_grey_active_element, R.color.blue_grey_lighter_background),
    BANK(R.drawable.ic_attach_money_white_18dp, 7, "Bank", R.color.yellow_active_element, R.color.yellow_accent, R.color.yellow_lighter_element, R.color.yellow_active_element, R.color.yellow_lighter_background),
    PERSONAL(R.drawable.ic_tag_faces_white_18dp, 8, "Personal", R.color.pink_active_element, R.color.pink_accent, R.color.pink_lighter_element, R.color.pink_active_element, R.color.pink_lighter_background),
    TRANSPORT(R.drawable.ic_directions_bus_white_18dp, 9, "Transport", R.color.orange_active_element, R.color.orange_accent, R.color.orange_lighter_element, R.color.orange_active_element, R.color.orange_lighter_background),
    CLOTHING(R.drawable.ic_tshirt_crew_white_18dp, 10, "Clothing", R.color.blue_active_element, R.color.blue_accent, R.color.blue_lighter_element, R.color.blue_active_element, R.color.blue_lighter_background),
    OTHERS(R.drawable.ic_add_circle_white_18dp, 11, "Others", R.color.grey_active_element, R.color.grey_accent, R.color.grey_lighter_element, R.color.grey_active_element, R.color.grey_lighter_background);

    private String name;
    private int resource;
    private int id;
    private int colorName;
    private int colorAccent;
    private int colorLighterElement;
    private int colorActiveElement;
    private int colorBackground;

    CategoryType(int resource, int id, String name, int colorName, int colorAccent, int colorLighterElement, int colorActiveElement, int colorBackground) {
        this.setResource(resource);
        this.setId(id);
        this.setName(name);
        this.setColorName(colorName);

        this.setColorAccent(colorAccent);
        this.setColorLighterElement(colorLighterElement);
        this.setColorActiveElement(colorActiveElement);
        this.setColorBackground(colorBackground);

    }

    public int getColorBackground() {
        return colorBackground;
    }

    public void setColorBackground(int colorBackground) {
        this.colorBackground = colorBackground;
    }

    public int getColorAccent() {
        return colorAccent;
    }

    public void setColorAccent(int colorAccent) {
        this.colorAccent = colorAccent;
    }

    public int getColorLighterElement() {
        return colorLighterElement;
    }

    public void setColorLighterElement(int colorLighterElement) {
        this.colorLighterElement = colorLighterElement;
    }

    public int getColorActiveElement() {
        return colorActiveElement;
    }

    public void setColorActiveElement(int colorActiveElement) {
        this.colorActiveElement = colorActiveElement;
    }

    public static CategoryType getCateroryTypebyId(int id){

        if(id == HEALTH.getId())return HEALTH;

        if(id == FOOD.getId())return FOOD;

        if(id == STORE.getId())return STORE;

        if(id == PETS.getId())return PETS;

        if(id == FUN.getId())return FUN;

        if(id == HOME.getId())return HOME;

        if(id == ELECTRONIC.getId())return ELECTRONIC;

        if(id == BANK.getId())return BANK;

        if(id == PERSONAL.getId())return PERSONAL;

        if(id == TRANSPORT.getId())return TRANSPORT;

        if(id == CLOTHING.getId())return CLOTHING;

        if(id == OTHERS.getId())return OTHERS;

        return null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getResource() {
        return resource;
    }

    public void setResource(int resource) {
        this.resource = resource;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getColorName() {
        return colorName;
    }

    public void setColorName(int colorName) {
        this.colorName = colorName;
    }
}
