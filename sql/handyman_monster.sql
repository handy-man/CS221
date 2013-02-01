-- phpMyAdmin SQL Dump
-- version 3.4.11.1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Feb 01, 2013 at 05:52 AM
-- Server version: 5.5.23
-- PHP Version: 5.2.17

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `handyman_monster`
--

-- --------------------------------------------------------

--
-- Table structure for table `fight`
--

CREATE TABLE IF NOT EXISTS `fight` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `local_mon_ID` int(11) NOT NULL,
  `remote_monster_ID` int(11) NOT NULL,
  `remote_server` varchar(256) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `friend_request`
--

CREATE TABLE IF NOT EXISTS `friend_request` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `local_user_ID` int(11) NOT NULL,
  `remote_user_ID` int(11) NOT NULL,
  `remote_server` varchar(256) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `monsters`
--

CREATE TABLE IF NOT EXISTS `monsters` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `ownerID` int(11) NOT NULL,
  `name` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `birth` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `age_rate` double NOT NULL,
  `health_lost` double NOT NULL,
  `genetic_strength` double NOT NULL,
  `genetic_defence` double NOT NULL,
  `breed_offer` int(11) NOT NULL,
  `sale_offer` int(11) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=5 ;

--
-- Dumping data for table `monsters`
--

INSERT INTO `monsters` (`ID`, `ownerID`, `name`, `birth`, `age_rate`, `health_lost`, `genetic_strength`, `genetic_defence`, `breed_offer`, `sale_offer`) VALUES
(1, 1, 'blargh', '2013-01-29 11:05:17', 0, 0, 0.5, 0.5, 0, 0),
(2, 5, 'blargh', '2013-01-31 16:12:29', 0.5, 0.5, 0.5, 0.5, 0, 0),
(3, 5, 'blargh', '2013-01-31 16:12:52', 0.5, 0.5, 0.5, 0.5, 0, 0),
(4, 5, 'blargh', '2013-01-31 16:12:56', 0.5, 0.5, 0.5, 0.5, 0, 0);

-- --------------------------------------------------------

--
-- Table structure for table `player`
--

CREATE TABLE IF NOT EXISTS `player` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(256) COLLATE utf8_unicode_ci NOT NULL,
  `password` varchar(32) COLLATE utf8_unicode_ci NOT NULL,
  `money` int(11) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=38 ;

--
-- Dumping data for table `player`
--

INSERT INTO `player` (`ID`, `email`, `password`, `money`) VALUES
(1, 'nathan454@hotmail.co.uk', 'password', 100),
(37, 'haha', 'pas', 500),
(36, 'pass', 'pass', 500),
(35, 'nathanh', 'pass', 500),
(34, 'nathan', 'pass', 500),
(33, 'jab38@king.com', '123', 500),
(22, 'newtests', 'pass', 500),
(23, 'nat', 'pass', 500),
(32, 'dgs', 'gabba', 500),
(25, 'nate', 'pass', 500),
(26, 'password', 'pass', 500),
(27, 'fuckyou', 'pass', 500),
(28, 'handyman', 'pass', 500),
(29, 'jimbob', 'potatoe', 500),
(30, 'nathanhand', 'pass', 500),
(31, 'blate', 'pass', 500);

-- --------------------------------------------------------

--
-- Table structure for table `player_friend`
--

CREATE TABLE IF NOT EXISTS `player_friend` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `remoteUserID` int(11) NOT NULL,
  `serverID` varchar(256) COLLATE utf8_unicode_ci NOT NULL,
  `localUserID` int(11) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=2 ;

--
-- Dumping data for table `player_friend`
--

INSERT INTO `player_friend` (`ID`, `remoteUserID`, `serverID`, `localUserID`) VALUES
(1, 1, 'http://www.otherserverdirectory.com', 1);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
