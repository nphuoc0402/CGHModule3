create database demo;
/*------------Create Products table-------------*/
create table Products (
Id int auto_increment primary key,
productCode varchar(10),
productName varchar(45),
productPrice int,
productAmount int,
productDescription varchar(45),
productStatus boolean default 1
);
/*================ Insert Table =============*/
insert into Products (productCode, productName, productPrice, productAmount, productDescription) values 
('ts','tra sua',45000,20,"Ngon, bo re"),
('tstcdd','tra sua tran chau duong den',85000,10,"Ngon, bo, nhung khong re"),
('cfd','cafe den',15000,50,"Khong ngon lam"),
('cfssg','cafe sua SG',25000,40,"Huong vi tuyet voi");

/*=================== Create index =====================*/
explain select * from Products where productCode = "ts";

alter table Products add unique idx_productCode (productCode);
-- DROP INDEX idx_productCode ON Products;
explain select * from Products where productCode = "ts";

alter table Products add index idx_nameAndPrice (productName,productPrice);
-- DROP INDEX idx_nameAndPrice ON Products;
explain select * from Products where productName = "tra sua" OR productPrice = 15000;

/*=============== Create view ================*/

create view vw_selectproduct as
select productCode, productName, productPrice, productStatus 
from Products;

select * from vw_selectproduct;

create or replace view vw_selectproduct as
select productCode, productName, productPrice, productStatus 
from Products
where productName = "tra sua";
select * from vw_selectproduct;
drop view vw_selectproduct;

/*=================== Create stored procedure ===================*/

DELIMITER //
create procedure sp_getinfomationproduct() 
	
begin 
	select * from products;
end//
DELIMITER ;

call sp_getinfomationproduct();

DELIMITER //
create procedure sp_inputproduct(
	IN 
		Code varchar(10),
		Name varchar(45),
		pPrice int,
		pAmount int,
		Description varchar(45),
		Status boolean)
begin 
	insert into products(productCode, productName, productPrice,productAmount,productDescription, productStatus)  values 
(Code, Name, pPrice, pAmount, Description, Status);
end//
DELIMITER ;

call sp_inputproduct('td','tra dao',18000,45,'Uong duoc',1);
call sp_getinfomationproduct() ;

DELIMITER //
create procedure sp_editproduct(
IN 
		pid int,
		Code varchar(10),
		Name varchar(45),
		Price int,
		Amount int,
		Description varchar(45),
		Status boolean)
begin 
		update products 
        set 	productCode = Code,
		productName = Name,
		productPrice = Price,
		productAmount = Amount,
		productDescription = Description,
		productStatus = Status
        where pid = id;
end//
DELIMITER ;

call sp_editproduct(1,'td','tra dao',18000,45,'Uong duoc',1);
call sp_getinfomationproduct() ;

DELIMITER //
create procedure sp_deleteproduct(
IN pid int)

begin 
	delete from  products where pid = id;
end//
	
DELIMITER ;    
call sp_deleteproduct(1);
call sp_getinfomationproduct();

