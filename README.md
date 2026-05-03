# 🚀 Codeforces Tracker

A full-stack web application that analyzes Codeforces users, tracks their submissions, and provides performance insights along with basic predictions.

---

## 📌 Overview

Codeforces Tracker helps competitive programmers understand their problem-solving patterns and improve their performance by analyzing submission data.

It fetches user data from Codeforces and provides:

* Tag-wise analysis
* Submission trends
* Performance insights
* Basic prediction of future performance

---

## ✨ Features

* 🔍 Fetch Codeforces user details
* 📊 Analyze submission history
* 🧠 Tag-wise problem-solving insights
* 📈 Performance tracking over time
* 🔮 Basic prediction system
* ⚡ Fast and responsive UI

---

## 🛠️ Tech Stack

### Frontend

* HTML
* CSS
* JavaScript

### Backend

* Spring Boot (Java)

### Database

* MongoDB

---

## 📁 Project Structure

```
majorPro
├── Backend    → Spring Boot backend (APIs, services, DB)
└── Frontend   → UI (HTML, CSS, JS)
```

---

## ⚙️ How to Run the Project

### 🔹 1. Clone the repository

```
git clone https://github.com/your-username/majorPro.git
cd majorPro
```

---

### 🔹 2. Run Backend (Spring Boot)

```
cd Backend
mvn spring-boot:run
```

👉 Make sure:

* Java is installed
* Maven is installed
* MongoDB is running

---

### 🔹 3. Run Frontend

```
cd Frontend
```

👉 Open `index.html` in your browser

---

## 🔌 API Overview

Some key endpoints:

* `/user/{handle}` → Fetch user data
* `/analysis/{handle}` → Get performance analysis
* `/prediction/{handle}` → Get prediction data

---

## 🎯 Future Improvements

* Add authentication system
* Improve prediction using ML models
* Add leaderboard comparison

---

## ⭐ If you like this project

Give it a star ⭐ on GitHub — it motivates me to build more!
