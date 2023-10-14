/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services;

import dao.DAO;
import dao.FlightDAO;
import dao.ReservationDAO;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import model.Flight;
import model.Passenger;
import model.Reservation;
import model.Seat;
import utils.Input;


/**
 *
 * @author bacda
 */
public class service {
    DAO<Flight> flightDAO;
    DAO<Reservation> reservationDAO;
    
    public service() {
        flightDAO = new FlightDAO();
        reservationDAO = new ReservationDAO();
        
    }
    
    public void addFlight() {
        while(true){
            Flight flight = inputFlight();
            flightDAO.save(flight);
            //The application asks to continuous create new product or go back to the main
            if(!Input.checkYesOrNo("Do you want to continue to add product in the collection ( Y/N ) ")){
                // TO do
                // System.out.println("Successfully add product: " + newProduct);
                return;
            }
        }
    }
    
    public void makeReservation() {
        if (flightDAO.getAll().isEmpty()) {
            System.out.println("There are currently no flights!");
            return;
        }
        System.out.print(flightDAO);
        int searchChoice = Input.inputSearchChoice();
        ArrayList<Flight> filteredFlights = searchFlight(searchChoice);
        if (filteredFlights.isEmpty()) {
            System.out.println("There is no flight available yet!");
            return;
        }
        Flight chosseFlight = Input.getFlightByIndex(filteredFlights);
        
        List<Seat> availableSeats = chosseFlight.getAvailableSeats();
        
        if (availableSeats.size() == 0) {
            System.out.println("The flight is already fully occupied");
            return;
        }
        
        
        String reservationID = String.format("R%04d", reservationDAO.getAll().size() + 1);

        String reservationFlightCode = chosseFlight.getFlightCode();

        
        String passengerName = Input.inputNonBlankStr("Please enter the name:");
        String passengerContact = Input.inputNonBlankStr("Enter Contact Number: ");
        
        Passenger newPassenger = new Passenger(passengerName, passengerContact);

        Reservation newReservation = new Reservation(reservationID, newPassenger, reservationFlightCode);
        reservationDAO.save(newReservation);

        // Update FlightSeat
        
        System.out.println("Added reservation successfully");
    }
    
    public void checkIn() {
        String reservationID = Input.inputValidCode("Reservation");
        Optional<Reservation> selectedReservation = reservationDAO.get(reservationID);
        if (selectedReservation.isEmpty()) {
            System.out.println("Reservation not found!");
            return;
        }
        
        if (selectedReservation.get().getPreserveSeatName() != -1) {
            System.out.println("The reservation has already been checked in!");
            return;
        }
        
        Flight selectedFlight = flightDAO.get(selectedReservation.get().getFlightID()).get();
        
        // show
        // choose
        // assign
        // update
    }
    
    private Flight inputFlight() {
        String code = checkFlightCodeExist();
        String departureCity = Input.inputValidCity("Enter the departure city name: ");
        String destinationCity = Input.inputValidCity("Enter the desinatoin city name: ");
        LocalDateTime depTime = Input.inputTime();
        LocalDateTime arrTime = Input.InputArrivalTime(depTime);
        int totalSeats = Input.inputInt("Enter the total seats: ", 0);
        return new Flight(code, departureCity, destinationCity, depTime, arrTime, totalSeats);
    }
    
    private String checkFlightCodeExist() {
        while(true) {
            String code = Input.inputValidCode("Flight");
            Optional<Flight> flight = flightDAO.get(code);
            if (flight.isPresent()) {
                System.err.println("Code already exists! Please enter again.");
            } else {
                return code;
            }
        } 
    }
    
    private ArrayList searchFlight(int choice) {
        ArrayList<Flight> matchingFlights = new ArrayList<>();
        switch (choice) {
            // By departure date
            case 1:
                LocalDate searchTime = Input.inputTime().toLocalDate();
                for (Flight flight : flightDAO.getAll()) {
                    if (flight.getDepartureTime().toLocalDate().equals(searchTime)) {
                        matchingFlights.add(flight);
                    }
                }
                break;
            // By Departure city
            case 2:
                String searchDepartureCity = Input.inputValidCity("Enter the Departure City Name: ");
                for (Flight flight : flightDAO.getAll()) {
                    if (flight.getDepartureCity().equalsIgnoreCase(searchDepartureCity)) {
                        matchingFlights.add(flight);
                    }
                }
                break;
            case 3:
                String searchDesinatoinCity = Input.inputValidCity("Enter the Desinatoin City Name: ");
                for (Flight flight : flightDAO.getAll()) {
                    if (flight.getDepartureCity().equalsIgnoreCase(searchDesinatoinCity)) {
                        matchingFlights.add(flight);
                    }
                }
                break;
        }
        return matchingFlights;
    }
}
