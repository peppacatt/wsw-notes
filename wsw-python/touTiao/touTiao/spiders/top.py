import scrapy


class TopSpider(scrapy.Spider):
    name = "top"
    allowed_domains = ["www.toutiao.com"]
    start_urls = ["https://www.toutiao.com/"]

    def parse(self, response):
        print("xxxxxxxxxxx今日头条xxxxxxxxxxxxx")
        content = response.xpath('//*[@id="root"]/div/div[5]/div[2]/div[4]/div/div/div/ol/li[1]/a')
        print(content)
        print("xxxxxxxxxxx今日头条xxxxxxxxxxxxx")