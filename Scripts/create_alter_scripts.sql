CREATE TABLE IF NOT EXISTS smartdoc.slot (
    slot_id INT unsigned AUTO_INCREMENT PRIMARY KEY,
    patient_id INT NOT NULL,
	doctor_id INT NOT NULL,
	first_name varchar(35) DEFAULT NULL,
	last_name varchar(35) DEFAULT NULL,
    slot_date DATE NOT NULL,
	slot_time varchar(10) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	update_date TIMESTAMP NULL,
    slot_status varchar(20) NOT NULL,
    priority TINYINT NOT NULL,
    comments varchar(50) NULL
)  ENGINE=INNODB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS smartdoc.patient (
    patient_id INT AUTO_INCREMENT PRIMARY KEY,
	first_name varchar(35) NOT NULL,
	last_name varchar(35) NOT NULL,
	gender char(1) NOT NULL,
    date_of_birth DATE NOT NULL,
	age INT NOT NULL,
	contact_number INT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    address varchar(50)	NULL
)  ENGINE=INNODB AUTO_INCREMENT=26;

CREATE TABLE IF NOT EXISTS smartdoc.doctor (
    doctor_id INT AUTO_INCREMENT PRIMARY KEY,
	first_name varchar(35) NOT NULL,
	last_name varchar(35) NOT NULL,
	gender char(1) NOT NULL,
    specialization varchar(50) NOT NULL
)  ENGINE=INNODB AUTO_INCREMENT=26;

CREATE TABLE smartdoc.loginstore (
	id int(11) INT AUTO_INCREMENT PRIMARY KEY ,
	username varchar(30) NOT NULL ,
	email varchar(50) NOT NULL ,
	password varchar(128) NOT NULL,
	first_name VARCHAR(35) NOT NULL,
	last_name VARCHAR(35) NOT NULL,
	dob VARCHAR(12) NOT NULL
) ENGINE=INNODB AUTO_INCREMENT=26;


ALTER TABLE smartdoc.patient ADD CONSTRAINT UC_Patient UNIQUE (first_name, last_name, gender, date_of_birth);

ALTER TABLE smartdoc.doctor ADD CONSTRAINT UC_Doctor UNIQUE (first_name, last_name, gender, specialization);

ALTER TABLE smartdoc.slot ADD CONSTRAINT UC_Slot UNIQUE (patient_id, doctor_id, slot_date);

Commit;
