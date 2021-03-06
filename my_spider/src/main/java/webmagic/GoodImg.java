package webmagic;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import util.io.DownloadImage;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @Author: 郭航飞
 * @Description: java类作用描述
 * @Date: created in      2018/4/1119:00
 */
public class GoodImg implements PageProcessor {
    /**
     *  列表页正则表达式
     */
    private static final String REGEX_PAGE_URL = "http://www\\.27270\\.com/ent/lianglimeimo/list_12_";

    /**
     *     爬取的列表页，页数。
     */

    private static final int PAGE_SIZE = 70;

    /**
     * 部分一：抓取网站的相关配置，包括编码、抓取间隔、重试次数等
     */
    private Site site = Site.me().
            setRetryTimes(3).
            setSleepTime(1000);


    /**
     * 爬取
     */
    @Override
    public void process(Page page) {
        /**
         * 把所有的列表页都添加到 爬取的目标URL中
         */
        List<String> targetRequests = new ArrayList<String>();
        for (int i = 1;i < PAGE_SIZE; i++){
            targetRequests.add("http://www.27270.com/ent/lianglimeimo/list_12_"+i+".html");
        }
        page.addTargetRequests(targetRequests);
        //用正则匹配是否是列表页
        if (page.getUrl().regex(REGEX_PAGE_URL).match()) {
            /**
             * 如果是，获取 class为 lady-name 的a 标签 的所有链接(详情页)。
             */
            List<String> urls = page.getHtml().xpath("[@class=\"MeinvTuPianBox\"]/ul/li/a/i").css("img","src").all();

            for (String url:urls) {
                String uuid = UUID.randomUUID().toString().replaceAll("-", "");
                try {
                    DownloadImage.download( url,uuid + ".jpg","F:\\美女\\");
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 设置属性
     */
    @Override
    public Site getSite() {

        return site;
    }

    public static void main(String[] args) {
        //启动爬虫
        Spider.create(new GoodImg())
                //添加初始化的URL
                .addUrl("http://www.27270.com/ent/lianglimeimo/list_12_1.html")
                //启动10个线程
                .thread(10)
                //运行
                .run();
    }


}
