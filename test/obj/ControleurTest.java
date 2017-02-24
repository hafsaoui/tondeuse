package obj;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ControleurTest {

	protected Controleur conroleur;

	@Before
	public void setUp() throws Exception {
		conroleur = new Controleur();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testLancer_traitement() {
		ArrayList<String> liste_ligne_fichier = new ArrayList<String>();
		liste_ligne_fichier.add("5 5");
		liste_ligne_fichier.add("1 2 N");
		liste_ligne_fichier.add("GAGAGAGAA");
		liste_ligne_fichier.add("3 3 E");
		liste_ligne_fichier.add("AADAADADDA");
		conroleur.setLignes_fichier(liste_ligne_fichier);
		conroleur.lancer_traitement();

	}

}
