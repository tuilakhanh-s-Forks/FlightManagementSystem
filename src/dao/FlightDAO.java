/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.util.ArrayList;
import java.util.Optional;
import model.Flight;
import utils.Utils;

/**
 *
 * @author bacda
 */
public class FlightDAO extends ArrayList<Flight> implements IDAO<Flight> {
    
    @Override
    public ArrayList getAll() {
        return this;
    }
    
    @Override
    public Optional<Flight> get(String flightNumber) {
        return this.stream().filter(item -> item.getFlightCode().equalsIgnoreCase(flightNumber)).findFirst();
    }
    
    @Override
    public void set(ArrayList<Flight> flightList) {
        this.clear();
        this.addAll(flightList);
    }
    
    @Override
    public void save(Flight flight) {
        this.add(flight);
    }
    
    @Override
    public void delete(Flight flight) {
        this.remove(flight);
    }
    
    @Override
    public String toString() {
        ArrayList<Flight> sortedFlight = new ArrayList<>();
        sortedFlight.addAll(this);
        sortedFlight.sort(Utils.dateDesc);
        String flightList = "";
        for (Flight flight : sortedFlight) {
            flightList += flight + "\n";
        }
        return flightList;
    }
}
