package obj;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;

import exception.LectureEntreException;
import thread.Traitement;
import tools.FichierTools;

public class Controleur {
	
	ArrayList<String> lignes_fichier;
	
	public void lancer_traitement(){
		// lecture des instructions
		HashMap<String, Object> liste_donnee_entre = new HashMap<String, Object>();
		try {
			liste_donnee_entre = lire_donnee(lignes_fichier);
		} catch (LectureEntreException e) {
			System.out.println(e.getMessage());
			return;
		}
		
		Surface surface = (Surface)liste_donnee_entre.get("surface");
		ArrayList<Instructions> liste_instruction = (ArrayList<Instructions>)liste_donnee_entre.get("liste_instruction");
		// pour chaque instruction on construit un thread pour faire le traitement.		
		for(Instructions inst:liste_instruction){
			Traitement trt = new Traitement(inst,surface);
			trt.start();
		}
	}
	/**
	 * @param chemin le chemin du fichier en entrée.
	 * @return
	 */
	public void demarrer(String chemin){
		// lecture du fichier en entre
		lignes_fichier = new ArrayList<String>();
		try {
			lignes_fichier = FichierTools.lecture(chemin);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		lancer_traitement();
	}
	
	/**
	 * @param lignes_fichier listes des lignes à lire.
	 * @return hasmap qui contient la liste des instruction et les données de surface.
	 * @throws LectureEntreException exception avec message d'erreur si les données ne sont pas au bon fomat
	 */
	public HashMap<String, Object> lire_donnee(ArrayList<String> lignes_fichier) throws LectureEntreException{
		HashMap<String, Object> list_donnee = new HashMap<String, Object>();
		Surface surface = new Surface();
		// verification que le fichier n'est pas vide
		if(lignes_fichier.size()==0){
			throw new LectureEntreException("Le fichier est vide");
		}
		//lecture de la taille du surface, on declenche une exception si les données ne sont pas au bon format
		String taille_surface = lignes_fichier.get(0);
		String [] tab_taille = taille_surface.split(" ");
		if(tab_taille.length==2){
			String longueur = tab_taille[0];
			String largeur = tab_taille[1];
			try{
				surface.setLongueur(Integer.valueOf(longueur));
				surface.setLargeur(Integer.valueOf(largeur));
			}catch(NumberFormatException nfe){
				throw new LectureEntreException("Les données du taille de la surface ne sont pas au bon format: ligne 1");
			}
		}else{
			throw new LectureEntreException("Les données du taille de la surface ne sont pas au bon format: ligne 1");
		}
		list_donnee.put("surface", surface);
		
		//Lecture des postion intiale et des sequence de controle avec la verification des données.
		ArrayList<Instructions> liste_instruction = new ArrayList<Instructions>();
		//on vérifie si les donnéés sont bien de la forme paire initialisation/instructions
		if((lignes_fichier.size() & 1)==0){
			throw new LectureEntreException("Les données ne respectent pas la format (paire initialisation/instructions) ");
		}
		for(int i = 1;i<=lignes_fichier.size()-1;i+=2){
			Instructions instruction = new Instructions();
			String position_init = lignes_fichier.get(i);
			String sequence_control = lignes_fichier.get(i+1);
			// lecture du possition iniale
			String [] tab_position = position_init.split(" ");
			if(tab_position.length==3){
				String position_x = tab_position[0];
				String position_y = tab_position[1];
				String direction = tab_position[2];
				try{
					instruction.setPosition_init_x(Integer.valueOf(position_x));
					instruction.setPosition_init_y(Integer.valueOf(position_y));
					if(instruction.getPosition_init_x()>surface.getLongueur() || instruction.getPosition_init_y()>surface.getLargeur()){
						throw new LectureEntreException("Les cordonnées de la tondeuse sont hors la surface: ligne "+(++i));
					}
				}catch(NumberFormatException nfe){
					throw new LectureEntreException("Les cordonnées de la tondeuse ne sont pas au bon format: ligne "+(++i));
				}
				if("N".equals(direction)||"E".equals(direction)||"W".equals(direction)||"S".equals(direction)){
					instruction.setDirection(direction);
				}else{
					throw new LectureEntreException("La direction de la tondeuse n'est pas au bon format: ligne "+(++i));
				}
			}else{
				throw new LectureEntreException("Les cordonnées de la tondeuse ne sont pas au bon format: ligne "+(++i));
			}
			// lecture de la liste des instruction
			ArrayList<String> sequence = new ArrayList<String>();
			for(int j =0;j<sequence_control.length();j++){
				String mouvement = String.valueOf(sequence_control.charAt(j));
				if("D".equals(mouvement)||"G".equals(mouvement)||"A".equals(mouvement)){
					sequence.add(mouvement);
				}else{
					throw new LectureEntreException("La sequence de mouvement de la tondeuse n'est pas au bon format: ligne "+(i+=2));
				}
			}
			instruction.setSequence_control(sequence);
			liste_instruction.add(instruction);
		}
		if(liste_instruction.size()>0){
			list_donnee.put("liste_instruction", liste_instruction);
		}else{
			throw new LectureEntreException("le fichier ne contient pas les données pourla tondeuse: ligne 2 ");
		}
		
		return list_donnee;
	}
	public ArrayList<String> getLignes_fichier() {
		return lignes_fichier;
	}
	public void setLignes_fichier(ArrayList<String> lignes_fichier) {
		this.lignes_fichier = lignes_fichier;
	}

}
