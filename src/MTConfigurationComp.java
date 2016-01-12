import de.fhwgt.dionarap.model.data.MTConfiguration;

/**
 * 
 * Klasse MTConfigurationComp dient zur Erzeugung einer neuen Configuration.
 *
 */

public class MTConfigurationComp extends MTConfiguration 
{
	int zeilen;
	int spalten;
	int obstacleCount;
	int opponentCount;
	
	public MTConfigurationComp() 
	{
		
	}
	
/**
 * Dient zum setzen der neuen Zeilenmenge.
 * @param _zeilen die eingegeben Zeilen als Int
 */
	
	void setZeilen(int _zeilen) 
	{
		zeilen = _zeilen;
	}
	
/**
 * Dient zum setzen der neuen Spaltenmenge.
 * @param _spalten die eingegeben Spalten als Int
 */
	
	void setSpalten(int _spalten) 
	{
		spalten = _spalten;
	}
	
/**
 * Dient zum setzen der neuen Hindernissmenge.
 * @param _obstacleCount die eingegebene Menge der Hindernisse als Int
 */
	
	void setObstacleCount(int _obstacleCount) 
	{
		obstacleCount = _obstacleCount;
	}
	
/**
 * Dient zum setzen der neuen Gegnermenge.
 * @param _opponentCount die eingegebene Menge der Gegner als Int
 */
	
	void setOpponentCount(int _opponentCount) 
	{
		opponentCount = _opponentCount;
	}
	
	int getZeilen() 
	{
		return zeilen;
	}
	
	int getSpalten() 
	{
		return spalten;
	}
	
	int getObstacleCount() 
	{
		return obstacleCount;
	}
	
	int getOpponentCount() 
	{
		return opponentCount;
	}
}