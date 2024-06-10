CREATE TABLE mTrend
(
	mt_no INTEGER NOT NULL,
	mt_trend CHAR(100) NOT NULL,
	insert_date TIMESTAMP NOT NULL,
	status CHAR(10) NULL,
	deleted CHAR(1) NOT NULL,
	PRIMARY KEY (mt_no)
);

CREATE TABLE mNews
(
	mn_no INTEGER NOT NULL,
	mn_keyword CHAR(100) NOT NULL,
	mn_title VARCHAR(100) NOT NULL,
	mn_content VARCHAR(1000) NOT NULL,
	mn_url VARCHAR(500) NOT NULL,
	PRIMARY KEY (mn_no)
);