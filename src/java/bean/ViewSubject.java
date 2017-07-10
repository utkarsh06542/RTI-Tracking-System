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
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This class and its method is used for displaying complete details of any particular RTI record. Its only method returns(in response) a
 *  String containing HTML statements used for displaying the details of the RTI record.
 * @author Utkarsh Prabhakar
 */
public class ViewSubject extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String user_info = request.getParameter("user_info");
        String tracking_id = request.getParameter("tracking_id");
        Connection con =null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        try {
            con = ConnectionProvider.getCon();
            String query = "SELECT * from \"SYSTEM\"." + user_info + " WHERE TRACKINGNUMBER=" + Integer.parseInt(tracking_id);
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            String html_query="<table class=\"subjectTable table table-striped\" style=\"width:100%;\">";
            
            while(rs.next()){
                html_query+="<tr><td><b>Tracking Number :</b></td><td id=\"track_id\" user=\""+user_info+"\">"+rs.getInt(1)+"</td>"
                        + "<td><b>Name :</b></td><td>"+rs.getString(2)+"</td>"
                        + "<td><b>RTI Type :</b></td><td>"+rs.getString(8)+"</td></tr>"
                        + "<tr><td><b>Receiving Channel :</b></td><td>"+rs.getString(3)+"</td>"
                        + "<td><b>Address :</b></td><td>"+rs.getString(4)+"</td>"
                        + "<td><b>Receiving Date :</b></td><td>"+rs.getString(9)+"</td></tr>"
                        + "<tr><td><b>Ref. Number :</b></td><td>"+rs.getString(5)+"</td>"
                        + "<td><b>Fee Detail(in Rs.) :</b></td><td>"+rs.getInt(6)+"</td>"
                        + "<td><b>Forward To :</b></td><td><input type=\"text\" value=\""+rs.getString(10)+"\" disabled></td></tr></table>"
                        + "<table class=\"subjectTable\"><tr><td><b>Subject Matter :</b></td><td><textarea form=\"insert_record_form\" rows=\"5\" cols=\"50\" disabled>"+rs.getString(7)+"</textarea></td></tr>"
                        + "<tr><td><b>Reply :</b></td><td><textarea id=\"reply\" name=\"Subject\" form=\"insert_record_form\" rows=\"5\" cols=\"50\">"+rs.getString(11)+"</textarea></td></tr></table>";
            }
            html_query+="<button onclick=\"saveReply()\" class=\"btn btn-success btn-lg\" style=\"float:left;\">Save</button>";
            html_query+="<button onclick=\"goBack()\" class=\"btn btn-info btn-lg\" style=\"margin-left:15px;\">Back</button>";;
            response.setContentType("text/plain");
            response.getWriter().write(html_query);

        } catch (Exception e) {
            response.setContentType("text/plain");
            StringWriter str = new StringWriter();
            PrintWriter writer = new PrintWriter(str);
            e.printStackTrace(writer);
            System.out.println(e);

            response.getWriter().write("Error in values or in connection with database");
        }
        finally{
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

    }

}
