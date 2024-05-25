package com.example.laba4;
import org.json.simple.JSONObject;

public class Cars {
    private String brand;
    private String model;
    private int mileage;
    private String color;
    private int price;

    public Cars(String brand, String model, int mileage, String color, int price) {
        this.brand = brand;
        this.model = model;
        this.mileage = mileage;
        this.color = color;
        this.price = price;
    }

    public String getBrand() {
        return brand;
    }
    public String getModel() {
        return model;
    }
    public int getMileage() {
        return mileage;
    }
    public String getColor() {
        return color;
    }
    public int getPrice() {
        return price;
    }

    public JSONObject toJSON() {
        JSONObject json = new JSONObject();
        json.put("brand", brand);
        json.put("model", model);
        json.put("mileage", mileage);
        json.put("color", color);
        json.put("price", price);
        return json;
    }

    public static Cars fromJSON(JSONObject json) {
        String brand = (String) json.get("brand");
        String model = (String) json.get("model");
        int mileage = ((Long) json.get("mileage")).intValue();
        String color = (String) json.get("color");
        int price = ((Long) json.get("price")).intValue();

        return new Cars(brand, model, mileage, color, price);
    }
}