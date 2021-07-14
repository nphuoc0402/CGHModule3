
SELECT Address , COUNT(StudentId) AS "So luong sinh vien"  FROM student
GROUP BY Address ;

SELECT s.StudentName, AVG(m.Mark) AS "Diem Trung Binh" FROM student s
JOIN mark m ON m.StudentId = s.StudentId 
JOIN subject sb ON sb.SubId = m.SubId
GROUP BY s.StudentName;

SELECT s.StudentName, AVG(m.Mark) AS "Diem Trung Binh" FROM student s
JOIN mark m ON m.StudentId = s.StudentId 
JOIN subject sb ON sb.SubId = m.SubId
GROUP BY s.StudentName
HAVING  AVG(m.Mark) > 15;

SELECT s.StudentId, s.StudentName, AVG(m.Mark) AS "MAX AVG" FROM student s
JOIN mark m ON m.StudentId = s.StudentId
GROUP BY s.StudentId, s.StudentName
HAVING AVG(Mark) >= ALL (SELECT AVG(Mark) FROM Mark GROUP BY Mark.StudentId);


