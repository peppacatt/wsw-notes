import urllib.request

url = 'https://movie.douban.com/j/chart/top_list?type=5&interval_id=100%3A90&action=&start=0&limit=20'
headers = {
    'User-Agent': 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/123.0.0.0 Safari/537.36'
}
request = urllib.request.Request(url=url, headers=headers)
res = urllib.request.urlopen(request)
ret = res.read().decode('utf-8')
print(ret)
with open("doubanTop.json", 'w', encoding='utf-8') as file:
    file.write(ret)
