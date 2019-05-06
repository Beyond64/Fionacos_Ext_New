<?php
    $name = $_GET['name'];
    $pwd = $_GET['pwd'];
    // var_dump($name);

    // 响应格式为HTML
    // echo "get success";

    // 响应格式为XML
    // header("Content-Type:text/xml");
    // echo '<user><name>harriet</name><pwd></pwd></user>';

    // 响应格式为JSON
    echo '{"name" : "harriet","pwd":"ljh"}';
?>
