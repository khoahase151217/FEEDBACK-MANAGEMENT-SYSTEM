/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.feedback;

/**
 *
 * @author Admin
 */
public class FeedbackLoaderDTO {

    private int amount;
    private String feedbackID;
    private String html;
    private String imageString;

    public FeedbackLoaderDTO(int amount, String feedbackID, String html, String imageString) {
        this.amount = amount;
        this.feedbackID = feedbackID;
        this.html = html;
        this.imageString = imageString;
    }
    

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getFeedbackID() {
        return feedbackID;
    }

    public void setFeedbackID(String feedbackID) {
        this.feedbackID = feedbackID;
    }

    public String getHtml() {
        return html;
    }

    public void setHtml(String html) {
        this.html = html;
    }

}
