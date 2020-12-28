package com.ex.ers.servlets;

import com.ex.ers.daos.RoleDAOImpl;
import com.ex.ers.daos.UserDAOImpl;
import com.ex.ers.models.Role;
import com.ex.ers.models.User;
import com.ex.ers.utilities.HashingUtil;
import org.hibernate.Session;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

/**
 * Servlet to manage login response and requests
 */
public class LoginServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        if(session.getAttribute("user")!=null){
            req.getRequestDispatcher("EmployeeHomepage.jsp").forward(req, resp);
        }
        else{
            req.getRequestDispatcher("login.jsp").forward(req, resp);
        }
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("email");
        String password = req.getParameter("password");

        UserDAOImpl dao = new UserDAOImpl();
        HashingUtil util = new HashingUtil();
        User user = dao.getByEmail(username);
        boolean isEqual = false;
        if(user!=null){
            try {
                isEqual = util.comparePassword(password,user.getSalt(),user.getHash());
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (InvalidKeySpecException e) {
                e.printStackTrace();
            }
        }

        if(isEqual){
            HttpSession session = req.getSession();
            session.setAttribute("user",user);
            boolean isManager = false;
            for(Role role:user.getRoles()){
                if(role.getName().equals("Manager")){
                    isManager = true;
                }
            }
            session.setAttribute("isManager",isManager);

            if(isManager){
                resp.sendRedirect(req.getContextPath() + "/manager-home");
            }
            else{
                resp.sendRedirect(req.getContextPath() + "/employee-home");
            }
        }
        else{
            resp.sendRedirect(req.getContextPath() + "/login");
        }
    }
}
