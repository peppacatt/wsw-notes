from urllib import request
from lxml import etree

url = 'https://sc.chinaz.com/tupian/'
headers = {
    "User-Agent": "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/123.0.0.0 Safari/537.36"
}
req = request.Request(url=url, headers=headers)
res = request.urlopen(req)
ret = res.read().decode('utf-8')
print(ret)
tree = etree.HTML(ret)
txt = tree.xpath('/html/body/div[3]/div[2]/div[3]/div/a/@title')
print(txt)