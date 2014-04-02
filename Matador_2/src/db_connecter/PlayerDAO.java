package db_connecter;

import java.sql.*;
import java.util.List;

import game.Player;

public class PlayerDAO {

	public static void main(String[] args){
	public addPlayer(Player player) throws SQLException {
		Connection conn = DBConnection.getConnection();
		PreparedStatement p = conn.prepareStatement("UPDATE INTO player (player_name, account_balance, car_type, car_color) VALUES (Jakob, 30000, car, blue)");
		
		}
	}
}
