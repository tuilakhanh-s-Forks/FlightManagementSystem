/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.util.ArrayList;
import java.util.Optional;
import model.Reservation;

/**
 *
 * @author bacda
 */
public class ReservationDAO extends ArrayList<Reservation> implements DAO<Reservation> {
    
    @Override
    public ArrayList getAll() {
        return this;
    }
    
    @Override
    public Optional<Reservation> get(String ReservationID) {
        return this.stream().filter(item -> item.getReservationID() == ReservationID).findFirst();
    }
    
    @Override
    public void save(Reservation reservation) {
        this.add(reservation);
    }
}
