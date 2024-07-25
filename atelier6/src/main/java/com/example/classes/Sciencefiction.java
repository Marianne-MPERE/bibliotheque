package com.example.classes;

public class Sciencefiction extends Livre {
    private String genreScienceFiction; // Nouveau nom pour la catégorie spécifique à la science-fiction

    public Sciencefiction(String identifiant, String titre, String auteurNom, String editeur, String genre) {
        super(identifiant, titre, auteurNom, editeur, genre);
        this.genreScienceFiction = genre; // Initialisation avec le genre passé au constructeur
    }

    // Getter et Setter pour genreScienceFiction
    public String getGenreScienceFiction() {
        return genreScienceFiction;
    }

    public void setGenreScienceFiction(String genreScienceFiction) {
        this.genreScienceFiction = genreScienceFiction;
    }
}
