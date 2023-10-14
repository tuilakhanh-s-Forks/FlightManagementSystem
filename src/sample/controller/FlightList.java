/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.controller;

import model.BoardingPass;
import model.CrewAssignment;
import model.Flight;
import model.Passenger;
import model.Reservation;
import utils.Utils;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.logging.Level;
import java.util.logging.Logger;
import sample.dto.I_FlightList;

/**
 *
 * @author LENOVO
 */
public class FlightList extends ArrayList<Flight> implements I_FlightList {

    List<Passenger> passengerList = new ArrayList<>();
    List<Reservation> reservationList = new ArrayList<>();
    List<BoardingPass> boardingPassList = new ArrayList<>();
    List<CrewAssignment> crewAssignmentList = new ArrayList<>();
    String fileName = "Product.dat";

//------------------------------------------------------------------------------
    public void displayFlightInfor(List<Flight> listFlight) {
        Collections.sort(listFlight, Comparator.comparing((Flight p) -> p.getArrivalTime()).reversed());
        for (Flight f : listFlight) {
            System.out.println(f);
        }
    }
    //==============================================================================

    @Override
    public void flightSchelduleManagement() {
        add();
    }

    public void add() {
        boolean quit;
        do {
            boolean check = true;
            String flightNum;
            String flightNumPattern = "F\\d{4}";
            do {
                flightNum = Utils.getString("Input flight number (Fxxxx): ");
                int index = this.find(flightNum);
                if (index == -1 && flightNum.matches(flightNumPattern)) {
                    check = false;
                }
            } while (check);

            String departCity = Utils.getString("Input departure city: ");
            String destiCity = Utils.getString("Input destination city: ");
            String departTime = Utils.getDate("Input departure time (MM/dd/yyyy): ", "MM/dd/yyyy");
            String arrivalTime = Utils.getDate("Input arrival time (MM/dd/yyyy): ", "MM/dd/yyyy");
            int avaiableSeats = Utils.getInt("Input available seats: ", 1, 100);
            this.add(new Flight(flightNum, departCity, destiCity, departTime, arrivalTime, avaiableSeats));

            quit = Utils.confirmYesNo("Do you want to continue adding (Y/N): ");
        } while (quit);
    }

    public int find(String num) {
        int index = -1;
        for (int i = 0; i < this.size(); i++) {
            if (this.get(i).getNumber().equals(num)) {
                index = i;
                break;
            }
        }
        return index;
    }

//==============================================================================
    @Override
    public void passengerReservationAndBooking() {
        List<Flight> availableFlights = findAvailableFlights();
        if (!availableFlights.isEmpty()) {
            for (Flight f : availableFlights) {
                System.out.println(f);
            }
            makeReservation(availableFlights);
        }
    }

    public void makeReservation(List<Flight> availableFlights) {
        System.out.println("Please choose your flight to make the reservation: ");
        int index = 1;
        for (Flight f : availableFlights) {
            System.out.println(index + ". " + "Flight: " + f.getNumber());
            index++;
        }
        int choice = Utils.getInt("Input choice: ", 1, availableFlights.size());
        availableFlights.get(choice - 1).bookSeat();

        String passengerName = Utils.getString("Input name: ");
        String passengerContactDetail = Utils.getString("Input contact detail: ");
        Passenger nPassenger = new Passenger(passengerName, passengerContactDetail);
        passengerList.add(nPassenger);

        String reservationID = "";
        reservationID += "R";
        int end_code = reservationList.size() + 1;
        int number_zero = 4 - (end_code + "").length();
        for (int i = 1; i <= number_zero; i++) {
            reservationID += "0";
        }
        reservationID += end_code;

        Reservation nReservation = new Reservation(nPassenger, availableFlights.get(choice - 1), reservationID);
        reservationList.add(nReservation);

        System.out.println(nPassenger + " ,ReservationID = " + reservationID);
    }

    public List<Flight> findAvailableFlights() {
        String findFlight = Utils.getString("Input departure, arrival locations or date to find: ");
        List<Flight> listFlights = new ArrayList<>();
        for (Flight f : this) {
            if (f.getDepartureCity().toUpperCase().equals(findFlight.toUpperCase()) || f.getDepartureTime().toUpperCase().equals(findFlight.toUpperCase()) || f.getDestinationCity().toUpperCase().equals(findFlight.toUpperCase()) || f.getArrivalTime().equals(findFlight)) {
                listFlights.add(f);
            }
        } 
        if (listFlights.isEmpty()) {
            System.out.println("Not find !!!");
        }
        return listFlights;
    }

//==============================================================================
    @Override
    public void passengerCheckInAndSeatAllocation() {
        passengerCheckInFlights();
    }

    private void passengerCheckInFlights() {
        boolean check = false;
        String providedReservationID;
        do {
            providedReservationID = Utils.getString("Input reservation ID for checking: ");
            for (Reservation r : reservationList) {
                if (r.getReservationID().equals(providedReservationID)) {
                    BoardingPass newBoardingPass = new BoardingPass(r.getPassenger(), r.getFlight());
                    boardingPassList.add(newBoardingPass);
                    allocateSeats(r.getPassenger(), r.getFlight());
                    check = true;
                }
            }
            if (!check) {
                check = Utils.confirmYesNo("Not found reservation ID. Do you want to continue input again (Y/N): ");
            }
        } while (!check);
    }

    private void allocateSeats(Passenger p, Flight f) {
        Passenger[] pList = f.getPassengerSeats();
        List<String> optionsList = new ArrayList<>();
        System.out.print("\t-----FLIGHT: " + f.getNumber() + " -----");
        for (int i = 0; i < f.getAvailableSeats(); i++) {
            Passenger passenger = pList[i];
            String s = "";
            if (passenger == null) {
                s = String.format("%d", i + 1);
                optionsList.add(s);
            } else {
                s = "X";
            }
            if ((i + 1) % 4 == 1) {
                System.out.print(String.format("\n\t[%s]", s));
            } else {
                System.out.print(String.format("\t[%s]", s));
            }
        }

        boolean choiceCheck = false;
        String seatChoice;
        do {
            seatChoice = Utils.getString("\nInput seat number: ");
            for (String s : optionsList) {
                if (seatChoice.equals(s)) {
                    System.out.println("Choose successfully !!!");
                    pList[Integer.parseInt(seatChoice) - 1] = p;
                    choiceCheck = true;
                    break;
                }
            }
            if (!choiceCheck) {
                System.out.println("Invalid seat !!!");
            }
        } while (!choiceCheck);

    }

//==============================================================================
    @Override
    public void crewManagementAndAssignments() {
        addCrewAssignments();
    }

    public void addCrewAssignments() {
        boolean hasFound = false;
        do {
            String nFlightNum = Utils.getString("Input flight number: ");
            for (Flight f : this) {
                if (f.getNumber().equals(nFlightNum)) {
                    hasFound = true;
                    int nPilots = Utils.getInt("Ïnput number of pilots (MAX=2): ", 1, 2);
                    int nFlightAttendants = Utils.getInt("Input number of flight attendants (MAX=10): ", 1, 10);
                    int nGroundStaffs = Utils.getInt("Input number of ground staffs (MAX=20): ", 1, 10);
                    CrewAssignment nCrewAssignment = new CrewAssignment(f, nPilots, nFlightAttendants, nGroundStaffs);
                    crewAssignmentList.add(nCrewAssignment);
                    System.out.println("Create crew assignments successfully !!!");
                }
            }
            if (!hasFound) {
                hasFound = Utils.confirmYesNo("Do you want to continue adding Crew Assignments (Y/N): ");
            }
        } while (!hasFound);

    }

//==============================================================================
    @Override
    public void save() {
        File file = new File(fileName);
        if (file.exists()) {

        } else {
            try {
                file.createNewFile();
            } catch (IOException ex) {
                Logger.getLogger(FlightList.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        try {
            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            oos.writeObject(this);
            oos.writeObject(reservationList);
            oos.writeObject(crewAssignmentList);

            System.out.println("Save to file successfully !!!");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FlightList.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FlightList.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void load() throws IOException {
        File file = new File(fileName);
        if (file.exists()) {

        } else {
            file.createNewFile();
        }
        FileInputStream fis = new FileInputStream(file);

        if (file.length() > 0) {
            ObjectInputStream ois = new ObjectInputStream(fis);
            List<Flight> loadedFlightList = new ArrayList<>();
            List<Reservation> loadedReservationList = new ArrayList<>();
            List<CrewAssignment> loadedCrewAssignmentList = new ArrayList<>();
            while (true) {
                try {
                    loadedFlightList = (List<Flight>) ois.readObject();
                    loadedReservationList = (List<Reservation>) ois.readObject();
                    loadedCrewAssignmentList = (List<CrewAssignment>) ois.readObject();

                } catch (ClassNotFoundException e) {
                    System.out.println(e);
                } catch (EOFException e) {
                    break;
                }
            }

            this.clear();
            this.addAll(loadedFlightList);
            reservationList.clear();
            reservationList = loadedReservationList;
            crewAssignmentList.clear();
            crewAssignmentList = loadedCrewAssignmentList;
        }
    }
}
