package sentimental_sips.application.sentimentalsips.Controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import sentimental_sips.application.sentimentalsips.Model.DAO.CategoriaDAO;
import sentimental_sips.application.sentimentalsips.Model.Service.ProdottoService;

import java.io.IOException;

@WebServlet(name = "ProdottiPageController", value = "/Prodotti")
public class ProdottiPageController extends HttpServlet {

    private ProdottoService prodottoService;

    public void init() {
        prodottoService = new ProdottoService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request = prodottoService.validateAndReturnProdottoById(request, response);
        prodottoService.getProductsForHomePage(request, response);
        getServletContext().getRequestDispatcher("/View/prodottiPage.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}