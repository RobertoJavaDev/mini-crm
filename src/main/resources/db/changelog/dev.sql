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

-- changeset RobertoJavaDev:5
INSERT INTO companies (id, company_name, email, logo_filename, website)
VALUES
    (gen_random_uuid(),
     'TechNova Sp. z o.o.',
     'kontakt@technova.pl',
     '/uploads/logos/technova.png',
     'https://www.technova.pl');

INSERT INTO companies (id, company_name, email, logo_filename, website)
VALUES
    (gen_random_uuid(),
     'GreenFuture S.A.',
     'info@greenfuture.pl',
     '/uploads/logos/greenfuture.png',
     'https://www.greenfuture.pl');

INSERT INTO companies (id, company_name, email, logo_filename, website)
VALUES
    (gen_random_uuid(),
     'BlueOcean Tech',
     'biuro@blueocean.pl',
     '/uploads/logos/blueocean.png',
     'https://www.blueocean.pl');

INSERT INTO companies (id, company_name, email, logo_filename, website)
VALUES
    (gen_random_uuid(),
     'NextGen Innovations',
     'kontakt@nextgen.pl',
     '/uploads/logos/nextgen.png',
     'https://www.nextgen.pl');

INSERT INTO companies (id, company_name, email, logo_filename, website)
VALUES
    (gen_random_uuid(),
     'PolSoft Systems',
     'office@polsoft.pl',
     '/uploads/logos/polsoft.png',
     'https://www.polsoft.pl');

INSERT INTO companies (id, company_name, email, logo_filename, website)
VALUES
    (gen_random_uuid(),
     'DataWorks Polska',
     'support@dataworks.pl',
     '/uploads/logos/dataworks.png',
     'https://www.dataworks.pl');

INSERT INTO companies (id, company_name, email, logo_filename, website)
VALUES
    (gen_random_uuid(),
     'Skyline Solutions',
     'info@skyline.pl',
     '/uploads/logos/skyline.png',
     'https://www.skyline.pl');