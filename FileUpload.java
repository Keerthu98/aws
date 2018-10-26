

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.amazonaws.Request;
import java.io.*;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.io.ObjectOutputStream;
import java.util.List;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;

/**
 *
 * @author keerthana
 */
public class FileUpload extends HttpServlet {

    
    String filepath="F:\\Keerthana\\V sem\\Cloud\\aws\\s3\\test.data";
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
       try
    {   
        
        out.println("<!DOCTYPE html> <link rel=\"stylesheet\" href=\"clean_background.css\""); 
        out.println("<html>");
        out.println("<head>");
        out.println("<link rel=\"stylesheet\" href=\"clean_background.css\" ");
        out.println("<h1> Successfully Submitted");
        out.println("<form action=\"Options.html\"><input type='submit' value='home'/></form>");
        out.println("<h1>Name: " + request.getParameter("fullname") + "</h1>");
        out.println("<h1>Location: " + request.getParameter("location") + "</h1>");
        out.println("<h1>Phone: " + request.getParameter("phonenumber") + "</h1>");
        out.println("<h1>District: " + request.getParameter("District") + "</h1>");
        out.println("<h1>Items Requested: " + Arrays.asList(request.getParameter("req_item")).toString()+ "</h1>");        
        
        // Getting all parameters
        String date=new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(new Date());
        String district=request.getParameter("District");
        String fullname=request.getParameter("fullname");
        String location=request.getParameter("location");
        
        String phonenumber=request.getParameter("phonenumber");
        String[] reitems = request.getParameterValues("req_item");
        
        
        //converting array to string
        String emerg_items="";
        for(int i=0;i<reitems.length-1;i++)
        {
            emerg_items+=reitems[i]+",";
        }
        //to remove comma atlast
        emerg_items+=reitems[reitems.length-1];
        
        Requests new_request=new Requests(fullname, location, district, phonenumber, emerg_items,date);
        // Reading this file info test.txt
        try{
            ArrayList<Requests> request_list;
            try (FileInputStream fis = new FileInputStream(filepath); ObjectInputStream ois = new ObjectInputStream(fis)) {
                request_list = (ArrayList<Requests>)ois.readObject();
                //out.println("<h1>Request_count :"+request_list.size()+"</h1>");
                request_list.add(new_request);
            }
            try (FileOutputStream fos = new FileOutputStream(filepath); ObjectOutputStream oos = new ObjectOutputStream(fos)) {
                oos.writeObject(request_list);
            }
        InsertFile();
        }
        //if file doesn't exist, creation of new file takes place
        catch(IOException | ClassNotFoundException e)
        {
             out.println("<h1> New File Created </h1>");
             ArrayList<Requests> request_list = new ArrayList<>();
             request_list.add(new_request);
            try (FileOutputStream fos = new FileOutputStream(filepath); ObjectOutputStream oos = new ObjectOutputStream(fos)) {
                oos.writeObject(request_list);
            }
            InsertFile();
        }
        
        
  
        
    
    
        

        
                 out.println("</body>");
            out.println("</html>");
        
    }
    catch( IOException ex)
    {
        
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
        processRequest(request, response);
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
        processRequest(request, response);
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
    private void InsertFile()
    {
       BasicAWSCredentials creds=null;
        String bucketName = "rescuelife123";
              creds = new BasicAWSCredentials("", "");
      String path=filepath;
      String foldername="s3/test.data";
      System.out.println("File uploaded");
      AmazonS3 s3 = AmazonS3Client.builder().withRegion(Regions.US_EAST_1).withCredentials(new AWSStaticCredentialsProvider(creds)).build();
      PutObjectResult res=s3.putObject(new PutObjectRequest(bucketName, foldername, new File(path)));
        System.out.println( res.toString());
    }
}
