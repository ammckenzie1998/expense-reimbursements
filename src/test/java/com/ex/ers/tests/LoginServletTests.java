package com.ex.ers.tests;

import com.ex.ers.models.User;
import com.ex.ers.servlets.LoginServlet;
import org.junit.Test;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class LoginServletTests {
    @Test
    public void shouldForwardToHomepageFromLogin() throws ServletException, IOException {
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);

        User user = new User();
        user.setFirstName("Bob");
        user.setLastName("Billy");

        when(req.getSession()).thenReturn(session);
        when(session.getAttribute("user")).thenReturn(user);
        when(req.getRequestDispatcher("EmployeeHomepage.jsp")).thenReturn(dispatcher);

        LoginServlet loginServlet = new LoginServlet();
        loginServlet.doGet(req,resp);
    }

    @Test
    public void shouldRedirectToLoginSinceUserIsNull() throws ServletException, IOException {
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);

        when(req.getSession()).thenReturn(session);
        when(session.getAttribute("user")).thenReturn(null);
        when(req.getRequestDispatcher("login.jsp")).thenReturn(dispatcher);

        LoginServlet loginServlet = new LoginServlet();
        loginServlet.doGet(req,resp);
    }

    @Test
    public void shouldTestPostAndRedirectToManagerHome() throws ServletException, IOException {
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);

        when(req.getParameter("email")).thenReturn("testmanager@gmail.com");
        when(req.getParameter("password")).thenReturn("password");
        when(req.getSession()).thenReturn(session);

        LoginServlet loginServlet = new LoginServlet();
        loginServlet.doPost(req,resp);
    }

    @Test
    public void shouldTestPostAndRedirectToEmployeeHome() throws ServletException, IOException {
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);

        when(req.getParameter("email")).thenReturn("testuser@gmail.com");
        when(req.getParameter("password")).thenReturn("password");
        when(req.getSession()).thenReturn(session);

        LoginServlet loginServlet = new LoginServlet();
        loginServlet.doPost(req,resp);
    }

    @Test
    public void shouldTestPostAndRedirectToLogin() throws ServletException, IOException {
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);

        when(req.getParameter("email")).thenReturn("");
        when(req.getParameter("password")).thenReturn("");
        when(req.getSession()).thenReturn(session);

        LoginServlet loginServlet = new LoginServlet();
        loginServlet.doPost(req,resp);
    }
}
