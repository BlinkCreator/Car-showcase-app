package com.example.a302;

import java.io.Serializable;

//Declares Car class as public and enables serializable so that the object can be transformed
//into a series of bytes for writing onto disk/memory
public class Car implements Serializable {
    //Declares values that the Car class holds including carId for database purposes and the rest
    //to make up the bulk of the Car. Images are held in an Array so additional images can be added
    //at any time via the database provider.
    protected int carId;
    protected String carName;
    protected String category;
    protected int[] imageArray;
    protected String year;
    protected String make;
    protected String description;

    // Constructor for the car
    public Car(int carId, String carName, String category, int[] imageArray, String year, String make, String description) {
        this.carId = carId;
        this.carName = carName;
        this.category = category;
        this.imageArray = imageArray;
        this.year = year;
        this.make = make;
        this.description = description;
    }

    //Methods for the Car class to return values that Car contains
    public int getCarId() {
        return carId;
    }

    public String getName() {
        return this.carName;
    }

    public int getImage(int arrayPlace) { return this.imageArray[arrayPlace]; }

    public String getYear() {
        return this.year;
    }
}