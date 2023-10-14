/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dao;

import java.util.ArrayList;
import java.util.Optional;

/**
 *
 * @author bacda
 */
public interface IDAO<T> {
    ArrayList<T> getAll();
 
    Optional<T> get(String id);
    
    void set(ArrayList<T> t);
 
    void save(T t);
 
    void delete(T t);
    
}
