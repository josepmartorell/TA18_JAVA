drop database if exists `UD18_08_grandes_almacenes`;
create database if not exists `UD18_08_grandes_almacenes`;

use `UD18_08_grandes_almacenes`;

drop table if exists cajeros;
drop table if exists productos;
drop table if exists maquinas_registradoras;
drop table if exists venta;

create table cajeros ( 
	Codigo 			 int not null,
    NomApels		 nvarchar(255) not null,
    constraint primary key (Codigo)
);

create table maquinas_registradoras (
	Codigo 			 int not null,
    piso 		     int not null,
    constraint primary key (Codigo)
);

create table productos (
	Codigo 			 int not null,
    Nombre		     nvarchar(100) not null,
    Precio		     double not null,
    constraint primary key (Codigo)
);

create table venta (
Cajero			     int not null,
Maquina   			 int not null,
Producto			 int not null,
primary key (Cajero, Maquina, Producto),
constraint foreign key (Cajero) references cajeros (Codigo) on delete cascade on update cascade,
constraint foreign key (Maquina) references maquinas_registradoras (Codigo) on delete cascade on update cascade,
constraint foreign key (Producto) references productos (Codigo) on delete cascade on update cascade
);

insert into `cajeros` values (1, 'Sergio Mendez Coballos'),
							 (2, 'Juan Cañiz Garrín'),
                             (3, 'Gina Ortiz Arnan'),
                             (4, 'Marta Valiente Santon'),
                             (5, 'Ana Cabello Cortado'),
							 (6, 'Manuel Gutierrez Postín'),
                             (7, 'Fabián Franco Puigdemon');

insert into `maquinas_registradoras` values (1, 1),
											(2, 1),
											(3, 2),
											(4, 3),
											(5, 4);  
                                            
insert into `productos` values (1, 'Bañera', 124.99),
							   (2, 'bufanda', 11.99),
							   (3, 'chubasquero', 100.69),
							   (4, 'armilla', 599.99),
							   (5, 'lavadora', 789.99),
							   (6, 'almohada', 35.99),
							   (7, 'crema arrugas', 189.99), 
							   (8, 'chubasquero', 100.69),
							   (9, 'freidora', 599.99),
							   (10, 'jamón serrano', 789.99),
							   (11, 'leghuga', 35.99),
							   (12, 'berenjena', 9.99); 
                                            

                             
                             