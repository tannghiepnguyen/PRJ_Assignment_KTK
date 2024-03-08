/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nghiepnlt.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import nghiepnlt.registration.RegistrationCreateError;
import nghiepnlt.registration.RegistrationDAO;
import nghiepnlt.registration.RegistrationDTO;

/**
 *
 * @author tanng
 */
public class RegisterServlet extends HttpServlet {
    private final String ERROR_PAGE = "register.jsp";
    private final String LOGIN_PAGE = "login.html";
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
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        //1. get all parameters
        String username = request.getParameter("txtUsername");
        String password = request.getParameter("txtPassword");
        String confirm = request.getParameter("txtConfirm");
        String fullname = request.getParameter("txtFullname");
        RegistrationCreateError errors = new RegistrationCreateError();
        boolean foundErr = false;
        String url = ERROR_PAGE;
        try {
            //2. check all user's errors
            if (username.trim().length() < 6 || username.trim().length() > 20){
                foundErr = true;
                errors.setUsernameLengthError("Username is required input from 6 to 20 characters");
            }
            if (password.trim().length() < 6 || password.trim().length() > 30){
                foundErr = true;
                errors.setPasswordLengthError("Password is required input from 6 to 30 characters");
            }
            else if (!confirm.trim().equals(password.trim())){
                foundErr = true;
                errors.setConfirmNoMatched("Confirm must match Password");
            }
            if (fullname.trim().length() < 2 || fullname.trim().length() > 50){
                foundErr = true;
                errors.setFullnameLengthError("Fullname is required input from 2 to 50 characters");
            }
            if (foundErr){
                //caching to attribute and tranfer to error page
                request.setAttribute("CREATE_ERRORS", errors);
            }
            else{
                //3. call DAO
                RegistrationDAO dao = new RegistrationDAO();
                RegistrationDTO dto = new RegistrationDTO(username, password, fullname, false);
                boolean result = dao.createAccount(dto);
                if (result){
                    url = LOGIN_PAGE;
                }
            }
            
            //4. process result
        } catch (SQLException ex) {
            log("RegisterServlet_SQL: " + ex.getMessage());
            String msg = ex.getMessage();
            if (msg.contains("duplicate")){
                errors.setUsernameIsExisted(username + " is existed");
                request.setAttribute("CREATE ERRORS", errors);
            }
        } catch (NamingException ex) {
            log("RegisterServlet_Naming: " + ex.getMessage()); 
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
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

}
