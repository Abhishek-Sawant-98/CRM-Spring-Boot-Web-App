
-- -----------------------------------------------------
-- Schema crm
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `crm` ;

CREATE SCHEMA IF NOT EXISTS `crm`;
USE `crm` ;

-- -----------------------------------------------------
-- Table `crm`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `crm`.`user` (
  `user_id` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `first_name` VARCHAR(45) NOT NULL,
  `last_name` VARCHAR(45) NULL DEFAULT NULL,
  `date_of_birth` DATE NOT NULL,
  `gender` VARCHAR(10) NOT NULL,
  `contact_number` CHAR(10) NOT NULL,
  `email_id` VARCHAR(255) NOT NULL,
  `job_title` VARCHAR(45) NOT NULL,
  `company` VARCHAR(45) NOT NULL,
  `employee_count` VARCHAR(10) NOT NULL,
  `country` VARCHAR(45) NOT NULL,
  UNIQUE INDEX `user_id_UNIQUE` (`user_id` ASC) VISIBLE,
  PRIMARY KEY (`user_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `crm`.`account`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `crm`.`account` (
  `idAccount` INT NOT NULL AUTO_INCREMENT,
  `account_owner` VARCHAR(45) NOT NULL,
  `account_name` VARCHAR(45) NOT NULL,
  `phone` CHAR(10) NOT NULL,
  `fax` VARCHAR(20) NULL DEFAULT NULL,
  `website` VARCHAR(45) NULL DEFAULT NULL,
  `type` VARCHAR(45) NOT NULL,
  `employee_count` INT NOT NULL,
  `annual_revenue` INT NOT NULL,
  `industry` VARCHAR(45) NOT NULL,
  `description` TEXT NULL DEFAULT NULL,
  PRIMARY KEY (`idAccount`),
  INDEX `fk_account_user_idx` (`account_owner` ASC) VISIBLE,
  CONSTRAINT `fk_account_user`
    FOREIGN KEY (`account_owner`)
    REFERENCES `crm`.`user` (`user_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `crm`.`lead`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `crm`.`lead` (
  `idLead` INT NOT NULL AUTO_INCREMENT,
  `lead_owner` VARCHAR(45) NOT NULL,
  `lead_status` VARCHAR(45) NOT NULL,
  `phone` CHAR(10) NOT NULL,
  `company` VARCHAR(45) NOT NULL,
  `email_id` VARCHAR(45) NOT NULL,
  `website` VARCHAR(45) NOT NULL,
  `lead_source` VARCHAR(45) NOT NULL,
  `employee_count` INT NOT NULL,
  `annual_revenue` INT NOT NULL,
  `industry` VARCHAR(45) NOT NULL,
  `description` TEXT NULL DEFAULT NULL,
  PRIMARY KEY (`idLead`),
  INDEX `fk_lead_user1_idx` (`lead_owner` ASC) VISIBLE,
  CONSTRAINT `fk_lead_user1`
    FOREIGN KEY (`lead_owner`)
    REFERENCES `crm`.`user` (`user_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `crm`.`contact`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `crm`.`contact` (
  `idContact` INT NOT NULL AUTO_INCREMENT,
  `contact_owner` VARCHAR(45) NOT NULL,
  `idAccount` INT NOT NULL,
  `mobile` CHAR(10) NOT NULL,
  `email_id` VARCHAR(45) NOT NULL,
  `title` VARCHAR(45) NOT NULL,
  `fax` VARCHAR(20) NULL DEFAULT NULL,
  `department` VARCHAR(45) NOT NULL,
  `home_phone` CHAR(10) NULL DEFAULT NULL,
  `other_phone` CHAR(10) NULL DEFAULT NULL,
  `lead_source` VARCHAR(45) NOT NULL,
  `birth_date` VARCHAR(45) NOT NULL,
  `assistant` VARCHAR(45) NULL DEFAULT NULL,
  `assistant_phone` CHAR(10) NULL DEFAULT NULL,
  `description` TEXT NULL DEFAULT NULL,
  PRIMARY KEY (`idContact`),
  INDEX `fk_contact_user1_idx` (`contact_owner` ASC) VISIBLE,
  INDEX `fk_contact_account1_idx` (`idAccount` ASC) VISIBLE,
  CONSTRAINT `fk_contact_user1`
    FOREIGN KEY (`contact_owner`)
    REFERENCES `crm`.`user` (`user_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_contact_account1`
    FOREIGN KEY (`idAccount`)
    REFERENCES `crm`.`account` (`idAccount`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `crm`.`address`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `crm`.`address` (
  `idAddress` INT NOT NULL AUTO_INCREMENT,
  `street` VARCHAR(45) NOT NULL,
  `city` VARCHAR(45) NOT NULL,
  `state_province` VARCHAR(45) NOT NULL,
  `zip_postal` VARCHAR(20) NOT NULL,
  `country` VARCHAR(45) NOT NULL,
  `type` VARCHAR(20) NULL DEFAULT NULL,
  `idLead` INT NULL,
  `idAccount` INT NULL,
  `idContact` INT NULL,
  PRIMARY KEY (`idAddress`),
  INDEX `fk_address_lead1_idx` (`idLead` ASC) VISIBLE,
  INDEX `fk_address_account1_idx` (`idAccount` ASC) VISIBLE,
  INDEX `fk_address_contact1_idx` (`idContact` ASC) VISIBLE,
  UNIQUE INDEX `idLead_UNIQUE` (`idLead` ASC) VISIBLE,
  CONSTRAINT `fk_address_lead1`
    FOREIGN KEY (`idLead`)
    REFERENCES `crm`.`lead` (`idLead`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_address_account1`
    FOREIGN KEY (`idAccount`)
    REFERENCES `crm`.`account` (`idAccount`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_address_contact1`
    FOREIGN KEY (`idContact`)
    REFERENCES `crm`.`contact` (`idContact`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `crm`.`case`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `crm`.`case` (
  `idCase` INT NOT NULL AUTO_INCREMENT,
  `case_owner` VARCHAR(45) NOT NULL,
  `idContact` INT NOT NULL,
  `idAccount` INT NOT NULL,
  `email_id` VARCHAR(45) NOT NULL,
  `status` VARCHAR(45) NOT NULL,
  `type` VARCHAR(45) NOT NULL,
  `case_origin` VARCHAR(45) NOT NULL,
  `case_reason` VARCHAR(45) NOT NULL,
  `case_priority` VARCHAR(45) NOT NULL,
  `subject` TEXT NOT NULL,
  `description` TEXT NULL DEFAULT NULL,
  `internal_comments` TEXT NULL DEFAULT NULL,
  PRIMARY KEY (`idCase`),
  INDEX `fk_case_user1_idx` (`case_owner` ASC) VISIBLE,
  INDEX `fk_case_account1_idx` (`idAccount` ASC) VISIBLE,
  INDEX `fk_case_contact1_idx` (`idContact` ASC) VISIBLE,
  CONSTRAINT `fk_case_user1`
    FOREIGN KEY (`case_owner`)
    REFERENCES `crm`.`user` (`user_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_case_account1`
    FOREIGN KEY (`idAccount`)
    REFERENCES `crm`.`account` (`idAccount`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_case_contact1`
    FOREIGN KEY (`idContact`)
    REFERENCES `crm`.`contact` (`idContact`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `crm`.`event`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `crm`.`event` (
  `idEvent` INT NOT NULL AUTO_INCREMENT,
  `idContact` INT NOT NULL,
  `idAccount` INT NOT NULL,
  `subject` VARCHAR(45) NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `date` DATE NOT NULL,
  `start` TIME NOT NULL,
  `end` TIME NOT NULL,
  `location` VARCHAR(45) NOT NULL,
  `description` TEXT NULL DEFAULT NULL,
  PRIMARY KEY (`idEvent`),
  INDEX `fk_event_contact1_idx` (`idContact` ASC) VISIBLE,
  INDEX `fk_event_account1_idx` (`idAccount` ASC) VISIBLE,
  CONSTRAINT `fk_event_contact1`
    FOREIGN KEY (`idContact`)
    REFERENCES `crm`.`contact` (`idContact`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_event_account1`
    FOREIGN KEY (`idAccount`)
    REFERENCES `crm`.`account` (`idAccount`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `crm`.`name`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `crm`.`name` (
  `idName` INT NOT NULL AUTO_INCREMENT,
  `salutation` VARCHAR(10) NOT NULL,
  `first_name` VARCHAR(45) NOT NULL,
  `last_name` VARCHAR(45) NULL DEFAULT NULL,
  `idLead` INT NULL,
  `idContact` INT NULL,
  PRIMARY KEY (`idName`),
  INDEX `fk_name_lead1_idx` (`idLead` ASC) VISIBLE,
  INDEX `fk_name_contact1_idx` (`idContact` ASC) VISIBLE,
  UNIQUE INDEX `idLead_UNIQUE` (`idLead` ASC) VISIBLE,
  UNIQUE INDEX `idContact_UNIQUE` (`idContact` ASC) VISIBLE,
  CONSTRAINT `fk_name_lead1`
    FOREIGN KEY (`idLead`)
    REFERENCES `crm`.`lead` (`idLead`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_name_contact1`
    FOREIGN KEY (`idContact`)
    REFERENCES `crm`.`contact` (`idContact`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `crm`.`opportunity`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `crm`.`opportunity` (
  `idOpportunity` INT NOT NULL AUTO_INCREMENT,
  `opportunity_owner` VARCHAR(45) NOT NULL,
  `idAccount` INT NOT NULL,
  `opportunity_name` VARCHAR(45) NOT NULL,
  `type` VARCHAR(45) NOT NULL,
  `stage` VARCHAR(45) NOT NULL,
  `probability` INT NULL DEFAULT NULL,
  `close_date` DATE NOT NULL,
  `lead_source` VARCHAR(45) NOT NULL,
  `amount` VARCHAR(45) NOT NULL,
  `description` TEXT NULL DEFAULT NULL,
  `next_step` TEXT NULL DEFAULT NULL,
  PRIMARY KEY (`idOpportunity`),
  INDEX `fk_opportunity_user1_idx` (`opportunity_owner` ASC) VISIBLE,
  INDEX `fk_opportunity_account1_idx` (`idAccount` ASC) VISIBLE,
  CONSTRAINT `fk_opportunity_user1`
    FOREIGN KEY (`opportunity_owner`)
    REFERENCES `crm`.`user` (`user_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_opportunity_account1`
    FOREIGN KEY (`idAccount`)
    REFERENCES `crm`.`account` (`idAccount`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `crm`.`task`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `crm`.`task` (
  `idTask` INT NOT NULL AUTO_INCREMENT,
  `idContact` INT NOT NULL,
  `idAccount` INT NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `subject` TEXT NOT NULL,
  `comments` TEXT NULL DEFAULT NULL,
  `due_date` DATE NOT NULL,
  `reminder_date` DATE NOT NULL,
  `reminder_time` TIME NOT NULL,
  `status` VARCHAR(45) NOT NULL,
  `priority` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`idTask`),
  INDEX `fk_task_contact1_idx` (`idContact` ASC) VISIBLE,
  INDEX `fk_task_account1_idx` (`idAccount` ASC) VISIBLE,
  CONSTRAINT `fk_task_contact1`
    FOREIGN KEY (`idContact`)
    REFERENCES `crm`.`contact` (`idContact`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_task_account1`
    FOREIGN KEY (`idAccount`)
    REFERENCES `crm`.`account` (`idAccount`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;

