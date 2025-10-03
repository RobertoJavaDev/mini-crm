-- liquibase formatted sql


-- changeset RobertoJavaDev:1
CREATE TABLE companies
(
    id            UUID         NOT NULL Primary Key,
    company_name  VARCHAR(255) NOT NULL,
    email         VARCHAR(255),
    logo_filename VARCHAR(255),
    website       VARCHAR(255)
);

-- changeset RobertoJavaDev:2
CREATE TABLE employees
(
    id         UUID         NOT NULL PRIMARY KEY,
    first_name VARCHAR(255) NOT NULL,
    last_name  VARCHAR(255) NOT NULL,
    company_id UUID,
    email      VARCHAR(255),
    phone      VARCHAR(50),
    CONSTRAINT fk_employee_company FOREIGN KEY (company_id) REFERENCES companies (id) ON DELETE SET NULL
);

-- changeset RobertoJavaDev:3
CREATE TABLE users
(
    id         UUID         NOT NULL primary key,
    first_name VARCHAR(255),
    last_name  VARCHAR(255),
    password   VARCHAR(255) NOT NULL,
    email      VARCHAR(255) NOT NULL,
    role       VARCHAR(50)
);
