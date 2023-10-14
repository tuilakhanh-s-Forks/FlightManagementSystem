/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author bacda
 */
public class ProductData implements Serializable {

    private ArrayList<Flight> flightList;
    // private ArrayList<Employee> crewList;
    private ArrayList<Reservation> reservations;

    public ProductData(ArrayList<Flight> flightList, ArrayList<Reservation> reservations) {
        this.flightList = flightList;
        this.reservations = reservations;
    }

    public ArrayList<Flight> getFlightManagement() {
        return flightList;
    }

//    public List<Employee> getCrewList() {
//        return crewList;
//    }

    public ArrayList<Reservation> getReservations() {
        return reservations;
    }
}

