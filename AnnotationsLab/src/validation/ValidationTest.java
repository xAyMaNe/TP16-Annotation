package validation;

import java.util.List;

import com.example.annotations.Utilisateur;
import com.example.annotations.Validateur;

public class ValidationTest {
    
    public static void main(String[] args) {
        // Utilisateur valide
        Utilisateur utilisateurValide = new Utilisateur("John Doe", "john.doe@example.com", 30);
        List<String> erreursValide = Validateur.valider(utilisateurValide);
        
        System.out.println("Validation de l'utilisateur valide:");
        if (erreursValide.isEmpty()) {
            System.out.println("Aucune erreur trouvée");
        } else {
            for (String erreur : erreursValide) {
                System.out.println("- " + erreur);
            }
        }
        
        System.out.println();
        
        // Utilisateur invalide
        Utilisateur utilisateurInvalide = new Utilisateur("Jo", null, 15);
        List<String> erreursInvalide = Validateur.valider(utilisateurInvalide);
        
        System.out.println("Validation de l'utilisateur invalide:");
        if (erreursInvalide.isEmpty()) {
            System.out.println("Aucune erreur trouvée");
        } else {
            for (String erreur : erreursInvalide) {
                System.out.println("- " + erreur);
            }
        }
    }
}
