package com.example.atelier2_json_volley;

import java.util.List;

public class ListeProduits {

    // represente la liste articles de JSON

    private List<Produit> articles;

    public ListeProduits(List<Produit> articles) {
        this.articles = articles;
    }
    public List<Produit> getArticles() {
        return articles;
    }
}
