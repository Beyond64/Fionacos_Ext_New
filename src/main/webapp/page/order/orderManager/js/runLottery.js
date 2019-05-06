function runLottery(elem) {
    var context = elem.getContext('2d');

    const WIDTH = elem.width;
    const HEIGHT = elem.height;

    // 图片加载
    var as = new Image();
    as.src = "img/timg.png";
    var point = new Image();
    point.src = "img/point.png";
    point.width = 50;
    point.height = 180;

    // 平移整个画布
    context.translate(WIDTH/2,HEIGHT/2);

    // 动态绘制
    var loop = setInterval(draw,50);

    // 定义旋转的角度值
    var pie = Math.random()*(Math.PI*2);
    // 定义每次旋转增加的角度
    var addPie = 0;
    // 定义每次旋转的差值
    var add = Math.PI/180;

    // 定义开始旋转的时间
    var startTime;

    // 定义专门用于绘制图片的函数
    function draw() {
        // 旋转后，每次增加的角度+addPie
        if(addPie == 0){
            addPie += addPie;
        }else{
            addPie += add;
        }

        // 旋转并绘制转盘图片
        context.rotate(pie + addPie);
        myDraw(as,-as.width/2,-as.height/2);
        // 旋转并绘制指针图片
        context.rotate(-pie - addPie);
        myDraw(point,-point.width/2+1,-point.height/2-30);

        // 获取当前的时间
        var endTime = new Date().getTime();

        if(endTime - startTime <= 3000){
            add += Math.PI/360;
        }else if(endTime - startTime > 3000 && endTime - startTime < 6000){
            add -= Math.PI/360;
        }else if(endTime - startTime >= 6000){
            clearInterval(loop);
            $("#btnLottery").removeAttr("disabled");
        }

    }

    function start() {
        addPie = Math.PI/180;
        startTime = new Date().getTime();
        // 把按钮变成不可点击
        $("#btnLottery").attr("disabled","disabled");
    }
    
    function myDraw(img,x,y) {
        context.drawImage(img,x,y);
    }

    return start;

}