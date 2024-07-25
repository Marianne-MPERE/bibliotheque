package com.example.classes;

public class Roman extends Livre {
    private String genreRoman; // Nouveau nom pour la catégorie spécifique aux romans

    public Roman(String identifiant, String titre, String auteurNom, String editeur, String genre) {
        super(identifiant, titre, auteurNom, editeur, genre);
        this.genreRoman = genre; // Initialisation avec le genre passé au constructeur
    }

    // Getter et Setter pour genreRoman
    public String getGenreRoman() {
        return genreRoman;
    }

    public void setGenreRoman(String genreRoman) {
        this.genreRoman = genreRoman;
    }
}
