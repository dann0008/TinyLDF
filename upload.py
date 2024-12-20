import csv
import requests

api = "https://tiny-ldf.oa.r.appspot.com/_ah/api/myApi/v1/insertQuad"

with open("data/athletics.csv", mode='r', encoding='utf-8') as csvfile:
        reader = csv.reader(csvfile, delimiter=',')
        for row in reader:
            params = {
            "sujet": row[0],
            "predicat": row[1],
            "objet": row[2].replace(" ","_"),
            "contexte": "Projet Web Sémantique"
            }

            response = requests.get(api, params=params)


            if response.status_code != 200: 
                print(params)