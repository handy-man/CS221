SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

CREATE SCHEMA IF NOT EXISTS `monster_mash` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `monster_mash` ;

-- -----------------------------------------------------
-- Table `monster_mash`.`player`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `monster_mash`.`player` (
  `id` INT NOT NULL ,
  `serverID` INT NULL ,
  `email` VARCHAR(256) NULL ,
  `password` VARCHAR(8) NULL ,
  `money` INT NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `monster_mash`.`monster`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `monster_mash`.`monster` (
  `owner` INT NOT NULL ,
  `name` VARCHAR(45) NULL ,
  `birth` DATE NULL ,
  `health_Lost` DOUBLE NULL ,
  'base_health' DOUBLE NULL,
  'genetic_strength', DOUBLE NULL,
  'genetic_toughness', DOUBLE NULL,
  'genetic_evasion', DOUBLE NULL,
  PRIMARY KEY (`owner`) ,
  INDEX `owner_idx` (`owner` ASC) ,
  CONSTRAINT `owner`
    FOREIGN KEY (`owner` )
    REFERENCES `monster_mash`.`player` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;



SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
