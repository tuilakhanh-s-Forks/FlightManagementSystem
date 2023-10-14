/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import Services.Service;
import utils.Input;

/**
 *
 * @author bacda
 */
public class Program {
    public void executed() {
        
        Service service = new Service();
//        service.loadFromFile();
//        service.loginForm();
        int choice = -1;
        do
        {            
            try
            {
                choice = Menu.getChoice(Menu.MAIN_MENU);
                switch(choice){
                    case 0:
                        Boolean check = Input.checkYesOrNo("Do you want to quit? (y/n): ");
                        if(check)
                            choice = -1;
                        else
                            System.out.println("Back to main menu!");
                        break;
                    case 1: 
//                        if(service.getCurrentUser().getRole().equalsIgnoreCase("admin") || service.getCurrentUser().getRole().equalsIgnoreCase("staff"))
                        service.addFlight();
                        break;
                    case 2:
                        service.makeReservation();
                        break;
                    case 3:
//                        service.check_Ins();
                        break;
                    case 4:
//                        service.crewAssignment();
                        break;
                    case 5:
//                        service.saveToFile();
                        break;
                    case 6:
                        service.ShowAll();
                        break;
                    case 7:
//                        service.loginForm();
                        break;
                }
            } catch (Exception e)
            {
                System.out.println("Your choice is invalid!");
            }
        } while (choice != -1);
    }
}
