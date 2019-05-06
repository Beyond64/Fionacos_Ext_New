function recordPaint(elem,data){
    var context = elem.getContext('2d');

    // 定义画布的常量
    const WIDTH = elem.width;
    const HEIGHT = elem.height;
    //定义画布的内边距
    var padding = 20;
    var paddingLeft = 55;
    var paddingButtom = 30;

    var axisY = {
        x : paddingLeft,
        y : padding
    }

    var origin = {
        x : paddingLeft,
        y : HEIGHT - paddingButtom
    }

    var axisX = {
        x : WIDTH - padding,
        y : HEIGHT - paddingButtom
    }

    //绘制坐标轴
    context.beginPath();
    context.moveTo(axisY.x,axisY.y);
    context.lineTo(origin.x,origin.y);
    context.lineTo(axisX.x,axisX.y);
    context.stroke();

    //绘制y轴箭头
    context.beginPath();
    context.moveTo(axisY.x - 7,axisY.y + 15);
    context.lineTo(axisY.x,axisY.y);
    context.lineTo(axisY.x + 7,axisY.y+ 15);
    context.stroke();
    //绘制x轴箭头
    context.beginPath();
    context.moveTo(axisX.x - 15,axisX.y - 7);
    context.lineTo(axisX.x,axisX.y);
    context.lineTo(axisX.x - 15,axisX.y + 7);
    context.stroke();

    // pointsX : X月份坐标值
    var pointsX = [];
    //绘制X月份坐标
    var month = {
        x : paddingLeft,
        y : HEIGHT - paddingButtom
    }

    context.font = "14px microsoftYaHei";
    context.textBaseline = "top";
    for(var i=1;i<=12;i++){
        pointsX[pointsX.length]= month.x;

        context.fillText(i+"月",month.x,month.y);
        context.fillText("ㅣ",month.x,month.y-15);
        month.x += (axisX.x - origin.x)/12;
    }

    /*取到数组中的最大值，不改变原数组
    var datas = [];
    for(index in data){
        datas[datas.length] = data[index];
    }
    function sortNumber(a,b) {
        // 排序从小到大
        return a-b;
    }
    var max =datas.sort(sortNumber)[datas.length-1];
    console.log(datas);
    */

    // 更简单的方式
    var max = Math.max.apply(Math,data);

    //绘制Y金额坐标
    var moneyY = (origin.y - axisY.y)/(max/500+1);

    var money = {
        x : axisY.x - 5,
        y : axisY.y + moneyY,
        amount : max
    }


    context.textAlign = "right";
    // 遍历"最高值/间隔"次
    for(var i=0;i<max/500;i++){
        context.fillText(money.amount+"元",money.x,money.y);

        context.fillText("━",axisY.x+8,money.y);
        money.y += moneyY;

        money.amount -= 500;
    }


    // 绘制折线
    // x : 12个月份的x坐标
    // y : 折点到原点的距离 = （3000点的y到原点的y的长度）*当前金额/3000

    context.beginPath();
    for(var i = 0;i<data.length;i++){
        var pointY = origin.y - (origin.y - (axisY.y + moneyY))*data[i]/max;
        var pointX = pointsX[i];

        // 绘制折线
        if(i == 0){
            context.textAlign = "left";
            context.moveTo(pointX,pointY);
        }else {
            // 设置水平对齐
            context.textAlign = "center";
            context.textBaseline = "bottom";
            context.lineTo(pointX,pointY);
        }

        // 绘制金额文字
        context.fillText(data[i],pointX,pointY);
    }
    context.stroke();

    //绘制折点的圆
    for(var i = 0;i<data.length;i++){
        var pointY = origin.y - (origin.y - (axisY.y + moneyY))*data[i]/max;
        var pointX = pointsX[i];

        context.fillStyle = "purple";
        context.beginPath();
        context.arc(pointX,pointY,3,0,Math.PI*2);
        context.fill();
    }



}