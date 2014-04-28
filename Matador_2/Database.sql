-- --------------------------------------------------------
-- Host:                         mysql7.gigahost.dk
-- Server version:               5.1.73-1-log - (Debian)
-- Server OS:                    debian-linux-gnu
-- HeidiSQL Version:             8.3.0.4694
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- Dumping database structure for jakobsabinsky_matador
DROP DATABASE IF EXISTS `jakobsabinsky_matador`;
CREATE DATABASE IF NOT EXISTS `jakobsabinsky_matador` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `jakobsabinsky_matador`;


-- Dumping structure for table jakobsabinsky_matador.fields
DROP TABLE IF EXISTS `fields`;
CREATE TABLE IF NOT EXISTS `fields` (
  `field_number` int(11) NOT NULL,
  `field_name` varchar(45) DEFAULT NULL,
  `field_owner` int(11) DEFAULT NULL,
  `number_of_houses` int(11) DEFAULT NULL,
  PRIMARY KEY (`field_number`),
  KEY `field_owner_idx` (`field_owner`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- Dumping data for table jakobsabinsky_matador.fields: 40 rows
DELETE FROM `fields`;
/*!40000 ALTER TABLE `fields` DISABLE KEYS */;
INSERT INTO `fields` (`field_number`, `field_name`, `field_owner`, `number_of_houses`) VALUES
	(1, 'Start', NULL, NULL),
	(2, 'Rødovrevej', NULL, NULL),
	(3, 'Prøv Lykken', NULL, NULL),
	(4, 'Hvidovrevej', NULL, NULL),
	(5, 'SKAT', NULL, NULL),
	(6, 'Scanlines', 1, NULL),
	(7, 'Roskildevej', NULL, NULL),
	(8, 'Prøv Lykken', NULL, NULL),
	(9, 'Valby Langgade', NULL, NULL),
	(10, 'Allégade', 1, NULL),
	(11, 'Fængsel', NULL, NULL),
	(12, 'Frederiksberg Allé', 4, NULL),
	(13, 'Tuborg', NULL, NULL),
	(14, 'Bülowsvej', NULL, NULL),
	(15, 'Gl. Kongevej', NULL, NULL),
	(16, 'Mols Linien', NULL, NULL),
	(17, 'Bernstofsvej', 4, NULL),
	(18, 'Prøv Lykken', NULL, NULL),
	(19, 'Hellerupvej', NULL, NULL),
	(20, 'Strandvejen', NULL, NULL),
	(21, 'Helle', NULL, NULL),
	(22, 'Trianglen', NULL, NULL),
	(23, 'Prøv Lykken', NULL, NULL),
	(24, 'Østerbrogade', NULL, NULL),
	(25, 'Grønningen', NULL, NULL),
	(26, 'Color Line', NULL, NULL),
	(27, 'Bredgade', NULL, NULL),
	(28, 'Kgs. Nytorv', NULL, NULL),
	(29, 'Coca Cola', NULL, NULL),
	(30, 'Østergade', NULL, NULL),
	(31, 'Gå i fængsel', NULL, NULL),
	(32, 'Amagertorv', NULL, NULL),
	(33, 'Vimmelskaftet', NULL, NULL),
	(34, 'Prøv Lykken', NULL, NULL),
	(35, 'Nygade', NULL, NULL),
	(36, 'Oslo Færgen', NULL, NULL),
	(37, 'Prøv Lykken', NULL, NULL),
	(38, 'Frederiksberg gade', NULL, NULL),
	(39, 'Extraordinær skat', NULL, NULL),
	(40, 'Rådhuspladsen', NULL, NULL);
/*!40000 ALTER TABLE `fields` ENABLE KEYS */;


-- Dumping structure for table jakobsabinsky_matador.player
DROP TABLE IF EXISTS `player`;
CREATE TABLE IF NOT EXISTS `player` (
  `id_player` int(11) NOT NULL AUTO_INCREMENT,
  `player_name` varchar(45) DEFAULT NULL,
  `field_position` int(11) NOT NULL,
  `account_balance` int(11) DEFAULT NULL,
  `prison_turn` int(11) DEFAULT NULL,
  `get_out_of_jail_cards` int(11) DEFAULT NULL,
  `car_color` int(11) NOT NULL,
  `car_type` int(11) NOT NULL,
  `player_turn` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_player`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

-- Dumping data for table jakobsabinsky_matador.player: 4 rows
DELETE FROM `player`;
/*!40000 ALTER TABLE `player` DISABLE KEYS */;
INSERT INTO `player` (`id_player`, `player_name`, `field_position`, `account_balance`, `prison_turn`, `get_out_of_jail_cards`, `car_color`, `car_type`, `player_turn`) VALUES
	(1, 'Player3', 10, 23600, 0, 0, -16776961, 0, 0),
	(2, 'Player4', 8, 31000, 0, 0, -256, 0, 1),
	(3, 'Player1', 12, 25800, 0, 0, -16777216, 0, 2),
	(4, 'Player2', 17, 23800, 0, 0, -65536, 0, 3);
/*!40000 ALTER TABLE `player` ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
