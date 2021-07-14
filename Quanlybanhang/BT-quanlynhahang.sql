USE quanlybanhang;

INSERT INTO customers  (cName,cAge) VALUES ("Minh Quan",10),
("Ngoc Anh",20),
("Hong Ha",50)
;

INSERT INTO orders (cID,oDate) VALUES (1,"2006-3-21"),
(2,"2006-3-23"),
(1,"2006-3-16")
;

INSERT INTO products(pName,pPrice) VALUES ("May Giat",3),
("Tu Lanh",5),
("Dieu Hoa",7),
("Quat",1),
("Bep Dien",2)
;

INSERT INTO orderdetail (oID,pID,odQTY) VALUES (1,1,3),
(1,3,7),
(1,4,2),
(2,1,1),
(3,1,8),
(2,5,4),
(2,3,3)
;

SELECT oID, oDate, oTotalPrice FROM orders;

SELECT c.cID, c.cName , p.pName  FROM customers c 
JOIN orders o ON o.cID = c.cID 
JOIN orderdetail od ON od.oID = o.oID 
JOIN  products p ON p.pID = od.pID
GROUP BY c.cID, c.cName, p.pName;

SELECT c.cName FROM customers c 
JOIN orders o ON o.cID = c.cID
JOIN orderdetail od ON od.oID = o.oID 
WHERE od.odQTY = 0;

SELECT o.oID, o.oDate, (p.pPrice * od.odQTY) AS "TotalPrice" FROM orders o
JOIN orderdetail od ON od.oID = o.oID 
JOIN products p ON p.pID = od.pID;
 














