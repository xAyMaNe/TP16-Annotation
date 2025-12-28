package com.example.annotations;

@Author(name = "John Doe", date = "2023-06-15")
@Version(value = "1.0")
@Bug(id = 101, description = "Problème de validation du formulaire")
@Bug(id = 102, description = "Erreur de chargement des images", status = "FIXED")
@Bug(id = 103, description = "Timeout sur les requêtes longues")
public class AnnotatedClass {
    
    @MethodInfo(
        description = "Méthode qui affiche des informations",
        tags = {"info", "affichage"},
        revision = 2
    )
    public void afficherInfo() {
        System.out.println("Classe annotée avec @Author et @Bug");
    }
    
    @MethodInfo(description = "Méthode principale")
    public static void main(String[] args) {
        AnnotatedClass obj = new AnnotatedClass();
        obj.afficherInfo();
    }
}
