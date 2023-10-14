/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import Services.Service;
import model.UserRole;
import utils.Input;

/**
 *
 * @author buiductrieu
 */
public class Program {
    public void executed() {
        
        Service service = new Service();
        service.loadFormFile();
        service.loginForm();
        int choice = -1;
        boolean isContinue = false;
        do
        {            
            if (!isContinue) {
                choice = Menu.getChoice(Menu.MAIN_MENU);
            }
            switch(choice){
                case 0:
                    Boolean check = Input.checkYesOrNo("Do you want to quit? (y/n): ");
                    if(check)
                        choice = -1;
                    else
                        System.out.println("Back to main menu!");
                    break;
                case 1:
                    if (service.getUserRole() == UserRole.PASSENGER) {
                        System.out.println("You do not have the permission to add flights!");
                        continue;
                    }
                    service.addFlight();
                    break;
                case 2:
                    service.makeReservation();
                    break;
                case 3:
                    service.checkIn();
                    break;
                case 4:
                    service.crewAssignMent();
                    break;
                case 5:
                    service.saveToFile();
                    break;
                case 6:
                    service.ShowAll();
                    break;
                case 7:
                    service.loginForm();
                    break;
                case 8:
                    return;
            }
            isContinue = Input.checkYesOrNo("Do you want to redo this function? (Y/N): ");
        } while (choice != -1);
    }
}
