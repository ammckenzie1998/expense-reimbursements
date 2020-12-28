package com.ex.ers.servlets;

import com.ex.ers.daos.ReimbursementRequestDAO;
import com.ex.ers.daos.ReimbursementRequestDAOImpl;
import com.ex.ers.daos.UserDAOImpl;
import com.ex.ers.models.ReimbursementRequest;
import com.ex.ers.models.Role;
import com.ex.ers.models.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Servlet to handle manager homepage responses and requests
 */
public class ManagerHomepageServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");

        if(user!=null){
            req.setAttribute("user",user);
            ReimbursementRequestDAO dao = new ReimbursementRequestDAOImpl();
            UserDAOImpl userDAO = new UserDAOImpl();
            req.setAttribute("requests",dao.getAll());
            List<User> users = userDAO.getAllUsers();
            List<User> employees = new ArrayList<>();
            for(User u:users){
                for(Role role:u.getRoles()){
                    if(role.getName().equals("EMPLOYEE")){
                        employees.add(u);
                    }
                }
            }
            req.setAttribute("employees",employees);
            req.getRequestDispatcher("ManagerHomepage.jsp").forward(req, resp);
        }
        else{
            resp.sendRedirect(req.getContextPath() + "/login");
        }
    }
}
