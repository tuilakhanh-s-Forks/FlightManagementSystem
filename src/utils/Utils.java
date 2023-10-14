/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.util.Comparator;
import model.Flight;

/**
 *
 * @author LENOVO
 */
public class Utils {
    public static Comparator<Flight> dateDesc = new Comparator<Flight>() {
        @Override
        public int compare(Flight f1, Flight f2) {
            if (f1.getDepartureTime().isAfter(f2.getDepartureTime()))
            {
                return 1;
            } else if (f1.getDepartureTime().isBefore(f2.getDepartureTime()))
            {
                return -1;
            } else
            {
                return 0;
            }
        }
    };
}
