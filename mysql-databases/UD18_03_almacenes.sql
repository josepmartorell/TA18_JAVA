drop database if exists `UD18_03_almacenes`;
create database if not exists `UD18_03_almacenes`;

use `UD18_03_almacenes`;

drop table if exists almacenes;
drop table if exists cajas;

create table almacenes ( 
	Codigo 			 int not null,
    Lugar		 	 nvarchar(255) not null,
    Capacidad    	 int not null,
    constraint primary key (Codigo)
);

create table cajas ( 
	NumReferencia 	  char(5) not null,
    Contenido		  nvarchar(100) not null,
    Valor 			  double not null,
    Almacen    	      int not null,
    constraint primary key (NumReferencia),
	constraint foreign key (Almacen) references almacenes (Codigo)
);

insert into `almacenes` values (1,'Valencia',3),
							   (2,'Barcelona',4),
                               (3,'Bilbao',7),
                               (4,'Los Angeles',2),
                               (5,'San Francisco',8);
                               
insert into `cajas` values ('0MN7','Rocks',180,3),
						   ('4H8P','Rocks',250,1),
                           ('4RT3','Scissors',190,4),
                           ('7G3H','Rocks',200,1),
                           ('8JN6','Papers',75,1),
                           ('8Y6U','Papers',50,3),
                           ('9J6F','Papers',175,2),
                           ('LL08','Rocks',140,4),
                           ('P0H6','Scissors',125,1),
                           ('P2T6','Scissors',150,2),
                           ('TU55','Papers',90,5);


