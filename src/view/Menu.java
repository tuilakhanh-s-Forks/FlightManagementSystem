/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import utils.Input;

/**
 *
 * @author buiductrieu
 */
public class Menu {

    public static final String[] MAIN_MENU =
    {
        "Flight Schedule Management",
        "Passenger Reservation and booking",
        "Passenger Check-In and Seat Allocation and Availability",
        "Crew Management and Assignments",
        "Save to file ",
        "Print all lists from file ",
        "Log in as Administrator",
        "Quit the program"
    };
    public static final String[] SEARCH_MENU =
    {

        "Search flight by date",
        "Search flight by departure location",
        "Seach flight by destination location"
    };
    public static final String[] USERTYPE_MENU =
    {
        "Login As Passenger",
        "Login As Airline Staff",
        "Login As Administrator"
    };
    public static final String[] CREWTYPE_MENU =
    {
        "Pilot",
        "Ground Staff",
        "Flight Attendant"
    };
    public static final String[] CREW_MENU =
    {
        "Add a crew member (Administrator only)",
        "Update a crew member (Administrator only) ",
        "Delete a crew member (Administrator only) ",
        "Assign a crew member",
    };
    
    public static int getChoice(String[] menu) {
        for (int i = 0; i < menu.length; i++)
            System.out.println((i+1) + ". " + menu[i]);
        
        return Input.inputIntMax("Choose: ", 1, menu.length);
    }
}
