layui.use(['form','layer','laydate','table','laytpl'],function(){
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        laydate = layui.laydate,
        laytpl = layui.laytpl,
        table = layui.table;

    var localhostPaht=$("#myPathValue").val();
    //文章列表
    var tableIns = table.render({
        elem: '#newsList',
        url : localhostPaht+ '/article/articleList',
        cellMinWidth : 95,
        page : true,
        height : "full-125",
        limit : 20,
        limits : [10,15,20,25],
        id : "newsListTable",
        cols : [[
            {type: "checkbox", fixed:"left", width:50},
            {field: 'articleId', title: 'ID', width:60, align:"center"},
            {field: 'articleName', title: '文章标题', width:350},
            {field: 'articleAuthor', title: '发布者', align:'center'},
            {field: 'department', title: '发布部门', align:'center',templet:function (d) {
                    if(d.department == 'purchase'){
                        return "采购部";
                    }else if(d.department == 'accap'){
                        return "财务部";
                    }else{
                        return "未知";
                    }
                }},
            {field: 'releaseStatus', title: '发布状态',  align:'center',templet:"#releaseStatus"},
            {field: 'isEmail', title: '发送邮件',  align:'center',templet:"#newsStatus"},
            {field: 'emailStatus', title: '邮件状态',  align:'center',templet:"#emailStatus"},
            {field: 'IsHide', title: '是否隐藏', align:'center', templet:function(d){
                var isChecked = d.IsHide == 1 ? "checked" : "";
                return '<input type="checkbox" name="newsTop" id="'+d.articleId+'" lay-filter="newsTop" lay-skin="switch" lay-text="是|否" '+ isChecked+'>'
            }},
            {field: 'articleTime', title: '发布时间', align:'center', minWidth:110, templet:function(d){
                // return d.newsTime.substring(0,10)
                    return d.articleTime
            }},
            {title: '操作', width:170, templet:'#newsListBar',fixed:"right",align:"center"}
        ]]
    });

    //是否隐藏
    form.on('switch(newsTop)', function(data){
        var index = layer.msg('修改中，请稍候',{icon: 16,time:false,shade:0.8});
        var IsHide = data.elem.checked == true ? 1 : 0;
        var articleId = data.elem.id;
        $.ajax({
            type: "POST",
            url: localhostPaht+ "/article/updateArticle",
            dataType: "json",
            data:{
                articleId : articleId,
                IsHide : IsHide
            },success: function(data){
                setTimeout(function(){
                    layer.close(index);
                    top.layer.msg("隐藏成功！");
                    tableIns.reload();
                },500);
            }
        });
    })


    //添加文章
    function addNews(edit){
        var index = layui.layer.open({
            title : "添加文章",
            type : 2,
            maxmin:true,
            content : localhostPaht + "/page/employee/newsAdd.jsp",
            success : function(layero, index){
                var body = layui.layer.getChildFrame('body', index);
                if(edit){
                    var isEmail = edit.isEmail == 1 ? "发送":"不发送";
                    var isDepartment = edit.department == 'accap' ? "财务部":"采购部";
                    body.find(".newsName").val(edit.articleName);
                    body.find(".articleId").val(edit.articleId);
                    body.find("#news_content").val(edit.articleContent);
                    body.find(".department input[name='department'][title='"+isDepartment+"']").prop("checked","checked");
                    body.find(".openness input[name='openness'][title='"+isEmail+"']").prop("checked","checked");
                    body.find(".newsTop input[name='newsTop']").prop("checked",edit.isHide);
                    body.find(".IsEdit").val(true);
                    form.render();
                }else{
                    body.find(".IsEdit").val(false);
                }
                setTimeout(function(){
                    layui.layer.tips('点击此处返回文章列表', '.layui-layer-setwin .layui-layer-close', {
                        tips: 3
                    });
                },500)
            }
        })
        layui.layer.full(index);
        //改变窗口大小时，重置弹窗的宽高，防止超出可视区域（如F12调出debug的操作）
        $(window).on("resize",function(){
            layui.layer.full(index);
        })
    }
    $(".addNews_btn").click(function(){
        addNews();
    })

    //批量删除
    $(".delAll_btn").click(function(){
        var checkStatus = table.checkStatus('newsListTable'),
            data = checkStatus.data,
            newsId = [];
        if(data.length > 0) {
            for (var i in data) {
                newsId.push(data[i].articleId);
            }
            layer.confirm('确定删除选中的文章？', {icon: 3, title: '提示信息'}, function (index) {
                $.ajax({
                    type: "POST",
                    url: localhostPaht+ "/article/deleteArticle",
                    contentType:"application/json",
                    dataType: "json",
                    data:JSON.stringify(newsId),
                    success: function(data){
                        setTimeout(function(){
                            layer.close(index);
                            top.layer.msg("删除成功！");
                            tableIns.reload();
                        },1000);
                    }
                });
            })
        }else{
            layer.msg("请选择需要删除的文章");
        }
    })

    //列表操作
    table.on('tool(newsList)', function(obj){
        var layEvent = obj.event,
            data = obj.data;

        if(layEvent === 'edit'){ //编辑
            addNews(data);
        } else if(layEvent === 'del'){ //删除
            layer.confirm('确定删除此文章？',{icon:3, title:'提示信息'},function(index){
                newsId = [];
                newsId.push(data.articleId);
                $.ajax({
                    type: "POST",
                    url: localhostPaht+ "/article/deleteArticle",
                    contentType:"application/json",
                    dataType: "json",
                    data:JSON.stringify(newsId),
                    success: function(data){
                        setTimeout(function(){
                            layer.close(index);
                            top.layer.msg("删除成功！");
                            tableIns.reload();
                        },1000);
                    }
                });
            });
        } else if(layEvent === 'email'){ //发送邮件
            if (data.emailStatus == 1){
                sendEmail("该文章正在发送邮件, 确定要再次发送邮件?",data.articleId);
            }else if(data.emailStatus == 2){
                sendEmail("该文章已发送过邮件, 确定要再次发送邮件?",data.articleId);
            }else{
                sendEmail("该文章为不发送邮件文章, 确定要发送邮件?",data.articleId);
            }
        }
    });

    //主动发送邮件,不考虑状态
    function sendEmail(msg,articleIds){
        layer.confirm(msg, {icon: 3, title: '提示信息'}, function (index) {
            $.ajax({
                type: "POST",
                url: localhostPaht+ "/article/activeSendArticleEmail",
                dataType: "json",
                data:{
                    articleIds: articleIds
                },
                success: function(data){
                    if(data == true){
                        setTimeout(function(){
                            layer.close(index);
                            top.layer.msg("发送成功！");
                            tableIns.reload();
                        },1000);
                    }
                }
            });
        })
    }

})