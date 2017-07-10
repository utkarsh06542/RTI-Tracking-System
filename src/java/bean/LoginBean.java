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
/**
 * This class is used as a bean to store the values provided by the user for login purposes.
 * It stores username and password.
 * @author Utkarsh Prabhakar
 */
public class LoginBean {  
private String uname,pass;  
/**
 * Returns username in String format.
 * @return 
 */
public String getUname() {  
    return uname;  
}  
 /**
  * Sets the parameter value
  * @param uname - Takes username in String format as input.
  */
public void setUname(String uname) {  
    this.uname = uname;  
}  
/**
 * Returns the password in String format.
 * @return 
 */  
public String getPass() {  
    return pass;  
}  
/**
 * Sets parameter value.
 * @param pass - Takes password in String format as input.
 */
public void setPass(String pass) {  
    this.pass = pass;  
}  
  
  
}
