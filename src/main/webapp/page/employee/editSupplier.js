var form, $,areaData;
layui.config({
    base : "../../js/"
}).extend({
    "address" : "address"
})
layui.use(['form','layer','upload','laydate',"address"],function(){
    form = layui.form;
    $ = layui.jquery;
    var layer = parent.layer === undefined ? layui.layer : top.layer,
        upload = layui.upload,
        laydate = layui.laydate,
        address = layui.address;

    var localhostPaht=$("#myPathValue").val();
    // //上传头像
    // upload.render({
    //     elem: '.businessLicenseBtn',
    //     url: '/upload/imgge/businessLicense?userName=' + $(".username").val(),
    //     method : "get",  //此处是为了演示之用，实际使用中请将此删除，默认用post方式提交
    //     done: function(res, index, upload){
    //         var num = parseInt(4*Math.random());  //生成0-4的随机数，随机显示一个头像信息
    //         $('#businessLicense').attr('src',res.data[num].src);
    //         window.sessionStorage.setItem('businessLicense',res.data[num].src);
    //     }
    // });

    //下面是图片上传模块//
    // var uploadInst = upload.render({
    //     elem: '.businessLicenseBtn'
    //     ,url: localhostPaht+'/upload/image/BUSINESS_LICENSE?userName=' + $(".username").val()
    //     ,before: function(obj){
    //         //预读本地文件示例，不支持ie8
    //         obj.preview(function(index, file, result){
    //             $('#businessLicense').attr('src', result); //图片链接（base64）
    //         });
    //     }
    //     ,done: function(res){
    //         //如果上传失败
    //         if(res.code > 0){
    //             return layer.msg('上传失败');
    //         }
    //         //上传成功
    //     }
    //     ,error: function(){
    //         //演示失败状态，并实现重传
    //         var demoText = $('#businessLicenseResult');
    //         demoText.html('<span style="color: #FF5722;">上传失败</span> <a class="layui-btn layui-btn-mini demo-reload">重试</a>');
    //         demoText.find('.demo-reload').on('click', function(){
    //             uploadInst.upload();
    //         });
    //     }
    // });
    //
    //
    // var taxCertificateBtn = upload.render({
    //     elem: '.taxCertificateBtn'
    //     ,url: localhostPaht+'/upload/image/TAX_CERTIFICATE?userName=' + $(".username").val()
    //     ,before: function(obj){
    //         //预读本地文件示例，不支持ie8
    //         obj.preview(function(index, file, result){
    //             $('#taxCertificate').attr('src', result); //图片链接（base64）
    //         });
    //     }
    //     ,done: function(res){
    //         //如果上传失败
    //         if(res.code > 0){
    //             return layer.msg('上传失败');
    //         }
    //         //上传成功
    //     }
    //     ,error: function(){
    //         //演示失败状态，并实现重传
    //         var demoText = $('#taxCertificateResult');
    //         demoText.html('<span style="color: #FF5722;">上传失败</span> <a class="layui-btn layui-btn-mini taxCertificate-reload">重试</a>');
    //         demoText.find('.taxCertificate').on('click', function(){
    //             taxCertificateBtn.upload();
    //         });
    //     }
    // });
    //
    //
    // var organizationCertificateBtn = upload.render({
    //     elem: '.organizationCertificateBtn'
    //     ,url: localhostPaht+'/upload/image/ORGANIZATION_CERTIFICATE?userName=' + $(".username").val()
    //     ,before: function(obj){
    //         //预读本地文件示例，不支持ie8
    //         obj.preview(function(index, file, result){
    //             $('#organizationCertificate').attr('src', result); //图片链接（base64）
    //         });
    //     }
    //     ,done: function(res){
    //         //如果上传失败
    //         if(res.code > 0){
    //             return layer.msg('上传失败');
    //         }
    //         //上传成功
    //     }
    //     ,error: function(){
    //         //演示失败状态，并实现重传
    //         var demoText = $('#organizationCertificateResult');
    //         demoText.html('<span style="color: #FF5722;">上传失败</span> <a class="layui-btn layui-btn-mini taxCertificate-reload">重试</a>');
    //         demoText.find('.taxCertificate').on('click', function(){
    //             organizationCertificateBtn.upload();
    //         });
    //     }
    // });
    //上面是图片上传模块//


    //图片预览模块
    window.myShowImg = function(img){
        var imgHtml = "<img src='" + img.currentSrc + "' />";
        var myImg = new Image();
        myImg.src = img.currentSrc;
        var imgWidth = null;
        var imgHeigh = null;
        var windowH = $(window).height();
        var windowW = $(window).width();
        // 如果图片被缓存，则直接返回缓存数据
        if(myImg.complete){
            imgWidth = myImg.width;
            imgHeigh =  myImg.height;
        }else{
            // 完全加载完毕的事件
            myImg.onload = function(){
                imgWidth = myImg.width;
                imgHeigh =  myImg.height;
            }
        }

        if(imgWidth > windowW){
            imgWidth = windowW;
        }
        if(imgHeigh > windowH){
            imgHeigh = windowH + 50;
        }

        layer.open({
            type: 1,
            shade: false,
            title: false, //不显示标题
            // area:['600px','500px'],
            area: [imgWidth + 'px', imgHeigh+'px'],
            content: imgHtml, //捕获的元素，注意：最好该指定的元素要存放在body最外层，否则可能被其它的相对元素所影响
            cancel: function () {
                //layer.msg('捕获就是从页面已经存在的元素上，包裹layer的结构', { time: 5000, icon: 6 });
            }
        });
    }



    //添加验证规则
    form.verify({
        userBirthday : function(value){
            if(!/^(\d{4})[\u4e00-\u9fa5]|[-\/](\d{1}|0\d{1}|1[0-2])([\u4e00-\u9fa5]|[-\/](\d{1}|0\d{1}|[1-2][0-9]|3[0-1]))*$/.test(value)){
                return "出生日期格式不正确！";
            }
        }
    })
    //选择出生日期
    laydate.render({
        elem: '.userBirthday',
        format: 'yyyy年MM月dd日',
        trigger: 'click',
        max : 0,
        mark : {"0-12-15":"生日"},
        done: function(value, date){
            if(date.month === 12 && date.date === 15){ //点击每年12月15日，弹出提示语
                layer.msg('今天是马哥的生日，也是layuicms2.0的发布日，快来送上祝福吧！');
            }
        }
    });

    //获取省信息
    address.provinces();

    //提交个人资料
    form.on("submit(addUser)",function(data){
        var index = layer.msg('提交中，请稍候',{icon: 16,time:false,shade:0.8});
        var username = JSON.parse(localStorage.getItem("user")).username;
        if(!username){
            layer.msg("您没有权限,进行此操作",{
                time:1500
            });
            return;
        }
        var data = '';
        data = {
            'userid' : $(".userid").val(),
            'username' : $(".username").val(),
            'nickname' : $(".nickname").val(),
            'email' : $(".email").val(),
            'businessName' : $(".businessName").val(),
            'businessPhone' : $(".businessPhone").val(),
            'financeName' : $(".financeName").val(),
            'financeEmail' : $(".financeEmail").val(),
            'financePhone' : $(".financePhone").val(),
            'backAddress' : $(".backAddress").val(),
            'backPhone' : $(".backPhone").val(),
            'bankName' : $(".bankName").val(),
            'bankAccount' : $(".bankAccount").val(),
            'lastOperator' : username,
        };
        $.ajax({
            type: "POST",
            url: localhostPaht + "/user" + "/updateSupplier",
            data:data,
            dataType: "json",
            success: function(data){
                if(data == true){
                    layer.close(index);
                    layer.msg("修改成功！",{
                        time:2000
                    });
                    setTimeout(function(){
                        layui.layer.tips('点击此处返回账户列表', '.layui-layer-setwin .layui-layer-close', {
                            tips: 3
                        });
                    },1000);
                }else{
                    layer.close(index);
                    layer.msg("修改失败,请检查!",{
                        time:2000
                    });
                }
            }
        });
        // window.sessionStorage.setItem("userInfo",JSON.stringify(userInfoHtml));设置缓存
        return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
    })

})