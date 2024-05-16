create database Animals;

CREATE SCHEMA IF NOT EXISTS `Animals` ;
USE `Animals` ;
/* Очистка таблиц для проверки*/

drop table RegistryAnimals;
drop table Commands;
drop  table  Animals;
drop table Title;
drop table AnimalTypes;
 

CREATE TABLE IF NOT EXISTS `Animals`.`AnimalTypes` (
  `idAnimalTypes` INT NOT NULL AUTO_INCREMENT,
  `AnimalTypeName` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idAnimalTypes`));

/*Добавление типа-домашнее или вьючное*/
insert into AnimalTypes (AnimalTypeName)
values
('Pets'),('Pack_animals');  
 

CREATE TABLE IF NOT EXISTS `Animals`.`Commands` (
  `idCommands` INT NOT NULL AUTO_INCREMENT,
  `CommandName` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idCommands`));
  
 /*Заполнение списком команд для животных*/
     insert into Commands (CommandName)
     values ('sit'), ('pounce'), ('stay'), ('roll'), ('scratch'), 
     ('jump'), ('meow'), ('trot'), ('carry'), ('carry load'), ('walk'),('bray'),
     ('gallop'), ('canter'), ('run'), ('kick'), ('throw'), ('get');
  

CREATE TABLE IF NOT EXISTS `Animals`.`Title` (
  `idTitle` INT NOT NULL AUTO_INCREMENT,
  `TitleName` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idTitle`));
  
  /*Заполнение  таблицы с названиями животных*/
    insert into Title (TitleName)
    values ('Dog'), ('Cat'),('Hamster'),('Horse'),('Camel'),('Donkey'),('Rabbit'), 
    ('Pig'), ('Cow'),('Parrot');
  
   
  CREATE TABLE IF NOT EXISTS `Animals`.`Animals` (
  `idAnimals` INT NOT NULL AUTO_INCREMENT,
  `Type` INT NOT NULL,
  `BirthDate` DATETIME NOT NULL,
  `Nickname` VARCHAR(45) NOT NULL,
  `AnimalTitle` INT NOT NULL,
  PRIMARY KEY (`idAnimals`),
  INDEX `fk_type_idx` (`Type` ASC) VISIBLE,
  INDEX `fk_title_idx` (`AnimalTitle` ASC) VISIBLE,
  CONSTRAINT `fk_type`
    FOREIGN KEY (`Type`)
    REFERENCES `Animals`.`AnimalTypes` (`idAnimalTypes`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_title`
    FOREIGN KEY (`AnimalTitle`)
    REFERENCES `Animals`.`Title` (`idTitle`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);
    
    INSERT INTO Animals
  (Type,BirthDate,Nickname,AnimalTitle)
VALUES
  (1,'2021-02-13','Fluffy',3),
  (1,'2023-05-21','Olive',3),
  (2,'2007-11-22','Demon',4),
  (2,'2003-11-01','Gray',5),
  (1,'2020-08-11','Vanilla ',2),
  (2,'2021-05-19','Glory',4),
  (1,'2019-05-15','Pop',7),
  (1,'2018-12-04','Wave',1),
  (1,'2023-01-07','Ninja',3),
  (1,'2021-12-17','Jump',2),
  (1,'2022-07-24','Gosha',8),
  (1,'2022-01-22','Moon',9),
  (1,'2021-01-11','Stas',7),
  (1,'2023-02-28','Bars',2),
  (2,'2017-03-04','Hero',5),
  (1,'2019-05-14','Lily',2),
  (1,'2022-05-16','Ranger',3),
  (2,'2020-04-19','Posh',5),
  (1,'2018-03-03','Peach',2),
  (1,'2023-07-02','Opal',3),
  (2,'2021-04-28','Noble',6),
  (2,'2021-09-16','Daisy',4),
  (1,'2020-07-11','Kesha',10),
  (1,'2014-07-02','Dusy',10),
  (2,'2017-02-24','May',5),
  (1,'2023-06-14','Boris',8),
  (1,'2014-07-01','Quick',1),
  (1,'2021-05-22','Strawberry',3),
  (1,'2022-08-02','Potato',3),
  (2,'2003-02-28','Flash',4),
  (2,'2016-05-14','Dew',4),
  (1,'2023-09-13','Rose',9),
  (1,'2013-08-31','Donut',2),
  (1,'2022-07-27','Sam',10),
  (1,'2020-02-12','Popka',10),
  (2,'2019-08-14','Topaz',6),
  (2,'2022-03-08','Silent',6),
  (1,'2020-09-02','Scarle',1),
  (2,'2007-03-02','Miracle',5),
  (2,'2000-05-06','Crazy',4),
  (1,'2023-10-17','Knight',3),
  (2,'2021-06-10','Abyss',4);
  
  
CREATE TABLE IF NOT EXISTS `Animals`.`RegistryAnimals` (
  `idRegistryAnimals` INT NOT NULL AUTO_INCREMENT,
  `Animal_id` INT NOT NULL,
  `RegistrCommand` INT NOT NULL,
  PRIMARY KEY (`idRegistryAnimals`),
  INDEX `fg_animal_idx` (`Animal_id` ASC) VISIBLE,
  INDEX `fg_reg_command_idx` (`RegistrCommand` ASC) VISIBLE,
  CONSTRAINT `fg_animal`
    FOREIGN KEY (`Animal_id`)
    REFERENCES `Animals`.`Animals` (`idAnimals`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fg_reg_command`
    FOREIGN KEY (`RegistrCommand`)
    REFERENCES `Animals`.`Commands` (`idCommands`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);

-- -----------------------------------------------------
/* Заполнение реестра с животными*/

INSERT INTO RegistryAnimals
  (Animal_id,RegistrCommand)
VALUES
(2,7),(3,10), (3,13), (3,8), (6,9), (6,13), (6,16), (6,3),
(8,18), (8,1), (12,16), (12,11), (14,7), (14,5), (15,12), (15,9),
(15,3), (15,11), (18,9), (18,4), (18,3), (19,7), (19,15), (21,3),
(22,13), (25,9), (25,3), (27,18), (27,1), (30,16), (30,3), (30,8),
(31,3), (36,9), (36,10), (36,3), (37,9), (37,3), (38,18), (38,1), (38,11),
(39,3), (40,3), (42,16), (42,3), (42,8);  

select* from Animals;
select* from AnimalTypes;
select* from Title;
select* from Commands;
select* from RegistryAnimals;

  
  /* Объединение таблицы со всеми животными и командами */

 select  Nickname, TitleName,BirthDate, group_concat(CommandName) as commands
 from Animals
 join Title on idTitle =AnimalTitle
 join RegistryAnimals on Animal_id = idAnimals
 join Commands on idCommands=RegistrCommand
 group by Nickname,TitleName,BirthDate;
 
/* Формирование таблицы с домашними животными */
 
  with pets_animals  as (select* from Animals
	join AnimalTypes on idAnimalTypes =Type and 
	AnimalTypeName ='Pets')
 select  Nickname, TitleName,BirthDate, group_concat(CommandName) as commands
 from pets_animals  
 join Title on idTitle =AnimalTitle
 join RegistryAnimals on Animal_id = idAnimals
 join Commands on idCommands=RegistrCommand
 group by Nickname,TitleName,BirthDate;
 
/* Формирование таблицы с вьючными животными */

with pack_animals  as (select* from Animals 
	join AnimalTypes on idAnimalTypes =Type and 
	AnimalTypeName ='Pack_animals')
 select  Nickname, TitleName,BirthDate, group_concat(CommandName) as commands
 from pack_animals  
 join Title on idTitle =AnimalTitle
 join RegistryAnimals on Animal_id = idAnimals
 join Commands on idCommands=RegistrCommand
 group by Nickname,TitleName,BirthDate;
 
 /*Создание таблицы с животными возрастом до 3 лет)*/
 
 with age_animal as(select* , TIMESTAMPDIFF(MONTH, BirthDate ,CURDATE()) as age from  Animals)
 select  Nickname, TitleName, age , group_concat(CommandName) as commands
 from age_animal
 join Title on idTitle =AnimalTitle 
 join RegistryAnimals on Animal_id = idAnimals and age <=36
 join Commands on idCommands=RegistrCommand
 group by Nickname,TitleName,BirthDate;
 
 /* Удаление записи с верблюдами*/
  
 delete from Title where TitleName ='Camel';
 
 
 
 
