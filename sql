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
