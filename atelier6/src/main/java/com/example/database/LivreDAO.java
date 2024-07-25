package com.example.database;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import com.example.classes.Livre;

public class LivreDAO {
    private Connection connection;

    public LivreDAO(Connection connection) {
        this.connection = connection;
    }

    public static void getLivreParCode(LivreDAO livreDAO, Scanner scanner) {
        System.out.print("Entrez le code du livre : ");
        String code = scanner.nextLine();
        try {
            Livre livre = livreDAO.getLivre(code);
            if (livre != null) {
                System.out.println("Le livre obtenu est : " + livre.getTitre());
            } else {
                System.out.println("Aucun livre trouvé avec le code " + code);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void modifierLivre(LivreDAO livreDAO, Scanner scanner) {
        System.out.print("Entrez le code du livre à modifier : ");
        String codeModif = scanner.nextLine();
        try {
            Livre livreExistant = livreDAO.getLivre(codeModif);
            if (livreExistant != null) {
                System.out.print("Entrez le nouveau titre du livre : ");
                String nouveauTitre = scanner.nextLine();
                System.out.print("Entrez le nouvel auteur du livre : ");
                String nouvelAuteur = scanner.nextLine();
                System.out.print("Entrez la nouvelle maison d'édition du livre : ");
                String nouvelleMaisonEdition = scanner.nextLine();
                System.out.print("Entrez la nouvelle catégorie du livre : ");
                String nouvelleCategorie = scanner.nextLine();

                Livre nouveauLivre = new Livre(codeModif, nouveauTitre, nouvelAuteur, nouvelleMaisonEdition, nouvelleCategorie);
                livreDAO.modifierLivre(nouveauLivre);
                System.out.println("Le livre a été modifié avec succès !");
            } else {
                System.out.println("Aucun livre trouvé avec le code " + codeModif);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void rechercherLivreParNom(LivreDAO livreDAO, Scanner scanner) {
        System.out.print("Entrez le nom du livre à rechercher : ");
        String nomRecherche = scanner.nextLine();
        try {
            Livre livreRecherche = livreDAO.rechercherParNom(nomRecherche);
            if (livreRecherche != null) {
                System.out.println("Le livre recherché est : " + livreRecherche.getTitre());
            } else {
                System.out.println("Aucun livre trouvé avec le nom " + nomRecherche);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void listerLivresParLettre(LivreDAO livreDAO, Scanner scanner) {
        System.out.print("Entrez la lettre par laquelle les livres commencent : ");
        char lettre = scanner.nextLine().charAt(0);
        try {
            Map<String, Livre> livresParLettre = livreDAO.listerLivresParLettre(lettre);
            if (!livresParLettre.isEmpty()) {
                System.out.println("Les livres commençant par " + lettre + " sont : ");
                livresParLettre.forEach((code, livre) -> System.out.println(code + ": " + livre.getTitre()));
            } else {
                System.out.println("Aucun livre trouvé commençant par la lettre " + lettre);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void afficherNombreLivres(LivreDAO livreDAO) {
        try {
            int nombreLivres = livreDAO.nombreDeLivres();
            System.out.println("Le nombre total de livres dans la bibliothèque est : " + nombreLivres);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void afficherLivresParCategorie(LivreDAO livreDAO, Scanner scanner) {
        System.out.print("Entrez la catégorie de livres à afficher : ");
        String categorie = scanner.nextLine();
        try {
            Map<String, Livre> livresParCategorie = livreDAO.livresParCategorie(categorie);
            if (!livresParCategorie.isEmpty()) {
                System.out.println("Les livres de la catégorie '" + categorie + "' sont : ");
                livresParCategorie.forEach((code, livre) -> System.out.println(code + ": " + livre.getTitre()));
            } else {
                System.out.println("Aucun livre trouvé dans la catégorie '" + categorie + "'");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void supprimerLivre(LivreDAO livreDAO, Scanner scanner) {
        System.out.print("Entrez le code du livre à supprimer : ");
        String codeSuppr = scanner.nextLine();
        try {
            Livre livreSupprime = livreDAO.getLivre(codeSuppr);
            if (livreSupprime != null) {
                livreDAO.supprimerLivre(codeSuppr);
                System.out.println("Le livre avec le code '" + codeSuppr + "' a été supprimé.");
            } else {
                System.out.println("Aucun livre trouvé avec le code " + codeSuppr);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void ajouterLivre(LivreDAO livreDAO, Scanner scanner) {
        System.out.print("Entrez le code du livre : ");
        String codeAjout = scanner.nextLine();
        System.out.print("Entrez le titre du livre : ");
        String titreAjout = scanner.nextLine();
        System.out.print("Entrez l'auteur du livre : ");
        String auteurAjout = scanner.nextLine();
        System.out.print("Entrez la maison d'édition du livre : ");
        String maisonEditionAjout = scanner.nextLine();
        System.out.print("Entrez la catégorie du livre : ");
        String categorieAjout = scanner.nextLine();

        Livre livreAjout = new Livre(codeAjout, titreAjout, auteurAjout, maisonEditionAjout, categorieAjout);
        try {
            livreDAO.ajouterLivre(livreAjout);
            System.out.println("Le livre '" + titreAjout + "' a été ajouté à la bibliothèque.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Méthode pour récupérer un livre par son identifiant
    public Livre getLivre(String identifiant) throws SQLException {
        Livre livre = null;
        String sql = "SELECT * FROM livres WHERE identifiant = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, identifiant);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                livre = new Livre(
                        resultSet.getString("identifiant"),
                        resultSet.getString("titre"),
                        resultSet.getString("auteurNom"),
                        resultSet.getString("editeur"),
                        resultSet.getString("genre")
                );
            }
        }
        return livre;
    }

    // Méthode pour modifier un livre
    public void modifierLivre(Livre livre) throws SQLException {
        String sql = "UPDATE livres SET titre = ?, auteurNom = ?, editeur = ?, genre = ? WHERE identifiant = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, livre.getTitre());
            statement.setString(2, livre.getAuteurNom());
            statement.setString(3, livre.getEditeur());
            statement.setString(4, livre.getGenre());
            statement.setString(5, livre.getIdentifiant());
            statement.executeUpdate();
        }
    }

    // Méthode pour rechercher un livre par son nom
    public Livre rechercherParNom(String nom) throws SQLException {
        Livre livre = null;
        String sql = "SELECT * FROM livres WHERE titre = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, nom);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                livre = new Livre(
                        resultSet.getString("identifiant"),
                        resultSet.getString("titre"),
                        resultSet.getString("auteurNom"),
                        resultSet.getString("editeur"),
                        resultSet.getString("genre")
                );
            }
        }
        return livre;
    }

    // Méthode pour lister les livres commençant par une lettre donnée
    public Map<String, Livre> listerLivresParLettre(char lettre) throws SQLException {
        Map<String, Livre> livresParLettre = new HashMap<>();
        String sql = "SELECT * FROM livres WHERE titre LIKE ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, lettre + "%");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Livre livre = new Livre(
                        resultSet.getString("identifiant"),
                        resultSet.getString("titre"),
                        resultSet.getString("auteurNom"),
                        resultSet.getString("editeur"),
                        resultSet.getString("genre")
                );
                livresParLettre.put(resultSet.getString("identifiant"), livre);
            }
        }
        return livresParLettre;
    }

    // Méthode pour obtenir le nombre total de livres
    public int nombreDeLivres() throws SQLException {
        int nombreLivres = 0;
        String sql = "SELECT COUNT(*) AS total FROM livres";
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                nombreLivres = resultSet.getInt("total");
            }
        }
        return nombreLivres;
    }

    // Méthode pour obtenir les livres par catégorie
    public Map<String, Livre> livresParCategorie(String categorie) throws SQLException {
        Map<String, Livre> livresParCategorie = new HashMap<>();
        String sql = "SELECT * FROM livres WHERE genre = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, categorie);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Livre livre = new Livre(
                        resultSet.getString("identifiant"),
                        resultSet.getString("titre"),
                        resultSet.getString("auteurNom"),
                        resultSet.getString("editeur"),
                        resultSet.getString("genre")
                );
                livresParCategorie.put(resultSet.getString("identifiant"), livre);
            }
        }
        return livresParCategorie;
    }

    // Méthode pour supprimer un livre par son identifiant
    public void supprimerLivre(String identifiant) throws SQLException {
        String sql = "DELETE FROM livres WHERE identifiant = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, identifiant);
            statement.executeUpdate();
        }
    }

    // Méthode pour ajouter un nouveau livre
    public void ajouterLivre(Livre livre) throws SQLException {
        String sql = "INSERT INTO livres (identifiant, titre, auteurNom, editeur, genre) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, livre.getIdentifiant());
            statement.setString(2, livre.getTitre());
            statement.setString(3, livre.getAuteurNom());
            statement.setString(4, livre.getEditeur());
            statement.setString(5, livre.getGenre());
            statement.executeUpdate();
        }
    }
}
