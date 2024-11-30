package sentimental_sips.application.sentimentalsips.Model.Entity;

public class ProdottoNelCarello {
    private Prodotto prodotto;
    private int quantita_selezionata;

    public Prodotto getProdotto() {
        return prodotto;
    }

    public void setProdotto(Prodotto prodotto) {
        this.prodotto = prodotto;
    }

    public int getQuantita_selezionata() {
        return quantita_selezionata;
    }

    public void setQuantita_selezionata(int quantita_selezionata) {
        this.quantita_selezionata = quantita_selezionata;
    }

    public ProdottoNelCarello(Prodotto prodotto, int quantita_selezionata) {
        this.prodotto = prodotto;
        this.quantita_selezionata = quantita_selezionata;
    }

    @Override
    public String toString() {
        return "ProdottoNelCarello{" +
                "prodotto=" + prodotto +
                ", quantita_selezionata=" + quantita_selezionata +
                '}';
    }
}
