\# 🏫 School Fee Management System



A Java desktop application built with \*\*Java Swing\*\*, \*\*JDBC\*\*, and \*\*Apache Derby (Java DB)\*\* in \*\*Apache NetBeans IDE\*\*. Designed for school counter admins to manage student fee records efficiently.



\---



\## 📌 Features



| # | Feature | Description |

|---|---------|-------------|

| 1 | \*\*Add Fees\*\* | Generate fee receipts with auto receipt no., GST calculation (CGST + SGST), multiple payment modes (Cash/Card/Cheque/DD), and direct printing |

| 2 | \*\*Search Record\*\* | Quickly search any student's fee record |

| 3 | \*\*Edit Course\*\* | View, add, update, or delete courses dynamically |

| 4 | \*\*View All Records\*\* | See complete fee records of all students |

| 5 | \*\*Generate Report\*\* | Filter by course and date range, export to Excel, and print |

| 6 | \*\*Login / Signup\*\* | Secure admin authentication system |



\---



\## 🛠️ Tech Stack



\- \*\*Language:\*\* Java (JDK 24)

\- \*\*IDE:\*\* Apache NetBeans

\- \*\*Database:\*\* Apache Derby (Java DB) via JDBC

\- \*\*Libraries:\*\* Apache POI (Excel export), JCalendar, AbsoluteLayout

\- \*\*UI:\*\* Java Swing



\---



\## 🗃️ Database Schema



\*\*COURSE Table\*\*

\- ID, CNAME, COST



\*\*FEES\_DETAILS Table\*\*

\- RECIEPT\_NO, STUDENT\_NAME, ROLL\_NO, PAYMENT\_MODE, CHEQUE\_NO, BANK\_NAME, DD\_NO, COURSE\_NAME, GSTIN, TOTAL\_AMOUNT, DATE, AMOUNT, CGST, SGST, TOTAL\_IN\_WORDS, REMARK, YEAR1, YEAR2



\*\*SIGNUP Table\*\*

\- ID, FIRSTNAME, LASTNAME, USERNAME, PASSWORD, DOB, CONTACT\_NO



\---



\## ⚙️ Setup \& Run



1\. Clone the repository:

```bash

&#x20;  git clone https://github.com/YOUR\_USERNAME/School-Fee-Management-System.git

```

2\. Open in \*\*Apache NetBeans\*\*

3\. Start the \*\*Java DB (Derby) database\*\* from NetBeans → Services → Databases

4\. Connect to `jdbc:derby://localhost:1527/fee\_management` with username `SOHAM`

5\. Run the SQL scripts to create tables (if needed)

6\. Build and Run the project (`Shift + F6`)



\---





\## 📄 Libraries Used



\- `jcalendar-1.4.jar` — Date picker

\- `poi-3.17.jar` + related POI jars — Excel export

\- `AbsoluteLayout.jar` — UI layout

\- `derby.jar`, `derbyclient.jar` — Java DB driver



\---



\## 👨‍💻 Developer



\*\*Soham Gopal Pawar\*\*  

B.Sc. Computer Science | Bhavan's College, Mumbai  

\[LinkedIn](https://www.linkedin.com/in/soham-pawar-984b32319/) | \[GitHub](https://github.com/soham-88)

