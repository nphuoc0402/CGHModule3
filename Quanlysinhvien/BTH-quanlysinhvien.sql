SELECT * FROM student;

SELECT * FROM student WHERE status = true;

SELECT s.SubName FROM subject s WHERE s.Credit < 10;

SELECT s.StudentId, s.StudentName , c.ClassId FROM student s 
JOIN class c ON s.ClassId = c.ClassID WHERE c.ClassName ="A1"; 

SELECT s.StudentID, s.StudentName, sb.SubName, m.mark FROM student s
JOIN mark m ON s.StudentId = m.StudentId JOIN subject sb ON m.SubId = sb.SubId
WHERE sb.subName = "CF"
