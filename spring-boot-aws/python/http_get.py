import time
import requests


# url = 'http://localhost:8080/api/v1/postdata'
# url = 'http://springboot-aws-dev.us-east-1.elasticbeanstalk.com/api/rds/saveGetRequest'
# url = 'http://springboot-aws-prod.us-east-1.elasticbeanstalk.com//api/rds/saveGetRequest'
url = 'http://main.octankstore.com/api/rds/saveGetRequest'

headers = {
    'Content-Type': 'application/json'
}

x = 0

while x < 100000:

    res = requests.get(url, headers=headers)
    x = x + 1
    print(res.status_code,res.text, x)
    # time.sleep(2)
