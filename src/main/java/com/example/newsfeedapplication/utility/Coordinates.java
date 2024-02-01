package com.example.newsfeedapplication.utility;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Coordinates {

    private double latitude;
    private double longitude;

    // Constructors
    public Coordinates() {
    }

    public Coordinates(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
