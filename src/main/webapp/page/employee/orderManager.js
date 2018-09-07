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
            // {title: '操作', width:130, templet:'#newsListBar',fixed:"right",align:"center"}
        ]]
    });

    //添加账户
    function addNews(edit){
        var index = layui.layer.open({
            title : "添加账户",
            type : 2,
            content : "addOrder.jsp",
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
                    layui.layer.tips('点击此处返回订单列表', '.layui-layer-setwin .layui-layer-close', {
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
    // function editNews(edit){
    //     var index = layui.layer.open({
    //         title : "编辑账户",
    //         type : 2,
    //         content : "editSupplier.jsp",
    //         success : function(layero, index){
    //             var body = layui.layer.getChildFrame('body', index);
    //             if(edit){
    //                 body.find(".username").val(edit.username);
    //                 body.find(".userid").val(edit.userid);
    //                 body.find(".nickname").val(edit.nickname);
    //                 body.find(".email").val(edit.email);
    //                 body.find(".businessName").val(edit.businessName);
    //                 body.find(".businessPhone").val(edit.businessPhone);
    //                 body.find(".financeName").val(edit.financeName);
    //                 body.find(".financeEmail").val(edit.financeEmail);
    //                 body.find(".financePhone").val(edit.financePhone);
    //                 body.find(".backAddress").val(edit.backAddress);
    //                 body.find(".backPhone").val(edit.backPhone);
    //                 body.find(".bankName").val(edit.bankName);
    //                 body.find(".bankAccount").val(edit.bankAccount);
    //                 body.find("#businessLicense").attr('src', localhostPaht+ edit.businessLicense);
    //                 body.find("#taxCertificate").attr('src', localhostPaht+ edit.taxCertificate);
    //                 body.find("#organizationCertificate").attr('src', localhostPaht+ edit.organizationCertificate);
    //                 form.render();
    //             }
    //             setTimeout(function(){
    //                 layui.layer.tips('点击此处返回账户列表', '.layui-layer-setwin .layui-layer-close', {
    //                     tips: 3
    //                 });
    //             },500)
    //         }
    //     })
    //     layui.layer.full(index);
    //     //改变窗口大小时，重置弹窗的宽高，防止超出可视区域（如F12调出debug的操作）
    //     $(window).on("resize",function(){
    //         layui.layer.full(index);
    //     })
    // }



    $(".addOrder_btn").click(function(){
        addNews();
    })

    $(".shuxin_btn").click(function(){
        location.reload();
    });


    //列表操作
    // table.on('tool(newsList)', function(obj){
    //     var layEvent = obj.event,
    //         data = obj.data;
    //
    //     if(layEvent === 'edit'){ //编辑
    //         editNews(data);
    //     }
    // });

})