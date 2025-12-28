package com.example.annotations;

import validation.Length;
import validation.NotNull;
import validation.Range;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class Validateur {
    
    public static List<String> valider(Object obj) {
        List<String> erreurs = new ArrayList<>();
        Class<?> clazz = obj.getClass();
        
        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true);
            
            try {
                Object valeur = field.get(obj);
                
                // Validation NotNull
                if (field.isAnnotationPresent(NotNull.class)) {
                    if (valeur == null) {
                        NotNull annotation = field.getAnnotation(NotNull.class);
                        erreurs.add(field.getName() + ": " + annotation.message());
                    }
                }
                
                // Validation Length
                if (field.isAnnotationPresent(Length.class) && field.getType() == String.class) {
                    if (valeur instanceof String) {
                        String strValue = (String) valeur;
                        Length annotation = field.getAnnotation(Length.class);
                        int min = annotation.min();
                        int max = annotation.max();
                        
                        if (strValue.length() < min || strValue.length() > max) {
                            String message = annotation.message()
                                    .replace("{min}", String.valueOf(min))
                                    .replace("{max}", String.valueOf(max));
                            erreurs.add(field.getName() + ": " + message);
                        }
                    }
                }
                
                // Validation Range
                if (field.isAnnotationPresent(Range.class) && 
                    (field.getType() == int.class || field.getType() == Integer.class)) {
                    if (valeur instanceof Integer || field.getType() == int.class) {
                        int intValue = field.getInt(obj);
                        Range annotation = field.getAnnotation(Range.class);
                        int min = annotation.min();
                        int max = annotation.max();
                        
                        if (intValue < min || intValue > max) {
                            String message = annotation.message()
                                    .replace("{min}", String.valueOf(min))
                                    .replace("{max}", String.valueOf(max));
                            erreurs.add(field.getName() + ": " + message);
                        }
                    }
                }
                
            } catch (IllegalAccessException e) {
                erreurs.add("Erreur d'accès au champ " + field.getName() + ": " + e.getMessage());
            }
        }
        
        return erreurs;
    }
    
    // Méthode utilitaire pour afficher les résultats
    public static void afficherErreurs(Object obj) {
        List<String> erreurs = valider(obj);
        if (erreurs.isEmpty()) {
            System.out.println("Validation réussie - aucune erreur");
        } else {
            System.out.println("Erreurs de validation (" + erreurs.size() + "):");
            for (String erreur : erreurs) {
                System.out.println("  - " + erreur);
            }
        }
    }
    
    public static void main(String[] args) {
        // Test 1: Utilisateur valide
        System.out.println("=== Test 1: Utilisateur valide ===");
        User validUser = new User("Jean Dupont", "jdupont", 25);
        afficherErreurs(validUser);
        
        System.out.println("\n=== Test 2: Nom trop court ===");
        User invalidUser1 = new User("A", "jdupont", 25);
        afficherErreurs(invalidUser1);
        
        System.out.println("\n=== Test 3: Âge invalide ===");
        User invalidUser2 = new User("Marie Martin", "mmartin", 15);
        afficherErreurs(invalidUser2);
        
        System.out.println("\n=== Test 4: Nom null ===");
        User invalidUser3 = new User(null, "test", 30);
        afficherErreurs(invalidUser3);
    }
}
