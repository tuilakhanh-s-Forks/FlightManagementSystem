/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author buiductrieu
 */
public class ProductData implements Serializable {

    private ArrayList<Flight> flightList;
    private ArrayList<Crew> crewList;
    private ArrayList<Reservation> reservationList;

    public ProductData(ArrayList<Flight> flightList, ArrayList<Crew> crewList, ArrayList<Reservation> reservations) {
        this.flightList = flightList;
        this.crewList = crewList;
        this.reservationList = reservations;
    }

    public ArrayList<Flight> getFlightList() {
        return flightList;
    }

    public ArrayList<Crew> getCrewList() {
        return crewList;
    }

    public ArrayList<Reservation> getReservationList() {
        return reservationList;
    }
}

