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
public class FeedbackDTO {

    private String feedbackID;
    private String userID;
    private String date;
    private String email;
    private String statusId;
    private String fullName;
    private String statusName;
    private String trashDate;
    private String empName;

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public FeedbackDTO() {
    }

    public FeedbackDTO(String feedbackID, String userID, String date, String statusId) {
        this.feedbackID = feedbackID;
        this.userID = userID;
        this.date = date;
        this.statusId = statusId;
    }

    public FeedbackDTO(String feedbackID, String userID, String date, String email, String statusId) {
        this.feedbackID = feedbackID;
        this.userID = userID;
        this.date = date;
        this.email = email;
        this.statusId = statusId;
    }

    public FeedbackDTO(String feedbackID, String userID, String date, String email, String statusId, String fullName, String statusName, String trashDate, String empName) {
        this.feedbackID = feedbackID;
        this.userID = userID;
        this.date = date;
        this.email = email;
        this.statusId = statusId;
        this.fullName = fullName;
        this.statusName = statusName;
        this.trashDate = trashDate;
        this.empName = empName;
    }
    public FeedbackDTO(String feedbackID, String userID, String date, String email, String statusId, String fullName) {
        this.feedbackID = feedbackID;
        this.userID = userID;
        this.date = date;
        this.email = email;
        this.statusId = statusId;
        this.fullName = fullName;
      
    }

    

//    public FeedbackDTO(String feedbackID, String userID, String date, String email) {
//        this.feedbackID = feedbackID;
//        this.userID = userID;
//        this.date = date;
//        this.email = email;
//    }

    public FeedbackDTO(String feedbackID, String userID, String date) {
        this.feedbackID = feedbackID;
        this.userID = userID;
        this.date = date;
    }

    public FeedbackDTO(String feedbackID, String userID, String date, String email, String statusId, String fullName, String statusName) {
        this.feedbackID = feedbackID;
        this.userID = userID;
        this.date = date;
        this.email = email;
        this.statusId = statusId;
        this.fullName = fullName;
        this.statusName = statusName;
    }

    public FeedbackDTO(String feedbackID, String userID, String date, String email, String statusId, String fullName, String statusName, String trashDate) {
        this.feedbackID = feedbackID;
        this.userID = userID;
        this.date = date;
        this.email = email;
        this.statusId = statusId;
        this.fullName = fullName;
        this.statusName = statusName;
        this.trashDate = trashDate;
    }

    public FeedbackDTO(String userID, String date) {
        this.userID = userID;
        this.date = date;
    }

    public String getStatusId() {
        return statusId;
    }

    public void setStatusId(String statusId) {
        this.statusId = statusId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFeedbackID() {
        return feedbackID;
    }

    public void setFeedbackID(String feedbackID) {
        this.feedbackID = feedbackID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTrashDate() {
        return trashDate;
    }

    public void setTrashDate(String trashDate) {
        this.trashDate = trashDate;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }
    
    
    

}
