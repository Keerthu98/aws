/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.Serializable;

/**
 *
 * @author keerthana
 */
public class Requests implements Serializable {
    public String fullname,location,district,phonenumber,reqitem,date;

    
    public Requests(String fullname,String location,String district,String phonenumber,String reqitem,String date ){
       
        this.fullname=fullname;
        this.location=location;
        this.district=district;
        this.phonenumber=phonenumber;
        this.reqitem=reqitem;
        this.date=date;
        
    }
}
