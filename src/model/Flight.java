/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author LENOVO
 */
public class Flight implements Serializable {

    private String flightCode;
    private String departureCity;
    private String destinationCity;
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;
    private Seat[] seats;

    public Flight() {
    }

    public Flight(String number, String departureCity, String destinationCity, LocalDateTime departureTime, LocalDateTime arrivalTime, int totalSeats) {
        this.flightCode = number;
        this.departureCity = departureCity;
        this.destinationCity = destinationCity;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.seats = new Seat[totalSeats];
        initializeSeats(totalSeats);
    }

    public String getFlightCode() {
        return flightCode;
    }

    public void setFlightCode(String number) {
        this.flightCode = number;
    }

    public String getDepartureCity() {
        return departureCity;
    }

    public void setDepartureCity(String departureCity) {
        this.departureCity = departureCity;
    }

    public String getDestinationCity() {
        return destinationCity;
    }

    public void setDestinationCity(String destinationCity) {
        this.destinationCity = destinationCity;
    }

    public LocalDateTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalDateTime departureTime) {
        this.departureTime = departureTime;
    }

    public LocalDateTime getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(LocalDateTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

   public Seat[] getSeats() {
        return seats;
    }
   
    public List getAvailableSeats(){
        List<Seat> availableList = new ArrayList<>();
        for (int i = 0; i < seats.length; i++)
        {
            Seat currentSeat = seats[i];
            if(currentSeat.isBooked() == false) {
                availableList.add(currentSeat);
            }
        }
        return availableList;
    }

    public void setTotalSeats(Seat[] totalSeats) {
        this.seats = seats;
    }
    
    private void initializeSeats(int size){
        for (int i = 0; i < size; i++)
        {
            seats[i] = new Seat(i+1);
        }
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
        final StringBuilder sb = new StringBuilder("Flight{");
        sb.append("flightNumber='").append(flightCode).append('\'');
        sb.append(", departureCity='").append(departureCity).append('\'');
        sb.append(", destinationCity='").append(destinationCity).append('\'');
        sb.append(", departureTime=").append(sdf.format(departureTime));
        sb.append(", arrivalTime=").append(sdf.format(arrivalTime));
        sb.append(", Seats=").append(seats);
        sb.append('}');
        return sb.toString();
    }
}
