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
            {field: 'address', title: '地址', width:"25%",align:'center'}
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

})