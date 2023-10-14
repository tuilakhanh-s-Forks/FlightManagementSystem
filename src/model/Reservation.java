/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;

/**
 *
 * @author LENOVO
 */
public class Reservation implements Serializable{

    private String reservationID;
    private Passenger bookedPassengers;
    private String flightID;
    private int preserveSeatName;

    public Reservation(String reservationID, Passenger bookedPassengers, String flightID) {
        this.reservationID = reservationID;
        this.bookedPassengers = bookedPassengers;
        this.flightID = flightID;
        this.preserveSeatName = -1;
    }

    public Reservation() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public String getReservationID() {
        return reservationID;
    }

    public void setReservationID(String reservationID) {
        this.reservationID = reservationID;
    }

    public Passenger getBookedPassengers() {
        return bookedPassengers;
    }

    public void setBookedPassengers(Passenger bookedPassengers) {
        this.bookedPassengers = bookedPassengers;
    }

    public String getFlightID() {
        return flightID;
    }

    public void setFlightID(String flightID) {
        this.flightID = flightID;
    }

    public int getPreserveSeatName() {
        return preserveSeatName;
    }

    public void setPreserveSeatName(int preserveSeatName) {
        this.preserveSeatName = preserveSeatName;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Reservation{");
        sb.append("reservationID='").append(getReservationID()).append(", ");
        sb.append(", bookedPassengers='").append(getBookedPassengers()).append(", ");
        sb.append(", flightID='").append(getFlightID()).append(", ");
        sb.append(", preserveSeatName='").append(getPreserveSeatName()).append(", ");
        sb.append('}');
        return sb.toString();
    }
}
