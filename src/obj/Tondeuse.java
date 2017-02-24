package obj;

import exception.LectureEntreException;

public class Tondeuse {
	
	private int position_x;
	private int position_y;
	private String orientation;
	
	
	public int getPosition_x() {
		return position_x;
	}
	public void setPosition_x(int position_x) {
		this.position_x = position_x;
	}
	public int getPosition_y() {
		return position_y;
	}
	public void setPosition_y(int position_y) {
		this.position_y = position_y;
	}
	public String getOrientation() {
		return orientation;
	}
	public void setOrientation(String orientation) {
		this.orientation = orientation;
	}
	/**
	 * Avancer la tondeuse
	 * @param surface la surface du deplacement du tondeuse.
	 * @return 
	 */
	public void avancer(Surface surface){
		if((position_x == 0 && "W".equals(orientation))||(position_x == surface.getLongueur() && "E".equals(orientation))||(position_y == 0 && "S".equals(orientation)) || (position_y == surface.getLargeur() && "N".equals(orientation))){
			
		}else if("W".equals(orientation)) {
			position_x--;
		}else if("E".equals(orientation)) {
			position_x ++;
		}else if("S".equals(orientation)) {
			position_y --;
		}else if("N".equals(orientation)) {
			position_y ++;
		}
		
	}
	/**
	 * Orienter la tondeuse
	 * @param direction le sens du mouvement "G" pour gauche et "D" pour droite.
	 * @return 
	 */
	public void pivoter(String direction){
		if("D".equals(direction)){
			switch (orientation) {
			case "N":orientation="E"; break;
			case "E":orientation="S"; break;
			case "S":orientation="W"; break;
			case "W":orientation="N"; break;
			default:
				break;
			}
		}else{
			switch (orientation) {
			case "N":orientation="W"; break;
			case "W":orientation="S"; break;
			case "S":orientation="E"; break;
			case "E":orientation="N"; break;
			default:
				break;
			}
		}
		
	}

}
