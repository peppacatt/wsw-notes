from urllib import request
from lxml import etree

url = 'https://www.toutiao.com/'
headers = {
    "User-Agent": "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/123.0.0.0 Safari/537.36"
}
req = request.Request(url=url, headers=headers)
res = request.urlopen(req)
ret = res.read().decode('utf-8')
print(ret)
tree = etree.HTML(ret)
txt = tree.xpath('//*[@id="root"]/div/div[5]/div[1]/div/div/div/div[2]/div[1]/div[5]/a/text')
print(txt)