layui.use(['form','layer','laydate','table','laytpl'],function(){
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        laydate = layui.laydate,
        laytpl = layui.laytpl,
        table = layui.table;

    var localhostPaht=$("#myPathValue").val();

    //用户列表
    var tableIns = table.render({
        elem: '#newsList',
        url : localhostPaht + '/user/findUserList',
        cellMinWidth : 80,
        page : false,
        height : "full-125",
        id : "newsListTable",
        cols : [[
            {type: "checkbox", fixed:"left", width:25},
            {field: 'username', title: '供应商码',width:85, align:"center"},
            {field: 'nickname', title: '供应商名称', width:175},
            {field: 'email', title: '邮箱地址',  width:170, align:'center',},
            {field: 'businessName', title: '业务联系人名称', width:120, align:'center'},
            {field: 'businessPhone', title: '业务联系人电话', width:120,align:'center'},
            {field: 'financeName', title: '财务联系人名称', width:120,align:'center'},
            {field: 'financeEmail', title: '财务联系人邮箱', width:170,align:'center'},
            {field: 'financePhone', title: '财务联系人电话', width:120,align:'center'},
            {field: 'backAddress', title: '退货地址', width:120,align:'center'},
            {field: 'backPhone', title: '退货联系电话', width:120,align:'center'},
            {field: 'bankName', title: '银行名称', width:200,align:'center'},
            {field: 'bankAccount', title: '银行账户', width:160,align:'center'},
            {field: 'state', title: '状态',  align:'center',templet:"#state"},
            {title: '操作', width:130, templet:'#newsListBar',fixed:"right",align:"center"}
        ]]
    });

    //添加账户
    function addNews(edit){
        var index = layui.layer.open({
            title : "添加账户",
            type : 2,
            content : "addSupplier.jsp",
            success : function(layero, index){
                var body = layui.layer.getChildFrame('body', index);
                if(edit){
                    body.find(".username").val(edit.username);
                    body.find(".userid").val(edit.userid);
                    body.find(".nickname").val(edit.nickname);
                    body.find(".email").val(edit.email);
                    body.find(".businessName").val(edit.businessName);
                    body.find(".businessPhone").val(edit.businessPhone);
                    body.find(".financeName").val(edit.financeName);
                    body.find(".financeEmail").val(edit.financeEmail);
                    body.find(".financePhone").val(edit.financePhone);
                    body.find(".backAddress").val(edit.backAddress);
                    body.find(".backPhone").val(edit.backPhone);
                    body.find(".bankName").val(edit.bankName);
                    body.find(".bankAccount").val(edit.bankAccount);
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

    //编辑用户
    function editNews(edit){
        var index = layui.layer.open({
            title : "编辑账户",
            type : 2,
            content : "editSupplier.jsp",
            success : function(layero, index){
                var body = layui.layer.getChildFrame('body', index);
                if(edit){
                    body.find(".username").val(edit.username);
                    body.find(".userid").val(edit.userid);
                    body.find(".nickname").val(edit.nickname);
                    body.find(".email").val(edit.email);
                    body.find(".businessName").val(edit.businessName);
                    body.find(".businessPhone").val(edit.businessPhone);
                    body.find(".financeName").val(edit.financeName);
                    body.find(".financeEmail").val(edit.financeEmail);
                    body.find(".financePhone").val(edit.financePhone);
                    body.find(".backAddress").val(edit.backAddress);
                    body.find(".backPhone").val(edit.backPhone);
                    body.find(".bankName").val(edit.bankName);
                    body.find(".bankAccount").val(edit.bankAccount);
                    body.find("#businessLicense").attr('src', localhostPaht+ edit.businessLicense);
                    body.find("#taxCertificate").attr('src', localhostPaht+ edit.taxCertificate);
                    body.find("#organizationCertificate").attr('src', localhostPaht+ edit.organizationCertificate);
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

    $(".shuxin_btn").click(function(){
        location.reload();
    });
    //更新银行账户
    $(".SynSupplierBankInfo_btn").click(function(){
        var lastOperator = JSON.parse(localStorage.getItem("user")).username;
        var index = layer.msg('更新中，请稍候',{icon: 16,time:false,shade:0.8});
                $.ajax({
                    type: "POST",
                    url: localhostPaht + "/user/SynSupplierBankInfo",
                    data:  {
                        "lastOperator" : lastOperator
                    },
                    dataType: "json",
                    success: function(data){
                        if(data == true){
                            layer.close(index);
                            layer.msg("更新成功!",{
                                time:1500
                            });

                            setTimeout(function(){
                                //刷新页面
                                location.reload();
                            },1000)

                        }else{
                            layer.close(index);
                            layer.msg("更新失败,请联系管理员",{
                                time:1500
                            });
                        }
                    }
                });
    })

    //列表操作
    table.on('tool(newsList)', function(obj){
        var layEvent = obj.event,
            data = obj.data;

        if(layEvent === 'edit'){ //编辑
            editNews(data);
        } else if(layEvent === 'del'){ //删除
            layer.confirm('确定冻结此账户？',{icon:3, title:'提示信息'},function(index){
                // $.get("删除文章接口",{
                //     newsId : data.userid  //将需要删除的newsId作为参数传入
                // },function(data){
                //     tableIns.reload();
                //     layer.close(index);
                // })
                var username = JSON.parse(localStorage.getItem("user")).username;
                if(!username){
                    layer.msg("您没有权限,进行此操作",{
                        time:1500
                    });
                    return;
                }
                    $.ajax({
                        type: "POST",
                        url: localhostPaht + "/user" + "/delete",
                        data:  {
                            "userid":data.userid,
                            "lastOperator" : username
                        },
                        dataType: "json",
                        success: function(data){
                            if(data == true){
                                tableIns.reload();
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
    });

})