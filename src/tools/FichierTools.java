package tools;

import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FichierTools {
	
	/**
	 * Parcour un fichier et retourne les lignes sous forme d'une liste
	 * @param chemin le chemin du fichier en entrée.
	 * @return une liste de ligne
	 */
	public static ArrayList<String> lecture(String chemin) throws FileNotFoundException{
		ArrayList<String> lignes_fichier = new ArrayList<String>();
		File fichier = new  File(chemin);
		Scanner scanner = new Scanner(fichier);
		while (scanner.hasNextLine()) {
			String ligne = scanner.nextLine();
			lignes_fichier.add(ligne);
		}
		scanner.close();
			
		return lignes_fichier;
	}
	public void ecriture(){
		
	}
	

}
