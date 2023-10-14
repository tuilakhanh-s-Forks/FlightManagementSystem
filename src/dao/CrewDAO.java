/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.util.ArrayList;
import java.util.Optional;
import model.Crew;

/**
 *
 * @author bacda
 */
public class CrewDAO extends ArrayList<Crew> implements DAO<Crew> {
    @Override
    public ArrayList getAll() {
        return this;
    }
    
    @Override
    public Optional<Crew> get(String crewID) {
        return this.stream().filter(item -> item.getCrewID().equalsIgnoreCase(crewID)).findFirst();
    }
    
    @Override
    public void save(Crew crew) {
        this.add(crew);
    }
}
