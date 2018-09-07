layui.use(['form','layer','laydate','table','laytpl'],function(){
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        laydate = layui.laydate,
        laytpl = layui.laytpl,
        table = layui.table;

    var localhostPaht=$("#myPathValue").val();
    var username = JSON.parse(localStorage.getItem("user")).username;

    var index = layer.msg('加载中，请稍候',{icon: 16,time:false,shade:0.8});
    //用户列表
    var tableIns = table.render({
        elem: '#newsList',
        url : localhostPaht + '/supplier/findItemListByGys?gongyingshang='+ username,
        cellMinWidth : 80,
        page : false,
        height : "full-125",
        id : "newsListTable",
        initSort: {field:'chsrp1', type:'asc'},
        cols : [[
            {field: 'turnoverDay', title: '周转天',sort:"true", width:"10%",align:'center',templet:'#state'},
            {field: 'cblitm', title: '国际条码',  width:"12%", align:'center',},
            {field: 'imdsc1', title: '条码描述', width:"22%", align:'center'},
            {field: 'chsrp1', title: '品类', sort:"true",width:"6%",align:'center'},
            {field: 'chsrp1_desc', title: '品类描述', width:"8%",align:'center'},
            {field: 'imprp3', title: '代购销', sort:"true",width:"6%",align:'center'},
            {field: 'cban8', title: '供应商码',width:"7%", align:"center"},
            // {field: 'biaoZhunDiTurnoverDay', title: '低周转天', width:"8%",align:'center'},
            // {field: 'biaoZhunGaoTurnoverDay', title: '高周转天', width:"8%",align:'center'},
            {field: 'abalky', title: '供应商名称', width:"15%"}
        ]],
        done: function(res, curr, count){
            layer.close(index);
        }
    });



    $(".shuxin_btn").click(function(){
        location.reload();
    });


})