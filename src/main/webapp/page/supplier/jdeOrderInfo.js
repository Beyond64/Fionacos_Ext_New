layui.use(['form','layer','laydate','table','laytpl'],function(){
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        laydate = layui.laydate,
        laytpl = layui.laytpl,
        table = layui.table;
    var localhostPaht=$("#myPathValue").val();
    var JdeOrderNumber = localStorage.getItem("JdeOrderNumber");
    var JdeOrderType = localStorage.getItem("JdeOrderType");
    var username = JSON.parse(localStorage.getItem("user")).username;



    var index = layer.msg('查询中，请稍候',{icon: 16,time:false,shade:0.8});
    //用户列表
    var tableIns = table.render({
        elem: '#newsList',
        url : localhostPaht + '/order/findJdeOrderDetails?orderNo=' + JdeOrderNumber + '&orderType=' + JdeOrderType,
        cellMinWidth : 80,
        page : false,
        height : "full-125",
        id : "newsListTable",
        cols : [[
            {field: 'pdlnid', title: '序号',width:'10%', align:"center"},
            {field: 'pdlitm', title: '国际条码', width:'10%'},
            {field: 'pddsc1', title: '商品名称', width:'20%', align:'center'},
            {field: 'pdpds2', title: '品牌码', width:'10%', align:'center'},
            {field: 'pdvr02', title: '包装规格', width:'10%', align:'center'},
            {field: 'pduom', title: '单位', width:'10%', align:'center'},
            {field: 'pduorg', title: '采购数量', width:'10%', align:'center'},
            {field: 'pdurat', title: '采购金额', width:'10%', align:'center'}
        ]],
        done: function(res, curr, count){
            layer.close(index);
        }
    });

})