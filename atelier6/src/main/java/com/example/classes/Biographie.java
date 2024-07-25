package com.example.classes;
public class Biographie extends Livre {
    private String genreBiographie; // Nouveau nom pour la catégorie spécifique à la biographie

    public Biographie(String identifiant, String titre, String auteurNom, String editeur, String genre) {
        super(identifiant, titre, auteurNom, editeur, genre);
        this.genreBiographie = genre; // Initialisation avec le genre passé au constructeur
    }

    // Getter et Setter pour genreBiographie
    public String getGenreBiographie() {
        return genreBiographie;
    }

    public void setGenreBiographie(String genreBiographie) {
        this.genreBiographie = genreBiographie;
    }
}
