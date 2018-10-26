/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.S3Object;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.lang.System.*;
import static java.lang.System.out;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author keerthana
 */
public class FileRetrieval extends HttpServlet {

       BasicAWSCredentials creds=null; 
      
   
    private  String bucketName = "rescuelife123";
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException{
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try
        {
        //try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
  
     
    
      creds = new BasicAWSCredentials("AKIAJDSNDKNOXG34S27A", "VWCwvBBXz8wMQaip7qpXP3uH916dTMA7vDDYMeCt");
      String filepath ="F:\\Keerthana\\V sem\\Cloud\\aws\\s3\\test\\test.data";
      String foldername="s3/test.data";
      AmazonS3 s3 = AmazonS3Client.builder().withRegion(Regions.US_EAST_1).withCredentials(new AWSStaticCredentialsProvider(creds)).build();
      S3Object s3object = s3.getObject(bucketName, foldername);
        try (InputStream inputStream = s3object.getObjectContent()) {
            if(Files.deleteIfExists(Paths.get("F:\\Keerthana\\V sem\\Cloud\\aws\\s3\\test\\test.data")));
            Files.copy(inputStream, Paths.get(filepath));
        }
        
        FileInputStream fin = new FileInputStream(filepath);
        ObjectInputStream ois = new ObjectInputStream(fin);
        ArrayList<Requests> requestList = (ArrayList<Requests>) ois.readObject();
        out.println("<!DOCTYPE html> "); 
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Registered Request</title>");
        out.println("<link rel=\"stylesheet\" href=\"clean_background.css\" ");
        out.println("</head>");
        out.println("<body>");
        out.println("<table border=\"2\">");
        out.println("  <tr>" );
                   out.println(   "<th>Name</th>" );
                       out.println(   "<th>District</th>" );
                        out.println(   "<th>Request_items</th>");
                       out.println(   "<th>Location</th>" );
                       out.println(   "<th>Date</th>" );
                        out.println(   "<th>Phonenumber</th>");
                        out.println("</tr>");
        for(int i=0; i < requestList.size();i++)
        {
            Requests request_temp = requestList.get(i);
            out.println(" <tr >");
 out.println(" <td>"+request_temp.fullname+"</td>" );
 out.println("                         <td>"+request_temp.district+"</td>" );
 out.println("                         <td>"+request_temp.reqitem+"</td>" );
 out.println("                         <td>"+request_temp.location+"</td>" );
 out.println("                         <td>"+request_temp.date + "</td>" );
 out.println("                         <td>"+request_temp.phonenumber + "</td>" );
 out.println("                     </tr>");
        }
        out.println("</body>");
        out.println("</html>");
        
 
}
          catch( Exception ex)
    {
        out.println("filehhhh"+ex);
    }
}
    
   
    

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
           try {
               processRequest(request, response);
           } catch (ClassNotFoundException ex) {
               Logger.getLogger(FileRetrieval.class.getName()).log(Level.SEVERE, null, ex);
           }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
           try {
               processRequest(request, response);
           } catch (ClassNotFoundException ex) {
               Logger.getLogger(FileRetrieval.class.getName()).log(Level.SEVERE, null, ex);
           }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
