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
 * This class acts a bean and is used to store the parameter values provided by the user for inserting new record 
 * in the RTI track record.
 * @author Utkarsh Prabhakar
 */
public class InsertRecord {
    private String receivingChannel,name,address,rtiReference,subject,rtiType,datepicker,forwardList,user;
    private int fee;
    public String getReceivingChannel(){
        return receivingChannel;
    }
    public void setReceivingChannel(String receivingChannel){
        this.receivingChannel=receivingChannel;
    }
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name=name;
    }
    public String getSubject(){
        return subject;
    }
    public void setSubject(String subject){
        this.subject=subject;
    }
    public String getAddress(){
        return address;
    }
    public void setAddress(String address){
        this.address=address;
    }
    public String getRtiReference(){
        return rtiReference;
    }
    public void setRtiReference(String rtiReference){
        this.rtiReference=rtiReference;
    }
    public String getRtiType(){
        return rtiType;
    }
    public void setRtiType(String rtiType){
        this.rtiType=rtiType;
    }
    public String getDatepicker(){
        return datepicker;
    }
    public void setDatepicker(String datepicker){
        this.datepicker=datepicker;
    }
    public String getUser(){
        return user;
    }
    public void setUser(String user){
        this.user=user;
    }
    public String getForwardList(){
        return forwardList;
    }
    public void setForwardList(String forwardList){
        this.forwardList=forwardList;
    }
    public int getFee(){
        return fee;
    }
    public void setFee(int fee){
        this.fee=fee;
    }
}
