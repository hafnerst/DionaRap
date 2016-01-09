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