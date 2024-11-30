package sentimental_sips.application.sentimentalsips.Controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import sentimental_sips.application.sentimentalsips.Model.Service.CarelloService;

import java.io.IOException;

@WebServlet(name = "CarelloPageController", value = "/carello")
public class CarelloController extends HttpServlet {
    private CarelloService carelloService = new CarelloService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        carelloService.getProdotti(request, response);
        getServletContext().getRequestDispatcher("/View/carelloPage.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        carelloService.addProdotto(request, response);
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) {
        carelloService.deleteProdotto(request, response);
    }
}
