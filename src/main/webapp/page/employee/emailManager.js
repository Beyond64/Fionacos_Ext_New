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
        url : localhostPaht + '/email/list',
        cellMinWidth : 80,
        page : false,
        height : "full-125",
        id : "newsListTable",
        cols : [[
            {field: 'emailId', title: 'ID', align:"center"},
            {field: 'emailTo', title: '发送至'},
            {field: 'emailAddress', title: '邮箱地址',width: '70%', align:'center',},
            // {field: 'emailContent', title: '邮件内容',width: 400,  align:'center'},
            {field: 'sendDate', title: '发送日期',align:'center'},
            {title: '预览', width:130, templet:'#newsListBar',fixed:"right",align:"center"}
        ]]
    });

    //日期
    var Mydate = getNowFormatDate();
    laydate.render({
        elem: '#test1'
        ,value: getNowFormatDate()
        ,done: function(value, date, endDate){
            Mydate = value;
        }
    });

    //获取当前时间
    function getNowFormatDate() {
        var date = new Date();
        var seperator1 = "-";
        var year = date.getFullYear();
        var month = date.getMonth() + 1;
        var strDate = date.getDate();
        if (month >= 1 && month <= 9) {
            month = "0" + month;
        }
        if (strDate >= 0 && strDate <= 9) {
            strDate = "0" + strDate;
        }
        var currentdate = year + seperator1 + month + seperator1 + strDate;
        return currentdate;
    }

    //预览
    function editNews(edit){
        var index = layui.layer.open({
            title : "邮件预览",
            type : 1,
            content : edit.emailContent,
            success : function(layero, index){
                setTimeout(function(){
                    layui.layer.tips('点击此处返回邮件列表', '.layui-layer-setwin .layui-layer-close', {
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



    $(".findEmail").click(function(){
        //执行重载
        tableIns.reload({
            url : localhostPaht + '/email/list',
            method:'GET',
            where: {
                date: Mydate
            }
        });

    });
    //刷新
    $(".shuxin_btn").click(function(){
        location.reload();
    });

    //列表操作
    table.on('tool(newsList)', function(obj){
        var layEvent = obj.event,
            data = obj.data;

        if(layEvent === 'edit'){ //编辑
            editNews(data);
        }
    });

})


