package projet.appli.vols;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.StringTokenizer;

import projet.appli.Avion;
import projet.appli.Vol;
import projet.appli.taches.TacheDebarquement;
import projet.exceptions.IdAvionException;
import projet.outils.Horaire;


public class VolArrivee extends Vol {
	
	static private Hashtable<String, VolArrivee> lesVolsArrivee = new Hashtable<String, VolArrivee>();
	private String villeDepart;
	
	// Constructeur
	public VolArrivee(String idVol, Horaire heureArrivee, String ville, Avion avion) {
		super(idVol, heureArrivee, avion);
		villeDepart = ville ;
		lesVolsArrivee.put(idVol, this);
	}
	
	// Retourne la ville du vol Arriv�e
	public String getVille()
	{
		return (villeDepart);
	}
	
	// Modifie l'affichage par defaut
	public String toString() {
		return "Vol Arriv�e" + super.toString() +"\n  - ville d�part :" + villeDepart;
	}
	
	
	static public void lireVolsArrivees (String adresseFichier) {
			
		BufferedReader entree = null;
			try {
				// Entr�e du fichier
			    entree = new BufferedReader(new FileReader (adresseFichier));
				
				// D�claration d'une ligne
				String ligne;
				
				// D�coupage en mot
				StringTokenizer mot;

				while ((ligne = entree.readLine()) != null )
				  { 
					// Lecture par mot sur chaque ligne
					  mot = new StringTokenizer(ligne);
					
					  while (mot.hasMoreTokens())
					  {
						  // Recuperation du mot
						  String id = mot.nextToken();
						  int heures = Integer.parseInt(mot.nextToken());
						  int minutes = Integer.parseInt(mot.nextToken());
						  String ville = mot.nextToken();
						  String idAvion = mot.nextToken();
						  
						  Horaire heureDepart = new Horaire(heures, minutes);
						 VolArrivee v = new VolArrivee(id, heureDepart, ville, Avion.getAvion(idAvion));
					  
					  }
				  }
				 entree.close();
			}
			catch (Exception e)
		      {
		    	  System.out.println("Erreur : "+ e.toString());
		      }

		
	}
	
	// Cr�ation des taches debarquement
	public void creerTaches ()
	{
		TacheDebarquement td = new TacheDebarquement(this) ;
	}
	
	// Retourne la liste des vols pour le graphisme
	public static ArrayList<Vol> getVolsArrivee() {
		return new ArrayList<Vol>(lesVolsArrivee.values());
	}
}