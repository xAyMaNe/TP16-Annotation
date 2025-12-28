package com.example.annotations;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;

public class AnnotationProcessor {
    
    // Méthode générale
    public static void processClass(Class<?> clazz) {
        System.out.println("=== Traitement de la classe: " + clazz.getName() + " ===");
        
        // Annotations de classe
        if (clazz.isAnnotationPresent(Author.class)) {
            Author author = clazz.getAnnotation(Author.class);
            System.out.println("Auteur: " + author.name());
            System.out.println("Date: " + author.date());
        }
        
        if (clazz.isAnnotationPresent(Version.class)) {
            Version version = clazz.getAnnotation(Version.class);
            System.out.println("Version: " + version.value());
        }
        
        // NOUVELLE MÉTHODE: Bugs avec getAnnotationsByType()
        processClassWithBugs(clazz);
        
        // Annotations des méthodes
        System.out.println("\n=== Annotations des méthodes ===");
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            if (method.isAnnotationPresent(MethodInfo.class)) {
                MethodInfo methodInfo = method.getAnnotation(MethodInfo.class);
                System.out.println("\nMéthode: " + method.getName());
                System.out.println("  Description: " + methodInfo.description());
                System.out.println("  Tags: " + String.join(", ", methodInfo.tags()));
                System.out.println("  Révision: " + methodInfo.revision());
            }
        }
        
        System.out.println("\n=== Fin du traitement ===\n");
    }
    
    // NOUVELLE MÉTHODE: getAnnotationsByType() pour annotations répétables
    public static void processClassWithBugs(Class<?> clazz) {
        System.out.println("Traitement des bugs pour: " + clazz.getName());
        
        // Récupérer toutes les annotations Bug (méthode moderne !)
        Bug[] bugs = clazz.getAnnotationsByType(Bug.class);
        
        if (bugs.length > 0) {
            System.out.println("Bugs trouvés: " + bugs.length);
            for (Bug bug : bugs) {
                System.out.println("ID: " + bug.id());
                System.out.println("Description: " + bug.description());
                System.out.println("Statut: " + bug.status());
                System.out.println();
            }
        } else {
            System.out.println("Aucun bug trouvé");
        }
    }
    
    public static void main(String[] args) {
        processClass(AnnotatedClass.class);
        processClass(BuggyClass.class);
    }
}
