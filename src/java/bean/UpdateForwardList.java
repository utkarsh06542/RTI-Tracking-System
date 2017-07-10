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


import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This class is used for saving the new entries of "Forward To" list in the database. It also contains method to send e-mails to
 *  the newly added staffs.
 * @author Utkarsh Prabhakar
 */
public class UpdateForwardList extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String user_info = request.getParameter("user_info");
        String tracking_id = request.getParameter("tracking_id");
        String forward_data = request.getParameter("forward_data");
        String email_data = request.getParameter("email_data");
        //sendEmail(email_data);//function to send Emails to the staffs
        Connection con=null;
        PreparedStatement ps=null;
        try {
            con = ConnectionProvider.getCon();
            String query = "UPDATE \"SYSTEM\"." + user_info + " SET FORWARDTO = '" + forward_data + "' WHERE TRACKINGNUMBER = " + Integer.parseInt(tracking_id) + "";
            ps = con.prepareStatement(query);
            boolean status=ps.execute();
            response.setContentType("text/plain");
            response.getWriter().write("Records added successfully !");
            
        } catch (Exception e) {
            response.setContentType("text/plain");
            StringWriter str = new StringWriter();
            PrintWriter writer = new PrintWriter(str);
            e.printStackTrace(writer);
            
            response.getWriter().write("Error in values or in connection with database");
        }
        finally{
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
    }
    /**
     * This method is used for sending e-mails to the newly added staffs. Returns nothing.
     * @param email_data Takes the name of staffs separated by commas in String format. Example:"john,ram,orangutan".
     */
    public void sendEmail(String email_data){
        //email data retrived here are the newly added ones or those whose value has been changed(new value received).
    }

}
