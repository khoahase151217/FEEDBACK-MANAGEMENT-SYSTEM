/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.response;

/**
 *
 * @author ADMIN
 */
public class ResponseDTO {

    private String feedbackDetailID;
    private String userID;
    private String image;
    private String des;
    private String statusID;
    private String responseID;
    private String deviceName;
    private String location;
    private String userName;
    private String quantity;
    private String date;

    public ResponseDTO() {
    }

    public ResponseDTO(String feedbackDetailID, String userID, String des, String statusID, String date) {
        this.feedbackDetailID = feedbackDetailID;
        this.userID = userID;
        this.des = des;
        this.statusID = statusID;
        this.date = date;
    }

    public ResponseDTO(String feedbackDetailID, String image, String des, String responseID, String deviceName, String location, String userName) {
        this.feedbackDetailID = feedbackDetailID;
        this.image = image;
        this.des = des;
        this.responseID = responseID;
        this.deviceName = deviceName;
        this.location = location;
        this.userName = userName;
    }

    public ResponseDTO(String feedbackDetailID, String image, String des, String statusID, String responseID, String deviceName, String location, String userName, String quantity, String date) {
        this.feedbackDetailID = feedbackDetailID;
        this.image = image;
        this.des = des;
        this.statusID = statusID;
        this.responseID = responseID;
        this.deviceName = deviceName;
        this.location = location;
        this.userName = userName;
        this.quantity = quantity;
        this.date = date;
    }

    public ResponseDTO(String feedbackDetailID, String image, String des, String responseID, String deviceName, String location, String userName, String quantity, String date) {
        this.feedbackDetailID = feedbackDetailID;
        this.image = image;
        this.des = des;
        this.responseID = responseID;
        this.deviceName = deviceName;
        this.location = location;
        this.userName = userName;
        this.quantity = quantity;
        this.date = date;
    }

 

    public ResponseDTO(String feedbackDetailID, String userID, String des, String statusID) {
        this.feedbackDetailID = feedbackDetailID;
        this.userID = userID;
        this.des = des;
        this.statusID = statusID;
    }

 

    public ResponseDTO(String feedbackDetailID, String userID, String image, String des, String statusID, String responseID) {
        this.feedbackDetailID = feedbackDetailID;
        this.userID = userID;
        this.image = image;
        this.des = des;
        this.statusID = statusID;
        this.responseID = responseID;
    }

    public String getFeedbackDetailID() {
        return feedbackDetailID;
    }

    public void setFeedbackDetailID(String feedbackDetailID) {
        this.feedbackDetailID = feedbackDetailID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getStatusID() {
        return statusID;
    }

    public void setStatusID(String statusID) {
        this.statusID = statusID;
    }

    public String getResponseID() {
        return responseID;
    }

    public void setResponseID(String responseID) {
        this.responseID = responseID;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}
