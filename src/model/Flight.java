/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author buiductrieu
 */
public class Flight implements Serializable {

    private String flightCode;
    private String departureCity;
    private String destinationCity;
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;
    private List<Seat> seats;
    private List<String> crews;

    public Flight() {
    }

    public Flight(String number, String departureCity, String destinationCity, LocalDateTime departureTime, LocalDateTime arrivalTime, int totalSeats) {
        this.flightCode = number;
        this.departureCity = departureCity;
        this.destinationCity = destinationCity;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.seats = new ArrayList<>(totalSeats);
        for (int i = 0; i < totalSeats; i++) {
            seats.add(new Seat(i + 1));
        }
        this.crews = new ArrayList<>();
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

   public List<Seat> getSeats() {
        return seats;
    }
   
     public List<Seat> getAvailableSeats() {
        List<Seat> availableSeats = new ArrayList<>();
        for (Seat seat : seats) {
            if (!seat.isBooked()) {
                availableSeats.add(seat);
            }
        }
        return availableSeats;
    }

    public void setSeats(List<Seat> seats) {
        this.seats = seats;
    }
   
    public List<String> getCrews() {
        return crews;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        final StringBuilder sb = new StringBuilder("Flight{");
        sb.append("flightNumber='").append(flightCode).append(", ");
        sb.append(", departureCity='").append(departureCity).append(", ");
        sb.append(", destinationCity='").append(destinationCity).append(", ");
        sb.append(", departureTime=").append(departureTime.format(formatter)).append(", ");;
        sb.append(", arrivalTime=").append(arrivalTime.format(formatter)).append(", ");;
        sb.append(", Seats=").append(seats.size());
        sb.append("}\n");
        return sb.toString();
    }
}
