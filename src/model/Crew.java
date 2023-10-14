/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;

/**
 *
 * @author LENOVO
 */
public class Crew implements Serializable {
    private String crewID;
    private String crewName;
    private CrewRole crewRole;
    private Flight currentFlight;

    public Crew(String crewID, String currentName, CrewRole crewRole) {
        this.crewID = crewID;
        this.crewName = currentName;
        this.crewRole = crewRole;
    }
    
    public String getCrewID() {
        return crewID;
    }

    public String getCrewName() {
        return crewName;
    }

    public Flight getCurrentFlight() {
        return currentFlight;
    }

    public CrewRole getCrewRole() {
        return crewRole;
    }
    
    public void setCrewID(String crewID) {
        this.crewID = crewID;
    }

    public void setCrewName(String crewName) {
        this.crewName = crewName;
    }

    public void setCurrentFlight(Flight currentFlight) {
        this.currentFlight = currentFlight;
    }

    public void setCrewRole(CrewRole crewRole) {
        this.crewRole = crewRole;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Employee{");
        sb.append("empID='").append(crewID).append(", ");
        sb.append(", name='").append(crewName).append(", ");
        sb.append(", role='").append(crewRole).append(", ");
        sb.append(", currentFlight=").append(currentFlight.getFlightCode());
        sb.append("}\n");
        return sb.toString();
    }

}
