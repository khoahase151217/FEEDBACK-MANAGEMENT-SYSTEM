/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.feedback;

import app.users.UserHistoryDTO;

/**
 *
 * @author ADMIN
 */
public class Comparator implements java.util.Comparator<UserHistoryDTO> {

    @Override
    public int compare(UserHistoryDTO o1, UserHistoryDTO o2) {
        int m = o1.getFeedbackId().compareToIgnoreCase(o2.getFeedbackId());
			return m;
    }
    
}
