/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.Serializable;

/**
 *
 * @author buiductrieu
 */
public class Seat implements Serializable{

    private int seatNumber;
    private boolean isBooked;
    private boolean isOccupied;

    public Seat(int seatNumber) {
        this.seatNumber = seatNumber;
        this.isBooked = false;
        this.isOccupied = false;
    }

    public Seat(String seatName, boolean isBooked, boolean isOccupied, int size) {
        this.seatNumber = seatNumber;
        this.isBooked = isBooked;
        this.isOccupied = isOccupied;
    }

    public boolean isOccupied() {
        return isOccupied;
    }

    public void setOccupied(boolean occupied) {
        this.isOccupied = occupied;
    }
    

    public int getSeatNum() {
        return seatNumber;
    }

    public void setSeatNum(int seatNumber) {
        this.seatNumber = seatNumber;
    }

    public boolean isBooked() {
        return isBooked;
    }

    public void setBooked(boolean isBooked) {
        this.isBooked = isBooked;
    }

    @Override
    public String toString() {
        return String.format("Seat=%s, isOccupied=%s", seatNumber, isOccupied);
    }
}

