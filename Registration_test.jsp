<%-- 
    Document   : Registration_test
    Created on : Jun 28, 2018, 8:28:03 PM
    Author     : kannan
--%><%@page import="java.util.Random"%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title> Kerala Rescue </title>
        <link rel="stylesheet" href="form.css">

    </head>
    
    <body>
       
        
        <form name="Register" action="FileUpload" method="post"><br/>
            <div>
                District: <select name="District" id="start">
                    <option value="kasaragod">kasaragod</option>
                    <option value="kannur"> kannur </option>
                    <option value="wayanad"> wayanad </option>
                    <option value="kozhikode"> kozhikode </option>
                    <option value="malappuram"> malappuram </option>
                    <option value="palakkad"> palakkad </option>
                    <option value="thrissur"> thrissur </option>
                    <option value="ernakulam"> ernakulam </option>
                    <option value="Idukki"> Idukki </option>
                    <option value="Kottayam"> Kottayam </option>
                    <option value="Alappuzha"> Alappuzha </option>
                    <option value="Pathanamthitta"> Pathanamthitta </option>
                    <option value="Kollam"> Kollam </option>
                    <option value="Thiruvananthapuram"> Thiruvananthapuram </option></br></br>
                    
                </select>
            
                <br/><br>
            Full Name : <br/><input type="text" name="fullname" required/> <br/><br/>
            Location : <br/><input type="text" name="location" required/> <br/><br/>
            Phone Number: <br/><input type="number" name='phonenumber' maxlength=10 minlength=10 required/> <br/><br/>
            Requested Items : <br/>
            <br/>Water<input type ="checkbox" name ="req_item" value="Water" />
            <br/>Medicine<input type ="checkbox" name ="req_item" value="Medicine" /> 
            <br/>Clothes<input type ="checkbox" name ="req_item" value="Clothes" /> 
            <br/>Food<input type ="checkbox" name ="req_item" value="Food" /> <br/> <br/>
            <input type='submit' value='submit'/>
            </div>
        </form>

    
    </body>
            
    
</html>
