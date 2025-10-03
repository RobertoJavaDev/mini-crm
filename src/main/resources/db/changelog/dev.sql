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

-- changeset RobertoJavaDev:4
INSERT INTO users (id, first_name, last_name, email, password, role)
VALUES (gen_random_uuid(),
        'Jan',
        'Administrator',
        'admin@admin.com',
        '$2a$12$3nZkpz.KbWCViBA4G0pLr.AyMTmBs5NpmJg6QoKOr2m6bn9drij02',
        'ROLE_ADMIN');
