DROP TABLE Student;

DROP TABLE AssignmentTeam;

DROP TABLE TutorialGroup;

DROP TABLE CourseProgramme;

DROP TABLE Programme;

DROP TABLE Course;

CREATE TABLE Course (
courseCode CHAR(3) NOT NULL,
courseName VARCHAR(50) NOT NULL,
courseDetail VARCHAR(255),
courseLevel CHAR(1) NOT NULL,
faculty CHAR(4) NOT NULL,
duration INT NOT NULL,
PRIMARY KEY (courseCode)
);

CREATE TABLE Programme (
programmeCode VARCHAR(8) NOT NULL,
programmeName VARCHAR(50) NOT NULL,
programmeDetail VARCHAR(255),
startDate DATE NOT NULL,
endDate DATE NOT NULL,
assignmentNum INT NOT NULL,
PRIMARY KEY (programmeCode)
);

CREATE TABLE CourseProgramme (
id CHAR(6) NOT NULL,
courseCode CHAR(3) NOT NULL,
programmeCode VARCHAR(8) NOT NULL,
PRIMARY KEY (id),
FOREIGN KEY (courseCode) REFERENCES Course (courseCode),
FOREIGN KEY (programmeCode) REFERENCES Programme (programmeCode)
);

CREATE TABLE TutorialGroup (
tutorialGroupId CHAR(6) NOT NULL,
groupNo INT NOT NULL,
numOfStudent INT NOT NULL,
courseCode CHAR(3) NOT NULL,
PRIMARY KEY (tutorialGroupId),
FOREIGN KEY (courseCode) REFERENCES Course (courseCode)
);

CREATE TABLE AssignmentTeam (
assignmentTeamId CHAR(6) NOT NULL,
tutorialGroupId CHAR(6) NOT NULL,
programmeCode VARCHAR(8) NOT NULL,
PRIMARY KEY (assignmentTeamId),
FOREIGN KEY (tutorialGroupId) REFERENCES TutorialGroup (tutorialGroupId),
FOREIGN KEY (programmeCode) REFERENCES Programme (programmeCode)
);

CREATE TABLE Student (
studentId CHAR(10) NOT NULL,
studentName VARCHAR(50) NOT NULL,
studentIc CHAR(12) NOT NULL,
studentEmail VARCHAR(50) NOT NULL,
phoneNo VARCHAR(11) NOT NULL,
studyYear INT NOT NULL,
semester INT NOT NULL,
tutorialGroupId CHAR(6) NOT NULL,
assignmentTeamId CHAR(6),
PRIMARY KEY (studentId),
FOREIGN KEY (tutorialGroupId) REFERENCES TutorialGroup (tutorialGroupId),
FOREIGN KEY (assignmentTeamId) REFERENCES AssignmentTeam (assignmentTeamId)
);

INSERT INTO COURSE VALUES('RSD', 'Bachelor In Software System Development', 'U will learn Data Structure and Algorithm, Software Engineering, Human Computer Interaction, etc...', 'R', 'FOCS', 3);
INSERT INTO COURSE VALUES('DFT', 'Diploma In Information Technology', 'Go RSD in Degree and you will learn...', 'D', 'FOCS', 2);
INSERT INTO COURSE VALUES('FBU', 'Foundation In Business', 'Actually I dont know what this course will learn but it is not FOCS one...', 'F', 'CPUS', 1);

INSERT INTO PROGRAMME VALUES('BACS2063', 'Data Structure and Algorithm', 'Told u RSD will learn this...', '2023-07-03', '2023-10-20', 3);
INSERT INTO PROGRAMME VALUES('BACS2053', 'Object-Oriented Analysis & Design', 'Ya, RSD will also need to learn this. NO ONE CAN ESCAPE FROM IT!!!!', '2023-07-03', '2023-10-20', 4);
INSERT INTO PROGRAMME VALUES('BAIT2203', 'Human Computer Interaction', 'Ermmmm............................ Ya.', '2023-07-03', '2023-10-20', 5);

INSERT INTO COURSEPROGRAMME VALUES('RSD101', 'RSD', 'BACS2063');
INSERT INTO COURSEPROGRAMME VALUES('RSD102', 'RSD', 'BACS2053');
INSERT INTO COURSEPROGRAMME VALUES('RSD103', 'RSD', 'BAIT2203');

INSERT INTO TUTORIALGROUP VALUES('RSDG01', 1, 25, 'RSD');
INSERT INTO TUTORIALGROUP VALUES('RSDG02', 2, 24, 'RSD');
INSERT INTO TUTORIALGROUP VALUES('RSDG03', 3, 24, 'RSD');

INSERT INTO ASSIGNMENTTEAM VALUES('AT1001', 'RSDG01', 'BACS2063');
INSERT INTO ASSIGNMENTTEAM VALUES('AT1002', 'RSDG01', 'BACS2063');
INSERT INTO ASSIGNMENTTEAM VALUES('AT1003', 'RSDG01', 'BACS2063');

INSERT INTO STUDENT VALUES('23WMR09297', 'Random Name Number 1', '010101010101', 'randomnn1-wm23@student.tarc.edu.my', '0123456789', '2', '1', 'RSDG01', 'AT1001');
INSERT INTO STUDENT VALUES('23WMR09295', 'This Name Not Random 1', '011001100110', 'thisnnr-wm23@student.tarc.edu.my', '0198765432', '2', '1', 'RSDG01', 'AT1001');
INSERT INTO STUDENT VALUES('22WMR09999', 'All Nine', '020909099999', 'alln-wm22@student.tarc.edu.my', '0199999999', '2', '1', 'RSDG01', 'AT1001');
