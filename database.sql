drop database Point_Of_Sale;

create database Point_Of_Sale;

use Point_Of_Sale;

create table Test(
    name varchar(20),
    age int,
    salary decimal(19,2)
);

create table Account(
    ID varchar(10) primary key,
    balance decimal(19,2)
);
create table Card(
    pin varchar(5),
    accID varchar(10),

    foreign key (accID) references Account(ID)
);
create table Client(
    ID varchar(10),
    name varchar(20),
    email varchar(20),
    accID varchar(10),

    foreign key (accID) references Account(ID)
);
create table Employee(
    ID varchar(10),
    name varchar(20),
    email varchar(20),
    salary decimal(19,2),
    taxNum varchar(10)
);

create table PerishableProd(
    ID varchar(10),
    description varchar(20),
    price decimal(19,2),
    weight decimal(3,2),
    qty int,
    expDate date
);
create table NonPerishableProd(
    ID varchar(10),
    description varchar(20),
    price decimal(19,2),
    weight decimal(3,2),
    qty int,
    manuDate date
);