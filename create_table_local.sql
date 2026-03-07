-- Run this in HeidiSQL: select calculator_db, then press F9 or click the Run button

USE calculator_db;

CREATE TABLE IF NOT EXISTS calculations (
    id        INT AUTO_INCREMENT PRIMARY KEY,
    num1      DOUBLE NOT NULL,
    num2      DOUBLE NOT NULL,
    operation VARCHAR(20) NOT NULL,
    result    DOUBLE NOT NULL,
    timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

