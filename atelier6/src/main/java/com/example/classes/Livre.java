package com.example.classes;

public class Livre {
    private String identifiant; // ID du livre
    private String titre; // Titre du livre
    private String auteurNom; // Nom de l'auteur
    private String editeur; // Maison d'édition
    private String genre; // Catégorie

    // Constructeur
    public Livre(String identifiant, String titre, String auteurNom, String editeur, String genre) {
        this.identifiant = identifiant;
        this.titre = titre;
        this.auteurNom = auteurNom;
        this.editeur = editeur;
        this.genre = genre;
    }

    // Getters et Setters
    public String getIdentifiant() {
        return identifiant;
    }

    public void setIdentifiant(String identifiant) {
        this.identifiant = identifiant;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getAuteurNom() {
        return auteurNom;
    }

    public void setAuteurNom(String auteurNom) {
        this.auteurNom = auteurNom;
    }

    public String getEditeur() {
        return editeur;
    }

    public void setEditeur(String editeur) {
        this.editeur = editeur;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
}
