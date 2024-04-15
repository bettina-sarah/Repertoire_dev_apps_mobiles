package com.example.atelier2_json_volley;

public class Produit {

    private String nom;
    private String prix;

    public Produit(String nom, String prix) {
        this.nom = nom;
        this.prix = prix;
    }

    public static class Builder {
        private String nom;
        private String prix;

        public Builder() {}

        public Builder setNom(String nom) {
            this.nom = nom;
            return this;
        }

        public Builder setPrix(String prix) {
            this.prix = prix;
            return this;
        }

        public Produit build() {
            return new Produit(nom,prix);
        }


    }
}
