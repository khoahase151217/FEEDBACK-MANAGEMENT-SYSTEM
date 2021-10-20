/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.facility;

/**
 *
 * @author ADMIN
 */
public class FacilityDTO {
    private String facilityID;
    private String facilityName;
    private int quantity;
    private String maintenanceDate;
    private String statusID;
    private String categoryID;
    private String statusName;
    private String image;
    private int count;

    public FacilityDTO() {
    }

    public FacilityDTO(String facilityID, String facilityName, int quantity, String maintenanceDate, String statusID, String categoryID, String statusName, String image, int count) {
        this.facilityID = facilityID;
        this.facilityName = facilityName;
        this.quantity = quantity;
        this.maintenanceDate = maintenanceDate;
        this.statusID = statusID;
        this.categoryID = categoryID;
        this.statusName = statusName;
        this.image = image;
        this.count = count;
    }
    
    public FacilityDTO(String facilityID, String facilityName, int quantity, String maintenanceDate, String statusID, String categoryID, String statusName, String image) {
        this.facilityID = facilityID;
        this.facilityName = facilityName;
        this.quantity = quantity;
        this.maintenanceDate = maintenanceDate;
        this.statusID = statusID;
        this.categoryID = categoryID;
        this.statusName = statusName;
        this.image = image;
    }

    public FacilityDTO(String facilityID, String facilityName, int quantity, String maintenanceDate, String statusID, String categoryID, String statusName) {
        this.facilityID = facilityID;
        this.facilityName = facilityName;
        this.quantity = quantity;
        this.maintenanceDate = maintenanceDate;
        this.statusID = statusID;
        this.categoryID = categoryID;
        this.statusName = statusName;
    }
    

    public FacilityDTO(String facilityID, String facilityName, int quantity, String maintenanceDate, String statusID, String categoryID) {
        this.facilityID = facilityID;
        this.facilityName = facilityName;
        this.quantity = quantity;
        this.maintenanceDate = maintenanceDate;
        this.statusID = statusID;
        this.categoryID = categoryID;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getFacilityID() {
        return facilityID;
    }

    public void setFacilityID(String facilityID) {
        this.facilityID = facilityID;
    }

    public String getFacilityName() {
        return facilityName;
    }

    public void setFacilityName(String facilityName) {
        this.facilityName = facilityName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getMaintenanceDate() {
        return maintenanceDate;
    }

    public void setMaintenanceDate(String maintenanceDate) {
        this.maintenanceDate = maintenanceDate;
    }

    public String getStatusID() {
        return statusID;
    }

    public void setStatusID(String statusID) {
        this.statusID = statusID;
    }

    public String getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(String categoryID) {
        this.categoryID = categoryID;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
    
}
