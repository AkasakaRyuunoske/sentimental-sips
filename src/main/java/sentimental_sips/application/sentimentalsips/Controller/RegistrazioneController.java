package sentimental_sips.application.sentimentalsips.Controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import sentimental_sips.application.sentimentalsips.Model.Service.LogOutService;
import sentimental_sips.application.sentimentalsips.Model.Service.RegistrazioneService;
import java.io.IOException;

@WebServlet(name = "RegistrazioneController", value = "/user/RegistrazionePage")
public class RegistrazioneController extends HttpServlet {

    private RegistrazioneService registrazioneService;
    private LogOutService logOutService;

    public void init() {
        registrazioneService = new RegistrazioneService();
        logOutService = new LogOutService();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        if (logOutService.isLoggedIn(request)) {
            // If the user is already logged in, redirect them to the homepage
            response.sendRedirect(request.getContextPath() + "/HomePage");
        } else {
            // If the user is not logged in, show the registration page
            request.getRequestDispatcher("/View/registrazionePage.jsp").forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        registrazioneService.registrazione(request, response);
    }
}
