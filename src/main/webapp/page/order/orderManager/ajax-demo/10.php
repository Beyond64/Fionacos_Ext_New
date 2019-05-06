<?php
    $pro = $_POST['pro'];

    header('Content-Type:text/xml');

    switch($pro){
        case "山东省" :
        // 手工构建
            echo "<cities><city name='1' /><city name='2' /><city name='3' /></cities>";
            break;
        case "辽宁省" :
            echo "<cities><city name='4' /><city name='5' /><city name='6' /></cities>";
            break;
        case "吉林省" :
            echo "<cities><city name='7' /><city name='8' /><city name='9' /></cities>";
            break;
        case "广东省" :
            echo "<cities><city name='0' /><city name='0' /><city name='8' /></cities>";
            break;
    }
?>
