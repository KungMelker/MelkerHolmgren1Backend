/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.melkerholmgrenbackend.melkerholmgren1backend;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Melker
 */
public class TodoServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    private List<todoItem> todoList = new ArrayList<>();
    private String todotext = "";
    private String tododate = "";
    private Boolean todostatus;
    
   
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
       
        
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
            try (PrintWriter out = response.getWriter()) {
            
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet TodoServlet</title>");
            out.println("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">");
            out.println("<script type=\"text/javascript\" src=\"http://code.jquery.com/jquery-2.2.1.min.js\"></script>");
            out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"mystyle.css\">");
            out.println("</head>");
            out.println("<body>");
            out.println("<article>");
            out.println("<h1>Amazing ToDoList!</h1>");
            out.println("<form action=\"TodoServlet\" method=\"POST\">");
            out.println("<label for=\"todotext\">Write what to do:</label>");
            out.println("<input type=\"text\" id=\"todotext\" name=\"todotext\"/>");
            out.println("<label for=\"tododate\">Write when to do what to do:</label>");
            out.println("<input type=\"text\" id=\"tododate\" name=\"tododate\"/>");
            out.println("<label for=\"todostatus\">Done:</label>");
            out.println("<input type=\"checkbox\" id=\"todostatus\" name=\"todostatus\"/>");
            out.println("<input id=\"submit\" type=\"submit\" value=\"Submit to do!\"/>");
            out.println("<span id=\"hidden\"><input type=\"text\" id=\"id\" name=\"id\"/></span>");
            out.println("</form>");
            out.println("<table id=\"table\">");
            out.println("<thead>");
            out.println("<tr>");
            out.println("<th>Memo</th>");
            out.println("<th>Date</th>");
            out.println("<th>Status</th>");
            out.println("<th>Change</th>");
            out.println("<th></th>");
            out.println("</tr>");
            out.println("</thead>");
            out.println("<tbody>");
            out.println(makeHtmlOfArrayList());
            out.println("</tbody>");
            out.println("</table>");
            out.println("</article>");
            out.println("<script type=\"text/javascript\" src=\"myscript.js\"> </script>");
            out.println("</body>");
            out.println("</html>");
            
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
              
        response.setContentType("text/html;charsetUTF-8");
        request.setCharacterEncoding("UTF-8");
        
        if(request.getParameter("id").matches("")){
        
            todotext = request.getParameter("todotext");
            tododate = request.getParameter("tododate");
        
            if (request.getParameter("todostatus") == null){
            todostatus = false;
            }else{
            todostatus = true; 
            }
        
            todoItem newitem = new todoItem(todotext, tododate, todostatus);
            todoList.add(newitem);
        
            PrintWriter out=response.getWriter();
            if( "".equals(todotext) || "".equals(tododate) ){
        
            out.println("Det måste finnas något i fältet");
            //response.sendError(400, "Wrong dara, no description.");
            }else{
            //redirectar till oss själva. du skickar till baka en url.
            out.println(""+todoList.toString());
            }
        }else{
                    
        if(todoList.get(Integer.parseInt(request.getParameter("id"))).isStatus()){
        todoList.get(Integer.parseInt(request.getParameter("id"))).setStatus(false);
        }else{
        todoList.get(Integer.parseInt(request.getParameter("id"))).setStatus(true);
        }
        
        }
        response.sendRedirect(request.getContextPath()+"/TodoServlet");
    }

    /**
     * 
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
    
    
    private String makeHtmlOfArrayList(){
    
     String htmlContent = "";
           
    if(todoList.isEmpty()){ 
        return ""; 
    }else{ 
        for (int i = 0; i<todoList.size(); i++ ){
            
            htmlContent +="<form action=\"TodoServlet\" method=\"POST\"><tr><td>" + todoList.get(i).getTodotext() + 
                    "</td>" + "<td>" + todoList.get(i).getTododate() + "</td>" +"<td>" + doneOrToDoIsTheQuestion(i) +
                    "</td>"+ "<td><input id=\"submit\" type=\"submit\" value=\"Change\"></td><td><span id=\"hidden\"><input type=\"text\" value=\""+i+
                    "\"id=\"id\" name=\"id\"/></span></td></tr></form>"; 
        }
        
        return htmlContent;
    }
    
    
    
    }
   
    
     private String doneOrToDoIsTheQuestion(int index){
    
        if(todoList.get(index).isStatus()){
        return "Done!";
        }
        else{
        return "To do";}
    
    }
    
}
