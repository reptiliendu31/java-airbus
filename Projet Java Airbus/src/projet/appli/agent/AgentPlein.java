package projet.appli.agent;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

import projet.appli.Agent;
import projet.outils.Horaire;
import projet.outils.TrancheHoraire;

public class AgentPlein extends Agent{
	// constructeur
		public AgentPlein(String mat, String n, String p, int c){
			super(mat,n,p,c);
		}
		
		// r�cup�re les horaires en temps partiel
		public TrancheHoraire getH (int numHo){
			Horaire hdeb = new Horaire();
			Horaire hfin = new Horaire();		
			TrancheHoraire th = new TrancheHoraire(hdeb,hfin);
			switch(numHo){
				// cas de la semaine multiple de 3
				case 0:
					hdeb = new Horaire(13,30);
					hfin = new Horaire(21,30);
					th = new TrancheHoraire(hdeb,hfin);
					break;
				case 1:
					hdeb = new Horaire(9,0);
					hfin = new Horaire(17,0);
					th = new TrancheHoraire(hdeb,hfin);
					break;
				case 2:
					hdeb = new Horaire(6,0);
					hfin = new Horaire(14,0);
					th = new TrancheHoraire(hdeb,hfin);
					break;
			}
			return th;	
		}

		// m�thode permettant de trouver l'horaire pour une semaine donn�e
		//c = cycle / sem = num�ro de la semaine
		public TrancheHoraire getHoraire(int sem){
			Horaire hdeb = new Horaire();
			Horaire hfin = new Horaire();
			TrancheHoraire th = new TrancheHoraire(hdeb,hfin);
			if(super.getCycle() == 1){
				switch(sem%3){
					// cas de la semaine multiple de 3
					case 0:
						th = getH(3);
						break;
					case 1:
						th = getH(2);
						break;
					case 2:
						th = getH(1);
						break;
				}
			}
			if(super.getCycle() == 2){
				switch(sem%3){
					// cas de la semaine multiple de 3
					case 0:
						th = getH(2);
						break;
					case 1:
						th = getH(1);
						break;
					case 2:
						th = getH(3);
						break;
				}
			}
			else{
				switch(sem%3){
				// cas de la semaine multiple de 3
				case 0:
					th = getH(1);
					break;
				case 1:
					th = getH(3);
					break;
				case 2:
					th = getH(2);
					break;
			}
			}
				
			
			return th;
		}
		
		
		// r�cup�ration des donn�es du fichier
		
		 static public void lireAgent (String adresseFichier) 
			{
			
					BufferedReader entree = null;
					
					// Déclaration d'une ligne
					String ligne;
					
					// Découpage en mot
					StringTokenizer mot;
					
					try {
						// Entrée du fichier
						 entree = new BufferedReader(new FileReader (adresseFichier));
						
						while ((ligne = entree.readLine()) != null ) // boucle de lecture/affichage du fichier
						  { 
							// Lecture par mot sur chaque ligne
							  mot = new StringTokenizer(ligne);
							
							  while (mot.hasMoreTokens())
							  {
								  // Recuperation du mot
								  String mat = mot.nextToken();
								  String nom = mot.nextToken();
								  String prenom = mot.nextToken();
								  String c = mot.nextToken();
								  int cycle = Integer.parseInt(c);
								  
								  AgentPlein a = new AgentPlein (mat,nom,prenom,cycle);
							  }
						  }
						entree.close();
					}
					catch (IOException e)
				      {
				    	  System.out.println("Erreur : "+ e.toString());
				      }
					catch (NumberFormatException e)
				      {
				    	  System.out.println("Erreur : "+ e.toString());
				      }
				
			}
		
}