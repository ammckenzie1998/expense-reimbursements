package com.ex.ers.servlets;

import com.ex.ers.daos.ReimbursementRequestDAO;
import com.ex.ers.daos.ReimbursementRequestDAOImpl;
import com.ex.ers.daos.UserDAOImpl;
import com.ex.ers.models.ReimbursementRequest;
import com.ex.ers.models.User;
import com.ex.ers.utilities.HashingUtil;
import com.ex.ers.utilities.PasswordPackage;
import org.apache.commons.codec.DecoderException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

/**
 * Servlet to handle user info responses and requests
 */
public class EditUserInfoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");

        if(user!=null){
            req.setAttribute("user",user);

            req.getRequestDispatcher("EditUserInfo.jsp").forward(req, resp);
        }
        else{
            resp.sendRedirect(req.getContextPath() + "/login");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        int modifiedID = Integer.parseInt(req.getParameter("id"));

        if(user!=null && modifiedID == user.getUserID()){
            String email = req.getParameter("email");
            String firstName = req.getParameter("firstName");
            String lastName = req.getParameter("lastName");
            String password = req.getParameter("password");
            String phoneNumber = req.getParameter("phoneNumber");
            String employeeID = req.getParameter("employeeID");

            UserDAOImpl dao = new UserDAOImpl();

            if(password!= null && password != ""){
                HashingUtil hashingUtil = new HashingUtil();
                try {
                    PasswordPackage passwordPackage = hashingUtil.hashPassword(password);
                    user.setHash(passwordPackage.getHash());
                    user.setSalt(passwordPackage.getSalt());
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                } catch (InvalidKeySpecException e) {
                    e.printStackTrace();
                } catch (DecoderException e) {
                    e.printStackTrace();
                }
            }
            user.setEmail(email);
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setPhoneNumber(phoneNumber);
            user.setEmployeeID(employeeID);
            dao.update(user);
            session.setAttribute("user",user);

            if(session.getAttribute("isManager").equals(true)){
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
