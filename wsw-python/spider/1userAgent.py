import urllib.request
url = 'https://www.baidu.com/'
imgUrl = 'http://image.baidu.com/search/albumsdetail?tn=albumsdetail&word=%E8%9B%8B%E7%B3%95&fr=albumslist&album_tab=%E9%A3%8E%E6%99%AF%E6%97%85%E8%A1%8C&album_id=585&rn=30';

# urllib.request.urlretrieve(imgUrl, 'light.jpg')

headers = {
    "User-Agent": "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/123.0.0.0 Safari/537.36"
}
request = urllib.request.Request(url=url, headers=headers)
res = urllib.request.urlopen(request)
print(res.read().decode("utf-8"))