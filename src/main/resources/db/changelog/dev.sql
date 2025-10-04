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
VALUES (gen_random_uuid(),
        'TechNova Sp. z o.o.',
        'kontakt@technova.pl',
        '/uploads/logos/technova.png',
        'https://www.technova.pl');

INSERT INTO companies (id, company_name, email, logo_filename, website)
VALUES (gen_random_uuid(),
        'GreenFuture S.A.',
        'info@greenfuture.pl',
        '/uploads/logos/greenfuture.png',
        'https://www.greenfuture.pl');

INSERT INTO companies (id, company_name, email, logo_filename, website)
VALUES (gen_random_uuid(),
        'BlueOcean Tech',
        'biuro@blueocean.pl',
        '/uploads/logos/blueocean.png',
        'https://www.blueocean.pl');

INSERT INTO companies (id, company_name, email, logo_filename, website)
VALUES (gen_random_uuid(),
        'NextGen Innovations',
        'kontakt@nextgen.pl',
        '/uploads/logos/nextgen.png',
        'https://www.nextgen.pl');

INSERT INTO companies (id, company_name, email, logo_filename, website)
VALUES (gen_random_uuid(),
        'PolSoft Systems',
        'office@polsoft.pl',
        '/uploads/logos/polsoft.png',
        'https://www.polsoft.pl');

INSERT INTO companies (id, company_name, email, logo_filename, website)
VALUES (gen_random_uuid(),
        'DataWorks Polska',
        'support@dataworks.pl',
        '/uploads/logos/dataworks.png',
        'https://www.dataworks.pl');

INSERT INTO companies (id, company_name, email, logo_filename, website)
VALUES (gen_random_uuid(),
        'Skyline Solutions',
        'info@skyline.pl',
        '/uploads/logos/skyline.png',
        'https://www.skyline.pl');


-- changeset RobertoJavaDev:6
INSERT INTO employees (id, first_name, last_name, company_id, email, phone)
VALUES (gen_random_uuid(),
        'Jan',
        'Kowalski',
        (SELECT id FROM companies WHERE company_name = 'TechNova Sp. z o.o.'),
        'jan.kowalski@technova.pl',
        '+48 600 123 456');

INSERT INTO employees (id, first_name, last_name, company_id, email, phone)
VALUES (gen_random_uuid(),
        'Anna',
        'Nowak',
        (SELECT id FROM companies WHERE company_name = 'TechNova Sp. z o.o.'),
        'anna.nowak@technova.pl',
        '+48 601 234 567');

INSERT INTO employees (id, first_name, last_name, company_id, email, phone)
VALUES (gen_random_uuid(),
        'Tomasz',
        'Wiśniewski',
        (SELECT id FROM companies WHERE company_name = 'GreenFuture S.A.'),
        'tomasz.wisniewski@greenfuture.pl',
        '+48 602 345 678');

INSERT INTO employees (id, first_name, last_name, company_id, email, phone)
VALUES (gen_random_uuid(),
        'Katarzyna',
        'Mazur',
        (SELECT id FROM companies WHERE company_name = 'GreenFuture S.A.'),
        'katarzyna.mazur@greenfuture.pl',
        '+48 603 456 789');

INSERT INTO employees (id, first_name, last_name, company_id, email, phone)
VALUES (gen_random_uuid(),
        'Paweł',
        'Zieliński',
        (SELECT id FROM companies WHERE company_name = 'GreenFuture S.A.'),
        'pawel.zielinski@blueocean.pl',
        '+48 604 567 890');

INSERT INTO employees (id, first_name, last_name, company_id, email, phone)
VALUES (gen_random_uuid(),
        'Monika',
        'Wójcik',
        (SELECT id FROM companies WHERE company_name = 'GreenFuture S.A.'),
        'monika.wojcik@blueocean.pl',
        '+48 605 678 901');

INSERT INTO employees (id, first_name, last_name, company_id, email, phone)
VALUES (gen_random_uuid(),
        'Marek',
        'Krawczyk',
        (SELECT id FROM companies WHERE company_name = 'BlueOcean Tech'),
        'marek.krawczyk@nextgen.pl',
        '+48 606 789 012');

INSERT INTO employees (id, first_name, last_name, company_id, email, phone)
VALUES (gen_random_uuid(),
        'Alicja',
        'Piotrowska',
        (SELECT id FROM companies WHERE company_name = 'NextGen Innovations'),
        'alicja.piotrowska@nextgen.pl',
        '+48 607 890 123');

INSERT INTO employees (id, first_name, last_name, company_id, email, phone)
VALUES (gen_random_uuid(),
        'Robert',
        'Grabowski',
        (SELECT id FROM companies WHERE company_name = 'NextGen Innovations'),
        'robert.grabowski@polsoft.pl',
        '+48 608 901 234');

INSERT INTO employees (id, first_name, last_name, company_id, email, phone)
VALUES (gen_random_uuid(),
        'Ewa',
        'Kubiak',
        (SELECT id FROM companies WHERE company_name = 'NextGen Innovations'),
        'ewa.kubiak@dataworks.pl',
        '+48 609 012 345');

INSERT INTO employees (id, first_name, last_name, company_id, email, phone)
VALUES (gen_random_uuid(),
        'Michał',
        'Dąbrowski',
        (SELECT id FROM companies WHERE company_name = 'PolSoft Systems'),
        'michal.dabrowski@dataworks.pl',
        '+48 610 123 456');

INSERT INTO employees (id, first_name, last_name, company_id, email, phone)
VALUES (gen_random_uuid(),
        'Joanna',
        'Czarnecka',
        (SELECT id FROM companies WHERE company_name = 'DataWorks Polska'),
        'joanna.czarnecka@skyline.pl',
        '+48 611 234 567');

INSERT INTO employees (id, first_name, last_name, company_id, email, phone)
VALUES (gen_random_uuid(),
        'Piotr',
        'Lis',
        (SELECT id FROM companies WHERE company_name = '	Skyline Solutions'),
        'piotr.lis@skyline.pl',
        '+48 612 345 678');