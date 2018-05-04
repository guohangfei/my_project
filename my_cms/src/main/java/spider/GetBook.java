package spider;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.scheduler.PriorityScheduler;
import util.io.WriteStringToLocalFile;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static util.io.WriteStringToLocalFile.FILESUFFIXTYPE_TXT;

/**
 *从小说网站爬取图书
 *
 * @Author:          郭航飞
 * @CreateDate:   2018/4/27 9:10
**/
public class GetBook implements PageProcessor {

//       private static int  endPage=33416020;
       private static int  endPage=33413576;
       private static int startPage=33413565;
       private static int count =0;
       private static String localPath="C:/Users/ghf/Desktop/";

    /**
     *     爬取的列表页，页数。
     */
    /**
     * 部分一：抓取网站的相关配置，包括编码、抓取间隔、重试次数等
     */
    private Site site = Site.me().
            setRetryTimes(3).
            setSleepTime(1000);
    /**
     * 爬取
     */
    public void process(Page page) {
        //        获得书籍名称
        String bookName = page.getHtml().xpath("//div[@class=\"leikuang_top\"]/a[2]/text()").toString();
        WriteStringToLocalFile writeStringToLocalFile=new WriteStringToLocalFile();
        /**
         * 把所有的目录页都添加到 爬取的目标URL中
         */
        List<String> targetRequests = new ArrayList<String>();
        for (int i = startPage; i <=endPage ; i++) {
            targetRequests.add("https://www.freexs.org/novel/0/63/"+i+".html");
        }
        page.addTargetRequests(targetRequests);
        count++;
        startPage++;

//            获得章节
            String chapter = page.getHtml().xpath("//div[@class=\"readout\"]/h1/text()").toString();
//            获得内容
            String content = page.getHtml().xpath("//div[@class=\"shuneirong\"]/text()").toString();
            String allContent=chapter+"\r\n"+content+"\r\n";

        try {
            writeStringToLocalFile.startDown(allContent,localPath,bookName,FILESUFFIXTYPE_TXT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 设置属性
     */
    public Site getSite() {
        return site;
    }


    public static void main(String[] args) {
        long startTime, endTime;
        System.out.println("开始爬取...");
        startTime = System.currentTimeMillis();
        //启动爬虫
        Spider.create(new GetBook()).setScheduler(new PriorityScheduler()).run();;
        endTime = System.currentTimeMillis();
        System.out.println("爬取结束，耗时约" + ((endTime - startTime) / 1000) + "秒，抓取了"+count+"条记录");
    }


}
