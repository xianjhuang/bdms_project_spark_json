import json
import random
import time

age_list = [i for i in range(15,70)]
gender_list = ["male","female","gay","lesbian","bisexual"]
alphabet_list = [chr(i) for i in range(97,123)] + [chr(i) for i in range(65,91)]
punctuation_list = ["*","_","~","!","@","#","$","&"]

example = {"id":1623399281,"name":"tom","age": "25","score":75,"gender":"male","education": [{"level": "primary", "address": "shen road NO.888 "}, {"level": "middle", "address": "guangzhou tianhe district "}, {"level": "college", "address": "foshan gov road NO.666"}, {"level": "doctor", "address": "hongkong victoria sea NO.999"}],"vocation": {"job": "software engineer", "salary": "200000"},"survey_location": {"district": "chaoyang","city": "beijing","people": "students"},  "motivation": "science for the planet earth"}

for i in range(2300000):
    #generate new id
    id = ""
    for n in range(random.randint(3,3)):
        id += str(random.randrange(0,10))
    for n in range(random.randint(3,3)):
        id += random.choice(alphabet_list)
    for n in range(random.randint(2,2)):
        id += random.choice(punctuation_list)

    #generate new name
    name = ""
    for n in range(random.randint(2,5)):
        name += random.choice(alphabet_list)
    # generate new age
    age = random.randint(15,69)
    # generate new score
    score = random.randint(1,99)
    # generate new gender
    gender = random.choice(gender_list)

    #generate new education
    edu = [{"level": "primary", "address": ""},{"level": "middle", "address": ""},{"level": "college", "address": ""},{"level": "doctor", "address": ""}]
    primary = ""
    for n in range(random.randint(15,27)):
        primary += random.choice(alphabet_list)
    for n in range(random.randint(1,3)):
        primary += str(random.randint(0,9))
    edu[0]["address"] = primary

    middle = ""
    for n in range(random.randint(15,27)):
        middle += random.choice(alphabet_list)
    for n in range(random.randint(1,3)):
        middle += str(random.randint(0,9))
    edu[1]["address"] = middle

    college = ""
    for n in range(random.randint(15,27)):
        college += random.choice(alphabet_list)
    for n in range(random.randint(1,3)):
        college += str(random.randint(0,9))
    edu[2]["address"] = college

    doctor = ""
    for n in range(random.randint(15,27)):
        doctor += random.choice(alphabet_list)
    for n in range(random.randint(1,3)):
        doctor += str(random.randint(0,9))
    edu[3]["address"] = doctor

    #generate new vocation
    vocation = {"job": "", "salary": 0}
    job = ""
    for n in range(random.randint(5,13)):
        job += random.choice(alphabet_list)
    vocation["job"] = job
    salary = random.randint(3000,50000)
    vocation["salary"] = salary

    #generate new survey location
    survey_location = {"district": "", "city": "", "people": ""}
    district = ""
    for n in range(random.randint(5,10)):
        district += random.choice(alphabet_list)
    survey_location["district"] = district

    city = ""
    for n in range(random.randint(2,6)):
        city += random.choice(alphabet_list)
    survey_location["city"] = city

    people = ""
    for n in range(random.randint(3,8)):
        people += random.choice(alphabet_list)
    survey_location["people"] = people

    #generate motivation
    motivation =  ""
    for n in range(random.randint(29,30)):
        motivation += random.choice(alphabet_list)

    #update value
    example["id"] = id
    example["name"] = name
    example["gender"] = gender
    example["score"] = score
    example["age"] = age
    example["education"] = edu
    example["vocation"] = vocation
    example["survey_location"] = survey_location
    example["motivation"] = motivation

    print(example)

    #write into files by row
    with open("test.txt","a") as f:
        f.write(json.dumps(example)+"\n")

