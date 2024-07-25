package com.example.classes;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Bibliotheque {
    private Map<String, Livre> livres; // Utilisation de l'interface Map pour la flexibilité

    public Bibliotheque() {
        this.livres = new HashMap<>(); // Utilisation de HashMap pour l'implémentation sous-jacente
    }

    // Ajouter un livre à la bibliothèque
    public void ajouterLivre(Livre livre) {
        livres.put(livre.getIdentifiant(), livre);
    }

    // Obtenir un livre par son code
    public Livre getLivre(String code) {
        return livres.get(code);
    }

    // Supprimer un livre par son code
    public void supprimerLivre(String code) {
        livres.remove(code);
    }

    // Modifier un livre
    public void modifierLivre(String code, Livre nouveauLivre) {
        livres.put(code, nouveauLivre);
    }

    // Rechercher un livre par nom
    public Livre rechercherParNom(String nom) {
        return livres.values().stream()
                .filter(livre -> livre.getTitre().equals(nom))
                .findFirst()
                .orElse(null);
    }

    // Lister les livres dont le nom commence par une lettre alphabétique
    public Map<String, Livre> listerLivresParLettre(char lettre) {
        return livres.values().stream()
                .filter(livre -> livre.getTitre().charAt(0) == lettre)
                .collect(Collectors.toMap(Livre::getIdentifiant, livre -> livre));
    }

    // Afficher le nombre de livres
    public int nombreDeLivres() {
        return livres.size();
    }

    // Afficher les livres par catégorie
    public Map<String, Livre> livresParCategorie(String categorie) {
        return livres.values().stream()
                .filter(livre -> livre.getEditeur().equals(categorie))
                .collect(Collectors.toMap(Livre::getIdentifiant, livre -> livre));
    }
}
