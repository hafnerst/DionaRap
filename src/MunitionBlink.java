/**
 * Programm:	  DionaRap
 * Ueberschrift:  Anweisungen des Munition BlinkeThreads
 * Beschreibung:  Zeigt die Anweisungen die beim aufrufen des Threads bearbeitet werden
 * Organisation:  Hochschule Ravensburg-Weingarten
 * @author Daniel Landler-Gaertner und Steffen Hafner
 * @version 1.0
 */

public class MunitionBlink implements Runnable {
	
	/** Die Toolbar, in der die Munitionsanzeige blinken soll */
	Toolbar toolbar;
	/** Die Zeit, die zwischen dem Blinken liegt */
	private static int BLINK_TIME = 300;
	
	/**
	 * Standartkonstruktor
	 */
	public MunitionBlink(Toolbar t) {
		toolbar = t;
	}

	/**
	 * Laesst die Munitionsanzeige in einem eigenen thread blinken
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
			}
			toolbar.ammoBlink(false);
			try {
				Thread.sleep(MunitionBlink.BLINK_TIME);
			} catch (InterruptedException e) {
				toolbar.ammoBlink(false);
			}
		}
	}
}