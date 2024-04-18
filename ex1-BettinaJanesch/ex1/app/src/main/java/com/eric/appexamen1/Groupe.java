package com.eric.appexamen1;


import java.io.Serializable;

public class Groupe  implements Serializable {
    private String nomCours;
    private int adresseImage;
    private String commentaires;

    public Groupe(String nomCours, int adresseImage, String commentaires) {
        this.nomCours = nomCours;
        this.adresseImage = adresseImage;
        this.commentaires = commentaires;
    }

    public String getNomCours() {
        return nomCours;
    }

    public int getAdresseImage() {
        return adresseImage;
    }

    public String getCommentaires() {
        return commentaires;
    }
}
