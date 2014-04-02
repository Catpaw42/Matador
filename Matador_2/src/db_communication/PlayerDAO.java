package db_communication;

import java.util.List;

import game.Player;

public interface PlayerDAO {
	public List<Player> getAllPlayers();
	public Player getPlayer(int id);
	public void updatePlayer(Player player);
	public void deletePlayer(Player plaayer);
}
