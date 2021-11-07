/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.users;

/**
 *
 * @author Admin
 */
public class UserDTO {

    private String userID;
    private String fullName;
    private String password;
    private String email;
    private String roleID;
    private String statusID;
    private String image;
    private String roleName;
    private String statusName;
    private int count;
    private int rating;

    public UserDTO() {
    }

    public UserDTO(String userID, String fullName, String password, String email, String roleID, String statusID, String image) {
        this.userID = userID;
        this.fullName = fullName;
        this.password = password;
        this.email = email;
        this.roleID = roleID;
        this.statusID = statusID;
        this.image = image;
    }

    public UserDTO(String userID, String fullName, String password, String email, String roleID, String statusID, String image, String roleName) {
        this.userID = userID;
        this.fullName = fullName;
        this.password = password;
        this.email = email;
        this.roleID = roleID;
        this.statusID = statusID;
        this.image = image;
        this.roleName = roleName;
    }

    public UserDTO(String userID, String fullName, String password, String email, String roleID, String statusID, String image, String roleName, String statusName, int count) {
        this.userID = userID;
        this.fullName = fullName;
        this.password = password;
        this.email = email;
        this.roleID = roleID;
        this.statusID = statusID;
        this.image = image;
        this.roleName = roleName;
        this.statusName = statusName;
        this.count = count;
    }

    public UserDTO(String userID, String fullName, String password, String email, String roleID, String statusID, String image, String roleName, String statusName, int count, int rating) {
        this.userID = userID;
        this.fullName = fullName;
        this.password = password;
        this.email = email;
        this.roleID = roleID;
        this.statusID = statusID;
        this.image = image;
        this.roleName = roleName;
        this.statusName = statusName;
        this.count = count;
        this.rating = rating;
    }

    

    public UserDTO(String userID, String fullName, String roleID) {
        this.userID = userID;
        this.fullName = fullName;
        this.roleID = roleID;
    }

    public UserDTO(String fullName, String password, String email, String roleID, String statusID, String image) {
        this.fullName = fullName;
        this.password = password;
        this.email = email;
        this.roleID = roleID;
        this.statusID = statusID;
        this.image = image;
    }

    public UserDTO(String userID, String fullName, String password, String email, String roleID, String statusID, String image, String roleName, String statusName) {
        this.userID = userID;
        this.fullName = fullName;
        this.password = password;
        this.email = email;
        this.roleID = roleID;
        this.statusID = statusID;
        this.image = image;
        this.roleName = roleName;
        this.statusName = statusName;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRoleID() {
        return roleID;
    }

    public void setRoleID(String roleID) {
        this.roleID = roleID;
    }

    public String getStatusID() {
        return statusID;
    }

    public void setStatusID(String statusID) {
        this.statusID = statusID;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
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

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
    
    
}
