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
                    service.addFlight();
                    break;
                case 2:
                    service.makeReservation();
                    break;
                case 3:
                    service.checkIn();
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
        } while (choice != -1);
    }
}
