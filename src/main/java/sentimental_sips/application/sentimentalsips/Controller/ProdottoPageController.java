package sentimental_sips.application.sentimentalsips.Controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import sentimental_sips.application.sentimentalsips.Model.Service.ProdottoService;

import java.io.IOException;


@WebServlet(name = "ProductPageController", value = "/ProductPage")
public class ProdottoPageController extends HttpServlet {

    private ProdottoService prodottoService;
    public void init() {
        prodottoService = new ProdottoService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request = prodottoService.validateAndReturnProdottoById(request, response);
        prodottoService.getProductsForHomePage(request, response);
        getServletContext().getRequestDispatcher("/View/prodottoPage.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
