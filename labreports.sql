-- phpMyAdmin SQL Dump
-- version 4.9.0.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 06, 2020 at 08:37 AM
-- Server version: 10.3.16-MariaDB
-- PHP Version: 7.3.7

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `labreport`
--

-- --------------------------------------------------------

--
-- Table structure for table `labreports`
--

CREATE TABLE `labreports` (
  `lReportID` int(10) NOT NULL,
  `lReportNum` varchar(10) NOT NULL,
  `lReportName` varchar(30) NOT NULL,
  `lReportDet` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `labreports`
--

INSERT INTO `labreports` (`lReportID`, `lReportNum`, `lReportName`, `lReportDet`) VALUES
(1, '10', 'Name 1', '1st Report'),
(2, '17', 'Name 8', 'New Report'),
(7, '30', 'Name 13', 'New Important Report 2'),
(11, '25', 'Name 13', 'Important Lab Report 3'),
(12, '26', 'Name 13', 'Important Lab Report 3');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `labreports`
--
ALTER TABLE `labreports`
  ADD PRIMARY KEY (`lReportID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `labreports`
--
ALTER TABLE `labreports`
  MODIFY `lReportID` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
