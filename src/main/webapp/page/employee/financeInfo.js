layui.use(['form','layer','upload','laydate','table','laytpl'],function(){
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        laydate = layui.laydate,
        laytpl = layui.laytpl,
        upload = layui.upload,
        table = layui.table;

    var localhostPaht=$("#myPathValue").val();
    var role = JSON.parse(localStorage.getItem("user")).role;

    if(role == "admin" || role == "employee"){
        $("#downloadMuBan").show();
        $("#test8").show();
        $("#test9").show();
        $(".addNews_btn").show();
        $(".quoteBox").show();
    }


    upload.render({
        elem: '#test8'
        ,url: localhostPaht + '/supplier/importFinaceFile'
        ,auto: false
         ,exts: 'xls|xlsx|pdf|jpg|png' //只允许上传压缩文件
        ,bindAction: '#test9'
        ,before: function(obj){ //obj参数包含的信息，跟 choose回调完全一致，可参见上文。
            layer.load(); //上传loading
        }
        ,done: function(res){
            layer.closeAll('loading'); //关闭loading
            if(res.code == 0){
                layer.msg('文件上传成功');
            }else if(res.code == 1){
                layer.msg('本月数据已存在,请勿重复上传, 如之前数据错误,请联系管理员删除后,再上传');
            }
        }
        ,error: function(index, upload){
            layer.closeAll('loading'); //关闭loading
        }
    });

    //用户列表
    var tableIns = table.render({
        elem: '#newsList',
        url : localhostPaht + '/supplier/findFinaceInfoList',
        cellMinWidth : 80,
        page : false,
        // height : "full-125",
        id : "newsListTable",
        cols : [[
            {field: 'name', title: '姓名',width:"10%", align:"center"},
            {field: 'position', title: '职位', width:"10%"},
            {field: 'phone', title: '电话',  width:"18%", align:'center',},
            {field: 'email', title: '邮箱', width:"20%", align:'center'},
            {field: 'address', title: '地址', width:"25%",align:'center'},
            {title: '操作', width:"8%", templet:'#newsListBar',fixed:"right",align:"center"}
        ]]
    });

    //文件列表
    var tableIns2 = table.render({
        elem: '#downList',
        url : localhostPaht + '/supplier/findFinaceFileList',
        cellMinWidth : 80,
        page : false,
        // height : "full-125",
        id : "downListTable",
        cols : [[
            {field: 'filename', title: '文件名称',width:"30%", align:"center"},
            {field: 'uploadDate', title: '上传日期', width:"10%"},
            {title: '操作', width:"8%", templet:'#downListBar',fixed:"right",align:"center"}
        ]]
    });

    //添加账户
    function addNews(edit){
        var index = layui.layer.open({
            title : "添加账户",
            type : 2,
            content : "addFinanceInfo.jsp",
            success : function(layero, index){
                var body = layui.layer.getChildFrame('body', index);
                if(edit){
                    body.find(".name").val(edit.name);
                    body.find(".position").val(edit.position);
                    body.find(".phone").val(edit.phone);
                    body.find(".email").val(edit.email);
                    body.find(".address").val(edit.address);
                    body.find(".objectId").val(edit.objectId);
                    form.render();
                }
                setTimeout(function(){
                    layui.layer.tips('点击此处返回账户列表', '.layui-layer-setwin .layui-layer-close', {
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


    //列表操作
    table.on('tool(newsList)', function(obj){
        var layEvent = obj.event,
            data = obj.data;

        if(layEvent === 'edit'){ //编辑
            addNews(data);
        } else if(layEvent === 'del'){ //删除
            deleteFile(data.objectId,1);
        }
    });


    table.on('tool(downList)', function(obj){
        var layEvent = obj.event,
            data = obj.data;

        if(layEvent === 'down'){ //编辑
            downFile(data);
        }else{
            deleteFile(data.object_id,2);
        }
    });


    function downFile(data){
            //var index = top.layer.msg('文件下载中,请勿重复点击',{icon: 16,time:false,shade:0.8});
            var   link = document.createElement('a');
            link.href = localhostPaht + data.filePath;
            link.setAttribute("download", "");
            link.click();
    }
    function deleteFile(objectId,type){
            layer.confirm('确定冻结此文件？',{icon:3, title:'提示信息'},function(index){
                var username = JSON.parse(localStorage.getItem("user")).username;
                if(!username){
                    layer.msg("您没有权限,进行此操作",{
                        time:1500
                    });
                    return;
                }
                $.ajax({
                    type: "POST",
                    url: localhostPaht + "/supplier" + "/deleteFinaceInfo",
                    data:  {
                        "objectId":objectId,
                        "type": type
                    },
                    dataType: "json",
                    success: function(data){
                        if(data == true){
                            tableIns.reload();
                            tableIns2.reload();
                            layer.close(index);
                            layer.msg("操作成功",{
                                time:1500
                            });
                        }else{
                            layer.msg("网络异常,请稍后再试",{
                                time:1500
                            });
                            layer.close(index);
                        }
                    }
                });
            });

    }

})