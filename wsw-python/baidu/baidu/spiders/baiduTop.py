import scrapy


class BaidutopSpider(scrapy.Spider):
    name = "baiduTop"
    # allowed_domains = ["www.baidu.com"]
    # start_urls = ["https://www.baidu.com/"]

    allowed_domains = ["top.baidu.com"]
    start_urls = ["https://top.baidu.com/board?platform=pc&sa=pcindex_entry"]

    def parse(self, response):
        # res = response.text
        res = response.xpath('//*[@id="sanRoot"]/main/div[1]/div[1]/div[2]/a[2]/div[2]/div[2]/div/div/text()')
        print(res)