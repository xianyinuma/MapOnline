# MapOnline
FDU 2017 SOTA Project
API: default-port-8080

#1. login
front to backend:
domain: + port + /login, post: username=string&password=string

backend to front:
A. login fail: json
{"loginResult":false,"userID":0}
B. login success: json
{
    "loginResult": true,
    "userID": 1,
    "friendMesaages": [
        {
            "userID": 2,
            "name": "xiaomaji"
        },...friendMessage array],
    "imageMessages": [{
        "imageID": 1,
        "title": "haha",
        "description": "huhuhu",
         "base64Coding": "base64Coding",
        "tags": [{
      "id": 1,
             "imageID": 1,
             "tagContent": "13212"
              },....tag array],
        "longitude": 110,
        "latitude": 30
        }, ...imageMessage array],
}

#2. register
front to backend:
domain: + port + /register, post: username=string&password=string

backend to front:
A. register fail:
{"userID":0,"registerResult":false}
B. register success:
{"userID":4,"registerResult":true}

#3. upload
front to backend
domain: + port + /upload, post: username=string&password=string&longitude=double&latitude=double&base64Coding=string

backend to front:
A. identity or format error:
"upload fail"(string)
B. upload success:
{
    "imageID": 2,
    "title": "majixiaoji-2017-05-09-04-16-20.jpg",
    "description": "a man standing in the dark",
    "adultOrNot": 0,
    "tags": [
        "man",
        "person",
        "dark",
        "looking",
        "standing",
        "front",
        "black",
        "monitor",
        "holding",
        "water",
        "red",
        "room",
        "flying"
    ],
    "weatherData": {
        "tempDay": 28,
        "tempNight": 11,
        "cityName": "鹤峰县",
        "conditionDay": "晴",
        "conditionNight": "多云"
    }
}

#4. edit
front to backend:
domain: + port + /edit, post data as following:
{
    "username": "maji",
    "password": "majiji",
    "uploadResponse": {
        "imageID": 1,
        "title": "haha",
        "description": "huhuhu",
        "adultOrNot": 0,
        "tags": [
            "man",
            "person",
            "dark",
            "looking",
            "standing",
            "front",
            "black",
            "monitor",
            "holding",
            "water",
            "red",
            "room",
            "flying"
        ],
        "weatherData": {
            "tempDay": 30,
            "tempNight": 12,
            "cityName": "鹤峰县",
            "conditionDay": "晴",
            "conditionNight": "晴"
        }
    }
}

backend to front:
A. identity and format correct
{"status": true}
B. identity or format error
{"status": false}

#5. view-friend
front to backend: (once view one friend)
domain: + port + /upload, post: username=string&password=string&friendID=int

backend to front:
A. identity all correct, return json as following:
{
    "userID": 4,
    "username": "majixiaoji",
    "imageMessages": [
        {
            "imageID": 2,
            "title": "majixiaoji-2017-05-09-04-16-20.jpg",
            "base64Coding": base64Coding(String);
            "tags": [],
            "longitude": 110,
            "latitude": 30
        }
    ]
}
B. identity error
{"status": false}

#6. delete-image
front to backend:
domain: + port + /upload, post: username=string&password=string&imageID=int

backend to front:
A. identity correct
{"status": true}
B. identity illeagal
{"status": false}

#7. follow friend
front to backend:
domain: + port + /follow-friend, post: username=string&password=string&friendUsername=string

backend to front:
A. follow successfully, identity correct
{friendID: userID(number)}
B. fllow failed, identity incorrect or had followed
{friendID: 0}

#8. delete friend
front to backend:
domain: + port + /delete-friend, post: username=string&password=string&friendID=number

backend to front:
A. identity correct
{"status": true}
B. identity incorrect
{"status": false}