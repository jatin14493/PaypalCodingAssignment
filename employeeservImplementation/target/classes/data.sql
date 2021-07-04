DROP TABLE IF EXISTS address;
DROP TABLE IF EXISTS employees;

CREATE TABLE address (
                         address_id INT AUTO_INCREMENT  PRIMARY KEY,
                         line1 VARCHAR(255) NOT NULL,
                         line2 VARCHAR(255),
                         city VARCHAR(255) NOT NULL,
                         state VARCHAR(255) NOT NULL,
                         country VARCHAR(255) NOT NULL,
                         zip_code VARCHAR(255) NOT NULL
);

CREATE TABLE employees (
                              id INT AUTO_INCREMENT  PRIMARY KEY,
                              first_name VARCHAR(255) NOT NULL,
                              last_name VARCHAR(255) NOT NULL,
                              dob DATE NOT NULL,
                              address_id INT NOT NULL,
                              FOREIGN KEY (address_id) REFERENCES address(address_id)

);


ALTER TABLE employees
ADD CONSTRAINT UNIQUE_Employee UNIQUE (first_name,last_name,dob);