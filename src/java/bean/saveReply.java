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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This class and its only method is used for saving reply of a particular RTI record. Reply should be in String format.
 * @author Utkarsh Prabhakar
 */
public class saveReply extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String user_info = request.getParameter("user");
        String track_id = request.getParameter("track_id");
        String reply_data = request.getParameter("reply_data");
        //reply_data=URLDecoder.decode(reply_data,"UTF-8");
        response.setContentType("text/plain");
        Connection con=null;
        PreparedStatement ps=null;
        try {
            con = ConnectionProvider.getCon();
            String query = "UPDATE \"SYSTEM\"."+user_info+" SET REPLY = '"+reply_data+"' WHERE TRACKINGNUMBER = "+ Integer.parseInt(track_id) + "";
            ps = con.prepareStatement(query);
            boolean rs = ps.execute();
            response.getWriter().write("Reply added successfully !");
        }
        catch(Exception e){
            StringWriter str = new StringWriter();
            PrintWriter writer = new PrintWriter(str);
            e.printStackTrace(writer);
            System.out.print("error:"+e);
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

}
