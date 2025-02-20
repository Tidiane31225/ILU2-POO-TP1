package villagegaulois;

import java.util.Iterator;

import personnages.Chef;
import personnages.Gaulois;
import villagegaulois.VillageSansChefException;

public class Village {
	private String nom;
	private Chef chef;
	private Gaulois[] villageois;
	private int nbVillageois = 0;
	private final Marche marche;

	public Village(String nom, int nbVillageoisMaximum,int nombreEtals) {
		this.nom = nom;
		this.villageois = new Gaulois[nbVillageoisMaximum];
		this.marche = new Marche(nombreEtals);
		//System.out.println("Le marché avec " + nombreEtals + " étals a été créé pour le village " + nom + ".");
	}

	public String getNom() {
		return nom;
	}

	public void setChef(Chef chef) {
		this.chef = chef;
	}

	public void ajouterHabitant(Gaulois gaulois) {
		if (nbVillageois < villageois.length) {
			villageois[nbVillageois] = gaulois;
			nbVillageois++;
		}
	}

	public Gaulois trouverHabitant(String nomGaulois) {
		if (nomGaulois.equals(chef.getNom())) {
			return chef;
		}
		for (int i = 0; i < nbVillageois; i++) {
			Gaulois gaulois = villageois[i];
			if (gaulois.getNom().equals(nomGaulois)) {
				return gaulois;
			}
		}
		return null;
	}

	public String afficherVillageois() {
		
		 if (chef == null) {
	            throw new VillageSansChefException("Le village ne peut pas exister sans chef !");
	        }
		
		StringBuilder chaine = new StringBuilder();
		if (nbVillageois < 1) {
			chaine.append("Il n'y a encore aucun habitant au village du chef "
					+ chef.getNom() + ".\n");
		} else {
			chaine.append("Au village du chef " + chef.getNom()
					+ " vivent les légendaires gaulois :\n");
			for (int i = 0; i < nbVillageois; i++) {
				chaine.append("- " + villageois[i].getNom() + "\n");
			}
		}
		return chaine.toString();
	}
	
public String installerVendeur(Gaulois vendeur, String produit,int nbProduit) {
	 StringBuilder chaine = new StringBuilder();

	    chaine.append(vendeur.getNom()).append(" cherche un endroit pour vendre ").append(nbProduit)
	          .append(" ").append(produit).append(".\n");

	    int indiceEtalLibre = marche.trouverEtalLibre();
	    if (indiceEtalLibre != -1) {
	        marche.utiliserEtal(indiceEtalLibre, vendeur, produit, nbProduit);
	        chaine.append("Le vendeur ").append(vendeur.getNom())
	              .append(" vend des ").append(produit)
	              .append(" à l'étal n°").append(indiceEtalLibre + 1).append(".\n");
	    } else {
	        chaine.append("Aucun étal libre n'est disponible pour le vendeur ")
	              .append(vendeur.getNom()).append(".\n");
	    }

	    return chaine.toString();
	}
public String rechercherVendeursProduit(String produit) {
	StringBuilder chaine= new StringBuilder();
    Etal[] etalsAvecProduit = marche.trouverEtals(produit);
    boolean produitTrouve = false;

    for (Etal etal : etalsAvecProduit) {
        if (etal != null && etal.isEtalOccupe()) {
            produitTrouve = true;
            if (!chaine.toString().contains("Les vendeurs qui proposent des " + produit + " sont :")) {
                chaine.append("Les vendeurs qui proposent des ").append(produit).append(" sont :\n");
            }
            chaine.append("- ").append(etal.getVendeur().getNom()).append("\n");
        }
    }

    if (!produitTrouve) {
        chaine.append("Il n'y a pas de vendeur qui propose des ").append(produit).append(" au marché.\n");
    }

    return chaine.toString();}

public Etal rechercherEtal(Gaulois vendeur) {
	Etal etal = new Etal();
	etal=marche.trouverVendeur(vendeur);
	return etal;
}

public String partirVendeur(Gaulois vendeur) {
	StringBuilder chaine = new StringBuilder();
    Etal etal = marche.trouverVendeur(vendeur);

    if (etal != null && etal.isEtalOccupe()) {
        // Appel de libererEtal pour libérer l'étal
        String resultatLiberation = etal.libererEtal();

        // Extraction des informations manquantes pour reformater la chaîne
        int quantiteDebutMarche = etal.getQuantiteDebutMarche();
        int quantiteVendue = quantiteDebutMarche - etal.getQuantite();
        String produit = etal.getNomProduit();

        // Construction de la phrase correcte
        chaine.append("Le vendeur ").append(vendeur.getNom())
              .append(" quitte son étal, il a vendu ").append(quantiteVendue)
              .append(" ").append(produit).append(" parmi les ")
              .append(quantiteDebutMarche).append(" qu'il voulait vendre.\n");
    } else {
        chaine.append("Le vendeur ").append(vendeur.getNom())
              .append(" n'a pas d'étal au marché.\n");
    }

    return chaine.toString();
}

public String afficherMarche() {
	 StringBuilder chaine = new StringBuilder();
	    chaine.append("Le marché du village \"").append(nom).append("\" possède plusieurs étals :\n");

	    int nbEtalsLibres = 0;

	    for (Etal etal : marche.etals) {
	        if (etal.isEtalOccupe()) {
	            chaine.append(etal.getVendeur().getNom()).append(" vend ")
	                  .append(etal.getQuantite()).append(" ")
	                  .append(etal.getNomProduit()).append("\n");
	        } else {
	            nbEtalsLibres++;
	        }
	    }

	    chaine.append("Il reste ").append(nbEtalsLibres).append(" étals non utilisés dans le marché.\n");

	    return chaine.toString();
	}

	private class Marche{
		private final Etal[] etals;
		
	
	private Marche (int nb_etal) {
		etals = new Etal[nb_etal];
		for(int i=0;i<nb_etal;i++) {
			etals[i]=new Etal();
		}
	}
private void utiliserEtal(int indiceEtal, Gaulois vendeur, String produit, int nbProduit) {
	etals[indiceEtal].occuperEtal(vendeur,produit,nbProduit);
}

private int trouverEtalLibre() {
	for(int i=0;i<etals.length;i++) {
		if (etals[i].isEtalOccupe()) {
			
		}else {
			return i;
		}
	}
	
	return -1;
}

private Etal[] trouverEtals(String produit) {
	Etal[] etal= new Etal[etals.length];
	int n=0;
	for (int i = 0; i < etals.length; i++) {
		if (etals[i].contientProduit(produit)) {
			etal[n]=etals[i];
			n++;
		}
		
	}		
	return etal;
}

private Etal trouverVendeur(Gaulois gaulois) {
	for (int i = 0; i < etals.length; i++) {
		if (etals[i].getVendeur()==gaulois) {
			return etals[i];
		}
	}
	
	return null;
}

private void afficherMarche(){
	for (int i = 0; i < etals.length; i++) {
		if(etals[i].isEtalOccupe()) {
		etals[i].afficherEtal();
		}else { 
			System.out.println("il reste " + (etals.length-i) +" etals non utilisé dans le marché. \n" );
			
		}
		
	}
}

}
}
