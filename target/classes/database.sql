drop database PetManager;
create database if not exists PetManager;

use PetManager;

create table if not exists owners(
	id int auto_increment not null,
	name varchar(50) not null,
	primary key(id)
);

create table if not exists petTypes(
	id int auto_increment not null,
    type varchar(20) not null,
    primary key(id)
);

create table if not exists pets(
	id int auto_increment not null,
    ownerId int null,
    name varchar(50) not null,
    age int not null,
    pet_type_id int not null,
    primary key(id),
	foreign key(ownerId) references owners(id),
    foreign key(pet_type_id) references petTypes(id)
);

create table if not exists meals(
	id int auto_increment not null,
    name varchar(50) not null,
    weight double not null,
    pet_type_id int not null,
	primary key(id),
    foreign key(pet_type_id) references petTypes(id)
);

create table if not exists toys(
	id int auto_increment not null,
    material varchar(50) not null,
    pet_type_id int not null,
    price double not null,
    primary key(id),
    foreign key(pet_type_id) references petTypes(id)
);

SELECT * FROM petmanager.pets;
SELECT * FROM petmanager.pettypes;
SELECT * FROM petmanager.owners;
SELECT * FROM petmanager.meals;
SELECT * FROM petmanager.toys;

SELECT pets.id, pets.name as pet_name,  pets.age as pet_age,
owners.id as owner_id, owners.name as owner_name,
pettypes.id as pet_type_id, pettypes.type as pet_type_value
FROM owners
RIGHT JOIN pets ON pets.ownerId = owners.id
INNER JOIN pettypes ON pets.pet_type_id = pettypes.id
ORDER BY id asc;

SELECT pets.id, pets.name as pet_name,  pets.age as pet_age,
owners.id as owner_id, owners.name as owner_name,
pettypes.id as pet_type_id, pettypes.type as pet_type_value
FROM pets
LEFT JOIN owners ON pets.ownerId = owners.id
INNER JOIN pettypes ON pets.pet_type_id = pettypes.id
WHERE pets.id = 1
LIMIT 1;


SELECT meals.id, meals.name, meals.weight, 
pettypes.id as pet_type_id, pettypes.type as pet_type_value
FROM meals
INNER JOIN pettypes ON meals.pet_type_id = pettypes.id
WHERE meals.pet_type_id = ?;

SELECT toys.id, toys.material, toys.price, 
pettypes.id as pet_type_id, pettypes.type as pet_type_value
FROM toys
INNER JOIN pettypes ON toys.pet_type_id = pettypes.id
WHERE toys.pet_type_id = 1;

INSERT INTO owners(name)
VALUES('Zino');

INSERT INTO pets(id, ownerId, name, age, pet_type_id) VALUES('7', '5', 'Punto', '3', '2');

DELETE FROM owners WHERE id = 3;
SELECT * FROM owners WHERE id = 3;

UPDATE pets SET ownerId = null where id = 4;

UPDATE pets SET ownerId = ? where id = 2;

UPDATE `petmanager`.`pets` SET `ownerId` = NULL WHERE (`id` = '1');

INSERT INTO `petmanager`.`pettypes` (`id`, `type`) VALUES ('0', 'CAT');
INSERT INTO `petmanager`.`pettypes` (`id`, `type`) VALUES ('2', 'DOG');
INSERT INTO `petmanager`.`pettypes` (`id`, `type`) VALUES ('3', 'RAT');

INSERT INTO `petmanager`.`meals` (`id`, `name`, `weight`, `pet_type_id`) VALUES ('1', 'RICE', '2', '2');
INSERT INTO `petmanager`.`meals` (`id`, `name`, `weight`, `pet_type_id`) VALUES ('2', 'MEAT', '1', '3');
INSERT INTO `petmanager`.`meals` (`id`, `name`, `weight`, `pet_type_id`) VALUES ('3', 'TUNA', '5', '1');
INSERT INTO `petmanager`.`meals` (`id`, `name`, `weight`, `pet_type_id`) VALUES ('4', 'GRAIN', '3', '1');

INSERT INTO `petmanager`.`owners` (`id`, `name`) VALUES ('1', 'Zino');
INSERT INTO `petmanager`.`owners` (`id`, `name`) VALUES ('2', 'Anete');
INSERT INTO `petmanager`.`owners` (`id`, `name`) VALUES ('3', 'Madara');
INSERT INTO `petmanager`.`owners` (`id`, `name`) VALUES ('4', 'Sandra');

INSERT INTO `petmanager`.`pets` (`id`, `ownerId`, `name`, `age`, `pet_type_id`) VALUES (1, 4, 'Fluffy', '5', 1);
INSERT INTO `petmanager`.`pets` (`id`, `ownerId`, `name`, `age`, `pet_type_id`) VALUES (2, 3, 'Morpy', '2', 2);
INSERT INTO `petmanager`.`pets` (`id`, `ownerId`, `name`, `age`, `pet_type_id`) VALUES (3, 2, 'Dancy', '4', 3);
INSERT INTO `petmanager`.`pets` (`id`, `ownerId`, `name`, `age`, `pet_type_id`) VALUES (4, 1, 'Runny', '1', 1);
INSERT INTO `petmanager`.`pets` (`id`, `ownerId`, `name`, `age`, `pet_type_id`) VALUES ('5', '6', 'Bella', '3', '2');


INSERT INTO `petmanager`.`toys` (`id`, `material`, `pet_type_id`, `price`) VALUES ('3', 'Wood', '3', '10.50');
UPDATE `petmanager`.`toys` SET `material` = 'Plastic', `pet_type_id` = '1', `price` = '0.50' WHERE (`id` = '1');
UPDATE `petmanager`.`toys` SET `material` = 'Ball', `pet_type_id` = '2', `price` = '2.50' WHERE (`id` = '2');
INSERT INTO `petmanager`.`toys` (`id`, `material`, `pet_type_id`, `price`) VALUES ('4', 'Cloth', '1', '3.50');

