/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.util.ArrayList;
import java.util.Optional;
import model.Reservation;
import utils.Utils;

/**
 *
 * @author buiductrieu
 */
public class ReservationDAO extends ArrayList<Reservation> implements IDAO<Reservation> {
    
    @Override
    public ArrayList getAll() {
        return this;
    }
    
    @Override
    public Optional<Reservation> get(String ReservationID) {
        return this.stream()
                .filter(item -> item.getReservationID() == ReservationID)
                .findFirst();
    }
    
    @Override
    public void set(ArrayList<Reservation> reservationList) {
        this.clear();
        this.addAll(reservationList);
    }
    
    @Override
    public void save(Reservation reservation) {
        this.add(reservation);
    }
    
    @Override
    public void delete(Reservation reservation) {
        this.remove(reservation);
    }
    
    @Override
    public String toString() {
        if (this.isEmpty()) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (Reservation reservation : this) {
            sb.append(reservation).append("\n");
        }
        return sb.toString();
    }
}
