/*
 * The MIT License
 *
 * Copyright 2017 Utkarsh Prabhakar.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package bean;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *This class contains methods used for getting HTML statements of profile table of the user
 * and calculating number of remaining days of the RTI records. This class does not establishes any connection
 * with the databases.
 * @author Utkarsh Prabhakar
 */
public class OtherProcesses {
    /**
     * This method is used for getting number of remaining days for a given RTI record on the basis of it's type i.e "Normal" or "Life and Liberty".
     * @param date_db Takes the given date(receiving date) of RTI record in String format(MM/DD/YYYY) as input.
     * @param rti_type Takes the type of RTI record in String format as input. It should be either "Normal" or "Life and Liberty".
     * @return String - Returns the number of remaining days in String format. If receiving date is out of bounds then it returns "expired".
     */
    public static String getRemainingDays(String date_db, String rti_type) {
        int month[] = new int[12];
        month[0] = 31;
        month[1] = 28;
        month[2] = 31;
        month[3] = 30;
        month[4] = 31;
        month[5] = 30;
        month[6] = 31;
        month[7] = 31;
        month[8] = 30;
        month[9] = 31;
        month[10] = 30;
        month[11] = 31;
        String result_date = "";
        String flag_image = "<img src=\"red_flag.png\" height=27px></img>";
        String timeStamp = new SimpleDateFormat("yyyy/MM/dd").format(Calendar.getInstance().getTime());
        String date = date_db;
        String present[] = timeStamp.split("/");
        String past[] = date.split("/");
        int year = Integer.parseInt(present[0]);
        boolean isLeapYear = ((year % 4 == 0) && (year % 100 != 0) || (year % 400 == 0));
        if (isLeapYear) {
            month[1] = 29;
        }
        if (Integer.parseInt(present[0]) == Integer.parseInt(past[2])) {
            int month_diff = java.lang.Math.abs(Integer.parseInt(present[1]) - Integer.parseInt(past[0]));
            if (month_diff == 0) {
                int future=Integer.parseInt(present[2]) - Integer.parseInt(past[1]);
                int days_diff = java.lang.Math.abs(Integer.parseInt(present[2]) - Integer.parseInt(past[1]));
                if(future<0){
                    if (rti_type.compareTo("Normal") == 0){result_date=Integer.toString(30);}
                    else result_date=Integer.toString(2);
                }
                else{
                if (rti_type.compareTo("Normal") == 0) {
                    if (days_diff >= 20 && days_diff <= 30) {
                        
                        int remaining_days = 30 - days_diff;
                        result_date = Integer.toString(remaining_days) + flag_image;
                    } else if (days_diff <= 20) {
                        result_date = Integer.toString(30 - days_diff);
                    } else {
                        result_date = "expired";
                    }
                } else {if (days_diff >= 1 && days_diff <= 2) {
                    int remaining_days = 2 - days_diff;
                    result_date = Integer.toString(remaining_days) + flag_image;
                } else if (days_diff < 1) {
                    result_date = Integer.toString(2 - days_diff);
                } else {
                    result_date = "expired";
                }
                }
                }
            } else if (month_diff == 1) {
                int month_present = Integer.parseInt(present[1]) - 1;
                int month_past = Integer.parseInt(past[0]) - 1;
                int days_diff;
                int future=month_present-month_past;
                if (month_present > month_past) {
                    days_diff = (month[month_past] - Integer.parseInt(past[1])) + Integer.parseInt(present[2]);
                } else {
                    days_diff = (month[month_present] - Integer.parseInt(present[2])) + Integer.parseInt(past[1]);
                }
                if(future<0){
                       if (rti_type.compareTo("Normal") == 0){result_date=Integer.toString(30);}
                    else result_date=Integer.toString(2);
                }
                else{
                if (rti_type.compareTo("Normal") == 0) {
                    if (days_diff >= 20 && days_diff <= 30) {
                        int remaining_days = 30 - days_diff;
                        result_date = Integer.toString(remaining_days) + flag_image;
                    } else if (days_diff <= 20) {
                        result_date = Integer.toString(30 - days_diff);
                    } else {
                        result_date = "expired";
                    }

                } else {if (days_diff >= 1 && days_diff <= 2) {
                    int remaining_days = 2 - days_diff;
                    result_date = Integer.toString(remaining_days) + flag_image;
                } else if (days_diff < 1) {
                    result_date = Integer.toString(2 - days_diff);
                } else {
                    result_date = "expired";
                }
                }
                }
            } else {
                result_date = "Expired";
            }
        } else {
            result_date = "Expired";
        }
        return result_date;
    }
    /**
     * This method is used for making Subject Matter short to a value of maximum 40 characters. It is used for displaying short 
     * subject matter in profile table of user(displaying RTI records in Profile.jsp).
     * @param original_subject_matter Takes the original Subject Matter in String format as input.
     * @return String Returns a short subject Matter of maximum 40 characters.
     */
    public static String getShortSubjectMatter(String original_subject_matter) {
        String short_subject;
        int length_subject = original_subject_matter.length();
        if (length_subject > 40) {
            short_subject = original_subject_matter.substring(0, 40);
        } else {
            short_subject = original_subject_matter;
        }
        return short_subject;
    }
    /**
     * This method is used for generating the profile table displaying the RTI track records of a given user.
     * This method returns a String which basically contains all the necessary HTML statements for displaying the profile table of the user.
     * Internally it uses ResultSet obtained form LoginDao.java method "getUserProfile(String user)"
     * @param user Takes username in String format.
     * @return String - Returns HTML statements in String format for displaying RTI profile table of given user. 
     */
    public static String getProfileTable(String user) {
        ResultSet rs = LoginDao.getUserProfile(user);
        String value = "";
        try {
            String table_row = "";
            value+="<font size=5>User: " + user + "</font><br><center>";
            value+="<div>Search: <input type=\"text\" id=\"myInput\" onkeyup=\"myFunction()\" placeholder=\"names/address/subject..\" title=\"Type in a name\"></div>";
            value += "<div id=\"tablebody\"><br><table id=\"table1\"  userinfo=\"" + user + "\" border=1 class=\"table table-hover table-responsive\">";
            value += "<tr id=\"theading\"><th>TRACKING NUMBER</th><th>NAME</th><th>RECEIVING CHANNEL</th><th>ADDRESS</th><th>REF. NUMBER</th><th>FEE</th><th>SUBJECT MATTER</th><th>RTI TYPE</th><th>RECEIVING DATE</th><th>FORWARD TO</th>";
            value += "<th>Remaining Days</th></tr>";
            while (rs.next()) {

                String forward[] = rs.getString(10).split(",");
                String dropdown = "<div class=\"dropdown\"><button class=\"dropbtn btn btn-info\">Dropdown</button><div class=\"dropdown-content\"  id=\"" + Integer.toString(rs.getInt(1)) + "\"><a href=\"#\" role=\"button\" class=\"edit btn btn-warning btn-block\" data-toggle=\"modal\" data-target=\"#myModal\">Edit</a>";
                for (int i = 0; i < forward.length; i++) {
                    dropdown += "<a href=\"#\" class=\"contentlink\">" + forward[i] + "</a>";
                }
                dropdown += "</div></div>";
                String sub = rs.getString(4);
                if (sub.length() > 25) {
                    sub = "..." + sub.substring(sub.length() - 24, sub.length());
                }
                String result_date = getRemainingDays(rs.getString(9), rs.getString(8));
                String short_subject = getShortSubjectMatter(rs.getString(7));
                table_row = "<tr><td class=\"tracking\" id=\"" + Integer.toString(rs.getInt(1)) + "\"><a href=\"#\" class=\"rtisubject\" role=\"button\">" + rs.getInt(1) + "</a></td><td>" + rs.getString(2) + "</td><td>" + rs.getString(3) + "</td><td>" + sub + "</td><td>" + rs.getString(5) + "</td><td>" + rs.getInt(6) + "</td><td class=\".subject\">" + short_subject + "...</td><td>" + rs.getString(8) + "</td><td>" + rs.getString(9) + "</td><td style=\"overflow:visible; \">" + dropdown + "</td><td>" + result_date + "</td></tr>";
                value +=table_row;
            }
            value+="</table></div></center>";
            
        } catch (SQLException e) {
            System.out.print("Error:" + e);
            value = "Error in connection with the Database!<br>Please try again.";
            return value;
        }
        return value;
    }
}
