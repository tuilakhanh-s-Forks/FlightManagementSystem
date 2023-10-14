/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import java.io.*;
import java.util.ArrayList;
import model.Crew;
import model.Flight;
import model.ProductData;
import model.Reservation;

/**
 *
 * @author buiductrieu
 */
public class FileManager {
    public static final String PRODUCT_PATH = "./Product.dat";


    public void saveToFile(ArrayList<Flight> flightList, ArrayList<Crew> crewList, ArrayList<Reservation> reservations) {
        ProductData productData = new ProductData(flightList, crewList, reservations);


        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(PRODUCT_PATH))) {
            oos.writeObject(productData);
            System.out.println("Save Product.dat to file successfully!");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public ProductData loadFromFile() {
        ProductData productData = null;

        File productFile = new File(PRODUCT_PATH);

        if (!productFile.exists()) {
            System.out.println("File " + PRODUCT_PATH + " does not exist.");
            return productData;
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(productFile))) {
            productData = (ProductData) ois.readObject();
            System.out.println("Load Product.dat from file successfully!");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return productData;
    }
}
