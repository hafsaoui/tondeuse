package thread;

import obj.Instructions;
import obj.Surface;
import obj.Tondeuse;

public class Traitement extends Thread {
	private Instructions instruction;
	private Surface surface;
	
	public Traitement(Instructions instruction,Surface surface){
		super();
		this.instruction=instruction;
		this.surface= surface;
	}
	
	public void run(){
		//on bloque les autres tondeuses d'avoir accès à la surface(une seule tondeuse sur la surface)
		synchronized (surface) {
			Tondeuse tondeuse = new Tondeuse();
			tondeuse.setPosition_x(instruction.getPosition_init_x());
			tondeuse.setPosition_y(instruction.getPosition_init_y());
			tondeuse.setOrientation(instruction.getDirection());
			for(String inst:instruction.getSequence_control()){
				if("A".equals(inst)){
					tondeuse.avancer(surface);
				}else {
					tondeuse.pivoter(inst);
				}
			}
			System.out.println(tondeuse.getPosition_x()+" "+tondeuse.getPosition_y()+ " "+ tondeuse.getOrientation());
		}
	}

	public Instructions getInstruction() {
		return instruction;
	}

	public void setInstruction(Instructions instruction) {
		this.instruction = instruction;
	}

}
