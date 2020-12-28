package com.ex.ers.servlets;

import com.ex.ers.daos.RequestItemDAO;
import com.ex.ers.daos.RequestItemDAOImpl;
import com.ex.ers.models.RequestItem;
import com.ex.ers.models.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Servlet to handle request item deletion responses and requests
 */
public class DeleteRequestItemServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");

        if(user!=null){
            int itemID = Integer.parseInt(req.getParameter("id"));
            RequestItemDAO dao = new RequestItemDAOImpl();
            RequestItem item = dao.getByID(itemID);
            dao.delete(itemID);
            resp.sendRedirect(req.getContextPath() + "/view-request?id="+item.getReimbursementRequest().getRequestID());
        }
        else{
            resp.sendRedirect(req.getContextPath() + "/login");
        }
    }
}
