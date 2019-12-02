package threads;

import controller.ControllerMenu;


public class MovementThread extends Thread {

	private ControllerMenu cM;

	public MovementThread(ControllerMenu cM) {
		this.cM = cM;

	}

	public void run() {
		boolean noEnd = true;
		while (noEnd) {
			try {
				Thread.sleep(20);
				try {
					cM.movement();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
