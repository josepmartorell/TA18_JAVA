drop database if exists `UD18_04_peliculas_salas`;
create database if not exists `UD18_04_peliculas_salas`;

use `UD18_04_peliculas_salas`;

drop table if exists peliculas;
drop table if exists salas;

create table peliculas ( 
	Codigo	  			int not null,
    Nombre		  		nvarchar(100) not null,
    CalificacionEdad    varchar(100) default null,
    constraint primary key (Codigo)
);

create table salas ( 
	Codigo 			 int not null,
    Nombre		 	 nvarchar(100) not null,
    Pelicula    	 int default null,
    constraint primary key (Codigo),
    constraint foreign key (Pelicula) references peliculas (Codigo) 
);

insert into `peliculas` values (1,'Citizen Kane','PG'),
							   (2,'Singin\' in the Rain','G'),
                               (3,'The Wizard of Oz','G'),
                               (4,'The Quiet Man',NULL),
                               (5,'North by Northwest',NULL),
                               (6,'The Last Tango in Paris','NC-17'),
							   (7,'Some Like it Hot','PG-13'),
                               (8,'A Night at the Opera',NULL),
                               (9,'Citizen King','G');

insert into `salas` values (1,'Odeon',5),
						   (2,'Imperial',1),
                           (3,'Majestic',NULL),
                           (4,'Royale',6),
                           (5,'Paraiso',3),
                           (6,'Nickelodeon',NULL);