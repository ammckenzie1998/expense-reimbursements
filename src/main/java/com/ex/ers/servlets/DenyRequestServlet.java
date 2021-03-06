package com.ex.ers.servlets;

import com.ex.ers.daos.ReimbursementRequestDAO;
import com.ex.ers.daos.ReimbursementRequestDAOImpl;
import com.ex.ers.daos.RequestHistoryDAO;
import com.ex.ers.daos.RequestHistoryDAOImpl;
import com.ex.ers.models.ReimbursementRequest;
import com.ex.ers.models.RequestHistory;
import com.ex.ers.models.RequestStatus;
import com.ex.ers.models.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Servlet to handle reimbursement request denial responses and requests
 */
public class DenyRequestServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        int requestID = Integer.parseInt(req.getParameter("id"));

        if(user!=null){
            ReimbursementRequestDAO dao = new ReimbursementRequestDAOImpl();
            RequestHistoryDAO requestHistoryDAO = new RequestHistoryDAOImpl();
            ReimbursementRequest request = dao.getByID(requestID);
            request.setStatus(RequestStatus.DENIED.toString());
            dao.update(request);

            RequestHistory history = new RequestHistory();
            history.setUser(user);
            history.setDescription("Denied Reimbursement Request");
            history.setStatus(RequestStatus.DENIED.toString());
            history.setReimbursementRequest(request);
            requestHistoryDAO.create(history);
            req.setAttribute("request",request);

            resp.sendRedirect(req.getContextPath() + "/manager-home");
        }
        else{
            resp.sendRedirect(req.getContextPath() + "/login");
        }
    }
}
