package histoire;

import personnages.Chef;
import personnages.Druide;
import personnages.Gaulois;
import villagegaulois.Etal;
import villagegaulois.Village;
import villagegaulois.VillageSansChefException;

public class Scenario {

	public static void main(String[] args) {
	Village village = new Village("le village des irréductibles", 10, 5);
		Chef abraracourcix = new Chef("Abraracourcix", 10, village);
		village.setChef(abraracourcix);
		Druide druide = new Druide("Panoramix", 2, 5, 10);
		Gaulois obelix = new Gaulois("Obélix", 25);
		Gaulois asterix = new Gaulois("Astérix", 8);
		Gaulois assurancetourix = new Gaulois("Assurancetourix", 2);
		Gaulois bonemine = new Gaulois("Bonemine", 7);
		 Gaulois acheteur = new Gaulois("Gladiatix", 10);
		Etal etal = new Etal();

		village.ajouterHabitant(bonemine);
		village.ajouterHabitant(assurancetourix);
		village.ajouterHabitant(asterix);
		village.ajouterHabitant(obelix);
		village.ajouterHabitant(druide);
		village.ajouterHabitant(abraracourcix);
		village.afficherVillageois();
		

		System.out.println(village.rechercherVendeursProduit("fleurs"));
		System.out.println(village.installerVendeur(bonemine, "fleurs", 20));		System.out.println(village.rechercherVendeursProduit("fleurs"));		
		System.out.println(village.installerVendeur(assurancetourix, "lyres", 5));
		System.out.println(village.installerVendeur(obelix, "menhirs", 2));
		System.out.println(village.installerVendeur(druide, "fleurs", 10));

		System.out.println(village.rechercherVendeursProduit("fleurs"));
		Etal etalFleur = village.rechercherEtal(bonemine);
		System.out.println(etalFleur.acheterProduit(10, abraracourcix));
		System.out.println(etalFleur.acheterProduit(15, obelix));
		System.out.println(etalFleur.acheterProduit(15, assurancetourix));
		System.out.println(village.partirVendeur(bonemine));
		System.out.println(village.afficherMarche());
		try {
            
            System.out.println("Tentative de libérer un étal non occupé...");
            etal.libererEtal();
        } catch (ScenarioCasDegrade e) {
            System.out.println("Exception attrapée : " + e.getMessage());
        }

        System.out.println("Fin du test");
 
		
		try {
            
            Gaulois abraracourcix1 = new Gaulois("Abraracourcix", 10);
            System.out.println("Tentative d'achat sur un étal non occupé...");
            System.out.println(etal.acheterProduit(5, abraracourcix1));
        } catch (ScenarioCasDegrade e) {
            System.out.println("Exception attrapée : " + e.getMessage());
        }

        System.out.println("Fin du test");
        
        try {
            etal.acheterProduit(5, null); // Ceci doit lever une exception
        } catch (ScenarioCasDegrade e) {
            System.err.println("Exception attrapée : " + e.getMessage());
        }
		
		try {
            // Appel avec une quantité invalide
            etal.acheterProduit(0, acheteur); // Doit lever une IllegalArgumentException
        } catch (IllegalArgumentException e) {
            System.err.println("Erreur : " + e.getMessage());
        } catch (ScenarioCasDegrade e) {
            System.err.println("Erreur scénario : " + e.getMessage());
        }

        System.out.println("Fin du test.");
		 Etal etal1 = new Etal();
        
        try {
            // Tentative d'achat sur un étal non occupé
            etal1.acheterProduit(5, acheteur); // Doit lever une IllegalStateException
        } catch (IllegalStateException e) {
            System.err.println("Erreur : " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.err.println("Erreur d'argument : " + e.getMessage());
        } catch (ScenarioCasDegrade e) {
            System.err.println("Erreur scénario : " + e.getMessage());
        }

        System.out.println("Fin du test.");
        
        Village village1 = new Village("Village des irréductibles", 10, 5);
        
        try {
            // Tentative d'affichage des villageois sans chef
            System.out.println(village1.afficherVillageois());
        } catch (VillageSansChefException e) {
            System.err.println("Erreur : " + e.getMessage());
        }

        System.out.println("Fin du test.");
    
    
	}

}
