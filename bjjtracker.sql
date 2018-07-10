-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema bjjtracker
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `bjjtracker` ;

-- -----------------------------------------------------
-- Schema bjjtracker
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `bjjtracker` DEFAULT CHARACTER SET utf8 ;
USE `bjjtracker` ;

-- -----------------------------------------------------
-- Table `bjjtracker`.`round`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bjjtracker`.`round` ;

CREATE TABLE IF NOT EXISTS `bjjtracker`.`round` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `opponent` VARCHAR(100) NOT NULL,
  `date` DATETIME NOT NULL,
  `points_scored` INT(11) NULL DEFAULT '0',
  `result` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 5
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `bjjtracker`.`round`
-- -----------------------------------------------------
START TRANSACTION;
USE `bjjtracker`;
INSERT INTO `bjjtracker`.`round` (`id`, `opponent`, `date`, `points_scored`, `result`) VALUES (1, 'Daniel Calvert', '2018-07-01', 2, 'loss');
INSERT INTO `bjjtracker`.`round` (`id`, `opponent`, `date`, `points_scored`, `result`) VALUES (2, 'Eric Robinson', '2018-07-02', 6, 'win');
INSERT INTO `bjjtracker`.`round` (`id`, `opponent`, `date`, `points_scored`, `result`) VALUES (3, 'Tim Varallo', '2018-07-02', 0, 'loss');
INSERT INTO `bjjtracker`.`round` (`id`, `opponent`, `date`, `points_scored`, `result`) VALUES (4, 'Erin Broderick', '2018-07-03', 10, 'win');

COMMIT;
