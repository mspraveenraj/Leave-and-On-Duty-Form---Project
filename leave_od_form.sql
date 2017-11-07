-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Nov 04, 2017 at 04:15 AM
-- Server version: 10.1.26-MariaDB
-- PHP Version: 7.1.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `leave_od_form`
--

-- --------------------------------------------------------

--
-- Table structure for table `faculty`
--

CREATE TABLE `faculty` (
  `facultyid` int(11) NOT NULL,
  `facultyname` varchar(30) NOT NULL,
  `advisorforyear` int(11) NOT NULL,
  `advisorforsection` char(1) NOT NULL,
  `role` varchar(20) NOT NULL,
  `username` varchar(30) NOT NULL,
  `password` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `faculty`
--

INSERT INTO `faculty` (`facultyid`, `facultyname`, `advisorforyear`, `advisorforsection`, `role`, `username`, `password`) VALUES
(1, 'raj', 4, 'c', 'advisor', 'raj', '1234');

-- --------------------------------------------------------

--
-- Table structure for table `leaveapplied`
--

CREATE TABLE `leaveapplied` (
  `leaveappliedid` int(11) NOT NULL,
  `studentid` int(11) DEFAULT NULL,
  `leaveformid` int(11) DEFAULT NULL,
  `acceptreject` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `leaveapplied`
--

INSERT INTO `leaveapplied` (`leaveappliedid`, `studentid`, `leaveformid`, `acceptreject`) VALUES
(1, 1, 1, 'accept'),
(2, 1, 2, 'pending'),
(3, 1, 3, 'pending'),
(4, 1, 4, 'pending');

-- --------------------------------------------------------

--
-- Table structure for table `leaveform`
--

CREATE TABLE `leaveform` (
  `leaveformid` int(11) NOT NULL,
  `studentid` int(11) DEFAULT NULL,
  `dateofapply` date NOT NULL,
  `dateofleavefrom` varchar(10) NOT NULL,
  `dateofleaveto` varchar(10) NOT NULL,
  `reason` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `leaveform`
--

INSERT INTO `leaveform` (`leaveformid`, `studentid`, `dateofapply`, `dateofleavefrom`, `dateofleaveto`, `reason`) VALUES
(1, 1, '2017-10-30', '10/02/2017', '10/10/2017', 'hello'),
(2, 1, '2017-10-30', '10/23/2017', '10/11/2017', 'hhf'),
(3, 1, '2017-10-31', '10/11/2017', '10/11/2017', 'fg'),
(4, 1, '2017-11-01', '11/01/2017', '11/16/2017', 'jh');

-- --------------------------------------------------------

--
-- Table structure for table `odapplied`
--

CREATE TABLE `odapplied` (
  `odappliedid` int(11) NOT NULL,
  `studentid` int(11) DEFAULT NULL,
  `odformid` int(11) DEFAULT NULL,
  `acceptreject` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `odapplied`
--

INSERT INTO `odapplied` (`odappliedid`, `studentid`, `odformid`, `acceptreject`) VALUES
(1, 1, 1, 'pending'),
(2, 1, 2, 'pending');

-- --------------------------------------------------------

--
-- Table structure for table `odform`
--

CREATE TABLE `odform` (
  `odformid` int(11) NOT NULL,
  `studentid` int(11) DEFAULT NULL,
  `dateofapply` date NOT NULL,
  `permissionfrom` varchar(20) NOT NULL,
  `permissionto` varchar(20) NOT NULL,
  `reason` varchar(50) NOT NULL,
  `workassignedby` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `odform`
--

INSERT INTO `odform` (`odformid`, `studentid`, `dateofapply`, `permissionfrom`, `permissionto`, `reason`, `workassignedby`) VALUES
(1, 1, '2017-10-30', '2017/09/15 05:03', '2017/09/15 05:03', 'hai', 'me'),
(2, 1, '2017-11-01', '2017/09/06 08:20', '2017/09/07 09:30', 'symposium', 'advisor');

-- --------------------------------------------------------

--
-- Table structure for table `student`
--

CREATE TABLE `student` (
  `studentid` int(11) NOT NULL,
  `studentname` varchar(30) NOT NULL,
  `regno` varchar(7) NOT NULL,
  `year` int(11) NOT NULL,
  `section` char(1) NOT NULL,
  `password` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `student`
--

INSERT INTO `student` (`studentid`, `studentname`, `regno`, `year`, `section`, `password`) VALUES
(1, 'raj', '1413075', 4, 'c', '1234');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `faculty`
--
ALTER TABLE `faculty`
  ADD PRIMARY KEY (`facultyid`),
  ADD UNIQUE KEY `username` (`username`);

--
-- Indexes for table `leaveapplied`
--
ALTER TABLE `leaveapplied`
  ADD PRIMARY KEY (`leaveappliedid`),
  ADD KEY `fk_studentleaveapplied` (`studentid`),
  ADD KEY `fk_leaveformleaveapplied` (`leaveformid`);

--
-- Indexes for table `leaveform`
--
ALTER TABLE `leaveform`
  ADD PRIMARY KEY (`leaveformid`),
  ADD KEY `fk_studentleaveform` (`studentid`);

--
-- Indexes for table `odapplied`
--
ALTER TABLE `odapplied`
  ADD PRIMARY KEY (`odappliedid`),
  ADD KEY `fk_studentodformapplied` (`studentid`),
  ADD KEY `fk_odformodapplied` (`odformid`);

--
-- Indexes for table `odform`
--
ALTER TABLE `odform`
  ADD PRIMARY KEY (`odformid`),
  ADD KEY `fk_studentodform` (`studentid`);

--
-- Indexes for table `student`
--
ALTER TABLE `student`
  ADD PRIMARY KEY (`studentid`),
  ADD UNIQUE KEY `regno` (`regno`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `faculty`
--
ALTER TABLE `faculty`
  MODIFY `facultyid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `leaveapplied`
--
ALTER TABLE `leaveapplied`
  MODIFY `leaveappliedid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `leaveform`
--
ALTER TABLE `leaveform`
  MODIFY `leaveformid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `odapplied`
--
ALTER TABLE `odapplied`
  MODIFY `odappliedid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `odform`
--
ALTER TABLE `odform`
  MODIFY `odformid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `student`
--
ALTER TABLE `student`
  MODIFY `studentid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `leaveapplied`
--
ALTER TABLE `leaveapplied`
  ADD CONSTRAINT `fk_leaveformleaveapplied` FOREIGN KEY (`leaveformid`) REFERENCES `leaveform` (`leaveformid`),
  ADD CONSTRAINT `fk_studentleaveapplied` FOREIGN KEY (`studentid`) REFERENCES `student` (`studentid`);

--
-- Constraints for table `leaveform`
--
ALTER TABLE `leaveform`
  ADD CONSTRAINT `fk_studentleaveform` FOREIGN KEY (`studentid`) REFERENCES `student` (`studentid`);

--
-- Constraints for table `odapplied`
--
ALTER TABLE `odapplied`
  ADD CONSTRAINT `fk_odformodapplied` FOREIGN KEY (`odformid`) REFERENCES `odform` (`odformid`),
  ADD CONSTRAINT `fk_studentodformapplied` FOREIGN KEY (`studentid`) REFERENCES `student` (`studentid`);

--
-- Constraints for table `odform`
--
ALTER TABLE `odform`
  ADD CONSTRAINT `fk_studentodform` FOREIGN KEY (`studentid`) REFERENCES `student` (`studentid`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
