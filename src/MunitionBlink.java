/**
 * 
 * Klasse MunitionBlink dient zur Erzeugung des Threads für das Blinken der Munition.
 *
 */

public class MunitionBlink implements Runnable {
	
	Toolbar toolbar;
	private static int BLINK_TIME = 300;

/**
 * Erzeugt eine Instanz der Klasse MunitionBlink.	
 * @param t die aktuelle Toolbar
 */
	
	public MunitionBlink(Toolbar t) {
		toolbar = t;
	}

/**
 * Lässt den Thread laufen.
 */
	
	public void run() {
		for (int i = 0; i < 3; i++) {
			
			//Falls spieler Ammo holt
			if(Thread.interrupted()){
				toolbar.ammoBlink(false);
				break;
			}
			
			toolbar.ammoBlink(true);
			try {
				Thread.sleep(MunitionBlink.BLINK_TIME);
			} catch (InterruptedException e) {
				toolbar.ammoBlink(false);
				break;
			}
			toolbar.ammoBlink(false);
			try {
				Thread.sleep(MunitionBlink.BLINK_TIME);
			} catch (InterruptedException e) {
				toolbar.ammoBlink(false);
				break;
			}
		}
	}
}