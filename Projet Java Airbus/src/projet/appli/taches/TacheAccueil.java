package projet.appli.taches;

import java.util.Hashtable;

import projet.appli.Tache;
import projet.outils.Duree;
import projet.outils.Horaire;

public class TacheAccueil extends Tache{
	private static int numTaches = 1 ;
	static private Hashtable<String, Tache> lesTachesAccueil = new Hashtable<String, Tache>();

	
	public TacheAccueil(Horaire horaireDeb, Horaire horaireFin) {
		super("ACC" + numTaches ,horaireDeb, horaireFin) ;
		lesTachesAccueil.put(this.getIdTache(), this);
		numTaches++;
	}

}
