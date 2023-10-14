/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;

/**
 *
 * @author buiductrieu
 */
public class Passenger implements Serializable{

    private String name;
    private String contactDetail;

    public Passenger(String name, String contactDetail) {
        this.name = name;
        this.contactDetail = contactDetail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactDetail() {
        return contactDetail;
    }

    public void setContactDetail(String contactDetail) {
        this.contactDetail = contactDetail;
    }

    @Override
    public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("Passenger{");
      sb.append("name = ").append(name);
      sb.append(", contactDetail = ").append(contactDetail);
      sb.append('}');
      return sb.toString();
    }
    
}
