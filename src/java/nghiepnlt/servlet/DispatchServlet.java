/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nghiepnlt.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author tanng
 */
@WebServlet(name = "DispatchServlet", urlPatterns = {"/DispatchServlet"})
public class DispatchServlet extends HttpServlet {
    private final String LOGIN_PAGE = "login.html";
    private final String LOGIN_CONTROLLER = "LoginServlet";
    private final String SEARCH_LASTNAME_CONTROLLER = "SearchLastnameServlet";
    private final String DELETE_ACCOUNT_CONTROLLER = "DeleteAccountServlet";
    private final String STARTUP_CONTROLLER = "StartUpServlet";
    private final String UPDATE_ACCOUNT_CONTROLLER = "UpdateAccountServlet";
    private final String ADD_TO_CART_CONTROLLER = "AddToCartServlet";
    private final String VIEW_CART_PAGE = "viewCart.jsp";
    private final String LOGOUT = "Logout";
    private final String GET_PRODUCT_LIST = "GetProductListServlet";
    private final String REMOVE_ITEM_FROM_CART_CONTROLLER = "RemoveItemFromCartServlet";
    private final String CHECKOUT_CONTROLLER = "CheckoutServlet";
    private final String REGISTER_CONTROLLER = "RegisterServlet";
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
        
        //1. Which button did user click?
        String button = request.getParameter("btnAction");
        String url = LOGIN_PAGE;
        try {
           if (button == null){ // first time
               url = STARTUP_CONTROLLER;
               // do nothing -> transfer to LOGIN PAGE
           }
           else if (button.equals("Login")){
               url = LOGIN_CONTROLLER;
           }
           else if (button.equals("Search")){
               url = SEARCH_LASTNAME_CONTROLLER;
           }
           else if (button.equals("Delete")){
               url = DELETE_ACCOUNT_CONTROLLER;
           }
           else if (button.equals("Update")){
               url = UPDATE_ACCOUNT_CONTROLLER;
           }
           else if (button.equals("Add Book To Your Cart")){
               url = ADD_TO_CART_CONTROLLER;
           }
           else if (button.equals("View Your Cart")){
               url = VIEW_CART_PAGE;
           }
           else if (button.equals("Logout")){
               url = LOGOUT;
           }
           else if (button.equals("GetProductList")){
               url = GET_PRODUCT_LIST;
           }
           else if (button.equals("Remove Selected Items")){
               url = REMOVE_ITEM_FROM_CART_CONTROLLER;
           }
           else if (button.equals("Checkout")){
               url = CHECKOUT_CONTROLLER;
           }
           else if (button.equals("Create New Account")){
               url = REGISTER_CONTROLLER;
           }
        } finally{
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
