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
import static bean.Provider.*;
/**
 * This class provides method used for establishing a new connection with the database.
 * @author Utkarsh Prabhakar
 */
public class ConnectionProvider {
    /**
     * This method establishes connection by taking parameters initialized in 
     * Provider.java class of bean package.
     * @return con - Returns the established connection with the database.
     */
    public static Connection getCon() {
        Connection con = null;
        try {
            Class.forName(DRIVER);
            con = DriverManager.getConnection(CONNECTION_URL, "system", "password");
            return con;
        } catch (Exception e) {
        }
        return con;  
    }
}