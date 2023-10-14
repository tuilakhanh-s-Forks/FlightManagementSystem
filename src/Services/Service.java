/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services;

import dao.CrewDAO;
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
import dao.IDAO;
import model.Crew;
import model.CrewRole;
import model.ProductData;
import model.User;
import model.UserRole;
import utils.FileManager;
import view.Menu;


/**
 *
 * @author buiductrieu
 */
public class Service {
    private IDAO<Flight> flightDAO;
    private IDAO<Reservation> reservationDAO;
    private IDAO<Crew> crewDAO;
    private FileManager fileManager;
    private User currentUser;
    
    public Service() {
        flightDAO = new FlightDAO();
        reservationDAO = new ReservationDAO();
        crewDAO = new CrewDAO();
        fileManager = new FileManager();
    }
    
    public void addFlight() {
        while(true){
            Flight flight = inputFlight();
            flightDAO.save(flight);
            System.out.println("Successfully add flight:\n" + flight);
            if(!Input.checkYesOrNo("Do you want to continue to add product in the collection (Y/N): ")){
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
        
        availableSeats.get(0).setBooked(true);
        
        String reservationID = String.format("R%04d", reservationDAO.getAll().size() + 1);

        String reservationFlightCode = chosseFlight.getFlightCode();

        
        String passengerName = Input.inputNonBlankStr("Please enter the name: ");
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
        
        int selectedSeatNum = Input.inputSeat(selectedFlight.getSeats());
        
        for (Seat seat : selectedFlight.getSeats()) {
            if (seat.getSeatNum() == selectedSeatNum) {
                if (seat.isOccupied()) {
                    System.out.println("Seat is Occupied");
                    return;
                }
                seat.setOccupied(true);
                selectedReservation.get().setPreserveSeatName(seat.getSeatNum());
                System.out.println("Assigned seat");
                StringBuilder sb = new StringBuilder();
                sb.append("BoardingPass{ ");
                sb.append(selectedReservation.get().getBookedPassengers()).append(", ");
                sb.append("Flight Number='").append(selectedFlight.getFlightCode()).append(", ");
                sb.append(", Seat Number=").append(selectedSeatNum).append("}");
                System.out.println(sb);
                break;
            }
        }
    }
    
    public void addCrew() {
        String crewID = Input.inputValidCode("Crew Member");

        // add if it's empty , and check if there is duplicate code
        if (crewDAO.getAll().isEmpty()) {
            for (Crew crew : crewDAO.getAll()) {
                if (crew.getCrewID().equalsIgnoreCase(crewID)) {
                    System.out.println("The ID is existed! Return to main menu..");
                    return;
                }
            }
        }

        String name = Input.inputNonBlankStr("Enter crew member Name:");
        CrewRole role;
        int subChoice = Menu.getChoice(Menu.CREWTYPE_MENU);
        switch (subChoice) {
            case 1:
                System.out.println("Assigning member as Pilot");
                role = CrewRole.PILOT;
                break;
            case 2:
                System.out.println("Assigning member as Ground Staff");
                role = CrewRole.GROUND_STAFF;
                break;
            case 3:
                System.out.println("Assigning member as Flight Attendant");
                role = CrewRole.FLIGHT_ATTENDANT;
                break;
            default:
                System.out.println("Invalid choice! return to main menu");
                return;
        }

        Crew newCrew = new Crew(crewID, name, role);

        crewDAO.save(newCrew);
        System.out.println("Add new member successfully!");
    }
    
    public void assignCrew() {
        if (crewDAO.getAll().isEmpty()) {
            System.out.println("Crew member is empty!");
            return;
        }

        System.out.println(crewDAO);

        String crewID = Input.inputValidCode("Crew Member");
        Optional<Crew> crewMember = crewDAO.get(crewID);
        if (crewMember.isEmpty()) {
            System.out.println("Crew member not found!");
            return;
        }

        System.out.println(flightDAO);

        String flightID = Input.inputValidCode("Flight");

        Optional<Flight> flight = flightDAO.get(flightID);
        
        if (flight.isEmpty()) {
            System.out.println("Flight not found!");
            return;
        }
        

        if (crewMember.get().getCurrentFlight() != null) {
            System.out.println("Crew member is already on a flight!");
            return;
        }

        crewMember.get().setCurrentFlight(flight.get());
        flightDAO.get(flightID).get().getCrews().add(crewID);

        System.out.println("Crew member assigned successfully!");
    }
    
    public void deleteCrew() {
        if (crewDAO.getAll().isEmpty()) {
            System.out.println("Crew member is empty!");
            return;
        }
        
        String crewID = Input.inputValidCode("Crew Member");
        Optional<Crew> crewMember = crewDAO.get(crewID);
        if (crewMember.isEmpty()) {
            System.out.println("Crew member not found!");
            return;
        }
        
        crewDAO.delete(crewMember.get());
    }
    
    public void updateCrew() {
         if (crewDAO.getAll().isEmpty()) {
            System.out.println("Crew member is empty!");
            return;
        }
        
        String crewID = Input.inputValidCode("Crew Member");
        Optional<Crew> crewMember = crewDAO.get(crewID);
        if (crewMember.isEmpty()) {
            System.out.println("Crew member not found!");
            return;
        }
        if (crewMember.get().getCurrentFlight() != null) {
            System.out.println("Crew member is in flight, Canot update");
            return;
        }
        String name = Input.inputNonBlankStr("Enter crew member Name:");
        CrewRole role;
        int subChoice = Menu.getChoice(Menu.CREWTYPE_MENU);
        switch (subChoice) {
            case 1:
                System.out.println("Assigning member as Pilot");
                role = CrewRole.PILOT;
                break;
            case 2:
                System.out.println("Assigning member as Ground Staff");
                role = CrewRole.GROUND_STAFF;
                break;
            case 3:
                System.out.println("Assigning member as Flight Attendant");
                role = CrewRole.FLIGHT_ATTENDANT;
                break;
            default:
                System.out.println("Invalid choice! return to main menu");
                return;
        }
        crewMember.get().setCrewName(name);
        crewMember.get().setCrewRole(role);
    }
    
    
    public void ShowAll() {
        System.out.println("Flight List");
        System.out.print(flightDAO);
        System.out.println("Reservation List");
        System.out.print(reservationDAO);
        System.out.println("Crew List");
        System.out.print(crewDAO);
    }
    
    public void saveToFile() {
        fileManager.saveToFile(flightDAO.getAll(), crewDAO.getAll(), reservationDAO.getAll());
    }
    
    public void loadFormFile() {
        ProductData productData = fileManager.loadFromFile();
        if (productData == null) {
            return;
        }
        flightDAO.set(productData.getFlightList());
        reservationDAO.set(productData.getReservationList());
        crewDAO.set(productData.getCrewList());
    }
    
    public void loginForm() {
        int loginChoice;
        do {
            loginChoice = Menu.getChoice(Menu.USERTYPE_MENU);

            switch (loginChoice) {
                case 1:
                    currentUser = new User("Passenger", "", UserRole.PASSENGER);
                    System.out.println("Login Successfully!");
                    break;
                case 2:
                    String temp = Input.inputNonBlankStr("Enter Password: ");
                    if (temp.equalsIgnoreCase("staff")) {
                        currentUser = new User("Staff", "staff", UserRole.STAFF);
                        System.out.println("Login Successfully!");
                    } else {
                        currentUser = new User("Passenger", "", UserRole.PASSENGER);
                        System.out.println("Login FAIL! Login As a Passenger!");
                    }
                    break;
                case 3:
                    String adminPassword = Input.inputNonBlankStr("Enter Password: ");
                    if (adminPassword.equalsIgnoreCase("admin")) {
                        currentUser = new User("admin", "admin", UserRole.ADMIN);
                        System.out.println("Login Successfully!");
                    } else {
                        currentUser = new User("Passenger", "", UserRole.PASSENGER);
                        System.out.println("Login FAIL! Login as a Passenger!");
                    }
                    break;
                default:
                    System.out.println("Invalid choice! Enter again!");
                    continue;
            }
            return;
        } while (true);
    }
    
    public UserRole getUserRole() {
        return currentUser.getRole();
    }
    
    public void crewAssignMent() {
        if (currentUser.getRole() == UserRole.PASSENGER) {
            System.out.println("You do not have permission! Only Staff and Administrators can use this.");
            return;
        }

        System.out.println("You are logged in as " + currentUser + "!");
        int subChoice = Menu.getChoice(Menu.CREW_MENU);

        if (currentUser.getRole() == UserRole.STAFF) {
            System.out.println("Only Administrators have permission.");
            return;
        }

        switch (subChoice) {
            case 1:
                addCrew();
                break;
            case 2:
                updateCrew();
                break;
            case 3:
                deleteCrew();
                break;
            case 4:
                assignCrew();
                break;
        }
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
