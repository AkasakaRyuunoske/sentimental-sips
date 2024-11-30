package sentimental_sips.application.sentimentalsips.Controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import sentimental_sips.application.sentimentalsips.Model.Entity.Carello;
import sentimental_sips.application.sentimentalsips.Model.Service.ProdottoService;
import java.io.IOException;

@WebServlet(name = "HomePageServlet", value = "/HomePage")
public class HomePageServlet extends HttpServlet {

    private ProdottoService prodottoService;

    public void init() {
        prodottoService = new ProdottoService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession(false) == null)
            request.getSession();

        if (request.getSession(false).getAttribute("carello") == null)
            request.getSession().setAttribute("carello", new Carello());

        prodottoService.getProductsForHomePage(request, response);

        getServletContext().getRequestDispatcher("/View/homePage.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response){

    }
}