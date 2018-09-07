layui.use(['form','layer','layedit','laydate','upload'],function(){
    var form = layui.form
        layer = parent.layer === undefined ? layui.layer : top.layer,
        laypage = layui.laypage,
        upload = layui.upload,
        layedit = layui.layedit,
        laydate = layui.laydate,
        $ = layui.jquery;

    //用于同步编辑器内容到textarea
    layedit.sync(editIndex);

    var localhostPaht=$("#myPathValue").val();

    // //上传缩略图
    // upload.render({
    //     elem: '.thumbBox',
    //     url: '../../json/userface.json',
    //     method : "get",  //此处是为了演示之用，实际使用中请将此删除，默认用post方式提交
    //     done: function(res, index, upload){
    //         var num = parseInt(4*Math.random());  //生成0-4的随机数，随机显示一个头像信息
    //         $('.thumbImg').attr('src',res.data[num].src);
    //         $('.thumbBox').css("background","#fff");
    //     }
    // });

    // //格式化时间
    // function filterTime(val){
    //     if(val < 10){
    //         return "0" + val;
    //     }else{
    //         return val;
    //     }
    // }

    form.verify({
        newsName : function(val){
            if(val == ''){
                return "文章标题不能为空";
            }
        },
        content : function(){
            var val = layedit.getContent(editIndex).split('<audio controls="controls" style="display: none;"></audio>')[0]
            if(val == ''){
                return "文章内容不能为空";
            }
        }
    })

    //发布
    form.on("submit(addNews)",function(data){
        var checkDepartment = $("input[name='department']:checked").val();
        if(!checkDepartment){
            layer.msg("请选择部门!",{
                time:1500
            });
            return;
        }
        //弹出loading
        var index = top.layer.msg('数据提交中，请稍候',{icon: 16,time:false,shade:0.8});
        var articleAuthor = JSON.parse(localStorage.getItem("user")).nickname;
        var IsEdit = $(".IsEdit").val();
        if(IsEdit == "true"){
            $.post(localhostPaht + "/article/updateArticle",{
                articleId: $(".articleId").val(),
                articleName : $(".newsName").val(),  //文章标题
                articleContent : layedit.getContent(editIndex).split('<audio controls="controls" style="display: none;"></audio>')[0],  //文章内容
                articleAuthor: articleAuthor,   //作者
                department: $("input[name='department']:checked").val(),   //部门
                releaseStatus : "1",    //发布状态
                isEmail :  $("input[name='openness']:checked").val(),
                IsHide : data.field.newsTop == "on" ? "1" : "0",    //是否隐藏
            },function(res){
                if(res == "true"){
                    setTimeout(function(){
                        top.layer.close(index);
                        top.layer.msg("文章修改成功！");
                        layer.closeAll("iframe");
                        //刷新父页面
                        parent.location.reload();
                    },1500);
                    return false;
                }else{
                    top.layer.close(index);
                    top.layer.msg("网络异常,请稍后再试！");
                }
            })
        }else{
            $.post(localhostPaht + "/article/addArticle",{
                articleName : $(".newsName").val(),  //文章标题
                articleContent : layedit.getContent(editIndex).split('<audio controls="controls" style="display: none;"></audio>')[0],  //文章内容
                articleAuthor: articleAuthor,   //作者
                releaseStatus : "1",    //发布状态
                isEmail :  $("input[name='openness']:checked").val(),
                department: $("input[name='department']:checked").val(),   //部门
                IsHide : data.field.newsTop == "on" ? "1" : "0",    //是否隐藏
            },function(res){
                if(res == "true"){
                    setTimeout(function(){
                        top.layer.close(index);
                        top.layer.msg("文章添加成功！");
                        layer.closeAll("iframe");
                        //刷新父页面
                        parent.location.reload();
                    },1500);
                    return false;
                }else{
                    top.layer.close(index);
                    top.layer.msg("网络异常,请稍后再试！");
                }
            })
        }
    })

    //保存
    form.on("submit(look)",function(){
        var checkDepartment = $("input[name='department']:checked").val();
        if(!checkDepartment){
            layer.msg("请选择部门!",{
                time:1500
            });
            return;
        }
        //弹出loading
        var index = top.layer.msg('数据提交中，请稍候',{icon: 16,time:false,shade:0.8});
        var articleAuthor = JSON.parse(localStorage.getItem("user")).nickname;
        var IsEdit = $(".IsEdit").val();
        //实际使用时的提交信息
        if(IsEdit == "true"){
            $.post(localhostPaht + "/article/updateArticle",{
                articleId: $(".articleId").val(),
                articleName : $(".newsName").val(),  //文章标题
                articleContent : layedit.getContent(editIndex).split('<audio controls="controls" style="display: none;"></audio>')[0],  //文章内容
                articleAuthor: articleAuthor,   //作者
                department: $("input[name='department']:checked").val(),   //部门
                releaseStatus : "0",    //发布状态
                isEmail :  "0",
                IsHide :  "0"   //隐藏
            },function(res){
                if(res == "true"){
                    setTimeout(function(){
                        top.layer.close(index);
                        top.layer.msg("文章修改成功！");
                        layer.closeAll("iframe");
                        //刷新父页面
                        parent.location.reload();
                    },1500);
                    return false;
                }else{
                    top.layer.close(index);
                    top.layer.msg("网络异常,请稍后再试！");
                }
            })
        }else{
            $.post(localhostPaht+ "/article/addArticle",{
                articleName : $(".newsName").val(),  //文章标题
                articleContent : layedit.getContent(editIndex).split('<audio controls="controls" style="display: none;"></audio>')[0],  //文章内容
                articleAuthor: articleAuthor,   //作者
                department: $("input[name='department']:checked").val(),   //部门
                releaseStatus : "0",    //发布状态
                isEmail :  "0",
                IsHide :  "0"   //隐藏
            },function(res){
                if(res == "true"){
                    setTimeout(function(){
                        top.layer.close(index);
                        top.layer.msg("文章保存成功！");
                        layer.closeAll("iframe");
                        //刷新父页面
                        parent.location.reload();
                    },1500);
                    return false;
                }else{
                    top.layer.close(index);
                    top.layer.msg("网络异常,请稍后再试！");
                }
            })
        }
    })

    //创建一个编辑器
    var editIndex = layedit.build('news_content',{
        height : 535,
        // uploadImage : {
        //     url : "../../json/newsImg.json"
        // }
    });

})