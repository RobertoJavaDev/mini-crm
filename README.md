<div align="center">
  <h1>Mini CRM</h1>
</div>

> 🧩 **Mini CRM** — lekki panel administracyjny do zarządzania firmami i pracownikami, stworzony w najnowszej wersji Java i Spring Boot.  
> Projekt realizuje pełny cykl CRUD dla encji *Company* i *Employee*, z uwierzytelnianiem, paginacją oraz obsługą plików graficznych.

---

## 🌐 Demo

🔗 **Aplikacja dostępna online:**  
👉 [https://mini-crm-l2gy.onrender.com](https://mini-crm-l2gy.onrender.com)

**Dane logowania (użytkownik startowy):**

- [ ] email: admin@admin.com

- [ ] hasło: password

---

## ⚙️ Technologie i stack

![Java 25](https://img.shields.io/badge/Java-25-007396?logo=openjdk&logoColor=white&style=flat)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5.6-6DB33F?logo=springboot&logoColor=white&style=flat)
![Thymeleaf](https://img.shields.io/badge/Thymeleaf-005F0F?logo=thymeleaf&logoColor=white&style=flat)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-336791?logo=postgresql&logoColor=white&style=flat)
![Liquibase](https://img.shields.io/badge/Liquibase-2962FF?logo=liquibase&logoColor=white&style=flat)
![MapStruct](https://img.shields.io/badge/MapStruct-1.5.5.Final-FF6C37?logo=mapstruct&logoColor=white&style=flat)
![Lombok](https://img.shields.io/badge/Lombok-1.18.x-BC4E9C?logo=lombok&logoColor=white&style=flat)
![Spring Security](https://img.shields.io/badge/Spring%20Security-6.x-6DB33F?logo=springsecurity&logoColor=white&style=flat)
![Bootstrap 5](https://img.shields.io/badge/Bootstrap-5.3-7952B3?logo=bootstrap&logoColor=white&style=flat)
![Maven](https://img.shields.io/badge/Maven-3.9+-C71A36?logo=apachemaven&logoColor=white&style=flat)
![Render](https://img.shields.io/badge/Render-Cloud%20Deploy-46E3B7?logo=render&logoColor=white&style=flat)

---

## 🧠 Opis projektu

Aplikacja **Mini CRM** została zaprojektowana jako **system administracyjny** do zarządzania firmami oraz ich pracownikami.  
Pozwala na dodawanie, edycję, usuwanie i przeglądanie danych — wszystko w przejrzystym interfejsie opartym o **Thymeleaf** i **Bootstrap 5**.

### ✳️ Główne funkcjonalności
- 🔐 Logowanie i autoryzacja użytkownika
- 🧱 Zarządzanie firmami
- 👨‍💼 Zarządzanie pracownikami
---

## 🏗️ Struktura danych

### **Tabela: Companies**
| Kolumna  | Typ | Wymagane | Opis |
|-----------|------|-----------|------|
| id | Long | ✔️ | Klucz główny |
| name | String | ✔️ | Nazwa firmy |
| email | String | ❌ | Email kontaktowy |
| logo | String | ❌ | Ścieżka do logo (min. 100×100) |
| website | String | ❌ | Adres strony WWW |

### **Tabela: Employees**
| Kolumna  | Typ | Wymagane | Opis |
|-----------|------|-----------|------|
| id | Long | ✔️ | Klucz główny |
| first_name | String | ✔️ | Imię pracownika |
| last_name | String | ✔️ | Nazwisko pracownika |
| company_id | FK | ✔️ | Powiązanie z firmą |
| email | String | ❌ | Email pracownika |
| phone | String | ❌ | Numer telefonu |

---

## 🚀 Jak uruchomić projekt lokalnie

### 1️⃣ Klonowanie repozytorium
```bash
git clone https://github.com/robertojavadev/mini-crm.git
cd mini-crm
```

### 2️⃣ Uruchomienie aplikacji
```bash
./mvnw spring-boot:run
```

### 3️⃣ Aplikacja będzie dostępna pod adresem
```bash
http://localhost:8080
```

<div align="center"> <br/> <b>Projekt stworzony jako zadanie rekrutacyjne.</b> </div>