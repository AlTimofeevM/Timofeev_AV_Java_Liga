CREATE Table school (
	id INTEGER NOT NULL PRIMARY KEY,
	name VARCHAR(255),
	address VARCHAR(255)
);

COMMENT ON COLUMN school.name
IS 'Наименование школы';

COMMENT ON COLUMN school.address
IS 'Адрес школы';

CREATE TABLE subject (
	id INTEGER NOT NULL PRIMARY KEY,
	name VARCHAR(255)
);

COMMENT ON COLUMN subject.name
IS 'Наименование предмета';

CREATE TABLE teacher (
	id INTEGER NOT NULL PRIMARY KEY,
	name VARCHAR(255),
	age INTEGER NOT NULL,
	sex BOOL NOT NULL,
	school_id INTEGER NOT NULL REFERENCES school(id)
);

COMMENT ON COLUMN teacher.name
IS 'ФИО преподавателя';

COMMENT ON COLUMN teacher.age
IS 'Возраст преподавателя';

COMMENT ON COLUMN teacher.sex
IS 'Пол преподавателя';

COMMENT ON COLUMN teacher.school_id
IS 'Школа преподавателя';

CREATE TABLE student (
	id INTEGER NOT NULL PRIMARY KEY,
	name VARCHAR(255),
	age INTEGER NOT NULL,
	sex BOOL NOT NULL,
	school_id INTEGER NOT NULL REFERENCES school(id)
);

COMMENT ON COLUMN student.name
IS 'ФИО ученика';

COMMENT ON COLUMN student.age
IS 'Возраст ученика';

COMMENT ON COLUMN student.sex
IS 'Пол ученика';

COMMENT ON COLUMN student.school_id
IS 'Школа ученика';

CREATE TABLE teacher_subject (
	teacher_id INTEGER NOT NULL REFERENCES teacher(id),
	subject_id INTEGER NOT NULL REFERENCES subject(id),
	PRIMARY KEY(teacher_id, subject_id)
);

CREATE TABLE student_subject (
	student_id INTEGER NOT NULL REFERENCES student(id),
	subject_id INTEGER NOT NULL REFERENCES subject(id),
	PRIMARY KEY(student_id, subject_id)
);