package com.example.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseSingleton {
    // Instance unique de la classe
    private static DatabaseSingleton instance = null;

    // Connexion à la base de données
    private Connection connection;

    // URL de connexion à la base de données (exemple pour MySQL)
    private static final String URL = "jdbc:mysql://localhost:3306/phn_db";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";

    // Requête SQL pour créer la table livres si elle n'existe pas
    private static final String CREATE_TABLE_SQL =
            "CREATE TABLE IF NOT EXISTS livres (" +
            "identifiant VARCHAR(50) PRIMARY KEY," +
            "titre VARCHAR(255) NOT NULL," +
            "auteurNom VARCHAR(100) NOT NULL," +
            "editeur VARCHAR(100)," +
            "genre VARCHAR(100)" +
            ")";

    // Constructeur privé pour empêcher l'instanciation directe
    private DatabaseSingleton() {
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            initializeDatabase(); // Appel de la méthode pour initialiser la base de données
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Méthode statique pour récupérer l'instance unique
    public static synchronized DatabaseSingleton getInstance() {
        if (instance == null) {
            instance = new DatabaseSingleton();
        }
        return instance;
    }

    // Méthode pour récupérer la connexion à la base de données
    public Connection getConnection() {
        return connection;
    }

    // Méthode pour fermer la connexion à la base de données
    public void closeConnection() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Méthode pour initialiser la base de données (création de la table livres si nécessaire)
    public void initializeDatabase() {
        try (Statement statement = connection.createStatement()) {
            // Exécute la requête pour créer la table livres si elle n'existe pas déjà
            statement.executeUpdate(CREATE_TABLE_SQL);
            System.out.println("Table livres vérifiée/créée avec succès.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
