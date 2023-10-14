/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import java.io.*;
import java.util.ArrayList;
import model.Flight;
import model.ProductData;
import model.Reservation;

/**
 *
 * @author bacda
 */
public class FileManager {
    public static final String PRODUCT_PATH = "./Product.dat";


    public FileManager() {

    }


    public void saveToFile(ArrayList<Flight> flightList, ArrayList<Reservation> reservations) {
        // add existing data of flight,crew, reservation management

        ProductData productData = new ProductData(flightList, reservations);


        try (FileOutputStream fos = new FileOutputStream(new File(PRODUCT_PATH));
           ObjectOutputStream oos = new ObjectOutputStream(fos)) {

          oos.writeObject(productData);
          System.out.println("    Save Product.dat to file successfully !");
        } catch (FileNotFoundException e) {
          e.printStackTrace();
        } catch (IOException e) {
          e.printStackTrace();

        }

    }


    private ProductData loadProductData() throws FileNotFoundException, IOException, ClassNotFoundException {

        FileInputStream fis = new FileInputStream(new File(PRODUCT_PATH));

        ObjectInputStream ois = new ObjectInputStream(fis);

        ProductData productData = null;

        try {

          productData = (ProductData) ois.readObject();

        } catch (EOFException e) {

        }
        ois.close();
        if (productData != null) {
          System.out.println("    Load Product.dat from file successfully !");
        }
        return productData;
    }
}
