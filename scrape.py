import requests
from bs4 import BeautifulSoup
from openpyxl import Workbook
import time, random  # added random for variable sleep

wb = Workbook()
ws = wb.active
ws.append(["ID", "Name", "Type", "Address", "Phone", "Email", "Website", "Head Teacher", "Total Students"])

start_id = 1717
end_id = 108572

for school_id in range(start_id, end_id + 1):
    url = f"https://lagosschoolsonline.com/single-school.php?id={school_id}"
    response = requests.get(url)

    if response.status_code != 200:
        # only print sometimes to reduce slowdown
        if school_id % 500 == 0:
            print(f"Skipping {school_id} (error {response.status_code})")
        continue

    soup = BeautifulSoup(response.text, "html.parser")

    # --- FIND THE CORRECT TYPE TAG (WITH THE GRADUATION CAP ICON) ---
    school_type_tag = None
    for span in soup.find_all("span", class_="school-details fw-bold"):
        icon = span.find("i", class_="fa-solid fa-graduation-cap")
        if icon:
            school_type_tag = span
            break

    if not school_type_tag:
        if school_id % 500 == 0:
            print(f"Skipping {school_id} (no type found)")
        continue

    school_type_tag.find("i").decompose()
    school_type = school_type_tag.get_text(strip=True).lower()

    # print progress occasionally instead of every time
    if school_id % 500 == 0:
        print(f"{school_id} → Type detected: {school_type}")

    if "private" not in school_type:
        continue  # silently skip non-private schools

    # Extract school name (check both h2 and h5)
    name_tag = soup.find("h2", style=lambda value: value and "font-size" in value) or soup.find("h5", class_="fw-bold")
    name = name_tag.get_text(strip=True) if name_tag else "N/A"

    # Extract other details
    address_tag = soup.find("i", class_="fa-solid fa-location")
    address = address_tag.find_next("small").get_text(strip=True) if address_tag else "N/A"

    phone_tag = soup.find("i", class_="fa-solid fa-phone")
    phone = phone_tag.find_next("small").get_text(strip=True) if phone_tag else "N/A"

    email_tag = soup.find("i", class_="fa-solid fa-envelope-open")
    email = email_tag.find_next("small").get_text(strip=True) if email_tag else "N/A"

    website_tag = soup.find("i", class_="fa-solid fa-globe")
    website = website_tag.find_next("small").get_text(strip=True) if website_tag else "N/A"

    head_tag = soup.find("div", class_="mt-4 mb-4")
    head_teacher = head_tag.find("small", class_="fw-bold single-school").get_text(strip=True) if head_tag and head_tag.find("small", class_="fw-bold single-school") else "N/A"

    total_tag = soup.find("p", class_="school-details fw-bold")
    total = "N/A"
    if total_tag and "Total" in total_tag.get_text():
        total = total_tag.get_text(strip=True).split("Total:")[-1].strip()

    ws.append([school_id, name, school_type, address, phone, email, website, head_teacher, total])

    # show progress every 500 schools
    if school_id % 500 == 0:
        print(f"✅ Added private school: {name}")

    # random delay to speed up
    time.sleep(random.uniform(0.15, 0.45))

wb.save("private_schools.xlsx")
print("✅ Done! Saved as private_schools.xlsx")
