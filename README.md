# 🏫 School Fee Management System

![Java](https://img.shields.io/badge/Java-JDK%2024-orange?style=for-the-badge&logo=java)
![NetBeans](https://img.shields.io/badge/IDE-Apache%20NetBeans-green?style=for-the-badge)
![Derby](https://img.shields.io/badge/Database-Apache%20Derby-blue?style=for-the-badge)
![Apache POI](https://img.shields.io/badge/Export-Apache%20POI-red?style=for-the-badge)
![Status](https://img.shields.io/badge/Status-Completed-brightgreen?style=for-the-badge)

A fully functional **Java Swing desktop application** built for school counter admins to manage student fee records, generate receipts, handle course data, and export reports — all from a single system.

---

## 📋 Table of Contents

- [About the Project](#about-the-project)
- [Features](#features)
- [Tech Stack](#tech-stack)
- [Database Schema](#database-schema)
- [Libraries Used](#libraries-used)
- [Setup & Installation](#setup--installation)
- [Developer](#developer)

---

## 📌 About the Project

The **School Fee Management System** is a desktop application designed for the admin sitting at the school fee counter. It removes the need for manual fee registers and paperwork by digitizing the entire fee collection process.

The admin can collect fees, generate printed receipts, manage courses, search student records, view all transactions, and export reports to Excel — all from one place.

This project was built as part of my academic work at **Bhavan's College, Mumbai** during my B.Sc. Computer Science (SYCS) program.

---

## ✅ Features

### 1. 🔐 Login & Signup
- Secure admin authentication system
- New admin registration with full details (name, username, password, DOB, contact)
- Only registered admins can access the system

---

### 2. 💰 Add Fees
- **Auto-generated Receipt Number** — increments automatically with each new entry
- **Payment Mode Selection** — Cash, Card, Cheque, or DD
  - Cheque fields (Cheque No, Bank Name) appear only when Cheque is selected
  - DD field appears only when DD is selected
- **Student Details** — Student Name, Roll No, Academic Year
- **Course Selection** — dynamically loaded from the database
- **GST Auto-Calculation** — enter fee amount and CGST + SGST are calculated automatically
- **Amount in Words** — total amount is auto-converted to words on the receipt
- **Edit Before Print** — review and correct any mistake before printing
- **Print Receipt** — direct print to printer for the student
- **Save to Database** — record is saved permanently

---

### 3. 🔍 Search Record
- Search any student's fee record instantly
- Quick access to past payment details by student name or receipt number

---

### 4. 📚 Edit Course
- View complete list of all available courses
- Add a new course with name and fee amount
- Update an existing course's details
- Delete a course from the system
- Changes reflect immediately in the Add Fees course dropdown

---

### 5. 📋 View All Records
- View complete fee transaction history of all students
- All records displayed in a clean table format

---

### 6. 📊 Generate Report
- Select a **course** and a **date range** (from date → to date)
- Click Submit — a filtered report is generated instantly
- **Print the report** directly
- **Export to Excel** using Apache POI
- Save the report file to your system

---

## 🛠️ Tech Stack

| Layer | Technology |
|-------|-----------|
| Language | Java (JDK 24) |
| UI Framework | Java Swing |
| IDE | Apache NetBeans |
| Database | Apache Derby (Java DB) via JDBC |
| DB Connection | `jdbc:derby://localhost:1527/fee_management` |
| Excel Export | Apache POI 3.17 |
| Date Picker | JCalendar 1.4 |
| Layout Manager | AbsoluteLayout |

---

## 🗃️ Database Schema

### COURSE Table
| Column | Type | Description |
|--------|------|-------------|
| ID | INT (PK) | Auto-increment primary key |
| CNAME | VARCHAR | Course name |
| COST | DECIMAL | Course fee amount |

---

### FEES_DETAILS Table
| Column | Type | Description |
|--------|------|-------------|
| RECIEPT_NO | INT (PK) | Auto-generated receipt number |
| STUDENT_NAME | VARCHAR | Name of the student |
| ROLL_NO | VARCHAR | Student roll number |
| PAYMENT_MODE | VARCHAR | Cash / Card / Cheque / DD |
| CHEQUE_NO | VARCHAR | Cheque number (if applicable) |
| BANK_NAME | VARCHAR | Bank name (if applicable) |
| DD_NO | VARCHAR | DD number (if applicable) |
| COURSE_NAME | VARCHAR | Enrolled course name |
| GSTIN | VARCHAR | GST identification number |
| TOTAL_AMOUNT | DECIMAL | Final amount after GST |
| DATE | DATE | Date of fee payment |
| AMOUNT | DECIMAL | Base fee amount entered |
| CGST | DECIMAL | Central GST (auto-calculated) |
| SGST | DECIMAL | State GST (auto-calculated) |
| TOTAL_IN_WORDS | VARCHAR | Total amount in words |
| REMARK | VARCHAR | Any additional remark |
| YEAR1 | VARCHAR | Academic year start |
| YEAR2 | VARCHAR | Academic year end |

---

### SIGNUP Table
| Column | Type | Description |
|--------|------|-------------|
| ID | INT (PK) | Auto-increment primary key |
| FIRSTNAME | VARCHAR | Admin first name |
| LASTNAME | VARCHAR | Admin last name |
| USERNAME | VARCHAR | Login username |
| PASSWORD | VARCHAR | Login password |
| DOB | DATE | Date of birth |
| CONTACT_NO | VARCHAR | Contact number |

---

## 📦 Libraries Used

| Library | Version | Purpose |
|---------|---------|---------|
| `jcalendar` | 1.4 | Date picker component |
| `poi` | 3.17 | Excel export core |
| `poi-ooxml` | 3.17 | Excel OOXML support |
| `poi-examples` | 3.17 | POI examples |
| `poi-excelant` | 3.17 | Excel Ant tasks |
| `poi-ooxml-schemas` | 3.17 | OOXML schema definitions |
| `poi-scratchpad` | 3.17 | Legacy format support |
| `xmlbeans` | 3.0.1 | XML binding for POI |
| `commons-collections4` | 4.4 | Collections support for POI |
| `AbsoluteLayout` | - | Swing UI layout |
| `derby` | - | Java DB driver |
| `derbyclient` | - | Derby client driver |
| `derbynet` | - | Derby network driver |

---

## ⚙️ Setup & Installation

### Prerequisites
- Java JDK 24 installed
- Apache NetBeans IDE installed
- Git installed

### Steps

**1. Clone the repository**
```bash
git clone https://github.com/soham-88/School-Fee-Management-System.git
```

**2. Open in NetBeans**
- Open Apache NetBeans
- Click **File → Open Project**
- Navigate to the cloned folder and open it

**3. Start the Database**
- In NetBeans, go to the **Services** tab (Window → Services)
- Expand **Databases → Java DB**
- Right-click **Java DB** → Click **Start Server**
- Then right-click the connection `jdbc:derby://localhost:1527/fee_management` → **Connect**

**4. Verify Libraries**
- Right-click the project → **Properties → Libraries**
- Make sure all JARs listed above are present
- If missing, right-click **Libraries** in the project → **Add JAR/Folder** and add them manually

**5. Build and Run**
- Press **Shift + F6** or click the **Run** button
- Login page will open — sign up first, then log in

---

## 👨‍💻 Developer

**Soham Gopal Pawar**
B.Sc. Computer Science (SYCS) — Bhavan's College, Mumbai

[![LinkedIn](https://img.shields.io/badge/LinkedIn-Connect-blue?style=for-the-badge&logo=linkedin)](https://www.linkedin.com/in/soham-pawar-984b32319/)
[![GitHub](https://img.shields.io/badge/GitHub-Follow-black?style=for-the-badge&logo=github)](https://github.com/soham-88)

---

> ⭐ If you found this project useful, consider giving it a star on GitHub!
