package db_communication;

import db_connecter.DBConnection;

public class PlayerDAOImpl implements PlayerDAO 
{
	public void createPlayer(PlayerDTO ple) throws DALException
	{
		DBConnection.doUpdate(
				"INSERT INTO player (id_player, player_name, field_position, account_balance, car_color, car_type) VALUES" +
						"("+ple.getid()+","+ple.name()+","+ple.getFieldPosition()+","+ple.getAccountBalance()+","+ple.getCarColor()+","+ple.getCarType()+")");
	}
}
