-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: Aug 12, 2019 at 05:28 AM
-- Server version: 8.0.17
-- PHP Version: 7.2.18

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `cms`
--

-- --------------------------------------------------------

--
-- Table structure for table `audit`
--

DROP TABLE IF EXISTS `audit`;
CREATE TABLE IF NOT EXISTS `audit` (
  `auditid` int(11) NOT NULL,
  `fk_userid_audit` int(11) DEFAULT NULL,
  `fk_requestid_audit` int(11) DEFAULT NULL,
  `date` date DEFAULT NULL,
  `field_updated` varchar(45) DEFAULT NULL,
  `field_before` varchar(45) DEFAULT NULL,
  `field_after` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`auditid`),
  KEY `fk_userid_audit_idx` (`fk_userid_audit`),
  KEY `fk_requestid_audit_idx` (`fk_requestid_audit`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `contract`
--

DROP TABLE IF EXISTS `contract`;
CREATE TABLE IF NOT EXISTS `contract` (
  `requestid` int(11) NOT NULL,
  `userid` int(11) DEFAULT NULL,
  `statusid` int(11) DEFAULT NULL,
  `agreement_title` varchar(45) DEFAULT NULL,
  `agreement_type` varchar(45) DEFAULT NULL,
  `description` varchar(45) DEFAULT NULL,
  `agreement_location` varchar(45) DEFAULT NULL,
  `language` varchar(45) DEFAULT NULL,
  `region` varchar(45) DEFAULT NULL,
  `related_agreements` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`requestid`),
  KEY `userid_idx` (`userid`),
  KEY `statusid_idx` (`statusid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `contract_stakeholder`
--

DROP TABLE IF EXISTS `contract_stakeholder`;
CREATE TABLE IF NOT EXISTS `contract_stakeholder` (
  `fk_requestid_contract_stakeholder` int(11) DEFAULT NULL,
  `fl_businessname_contract_stakeholders` varchar(45) DEFAULT NULL,
  KEY `fk_businessname_contract_stakeholders_idx` (`fl_businessname_contract_stakeholders`),
  KEY `fk_requestid_contract_stakeholder` (`fk_requestid_contract_stakeholder`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `expired`
--

DROP TABLE IF EXISTS `expired`;
CREATE TABLE IF NOT EXISTS `expired` (
  `fk_requestid_expired` int(11) DEFAULT NULL,
  `ending_reason` varchar(45) DEFAULT NULL,
  `expiry_date` date DEFAULT NULL,
  `termination_date` date DEFAULT NULL,
  `termination_notice_date` varchar(45) DEFAULT NULL,
  `binding_terms_active` varchar(45) DEFAULT NULL,
  KEY `fk_requestid_expired` (`fk_requestid_expired`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `external_stakeholders`
--

DROP TABLE IF EXISTS `external_stakeholders`;
CREATE TABLE IF NOT EXISTS `external_stakeholders` (
  `businessname` varchar(45) NOT NULL,
  `address` varchar(45) DEFAULT NULL,
  `phone` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `fax` varchar(45) DEFAULT NULL,
  `client_name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`businessname`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `in_negotiation`
--

DROP TABLE IF EXISTS `in_negotiation`;
CREATE TABLE IF NOT EXISTS `in_negotiation` (
  `fk_requestid_in_negotiation` int(11) DEFAULT NULL,
  `comments` varchar(45) DEFAULT NULL,
  KEY `fk_requestid_in_negotiation` (`fk_requestid_in_negotiation`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `operative`
--

DROP TABLE IF EXISTS `operative`;
CREATE TABLE IF NOT EXISTS `operative` (
  `fk_requestid_operative` int(11) DEFAULT NULL,
  `date_signed` date DEFAULT NULL,
  `date_commenced` date DEFAULT NULL,
  `date_expire` date DEFAULT NULL,
  `verification` varchar(45) DEFAULT NULL,
  KEY `requestid_idx` (`fk_requestid_operative`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `status`
--

DROP TABLE IF EXISTS `status`;
CREATE TABLE IF NOT EXISTS `status` (
  `statusid` int(11) NOT NULL,
  `previousstatus` int(11) DEFAULT NULL,
  `active` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`statusid`),
  KEY `fk_previous_status_idx` (`previousstatus`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
  `userid` int(11) NOT NULL,
  `firstname` varchar(45) DEFAULT NULL,
  `lastname` varchar(45) DEFAULT NULL,
  `role` varchar(45) DEFAULT NULL,
  `username` varchar(45) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  `locked` binary(1) DEFAULT NULL,
  PRIMARY KEY (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `audit`
--
ALTER TABLE `audit`
  ADD CONSTRAINT `fk_requestid_audit` FOREIGN KEY (`fk_requestid_audit`) REFERENCES `contract` (`requestid`),
  ADD CONSTRAINT `fk_userid_audit` FOREIGN KEY (`fk_userid_audit`) REFERENCES `user` (`userid`);

--
-- Constraints for table `contract`
--
ALTER TABLE `contract`
  ADD CONSTRAINT `statusid` FOREIGN KEY (`statusid`) REFERENCES `status` (`statusid`),
  ADD CONSTRAINT `userid` FOREIGN KEY (`userid`) REFERENCES `user` (`userid`);

--
-- Constraints for table `contract_stakeholder`
--
ALTER TABLE `contract_stakeholder`
  ADD CONSTRAINT `fk_businessname_contract_stakeholders` FOREIGN KEY (`fl_businessname_contract_stakeholders`) REFERENCES `external_stakeholders` (`businessname`),
  ADD CONSTRAINT `fk_requestid_contract_stakeholder` FOREIGN KEY (`fk_requestid_contract_stakeholder`) REFERENCES `contract` (`requestid`);

--
-- Constraints for table `expired`
--
ALTER TABLE `expired`
  ADD CONSTRAINT `fk_requestid_expired` FOREIGN KEY (`fk_requestid_expired`) REFERENCES `contract` (`requestid`);

--
-- Constraints for table `in_negotiation`
--
ALTER TABLE `in_negotiation`
  ADD CONSTRAINT `fk_requestid_in_negotiation` FOREIGN KEY (`fk_requestid_in_negotiation`) REFERENCES `contract` (`requestid`);

--
-- Constraints for table `operative`
--
ALTER TABLE `operative`
  ADD CONSTRAINT `requestid` FOREIGN KEY (`fk_requestid_operative`) REFERENCES `contract` (`requestid`);

--
-- Constraints for table `status`
--
ALTER TABLE `status`
  ADD CONSTRAINT `fk_previous_status` FOREIGN KEY (`previousstatus`) REFERENCES `status` (`statusid`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
