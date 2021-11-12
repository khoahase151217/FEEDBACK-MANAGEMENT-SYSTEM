/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.users;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class UserHistoryDTO implements Comparable<UserHistoryDTO> {

    private String feedbackId;
    private String date;
    private String image;
    private List<String> imageList = new ArrayList<String>();
    private String deviceName;
    private String location;
    private String statusName;
    private String statusId;
    private String imageFirebase;

    public UserHistoryDTO() {
    }

    public UserHistoryDTO(String feedbackId, String date, String image, String deviceName, String location, String statusName, String statusId) {
        this.feedbackId = feedbackId;
        this.date = date;
        this.image = image;
        this.deviceName = deviceName;
        this.location = location;
        this.statusName = statusName;
        this.statusId = statusId;
    }

    public UserHistoryDTO(String feedbackId, String date, List imageList, String deviceName, String location, String statusName, String statusId) {
        this.feedbackId = feedbackId;
        this.date = date;
        this.imageList = imageList;
        this.deviceName = deviceName;
        this.location = location;
        this.statusName = statusName;
        this.statusId = statusId;

    }

    public UserHistoryDTO(String feedbackId, String date, String image, String deviceName, String location, String statusName, String statusId, String imageFirebase) {
        this.feedbackId = feedbackId;
        this.date = date;
        this.image = image;
        this.deviceName = deviceName;
        this.location = location;
        this.statusName = statusName;
        this.statusId = statusId;
        this.imageFirebase = imageFirebase;
    }

    public String getImageFirebase() {
        return imageFirebase;
    }

    public void setImageFirebase(String imageFirebase) {
        this.imageFirebase = imageFirebase;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<String> getImageList() {
        return imageList;
    }

    public void setImageList(List<String> imageList) {
        this.imageList = imageList;
    }

    public String getStatusId() {
        return statusId;
    }

    public void setStatusId(String statusId) {
        this.statusId = statusId;
    }

    public String getFeedbackId() {
        return feedbackId;
    }

    public void setFeedbackId(String feedbackId) {
        this.feedbackId = feedbackId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setImage(List imageList) {
        this.imageList = imageList;
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

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    @Override
    public int compareTo(UserHistoryDTO o) {
        BigDecimal b1 = new BigDecimal(Integer.parseInt(this.feedbackId));
        BigDecimal b2 = new BigDecimal(Integer.parseInt(o.feedbackId));
        return b1.compareTo(b2);
    }

}
