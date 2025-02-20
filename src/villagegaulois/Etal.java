package villagegaulois;

import personnages.Gaulois;
import histoire.ScenarioCasDegrade;

public class Etal {
	private Gaulois vendeur;
	private String produit;
	private int quantiteDebutMarche;
	private int quantite;
	private boolean etalOccupe = false;

	public boolean isEtalOccupe() {
		return etalOccupe;
	}

	public Gaulois getVendeur() {
		return vendeur;
	}
	
	public int getQuantite() {
        return quantite;
    }
	
	public String getNomProduit() {
	    return produit;
	}
	
	public int getQuantiteDebutMarche() {
	    return quantiteDebutMarche;
	}
	
	public void afficherEtal() {
	    if (etalOccupe) {
	        System.out.println("Le vendeur " + vendeur.getNom() + " propose " + quantite + " " + produit + ".");
	    } else {
	        System.out.println("Cet étal est libre.");
	    }
	}


	public void occuperEtal(Gaulois vendeur, String produit, int quantite) {
		this.vendeur = vendeur;
		this.produit = produit;
		this.quantite = quantite;
		quantiteDebutMarche = quantite;
		etalOccupe = true;
	}

	public String libererEtal() {
	    if (!etalOccupe) {
	        throw new ScenarioCasDegrade("L'étal n'est pas occupé, impossible de le libérer.");
	    }
	    etalOccupe = false;
	    StringBuilder chaine = new StringBuilder();

	    int produitVendu = quantiteDebutMarche - quantite;
	    chaine.append("Le vendeur ").append(vendeur.getNom()).append(" quitte son étal, ");

	    if (produitVendu > 0) {
	        chaine.append("il a vendu ").append(produitVendu).append(" ")
	              .append(produit).append(" parmi les ").append(quantiteDebutMarche)
	              .append(" qu'il voulait vendre.\n");
	    } else {
	        chaine.append("il n'a malheureusement rien vendu.\n");
	    }

	    // Réinitialisation de l'étal
	    vendeur = null;
	    produit = null;
	    quantite = 0;
	    quantiteDebutMarche = 0;

	    return chaine.toString();
	}

	public String acheterProduit(int quantiteAcheter, Gaulois acheteur) {
	    /*if (!etalOccupe) {
	        throw new ScenarioCasDegrade("Impossible d'acheter un produit sur un étal non occupé.");
	    }*/
		 if (quantiteAcheter < 1) {
		        throw new IllegalArgumentException("La quantité achetée doit être supérieure ou égale à 1.");
		    }
		 if (acheteur == null) {
		        // Lancer une exception personnalisée
		        ScenarioCasDegrade exception = new ScenarioCasDegrade("Acheteur null : impossible d'effectuer l'achat.");
		        exception.printStackTrace(); // Affiche la pile d'erreurs
		        return ""; // Retourne une chaîne vide pour indiquer l'échec
		    }
		 if (!etalOccupe) {
			 //throw new IllegalStateException("L'étal n'est pas occupé, impossible d'acheter des produits.");
			    throw new ScenarioCasDegrade("L'étal n'est pas occupé, impossible d'acheter des produits.");

		    }

		    if (quantiteAcheter <= 0) {
		        return acheteur.getNom() + " n'a pas spécifié une quantité valide à acheter.";
		    }
	    StringBuilder chaine = new StringBuilder();
	    chaine.append(acheteur.getNom() + " veut acheter " + quantiteAcheter
	            + " " + produit + " à " + vendeur.getNom());
	    if (quantite == 0) {
	        chaine.append(", malheureusement il n'y en a plus !");
	        quantiteAcheter = 0;
	    }
	    if (quantiteAcheter > quantite) {
	        chaine.append(", comme il n'y en a plus que " + quantite + ", "
	                + acheteur.getNom() + " vide l'étal de "
	                + vendeur.getNom() + ".\n");
	        quantiteAcheter = quantite;
	        quantite = 0;
	    }
	    if (quantite != 0) {
	        quantite -= quantiteAcheter;
	        chaine.append(". " + acheteur.getNom()
	                + " est ravi de tout trouver sur l'étal de "
	                + vendeur.getNom() + "\n");
	    }
	    return chaine.toString();
	}


	public boolean contientProduit(String produit) {
		return produit.equals(this.produit);
	}

}
