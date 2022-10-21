-- drop database
drop database PostalService;

-- create database
CREATE database PostalService;
USE PostalService;

-- create tables

create table Employees
(
	EmpID int auto_increment not null,
    BranchID int not null,
    FirstName varchar(30) not null,
    LastName varchar(30) not null,
    constraint pk_Employees primary key (EmpID asc)
);

create table Customers
(
	CustID int auto_increment not null,
    FirstName varchar(30) not null,
    LastName varchar(30) not null,
    constraint pk_Customers primary key (CustID asc)
);

create table ShippingTypes
(
	ShipTypeID int auto_increment not null,
    ShipTypeDescrip varchar(30) not null,
    constraint pk_ShippingTypes primary key (ShipTypeID asc)
);

create table Branches
(
	BranchID int auto_increment not null,
    BranchName varchar(30) not null,
    constraint pk_Branches primary key (BranchID asc)
);

create table ShipOrders
(
	ShipOrderID int auto_increment not null,
    BranchID int not null,
    CustID int not null,
    EmpID int not null,
    ShipTypeID int not null,
    ShipAddress varchar(50) not null,
    constraint pk_ShipOrders primary key (ShipOrderID asc)
);

-- Foreign Keys Constraints
alter table ShipOrders
add constraint fk_ShipOrders_Branches foreign key (BranchID)
references Branches (BranchID)
;

alter table ShipOrders
add constraint fk_ShipOrders_Customers foreign key (CustID)
references Customers (CustID)
;

alter table ShipOrders
add constraint fk_ShipOrders_Employees foreign key (EmpID)
references Employees (EmpID)
;

alter table ShipOrders
add constraint fk_ShipOrders_ShippingTypes foreign key (ShipTypeID)
references ShippingTypes (ShipTypeID)
;

alter table Employees
add constraint fk_Employees_Branches foreign key (BranchID)
references Branches (BranchID)
;

-- manipulate data
select * from employees;
insert into employees(BranchID, FirstName, LastName) values (1, 'Mary', 'Jane1');
insert into employees(BranchID, FirstName, LastName) values (2, 'Mary', 'Jane2');
insert into employees(BranchID, FirstName, LastName) values (3, 'Mary', 'Jane3');
insert into employees(BranchID, FirstName, LastName) values (1, 'Mary', 'Jane4');
insert into employees(BranchID, FirstName, LastName) values (2, 'Mary', 'Jane5');

select * from branches;
insert into branches(BranchID, BranchName) values (1, 'Montreal');
insert into branches(BranchID, BranchName) values (2, 'Toronto');
insert into branches(BranchID, BranchName) values (3, 'Vancouver');


select * from customers;


select * from shippingtypes;
insert into shippingtypes(ShipTypeID, ShipTypeDescrip) values (1, 'Standard');
insert into shippingtypes(ShipTypeID, ShipTypeDescrip) values (2, 'Fast');

select * from shiporders;
insert into shiporders(BranchID, CustID, EmpID, ShipTypeID, ShipAddress) values (1, 2, 1, 1, '978 Cool st. Kingston');
insert into shiporders(BranchID, CustID, EmpID, ShipTypeID, ShipAddress) values (2, 3, 1, 1, '654 Noisy st. Montreal');
insert into shiporders(BranchID, CustID, EmpID, ShipTypeID, ShipAddress) values (2, 3, 4, 1, '345 Super st. Laval');
insert into shiporders(BranchID, CustID, EmpID, ShipTypeID, ShipAddress) values (1, 3, 1, 2, '234 Eternal st. Montreal');
insert into shiporders(BranchID, CustID, EmpID, ShipTypeID, ShipAddress) values (3, 1, 2, 2, '987 Long st. Toronto');
insert into shiporders(BranchID, CustID, EmpID, ShipTypeID, ShipAddress) values (2, 1, 2, 1, '2345 Short st. Vancouver');
insert into shiporders(BranchID, CustID, EmpID, ShipTypeID, ShipAddress) values (3, 2, 1, 2, '984 Funny st. Burlington');
insert into shiporders(BranchID, CustID, EmpID, ShipTypeID, ShipAddress) values (1, 2, 1, 1, '6589 Dusty st. Windsor');
insert into shiporders(BranchID, CustID, EmpID, ShipTypeID, ShipAddress) values (1, 4, 1, 1, '7659 Famous st. Montreal');

-- select employees by Branch
SELECT employees.EmpID employeeID, employees.FirstName employeeFirstName, employees.LastName employeeLastName, 
branches.BranchID branchID, branches.BranchName branchName 
from employees, branches WHERE employees.BranchID=branches.BranchID and branches.BranchID = 1;

-- Get all orders with all info
SELECT shiporders.ShipOrderID order_ID, 
		customers.FirstName cust_First_Name, 
		customers.LastName cust_Last_Name,
		branches.BranchID branch_ID, 
		branches.BranchName branch_Name,
		employees.EmpID employee_ID, 
		employees.FirstName employee_FirstName, 
		employees.LastName employee_LastName,
		shippingtypes.ShipTypeDescrip shipping_type,  
		shiporders.ShipAddress shipping_Address 
from shiporders, customers, branches, employees, shippingtypes 
WHERE shiporders.CustID=customers.CustID and
		shiporders.BranchID=branches.BranchID and
        shiporders.EmpID=employees.EmpID and
        shiporders.ShipTypeID=shippingtypes.ShipTypeID;
        
-- get orders by customer ID
SELECT shiporders.CustID CustomerID, 
customers.FirstName FirstName, 
customers.LastName LastName, 
shiporders.ShipOrderID ShipOrderID, 
branches.BranchName BranchName, 
shippingtypes.ShipTypeDescrip ShipType, 
shiporders.ShipAddress ShipAddress 
from shiporders, customers, branches, shippingtypes 
WHERE shiporders.CustID=customers.CustID and 
shiporders.BranchID=branches.BranchID and 
shiporders.ShipTypeID=shippingtypes.ShipTypeID and 
customers.CustID = 1;

-- get orders by shipping type
SELECT shippingtypes.ShipTypeDescrip ShipType, 
shiporders.ShipOrderID ShipOrderID,
shiporders.ShipAddress ShipAddress,
customers.FirstName FirstName, 
customers.LastName LastName, 
branches.BranchName BranchName 
from shiporders, customers, branches, shippingtypes 
WHERE shiporders.CustID=customers.CustID and 
shiporders.BranchID=branches.BranchID and 
shiporders.ShipTypeID=shippingtypes.ShipTypeID and 
shippingtypes.ShipTypeDescrip = 'Standard';


