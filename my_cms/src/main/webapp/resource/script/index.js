
$(function(){
    //点击执行爬虫
    $("#startSpider").click({
        $.ajax({
        type:"post",
        url:"user/start",
        success:function () {
            alert("开启成功")
        },error:function(){
            alert("存在异常")
        },
        });
     });
})
