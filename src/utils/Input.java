/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import model.Flight;
import model.Seat;
import view.Menu;

/**
 *
 * @author buiductrieu
 */
public class Input {

    public static final Scanner sc = new Scanner(System.in);
    private static final DateTimeFormatter DATETIME_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final DateTimeFormatter DATETIME_FORMATTER_WITH_SECONDS = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    public static String inputNonBlankStr(String mess) {
        String temp = null;
        do
        {
            System.out.print(mess);
            temp = sc.nextLine().trim();
            if (temp.isEmpty()) {
                System.out.println("Invalid String! String cannot be blank or with only spaces!");
            }
        } while (temp.isEmpty());
        return temp;
    }


    public static String inputStr(String mess) {
        System.out.println(mess);
        String temp = sc.nextLine().trim();
        return temp;
    }

    public static int inputInt(String mess, int min) {
        String temp = inputNonBlankStr(mess);
        int result = 0;
        do
        {
            result = Integer.parseInt(temp);
        } while (result < min);
        return result;
    }

    public static int inputIntMax(String mess, int min, int max) {
        while (true) {
            // allow user input a string 
            String input_raw = inputNonBlankStr(mess);
            
            try {
                // loi nhap sai dinh dang so 
                int input = Integer.parseInt(input_raw);
                // loi nhap ngoai range cho phep
                if (isWithinRange(input, min, max)) {
                    return input;
                } else {
                    System.err.println("Input must be a number between " + min + " and " + max + ".");
                }
            } catch (NumberFormatException e) {
                System.err.println("Must enter a valid number");
                continue;
            }

        }
    }
    
    public static boolean checkYesOrNo(String msg) {
        while (true) {
            String input = inputNonBlankStr(msg);
            
            if (input.equalsIgnoreCase("Y")) {
                return true;
            } else if (input.equalsIgnoreCase("N")) {
                return false;
            } else {
                System.err.println("Must input Y or N to select option");
            }
        }    
    }

    public static String inputValidCode(String type) {
        String regex = "";
        String ftype = "";
        if (type.equals("Flight")) {
            ftype = "F";
        } else if (type.equals("Crew Member")) {
            ftype = "E";
        } else if (type.equals("Reservation")) {
            ftype = "R";
        }
        regex = ftype + "\\d{4}";
        Pattern pattern = Pattern.compile(regex);

        do {
            String temp = inputNonBlankStr("Enter the " + type + " Code (Format " + ftype + "xxxx): ");
            temp = temp.toUpperCase();
            Matcher matcher = pattern.matcher(temp);

            if (matcher.matches()) {
                System.out.println("Input successfully");
                return temp;
            } else {
                System.out.println("Input Failed, Format must be in " + type + "xxxx (xxxx is a 4-digit number)");
            }
        } while (true);
    }
    
    public static int inputSearchChoice() {
        int choice = Menu.getChoice(Menu.SEARCH_MENU);
        return choice;
    }
    
    
    public static String inputValidCity(String mess) {
        do {
            String temp = inputNonBlankStr(mess);
            if (!temp.isBlank()) {
                System.out.println("Enter city name successfully!");
                return temp;
            }
        } while (true);
    }


    public static LocalDateTime inputTime() {
        String depDateStr = Input.inputNonBlankStr("Enter the date (optional for time) (dd/MM/yyyy hh:mm:ss): ");

        if (!depDateStr.isEmpty()) {
            validateDateStr(depDateStr);
        }
        while (true) {
            try {
                return LocalDate.parse(depDateStr, DATETIME_FORMATTER ).atStartOfDay();
            } catch (Exception e) {
                // If the date string cannot be parsed in the DD/MM/YYYY format, try to parse it in the DD/MM/YYYY HH:mm:ss format.
                try {
                    return LocalDateTime.parse(depDateStr, DATETIME_FORMATTER_WITH_SECONDS);
                } catch (Exception e2) {
                    // If the date string cannot be parsed in either format, throw an exception.
                    System.out.println("Invalid date and time format, enter again");
                    continue;
                }
            }
        }        
    }
    
    public static LocalDateTime InputArrivalTime(LocalDateTime depTime) {
        while (true) {
            LocalDateTime arrTime = inputTime();

            if (arrTime.isAfter(depTime)) {
                System.out.println("The arrival time must be after the departure time!");
                return arrTime;
            }

            if (arrTime.toLocalDate().isEqual(depTime.toLocalDate())) {
                String temp = Input.inputNonBlankStr("The date is on the same day. Would you like to set the arrival time to 1 hour after departure time? (Y/N): ");

                if (temp.equalsIgnoreCase("y")) {
                    // Calculate 1 hour duration and add it to arrival time
                    return depTime.plusHours(1);
                }
            }
        }
    }
    
    public static int inputSeat(List<Seat> seats) {
        System.out.println("Remaining Seat: ");
        for (Seat seat : seats) {
            if (!seat.isOccupied())
            {
                System.out.println(seat);
            }
        }
        int seatNum = inputIntMax("Choose seat number: ", 0, seats.size());
        return seatNum;
    }
    
    public static Flight getFlightByIndex(ArrayList<Flight> flightList) {
        int i = 0;
        for (Flight flight : flightList) {
            StringBuilder sb = new StringBuilder();
            sb.append(i).append(" | ");
            sb.append(flight);
            sb.append("\n");
            System.out.println(sb);
            i++;
        }
        int flightChoice = Input.inputIntMax(
            "Please select the flight you want to choose: ",
            0, i);
        return flightList.get(flightChoice);
    }
    
    private static <T extends Number> boolean isWithinRange(T value, T min, T max) {
        double doubleValue = value.doubleValue();
        double doubleMin = min.doubleValue();
        double doubleMax = max.doubleValue();

        return doubleValue >= doubleMin && doubleValue <= doubleMax;
    }

    private static void validateDateStr(String dateStr) {
        String regex = "\\d{2}/\\d{2}/\\d{4}( \\d{2}:\\d{2}:\\d{2})?";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(dateStr);

        if (!matcher.matches()) {
            throw new IllegalArgumentException("Invalid date format");
        }
    }
}