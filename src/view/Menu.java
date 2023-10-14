/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import utils.Input;

/**
 *
 * @author LENOVO
 */
public class Menu {

    public static final String[] MAIN_MENU =
    {
        "1. Flight Schedule Management",
        "2. Passenger Reservation and booking",
        "3. Passenger Check-In and Seat Allocation and Availability",
        "4. Crew Management and Assignments",
        "5. Save to file ",
        "6. Print all lists from file ",
        "7. Log in as Administrator",
        "0. Quit the program"
    };
    public static final String[] SEARCH_MENU =
    {

        "1. Search flight by date",
        "2. Search flight by departure location",
        "3. Seach flight by destination location"
    };
    public static final String[] USERTYPE_MENU =
    {
        "1. Login As Passenger",
        "2. Login As Airline Staff",
        "3. Login As Administrator"
    };
    public static final String[] CREWTYPE_MENU =
    {
        "1. Pilot",
        "2. Ground Staff",
        "3. Flight Attendant"
    };
    public static final String[] CREW_MENU =
    {
        "1. Add a crew member (Administrator only)",
        "2. Update a crew member (Administrator only) ",
        "3. Delete a crew member (Administrator only) ",
        "4. Assign a crew member",
    };
    
    public static int getChoice(String[] menu) {
        for (int i = 0; i < menu.length; i++)
            System.out.println((i+1) + ". " + menu[i]);
        
        return Input.inputIntMax("Choose: ", 1, menu.length);
    }
}
