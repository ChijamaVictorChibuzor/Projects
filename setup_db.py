import sqlite3

# Paths
db_path = "election.db"  # this will create election.db in your folder
sql_path = "bincom_test.sql"

# Create database and run script
conn = sqlite3.connect(db_path)
cursor = conn.cursor()

with open(sql_path, "r", encoding="utf-8") as f:
    sql_script = f.read()

cursor.executescript(sql_script)
conn.commit()
conn.close()

print("Database created successfully as election.db")
