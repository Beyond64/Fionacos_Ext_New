<?php
    $user = $_POST['user'];

    $json_user = json_decode($user,true);
    //json_decode 第二个参数传入true表示是否转换成数组
    // var_dump($json_user['pwd']);


    // 1、手工构建json
    // echo '{"a":"1","b":"2"}';

    // 2、使用json_encode()函数
    echo json_encode($json_user);
?>
