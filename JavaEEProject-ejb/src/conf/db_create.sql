CREATE TABLE student(
id INTEGER PRIMARY KEY,
firstName VARCHAR(20),
lastName VARCHAR(20),
gender VARCHAR(15),
age INTEGER)

CREATE TABLE teacher(
id INTEGER PRIMARY KEY,
firstName VARCHAR(20),
lastName VARCHAR(20),
gender VARCHAR(15),
age INTEGER,
userName VARCHAR(20),
password VARCHAR(30),
salary INTEGER,
dateHired DATE)

CREATE TABLE principal(
id INTEGER PRIMARY KEY,
firstName VARCHAR(20),
lastName VARCHAR(20),
gender VARCHAR(15),
age INTEGER,
userName VARCHAR(20),
password VARCHAR(30),
salary INTEGER,
dateHired DATE)


CREATE TABLE admin(
id INTEGER PRIMARY KEY,
firstName VARCHAR(20),
lastName VARCHAR(20),
gender VARCHAR(15),
age INTEGER,
userName VARCHAR(20),
password VARCHAR(30),
salary INTEGER,
dateHired DATE)

CREATE TABLE course(
id INTEGER PRIMARY KEY,
name VARCHAR(30),
code VARCHAR(10),
level VARCHAR(10),
language VARCHAR(15),
period VARCHAR(15),
maxNumberOfStudents INTEGER
)

CREATE TABLE ContactInformation(
id INTEGER PRIMARY KEY,
streetName VARCHAR(30),
zipCode VARCHAR(10),
city VARCHAR(50),
phoneNumber VARCHAR(20),
emailAddress VARCHAR(40)
)

CREATE TABLE AttendanceList(
id INTEGER PRIMARY KEY,
date DATE
)

CREATE TABLE Student_Course(
student_id INTEGER REFERENCES Student(id),
course_id INTEGER REFERENCES Course(id),
PRIMARY KEY (student_id, course_id)
)

ALTER TABLE Student
ADD CONSTRAINT fk_contact_information FOREIGN KEY(id) REFERENCES Course(id)

ALTER TABLE Teacher
ADD COLUMN teacher_course_id INTEGER

ALTER TABLE Teacher
ADD CONSTRAINT fk_teacher_course_id FOREIGN KEY(teacher_course_id) REFERENCES Course(id)

ALTER TABLE Course
ADD CONSTRAINT fk_attendanceList_id FOREIGN KEY(id) REFERENCES AttendanceList(id)

ALTER TABLE AttendanceList
ADD COLUMN attendancelist_student_id INTEGER

ALTER TABLE AttendanceList
ADD CONSTRAINT fk_attendancelist_student_id FOREIGN KEY(attendancelist_student_id) REFERENCES Student(id)

ALTER TABLE Student_Course
ADD CONSTRAINT fk_student_id FOREIGN KEY(Student_id) REFERENCES Student(id)

ALTER TABLE Student_Course
ADD CONSTRAINT fk_course_id FOREIGN KEY(Course_id) REFERENCES Course(id)

CREATE TABLE Student_AttendanceList(
student_id INTEGER REFERENCES Student(id),
attendanceList_id INTEGER REFERENCES AttendanceList(id),
PRIMARY KEY (student_id, attendanceList_id)
)