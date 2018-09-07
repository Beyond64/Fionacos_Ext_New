//获取系统时间
var newDate = '';
getLangDate();
//值小于10时，在前面补0
function dateFilter(date){
    if(date < 10){return "0"+date;}
    return date;
}
function getLangDate(){
    var dateObj = new Date(); //表示当前系统时间的Date对象
    var year = dateObj.getFullYear(); //当前系统时间的完整年份值
    var month = dateObj.getMonth()+1; //当前系统时间的月份值
    var date = dateObj.getDate(); //当前系统时间的月份中的日
    var day = dateObj.getDay(); //当前系统时间中的星期值
    var weeks = ["星期日","星期一","星期二","星期三","星期四","星期五","星期六"];
    var week = weeks[day]; //根据星期值，从数组中获取对应的星期字符串
    var hour = dateObj.getHours(); //当前系统时间的小时值
    var minute = dateObj.getMinutes(); //当前系统时间的分钟值
    var second = dateObj.getSeconds(); //当前系统时间的秒钟值
    var timeValue = "" +((hour >= 12) ? (hour >= 18) ? "晚上" : "下午" : "上午" ); //当前时间属于上午、晚上还是下午
    var user = JSON.parse(localStorage.getItem("user"));
    newDate = dateFilter(year)+"年"+dateFilter(month)+"月"+dateFilter(date)+"日 "+" "+dateFilter(hour)+":"+dateFilter(minute)+":"+dateFilter(second);
    document.getElementById("nowTime").innerHTML = user.nickname + "，"+timeValue+"好！ 欢迎使用妍丽•供应商平台。当前时间为： "+newDate+"　"+week;
    setTimeout("getLangDate()",1000);
}

layui.use(['form','element','layer','jquery'],function(){
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        element = layui.element;
        $ = layui.jquery;
    //上次登录时间【此处应该从接口获取，实际使用中请自行更换】
    $(".loginTime").html(newDate.split("日")[0]+"日</br>"+newDate.split("日")[1]);
    //icon动画
    $(".panel a").hover(function(){
        $(this).find(".layui-anim").addClass("layui-anim-scaleSpring");
    },function(){
        $(this).find(".layui-anim").removeClass("layui-anim-scaleSpring");
    })
    $(".panel a").click(function(){
        parent.addTab($(this));
    })


    // 判断是否web端打开
    if(!/http(s*):\/\//.test(location.href)){
        layer.alert("请联系管理员");
    }else{    //判断是否处于锁屏状态【如果关闭以后则未关闭浏览器之前不再显示】
        var email = JSON.parse(localStorage.getItem("user")).email;
        var role = JSON.parse(localStorage.getItem("user")).role;
        if(!email & role == 'supplier'){
            showEmailNotice();
        }
    }
    // //系统基本参数
    // if(window.sessionStorage.getItem("systemParameter")){
    //     var systemParameter = JSON.parse(window.sessionStorage.getItem("systemParameter"));
    //     fillParameter(systemParameter);
    // }else{
    //     $.ajax({
    //         url : "../json/systemParameter.json",
    //         type : "get",
    //         dataType : "json",
    //         success : function(data){
    //             fillParameter(data);
    //         }
    //     })
    // }
    // //填充数据方法
    // function fillParameter(data){
    //     //判断字段数据是否存在
    //     function nullData(data){
    //         if(data == '' || data == "undefined"){
    //             return "未定义";
    //         }else{
    //             return data;
    //         }
    //     }
    //     $(".version").text(nullData(data.version));      //当前版本
    //     $(".author").text(nullData(data.author));        //开发作者
    //     $(".homePage").text(nullData(data.homePage));    //网站首页
    //     $(".server").text(nullData(data.server));        //服务器环境
    //     $(".dataBase").text(nullData(data.dataBase));    //数据库版本
    //     $(".maxUpload").text(nullData(data.maxUpload));    //最大上传限制
    //     $(".userRights").text(nullData(data.userRights));//当前用户权限
    // }
    var localhostPaht=$("#myPathValue").val();

    //最新文章列表
    $.get( localhostPaht+"/article/FindArticleNameList",function(data){
        var hotNewsHtml = '';
        if(data == null){
            return;
        }
        for(var i=0;i<data.length;i++){
            hotNewsHtml += '<tr class="showTips"  onclick="showArticle('+ data[i].articleId +')">'
                +'<td align="left"><a> '+data[i].articleName+'</a></td>'
                +'<td>'+data[i].articleTime+'</td>'
                +'</tr>';
        }
        $(".hot_news").html(hotNewsHtml);
        $(".userAll span").text(data.length);
        showArticle(data[0].articleId);         //f初始加载第一个新闻
    })

    //提示用户去输入邮箱
    function showEmailNotice(){
        layer.open({
            type: 1,
            title: "系统公告",
            area: '300px',
            shade: 0.8,
            id: 'LAY_layuipro',
            btn: ['立即前往'],
            moveType: 1,
            content: '<div style="padding:15px 20px; text-align:justify; line-height: 22px; text-indent:2em;border-bottom:1px solid #e2e2e2;"><p class="layui-red">您暂未录入邮箱地址,请前往个人资料页面录入邮箱地址。<br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;后续收货单等信息由平台自动发送</p> </div>',
            success: function(layero){
                var btn = layero.find('.layui-layer-btn');
                btn.css('text-align', 'center');
                btn.on("click",function(){
                    tipsShow();
                });
            },
            cancel: function(index, layero){
                tipsShow();
            }
        });
    }
    function tipsShow(){
        window.parent.addTab($("#gotoEmail"));  //在新标签页打开连接
    }

    window.showArticle = function(id){
        //弹出loading

        var index = top.layer.msg('数据加载中，请稍候',{icon: 16,time:false,shade:0.8});
        $.post(localhostPaht + "/article/FindArticleById",{
            ArticleId : id,  //id
        },function(res){
            if(res != null){
                $(".articleName").html(res.articleName);
                $(".articleContent").html(res.articleContent);
                $(".articleTime").html(res.articleTime);
                // setTimeout(function(){
                //
                // },500);
                top.layer.close(index);
            }else{
                top.layer.close(index);
                top.layer.msg("网络异常,请稍后再试！");
            }
        })

    }

    // //用户数量
    // $.get("../json/userList.json",function(data){
    //     $(".userAll span").text(data.count);
    // })

    // //外部图标
    // $.get(iconUrl,function(data){
    //     $(".outIcons span").text(data.split(".icon-").length-1);
    // })

})

