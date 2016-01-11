import de.fhwgt.dionarap.model.data.MTConfiguration;


public class MTConfigurationComp extends MTConfiguration 
{
	int zeilen;
	int spalten;
	int obstacleCount;
	int opponentCount;
	
	public MTConfigurationComp() 
	{
		
	}
	
	void setZeilen(int _zeilen) 
	{
		zeilen = _zeilen;
	}
	
	void setSpalten(int _spalten) 
	{
		spalten = _spalten;
	}
	
	void setObstacleCount(int _obstacleCount) 
	{
		obstacleCount = _obstacleCount;
	}
	
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