package obj;

import java.util.ArrayList;

public class Instructions {
	
	private int position_init_x;
	private int position_init_y;
	private String direction;
	private ArrayList<String> sequence_control;

	public int getPosition_init_x() {
		return position_init_x;
	}
	public void setPosition_init_x(int position_init_x) {
		this.position_init_x = position_init_x;
	}
	public int getPosition_init_y() {
		return position_init_y;
	}
	public void setPosition_init_y(int position_init_y) {
		this.position_init_y = position_init_y;
	}
	public String getDirection() {
		return direction;
	}
	public void setDirection(String direction) {
		this.direction = direction;
	}
	public ArrayList<String> getSequence_control() {
		return sequence_control;
	}
	public void setSequence_control(ArrayList<String> sequence_control) {
		this.sequence_control = sequence_control;
	}
	
}
