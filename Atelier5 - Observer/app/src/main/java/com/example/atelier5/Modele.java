package com.example.atelier5;

//c'est le sujet
public class Modele {

    private int valeur;
    private ChangementObservable obs; // c'est un observateur, il pourrait en avoir plusieurs


    public Modele(ChangementObservable obs) {
        setValeur(14);
        ajouteObservateur(obs);

    }

    public void ajouteObservateur(ChangementObservable observer) {
        obs = observer;
    }


    public void avertitObservateurs() {

        obs.changement(valeur);

    }

    public int getValeur() {
        return valeur;
    }

    // traitement long
    public void setValeur(int valeur) {
        try {
            Thread.sleep(8000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        this.valeur = valeur;
        avertitObservateurs();
    }
}
