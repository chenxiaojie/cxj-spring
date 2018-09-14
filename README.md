spring学习经典案例，springboot，spring5.x，aop，mybatis，tomcat，groovy，dao

初始化：

create database GC_Print;

use GC_Print;

CREATE TABLE `CP_College` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `CollegeName` varchar(128) NOT NULL DEFAULT '',
  `CollegeAddress` varchar(512) NOT NULL DEFAULT '',
  PRIMARY KEY (`ID`),
  KEY `IX_Name` (`CollegeName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;