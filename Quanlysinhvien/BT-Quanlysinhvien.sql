SELECT * FROM student s WHERE s.StudentName LIKE "h%";

SELECT * FROM class c WHERE c.StartDate LIKE "%12%";

SELECT * FROM subject sb WHERE sb.Credit BETWEEN 3 AND 5;

UPDATE student s
SET s.ClassID = '2' WHERE s.StudentName = "Hung";

SELECT s.StudentName, sb.SubName, m.Mark FROM student s
JOIN mark m ON m.StudentId = s.StudentId
JOIN subject sb ON sb.SubId = m.SubId
ORDER BY m.mark DESC  