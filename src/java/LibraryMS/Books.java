/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package LibraryMS;


import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


@WebServlet("/BooksServlet")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 10, // 10 MB
    maxFileSize = 1024 * 1024 * 1000, // 1 GB
    maxRequestSize = 1024 * 1024 * 1000)   	// 1 GB
public class Books extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    public static int BUFFER_SIZE = 1024 * 100;
    public static final String UPLOAD_DIR = "resources";
    public static String fileName = null;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
     
            
    
             fileName = request.getParameter("filename");
             if (fileName == null || fileName.equals("")) {
        
                 response.setContentType("text/html");
             
                  response.getWriter().println("<h3>File " + fileName + " Is Not Present .....!</h3>");
                 } 
              else {
            String applicationPath = getServletContext().getRealPath("");
            String downloadPath = applicationPath + File.separator + UPLOAD_DIR;
            String filePath = downloadPath + File.separator + fileName;
            System.out.println(fileName);
            System.out.println(filePath);
            System.out.println("fileName:" + fileName);
            System.out.println("filePath :" + filePath);
            File file = new File(filePath);
            OutputStream outStream = null;
            FileInputStream inputStream = null;

            if (file.exists()) {

               
                String mimeType = "application/octet-stream";
                response.setContentType(mimeType);

               
                String headerKey = "Content-Disposition";
                String headerValue = String.format("attachment; filename=\"%s\"", file.getName());
                response.setHeader(headerKey, headerValue);

                try {

                   
                    outStream = response.getOutputStream();
                    inputStream = new FileInputStream(file);
                    byte[] buffer = new byte[BUFFER_SIZE];
                    int bytesRead = -1;

                    
                    while ((bytesRead = inputStream.read(buffer)) != -1) {
                        outStream.write(buffer, 0, bytesRead);
                    }
                } catch (IOException ioExObj) {
                    System.out.println("Exception While Performing The I/O Operation?= " + ioExObj.getMessage());
                }
                finally {
                    if (inputStream != null) {
                        inputStream.close();
                    }

                    outStream.flush();
                    if (outStream != null) {
                        outStream.close();
                    }
                }
               }
                
              else {
                response.setContentType("text/html");
                response.getWriter().println("<h3>File " + fileName + " Is Not Present .....!</h3>");
                   }
                }
             
              } 
  
   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

            String action = request.getParameter("action");
           if(action.equals("add")){
                          String BookId = request.getParameter("bookId");
                          String Title = request.getParameter("title");
                          String Author = request.getParameter("author");
                          String Edition =request.getParameter("edition");
                          String Genre =request.getParameter("genre");
                          String Publisher = request.getParameter("publisher");
                          String Pbdate = request.getParameter("PublicationDate");
                          Part filePart = request.getPart("filename");
                        
                    
            

                  try{
                         
                         String folderName = "resources";
                         String uploadPath = request.getServletContext().getRealPath("") + File.separator + folderName;//for netbeans use this code
                         File dir = new File(uploadPath);
                         if (!dir.exists()) {
                           dir.mkdirs();
                              }
                         String fileName = filePart.getSubmittedFileName();
                         String path = folderName + File.separator + fileName;
                         InputStream is = filePart.getInputStream();
                         Files.copy(is, Paths.get(uploadPath + File.separator + fileName), StandardCopyOption.REPLACE_EXISTING);
                         
                         Class.forName("com.mysql.cj.jdbc.Driver");
                          Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/LibraryMS", "root", "");
                         
                         PreparedStatement statement = con
                    .prepareStatement("insert into books1 values(?,?,?,?,?,?,?,?,?)");
                         
            statement.setString(1,BookId);
            statement.setString(2,Title);
            statement.setString(3,Author);
            statement.setInt(4,Integer.parseInt(Edition));
            statement.setString(5,Genre);
            statement.setString(6,Publisher);
            statement.setString(7,Pbdate);
            statement.setString(8, fileName);
            statement.setString(9, path);
            statement.executeUpdate();
         
                
            
            
                   out.println("<!DOCTYPE html>");
                   out.println("<html>");
                   out.println("<head>");
                   out.println("<title>Servlet Books</title>");            
                   out.println("</head>");
                   out.println("<body>");
                   out.println("<h1> ADDED SUCCESSFULLY</h1>");
                   out.println("<p><a href='index.html'> back to home </a></p>");
                   out.println("</body>");
                   out.println("</html>");
                   con.close();
                  } catch (ClassNotFoundException e) {
                out.println("ClassNotFoundException: " + e.getMessage());
            } catch (SQLException e) {
                out.println("SQLException: " + e.getMessage());
            }
                   
                   
                   
            }
           
           else if(action.equals("update")){
           
                          String BookId = request.getParameter("bookId");
                          String Title = request.getParameter("title");
                          String Author = request.getParameter("author");
                          String Edition =request.getParameter("edition");
                          String Genre =request.getParameter("genre");
                          String Publisher = request.getParameter("publisher");
                          String Pbdate = request.getParameter("PublicationDate");
                          Part filePart = request.getPart("filename");
                        
                        String folderName = "resources";
                         String uploadPath = request.getServletContext().getRealPath("") + File.separator + folderName;//for netbeans use this code
                         File dir = new File(uploadPath);
                         if (!dir.exists()) {
                           dir.mkdirs();
                              }
                         String fileName = filePart.getSubmittedFileName();
                         String path = folderName + File.separator + fileName;
                         InputStream is = filePart.getInputStream();
                         Files.copy(is, Paths.get(uploadPath + File.separator + fileName), StandardCopyOption.REPLACE_EXISTING);
         
                  try{
                           Class.forName("com.mysql.cj.jdbc.Driver");
                          Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/LibraryMS", "root", "");
                         
                         PreparedStatement statement1 = con
                        .prepareStatement("update books1 set Title = '" + Title + "' where BookID = '" + BookId + "'"); 
                PreparedStatement statement2 = con
                        .prepareStatement("update books1 set Author = '" + Author + "' where BookID = '" + BookId + "'");
                PreparedStatement statement3 = con
                        .prepareStatement("update books1 set Edition = '" + Edition + "' where BookID = '" + BookId + "'");
                   PreparedStatement statement4 = con
                        .prepareStatement("update books1 set genre = '" + Genre + "' where BookID = '" + BookId + "'");
                PreparedStatement statement5 = con
                        .prepareStatement("update books1 set Publisher = '" + Publisher + "' where BookID = '" + BookId + "'");
                PreparedStatement statement6 = con
                        .prepareStatement("update books1 set PublicationDate = '" + Pbdate + "' where BookID = '" + BookId + "'");
                PreparedStatement statement7 = con
                        .prepareStatement("update books1 set filename = '" + fileName + "' where BookID = '" + BookId + "'");
                PreparedStatement statement8 = con
                        .prepareStatement("update books1 set path = '" + path + "' where BookID = '" + BookId + "'");
                statement1.executeUpdate();
                statement2.executeUpdate();
                statement3.executeUpdate();
                statement4.executeUpdate();
                statement5.executeUpdate();
                statement6.executeUpdate();
                statement7.executeUpdate();
                statement8.executeUpdate();
               
                
                             out.println("<!DOCTYPE html>");
                   out.println("<html>");
                   out.println("<head>");
                   out.println("<title>Servlet Books</title>");            
                   out.println("</head>");
                   out.println("<body>");
                   out.println("<h1> UPDATED SUCCESSFULLY</h1>");
                   out.println("<p><a href='index.html'> back to home </a></p>");
                   out.println("</body>");
                   out.println("</html>");
                   con.close();
                         } catch (ClassNotFoundException e) {
                out.println("ClassNotFoundException: " + e.getMessage());
            } catch (SQLException e) {
                out.println("SQLException: " + e.getMessage());
            }
           }
           
           else if(action.equals("delete")){
           
            String BookId = request.getParameter("bookId");
             try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/LibraryMS", "root", "");
                
                PreparedStatement statement1 = con
                        .prepareStatement("delete from books1 where BookID = '" + BookId + "'");
                statement1.executeUpdate();
                
                out.println("<!DOCTYPE html>");
                   out.println("<html>");
                   out.println("<head>");
                   out.println("<title>Servlet Books</title>");            
                   out.println("</head>");
                   out.println("<body>");
                   out.println("<h1> DELETED SUCCESSFULLY</h1>");
                   out.println("<p><a href='index.html'> back to home </a></p>");
                   out.println("</body>");
                   out.println("</html>");
                   con.close();
                 } catch (ClassNotFoundException e) {
                out.println("ClassNotFoundException: " + e.getMessage());
            } catch (SQLException e) {
                out.println("SQLException: " + e.getMessage());
            }
           }
           else if(action.equals("search")){
            try{   
                
                         String BookId = request.getParameter("bookId"); 
                         
                           Class.forName("com.mysql.cj.jdbc.Driver");
                          Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/LibraryMS", "root", ""); 
                             String query="select * from books1 where BookID = '" + BookId + "'";
            PreparedStatement pStatement = con.prepareStatement(query);
            ResultSet rs= pStatement.executeQuery(); 
            
            response.setContentType("text/html");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>BOOKS Lists</title>");
                out.println("</head>");
                out.println("<body>");
                out.println("<h1>BOOKS Lists</h1>");
                out.println("<table border='1'>");
                out.println("<tr>");
                out.println("<th style='padding: 20px'>BookID</th>");
                out.println("<th style='padding: 20px'>Title</th>");
                out.println("<th style='padding: 20px'>Author</th>");
                out.println("<th style='padding: 20px'>Edition</th>");
                out.println("<th style='padding: 20px'>Genre</th>");
                out.println("<th style='padding: 20px'>Publisher</th>");
                out.println("<th style='padding: 20px'>PublicationDate</th>");
                out.println("<th style='padding: 20px'>File</th>");
                out.println("</tr>");
                
                while (rs.next()){
                    out.println("<tr>");
                    out.println("<td style='padding: 20px'>" + rs.getInt("bookId") + "</td>");
                    out.println("<td style='padding: 20px'>" + rs.getString("title") + "</td>");
                    out.println("<td style='padding: 20px'>" + rs.getString("author") + "</td>");
                    out.println("<td style='padding: 20px'>" + rs.getInt("edition") + "</td>");
                    out.println("<td style='padding: 20px'>" + rs.getString("genre") + "</td>");
                    out.println("<td style='padding: 20px'>" + rs.getString("publisher") + "</td>");
                    out.println("<td style='padding: 20px'>" + rs.getString("PublicationDate") + "</td>");
                    out.println("<td style='padding: 20px'>" + rs.getString("filename") + "</td>");
                    out.println("<td>");
                    out.println("</tr>");
                }
                out.println("</table>");
                out.println("<a href='index.html'> back to home </a>");
                out.println("</body>");
                out.println("</html>");
                  
            }catch (ClassNotFoundException e) {
                 out.println("ClassNotFoundException: " + e.getMessage());
            } catch (SQLException e) {
                out.println("SQLException: " + e.getMessage());
            }
           }
               
                
    }
     
    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
