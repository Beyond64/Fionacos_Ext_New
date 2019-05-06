<?php
    $conn = mysqli_connect('localhost','root','123456','jd','3306')or die('数据库链接失败：'.mysql_error());
    $sql = 'select o.order_num,o.shop_name,o.order_url,o.price,o.payment_mode,o.submit_time,o.order_state,'
            .'p.product_name,p.product_url,p.product_img from jd_orders o,jd_orders_product_detail d,jd_products p'
            .' where o.order_id = d.order_id and p.product_id = d.product_id and o.user_name ="aaa"';

    //解决中文乱码
     mysqli_query($conn,'SET NAMES utf8');

     $result = mysqli_query($conn,$sql);

     $arr = array();
     while($row = mysqli_fetch_assoc($result)){
        array_push($arr,$row);
     }

     $json_result = json_encode($arr);
//     var_dump($json_result);

    mysqli_close($conn);

    echo $json_result;

    /*
    $sql = 'select * from jd_orders where user_name = "aaa"';
    mysqli_query($conn,'SET NAMES utf8');
    $result = mysqli_query($conn,$sql);

    $orders = array();
    while($row = mysqli_fetch_assoc($result)){
        $orderid = $row['order_id'];
        array_push($orders,$orderid);

        $sql = 'select p.product_name,p.product_url,p.product_img '
        .'from jd_order_product_detail d,jd_products p'
        .'where d.product_id = p.product_id and d.order_id ='.$orderid;
        mysqli_query($conn,'SET NAMES utf8');
        $result = mysqli_query($conn,$sql);
    }
    */
?>