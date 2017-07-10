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

import java.sql.*;
import javax.activation.DataHandler;
/**
 * This class contains methods to validate Login, get User's RTI profile and insert new RTI records in the database.
* @author Utkarsh Prabhakar
 */
public class LoginDao {
        static ResultSet rs1 = null;
        static Connection con1 = null;
        static Statement ps1 = null;
    
        /**
         * This method is used for checking whether user provided username and password in index.html are correct (as provided in the server database).
         * @param bean Takes the LoginBean bean as input for getting username and password which are stored in it, taken from login input box in index.html
         * @return boolean - Returns a boolean value. True - if the user and password are matched with the database. False- otherwise
         */
    public static boolean validate(LoginBean bean) {
        ResultSet rs = null;
        Connection con = null;
        Statement ps = null;
        boolean status = false;
        try {
            con = ConnectionProvider.getCon();
            ps = con.createStatement();
            rs = ps.executeQuery("select * from \"SYSTEM\".LOGIN where USERNAME='" + bean.getUname() + "' and PASSWORD='" + bean.getPass() + "'");
            status = rs.next();
            return status;
        } catch (Exception e) {
        } finally {
            try{
                if(rs!=null){rs.close();}
            }catch(Exception e){
                System.out.println("Error:"+e);
            }
            try{
                if(ps!=null){ps.close();}
            }catch(Exception e){
                System.out.println("Error:"+e);
            }
            try{
                if(con!=null){con.close();}
            }catch(Exception e){
                System.out.println("Error:"+e);
            }
        }
        return status;

    }
    /**
     * This method is used for insertion of new record whose values are provided by user in AdminPage.jsp.
     * @param bean Takes InsertRecord.java Bean as input for passing values of the record stored from AdminPage.jsp
     * @return boolean - Returns boolean value. True:if record is inserted properly in the database. False:If there is an error in connection or insertion of new record in database.
     */
    public static boolean insert(InsertRecord bean) {
        Connection con = null;
        Statement ps = null;
        boolean status = false;
        try {
            String ReceivingChannel = bean.getReceivingChannel(), Name = bean.getName(), Address = bean.getAddress(), Subject = bean.getSubject(), RtiReference = bean.getRtiReference(), RtiType = bean.getRtiType(), datepicker = bean.getDatepicker(), ForwardList = bean.getForwardList(), user = bean.getUser();
            int Fee = bean.getFee();
            System.out.println("String:"+ForwardList);
            String query = "INSERT INTO \"SYSTEM\"." + user + " (TRACKINGNUMBER,NAME,RECEIVINGCHANNEL,ADDRESS,REFNUMBER,FEE,SUBJECTMATTER,RTITYPE,RECEIVINGDATE,FORWARDTO,REPLY,USERNAME) VALUES (";
            query = query + "TRACK.nextval,'" + Name + "','" + ReceivingChannel + "','" + Address + "','" + RtiReference + "'," + Fee + ",'" + Subject + "','" + RtiType + "','" + datepicker + "','" + ForwardList + "','','" + user + "')";
            con = ConnectionProvider.getCon();
            ps = con.createStatement();
            status = ps.execute(query);
            status = true;
            return status;
        } catch (Exception e) {
            System.out.print(e);
        } finally {
            try{
                if(ps!=null){ps.close();}
            }catch(Exception e){
                System.out.println("Error:"+e);
            }
            try{
                if(con!=null){con.close();}
            }catch(Exception e){
                System.out.println("Error:"+e);
            }
        }
        return status;
    }
    /**
     * This method will be used for getting contents of the RTI track for the given user(taken as input in this method).
     * This returns ResultSet of the profile table once a successful connection is established with the user's table in database.
     * @param user Takes user(String) name as an input for connecting to the user specific database
     * @return ResultSet - returns the ResultSet containing the contents of the profile table of the user
     */
    public static ResultSet getUserProfile(String user) {
        
        try {
            con1 = ConnectionProvider.getCon();
            String query = "SELECT * from \"SYSTEM\"." + user+" ORDER BY TRACKINGNUMBER DESC";
            ps1 = con1.createStatement();
            rs1 = ps1.executeQuery(query);
            return rs1;
        } catch (Exception e) {
            System.out.print("Error:" + e);
        }
        finally{
             //connection will be closed after contents are loaded (in Profile.jsp using closeAll() method of LoginDao.java)
        }

        return rs1;
    }
    /**
     * This method is used for closing ResultSet, PreparedStatement and Connection with database if they exist and are open.
     * This method is useful for closing all open connections with the database once the user is done retrieving informations/values from ResultSet
     */
    public static void closeAll(){
        try{
                if(rs1!=null){rs1.close();}
            }catch(Exception e){
                System.out.println("Error:"+e);
            }
            try{
                if(ps1!=null){ps1.close();}
            }catch(Exception e){
                System.out.println("Error:"+e);
            }
            try{
                if(con1!=null){con1.close();}
            }catch(Exception e){
                System.out.println("Error:"+e);
            }
    }
}
