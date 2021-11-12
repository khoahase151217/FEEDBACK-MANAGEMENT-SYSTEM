/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.feedback;

/**
 *
 * @author ASUS
 */
public class FeedbackDetailDTO {

    private String feedbackDetailID;
    private String facilityID;
    private String userID;
    private String feedbackID;
    private String quanity;
    private String reason;
    private String location;
    private String image;
    private boolean flag;
    private String deviceName;
    private String date;
    private String employeeName;
    private String responseDes;
    private String description;
    private String categoryDevice;
    private boolean check;
    private boolean checkDone;
    private String declineReason;
    private String imageFirebase;
    
    

    public FeedbackDetailDTO() {
    }

    public FeedbackDetailDTO(String feedbackDetailID, String facilityID, String userID, String feedbackID, String quanity, String reason, String location, String image, boolean flag, String deviceName, String date, String employeeName, String responseDes, String description, String categoryDevice, String declineReason) {
        this.feedbackDetailID = feedbackDetailID;
        this.facilityID = facilityID;
        this.userID = userID;
        this.feedbackID = feedbackID;
        this.quanity = quanity;
        this.reason = reason;
        this.location = location;
        this.image = image;
        this.flag = flag;
        this.deviceName = deviceName;
        this.date = date;
        this.employeeName = employeeName;
        this.responseDes = responseDes;
        this.description = description;
        this.categoryDevice = categoryDevice;
        this.declineReason = declineReason;
    }

    public FeedbackDetailDTO(String feedbackDetailID, String facilityID, String userID, String feedbackID, String quanity, String reason, String location, String image, boolean flag, String deviceName, String date, String employeeName, String description) {
        this.feedbackDetailID = feedbackDetailID;
        this.facilityID = facilityID;
        this.userID = userID;
        this.feedbackID = feedbackID;
        this.quanity = quanity;
        this.reason = reason;
        this.location = location;
        this.image = image;
        this.flag = flag;
        this.deviceName = deviceName;
        this.date = date;
        this.employeeName = employeeName;
        this.description = description;
    }

    public FeedbackDetailDTO(String feedbackDetailID, String facilityID, String userID, String feedbackID, String quanity, String reason, String location, boolean flag, String deviceName, String date, String description, String imageFirebase) {
        this.feedbackDetailID = feedbackDetailID;
        this.facilityID = facilityID;
        this.userID = userID;
        this.feedbackID = feedbackID;
        this.quanity = quanity;
        this.reason = reason;
        this.location = location;
        this.flag = flag;
        this.deviceName = deviceName;
        this.date = date;
        this.description = description;
        this.imageFirebase = imageFirebase;
    }

    public FeedbackDetailDTO(String facilityID, String userID, String feedbackID, String quanity, String reason, String location, boolean flag, String description) {
        this.facilityID = facilityID;
        this.userID = userID;
        this.feedbackID = feedbackID;
        this.quanity = quanity;
        this.reason = reason;
        this.location = location;
        this.flag = flag;
        this.description = description;
    }
   
//    public FeedbackDetailDTO(String feedbackDetailID, String facilityID, String userID, String feedbackID, String quanity, String reason, String location, String image, boolean flag, String deviceName, String date, String description, String categoryDevice) {
//        this.feedbackDetailID = feedbackDetailID;
//        this.facilityID = facilityID;
//        this.userID = userID;
//        this.feedbackID = feedbackID;
//        this.quanity = quanity;
//        this.reason = reason;
//        this.location = location;
//        this.image = image;
//        this.flag = flag;
//        this.deviceName = deviceName;
//        this.date = date;
//        this.description = description;
//        this.categoryDevice = categoryDevice;
//    }

    public FeedbackDetailDTO(String feedbackDetailID, String facilityID, String userID, String feedbackID, String quanity, String reason, String location, boolean flag, String deviceName, String date, String employeeName, String responseDes, String description, String categoryDevice, String imageFirebase) {
        this.feedbackDetailID = feedbackDetailID;
        this.facilityID = facilityID;
        this.userID = userID;
        this.feedbackID = feedbackID;
        this.quanity = quanity;
        this.reason = reason;
        this.location = location;
        this.flag = flag;
        this.deviceName = deviceName;
        this.date = date;
        this.employeeName = employeeName;
        this.responseDes = responseDes;
        this.description = description;
        this.categoryDevice = categoryDevice;
        this.imageFirebase = imageFirebase;
    }
    
    public FeedbackDetailDTO(String facilityID, String userID, String feedbackID, String quanity, String reason, String location, boolean flag) {
        this.facilityID = facilityID;
        this.userID = userID;
        this.feedbackID = feedbackID;
        this.quanity = quanity;
        this.reason = reason;
        this.location = location;
        this.flag = flag;
    }

    public FeedbackDetailDTO(String feedbackDetailID, String facilityID, String userID, String feedbackID, String quanity, String reason, String location, String image, boolean flag, String deviceName, String date) {
        this.feedbackDetailID = feedbackDetailID;
        this.facilityID = facilityID;
        this.userID = userID;
        this.feedbackID = feedbackID;
        this.quanity = quanity;
        this.reason = reason;
        this.location = location;
        this.image = image;
        this.flag = flag;
        this.deviceName = deviceName;
        this.date = date;
    }

    public FeedbackDetailDTO(String feedbackDetailID, String facilityID, String userID, String feedbackID, String quanity, String reason, String location, String image, boolean flag) {
        this.feedbackDetailID = feedbackDetailID;
        this.facilityID = facilityID;
        this.userID = userID;
        this.feedbackID = feedbackID;
        this.quanity = quanity;
        this.reason = reason;
        this.location = location;
        this.image = image;
        this.flag = flag;
    }

    public FeedbackDetailDTO(String feedbackDetailID, String facilityID, String userID, String feedbackID, String quanity, String reason, String location, String image, boolean flag, String deviceName, String date, String employeeName, String responseDes, String description, String categoryDevice, boolean check) {
        this.feedbackDetailID = feedbackDetailID;
        this.facilityID = facilityID;
        this.userID = userID;
        this.feedbackID = feedbackID;
        this.quanity = quanity;
        this.reason = reason;
        this.location = location;
        this.image = image;
        this.flag = flag;
        this.deviceName = deviceName;
        this.date = date;
        this.employeeName = employeeName;
        this.responseDes = responseDes;
        this.description = description;
        this.categoryDevice = categoryDevice;
        this.check = check;
    }

    public String getImageFirebase() {
        return imageFirebase;
    }

    public void setImageFirebase(String imageFirebase) {
        this.imageFirebase = imageFirebase;
    }

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }

    public String getResponseDes() {
        return responseDes;
    }

    public String getDeclineReason() {
        return declineReason;
    }

    public void setDeclineReason(String declineReason) {
        this.declineReason = declineReason;
    }

    public void setResponseDes(String responseDes) {
        this.responseDes = responseDes;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public String getFeedbackDetailID() {
        return feedbackDetailID;
    }

    public void setFeedbackDetailID(String feedbackDetailID) {
        this.feedbackDetailID = feedbackDetailID;
    }

    public String getFacilityID() {
        return facilityID;
    }

    public void setFacilityID(String facilityID) {
        this.facilityID = facilityID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getFeedbackID() {
        return feedbackID;
    }

    public void setFeedbackID(String feedbackID) {
        this.feedbackID = feedbackID;
    }

    public String getQuanity() {
        return quanity;
    }

    public void setQuanity(String quanity) {
        this.quanity = quanity;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategoryDevice() {
        return categoryDevice;
    }

    public void setCategoryDevice(String categoryDevice) {
        this.categoryDevice = categoryDevice;
    }

    public boolean isCheckDone() {
        return checkDone;
    }

    public void setCheckDone(boolean checkDone) {
        this.checkDone = checkDone;
    }

}
