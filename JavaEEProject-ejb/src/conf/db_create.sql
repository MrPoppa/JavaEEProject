CREATE TABLE ContactInformation(
id INTEGER NOT NULL AUTO_INCREMENT,
streetName VARCHAR(32),
zipCode VARCHAR(16),
city VARCHAR(64),
phoneNumber VARCHAR(16),
emailAddress VARCHAR(64),
PRIMARY KEY (id)
)

CREATE TABLE Student(
id INTEGER NOT NULL AUTO_INCREMENT,
firstName VARCHAR(32),
lastName VARCHAR(32),
gender VARCHAR(16),
age INTEGER,
contact_id INTEGER,
PRIMARY KEY (id),
CONSTRAINT fk_student_contact FOREIGN KEY (contact_id) REFERENCES Contactinformation(id)
)

CREATE TABLE Course(
id INTEGER NOT NULL AUTO_INCREMENT,
courseName VARCHAR(32),
courseCode VARCHAR(16),
courseLevel VARCHAR(16),
courseLanguage VARCHAR(16),
coursePeriod VARCHAR(16),
maxNumberOfStudents INTEGER,
PRIMARY KEY (id)
)

CREATE TABLE Course_Student (
student_id INTEGER,
course_id INTEGER,
CONSTRAINT fk_student FOREIGN KEY (student_id) REFERENCES Student(id),
CONSTRAINT fk_course FOREIGN KEY (course_id) REFERENCES Course(id)
)

CREATE TABLE Teacher(
id INTEGER NOT NULL AUTO_INCREMENT,
firstName VARCHAR(32),
lastName VARCHAR(32),
gender VARCHAR(16),
age INTEGER,
userName VARCHAR(16),
password VARCHAR(16),
salary INTEGER,
dateHired DATE,
contact_id INTEGER,
PRIMARY KEY (id),
CONSTRAINT fk_teacher_contact FOREIGN KEY (contact_id) REFERENCES Contactinformation(id)
)

CREATE TABLE Principal(
id INTEGER NOT NULL AUTO_INCREMENT,
firstName VARCHAR(32),
lastName VARCHAR(32),
gender VARCHAR(16),
age INTEGER,
userName VARCHAR(16),
password VARCHAR(16),
salary INTEGER,
dateHired DATE,
contact_id INTEGER,
PRIMARY KEY (id),
CONSTRAINT fk_principal_contact FOREIGN KEY (contact_id) REFERENCES Contactinformation (id)
)

CREATE TABLE Administrator (
id INTEGER NOT NULL AUTO_INCREMENT,
firstName VARCHAR(20),
lastName VARCHAR(20),
gender VARCHAR(15),
age INTEGER,
userName VARCHAR(20),
password VARCHAR(30),
salary INTEGER,
dateHired DATE,
PRIMARY KEY (id)
)

CREATE TABLE AttendanceLists(
id INTEGER NOT NULL AUTO_INCREMENT,
attendanceDate DATE,
PRIMARY KEY (id)
)

CREATE TABLE AttendanceList(
student_id INTEGER,
attendanceList_id INTEGER,

)