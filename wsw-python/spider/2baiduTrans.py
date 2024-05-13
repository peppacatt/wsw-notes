import urllib.request
import urllib.parse
import json

url = 'https://fanyi.baidu.com/sug'
data = {
    'kw': 'chat'
}
data = urllib.parse.urlencode(data).encode('utf-8')
headers = {
    'User-Agent': 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/123.0.0.0 Safari/537.36'
}
request = urllib.request.Request(url=url,data=data,headers = headers)
res = urllib.request.urlopen(request)
ret = res.read().decode('utf-8')
print(ret)
ret = json.loads(ret)
print(ret)
