package com.example.classes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;
import java.util.Scanner;
import javax.xml.crypto.Data;

import com.example.database.LivreDAO;

public class Main {
    public static void main(String[] args) {
        try {
            // Établir la connexion à la base de données MySQL
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/phn_db", "root", "");
             // Appel à la fonction pour initialiser la base de données
            //initializeDatabase(connection);
            // Créer l'instance de LivreDAO en utilisant la connexion
            LivreDAO livreDAO = new LivreDAO(connection);

            Scanner scanner = new Scanner(System.in);
            String choix;

            do {
                afficherMenu();
                choix = scanner.nextLine();

                switch (choix) {
                    case "1":
                        LivreDAO.getLivreParCode(livreDAO, scanner);
                        break;
                    case "2":
                        LivreDAO.modifierLivre(livreDAO, scanner);
                        break;
                    case "3":
                        LivreDAO.rechercherLivreParNom(livreDAO, scanner);
                        break;
                    case "4":
                        LivreDAO.listerLivresParLettre(livreDAO, scanner);
                        break;
                    case "5":
                        LivreDAO.afficherNombreLivres(livreDAO);
                        break;
                    case "6":
                        LivreDAO.afficherLivresParCategorie(livreDAO, scanner);
                        break;
                    case "7":
                        LivreDAO.supprimerLivre(livreDAO, scanner);
                        break;
                    case "8":
                        LivreDAO.ajouterLivre(livreDAO, scanner);
                        break;
                    case "9":
                        System.out.println("Au revoir !");
                        break;
                    default:
                        System.out.println("Option non valide. Veuillez réessayer.");
                }
            } while (!choix.equals("9"));

            scanner.close();
            connection.close();  // Fermer la connexion à la fin de l'exécution
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void afficherMenu() {
        System.out.println("\n1. Obtenir un livre par son code");
        System.out.println("2. Modifier un livre");
        System.out.println("3. Rechercher un livre par son nom");
        System.out.println("4. Lister les livres par lettre");
        System.out.println("5. Obtenir le nombre total de livres");
        System.out.println("6. Obtenir les livres par catégorie");
        System.out.println("7. Supprimer un livre");
        System.out.println("8. Ajouter un livre");
        System.out.println("9. Quitter");
        System.out.print("Choisissez une option : ");
    }
}