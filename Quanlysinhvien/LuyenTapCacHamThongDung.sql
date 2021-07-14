SELECT s.SubId, s.SubName, s.Credit, s.Status
FROM subject s 
GROUP BY  s.SubId, s.SubName, s.Credit, s.Status
HAVING MAX(s.Credit) >= ALL (SELECT Credit FROM subject );

SELECT s.SubId, s.SubName, MAX(m.Mark) AS "MAX" FROM subject s
JOIN mark m ON m.SubId = s.SubId;

SELECT s.StudentId, s.StudentName, AVG(m.Mark)  AS "DTB" FROM student s
JOIN mark m ON m.StudentId = s.StudentId
GROUP BY s.StudentId, s.StudentName
ORDER BY s.StudentId, s.StudentName DESC



