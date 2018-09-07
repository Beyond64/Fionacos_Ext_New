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
    var uploadInst = upload.render({
        elem: '.businessLicenseBtn'
        ,url: localhostPaht+ '/upload/image/BUSINESS_LICENSE?userName=' + $(".username").val()+ '&userId=' + $(".userid").val()
        ,before: function(obj){
            //预读本地文件示例，不支持ie8
            obj.preview(function(index, file, result){
                $('#businessLicense').attr('src', result); //图片链接（base64）
            });
        }
        ,done: function(res){
            //如果上传失败
            if(res.code > 0){
                return layer.msg('上传失败');
            }
            //上传成功
        }
        ,error: function(){
            //演示失败状态，并实现重传
            var demoText = $('#businessLicenseResult');
            demoText.html('<span style="color: #FF5722;">上传失败</span> <a class="layui-btn layui-btn-mini demo-reload">重试</a>');
            demoText.find('.demo-reload').on('click', function(){
                uploadInst.upload();
            });
        }
    });


    var taxCertificateBtn = upload.render({
        elem: '.taxCertificateBtn'
        ,url: localhostPaht + '/upload/image/TAX_CERTIFICATE?userName=' + $(".username").val() + '&userId=' + $(".userid").val()
        ,before: function(obj){
            //预读本地文件示例，不支持ie8
            obj.preview(function(index, file, result){
                $('#taxCertificate').attr('src', result); //图片链接（base64）
            });
        }
        ,done: function(res){
            //如果上传失败
            if(res.code > 0){
                return layer.msg('上传失败');
            }
            //上传成功
        }
        ,error: function(){
            //演示失败状态，并实现重传
            var demoText = $('#taxCertificateResult');
            demoText.html('<span style="color: #FF5722;">上传失败</span> <a class="layui-btn layui-btn-mini taxCertificate-reload">重试</a>');
            demoText.find('.taxCertificate').on('click', function(){
                taxCertificateBtn.upload();
            });
        }
    });


    var organizationCertificateBtn = upload.render({
        elem: '.organizationCertificateBtn'
        ,url: localhostPaht + '/upload/image/ORGANIZATION_CERTIFICATE?userName=' + $(".username").val()+ '&userId=' + $(".userid").val()
        ,before: function(obj){
            //预读本地文件示例，不支持ie8
            obj.preview(function(index, file, result){
                $('#organizationCertificate').attr('src', result); //图片链接（base64）
            });
        }
        ,done: function(res){
            //如果上传失败
            if(res.code > 0){
                return layer.msg('上传失败');
            }
            //上传成功
        }
        ,error: function(){
            //演示失败状态，并实现重传
            var demoText = $('#organizationCertificateResult');
            demoText.html('<span style="color: #FF5722;">上传失败</span> <a class="layui-btn layui-btn-mini taxCertificate-reload">重试</a>');
            demoText.find('.taxCertificate').on('click', function(){
                organizationCertificateBtn.upload();
            });
        }
    });



    //添加验证规则
    form.verify({
        userBirthday : function(value){
            if(!/^(\d{4})[\u4e00-\u9fa5]|[-\/](\d{1}|0\d{1}|1[0-2])([\u4e00-\u9fa5]|[-\/](\d{1}|0\d{1}|[1-2][0-9]|3[0-1]))*$/.test(value)){
                return "出生日期格式不正确！";
            }
        }
    })

    window.myShowImg = function(img){
        var imgHtml = "<img src='" + img.currentSrc + "' />";
        var myImg = new Image();
        myImg.src = img.currentSrc;
        var imgWidth = null;
        var imgHeigh = null;
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
    form.on("submit(changeUser)",function(data){
        var index = layer.msg('提交中，请稍候',{icon: 16,time:false,shade:0.8});
        //将填写的用户信息存到session以便下次调取
        var data = '';
        data = {
            'userid' : $(".userid").val(),
            'financeName' : $(".financeName").val(),
            'financeEmail' : $(".financeEmail").val(),
            'financePhone' : $(".financePhone").val(),
            'businessName' : $(".businessName").val(),
            'businessPhone' : $(".businessPhone").val(),
            'email' : $(".userEmail").val(),
            'backAddress' : $(".backAddress").val(),
            'backPhone' : $(".backPhone").val()
        };
        $.ajax({
            type: "POST",
            url: localhostPaht + "/user" + "/updateByUserId",
            data:data,
            dataType: "json",
            success: function(data){
                if(data == true){
                    layer.close(index);
                    layer.msg("修改成功！",{
                        time:2000
                    });
                    window.parent.location.reload();
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