CREATE TABLE if NOT EXISTS m_trend
(
	mt_no INTEGER NOT NULL,
	mt_trend CHAR(100) NOT NULL,
	insert_date TIMESTAMP NOT NULL,
	update_date TIMESTAMP NOT NULL,
	mt_status CHAR(10) NULL,
	mt_deleted CHAR(1) NOT NULL,
	PRIMARY KEY (mt_no)
)ENGINE=InnoDB;

CREATE TABLE if NOT EXISTS m_news
(
	mn_no INTEGER NOT NULL,
	mt_no INTEGER NOT NULL,
	mn_title VARCHAR(100) NOT NULL,
	mn_content VARCHAR(5000) NOT NULL,
	mn_url VARCHAR(500) NOT NULL,
	insert_date TIMESTAMP NOT NULL,
	update_date TIMESTAMP NOT NULL,
	mn_status CHAR(10) NULL,
	mn_deleted CHAR(1) NOT NULL,
	PRIMARY KEY (mn_no)
)ENGINE=InnoDB;

CREATE TABLE if NOT EXISTS m_summary
(
	ms_no INTEGER NOT NULL,
	mn_no INTEGER NOT NULL,
	ms_summary VARCHAR(1000) NOT NULL,
	insert_date TIMESTAMP NOT NULL,
	update_date TIMESTAMP NOT NULL,
	ms_status CHAR(10) NULL,
	ms_deleted CHAR(1) NOT NULL,
	PRIMARY KEY (ms_no)
)ENGINE=InnoDB;