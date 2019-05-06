<?php

    $state = $_REQUEST['state'];

    if($state == 1){
        echo '山东省 辽宁省 吉林省 广东省';
    }else{
        $province = $_POST['province'];

        switch ($province){
            case "山东省" :
                echo "1,2,3";
            break;
            case "辽宁省" :
                echo "4,5,6";
                break;
            case "吉林省" :
                echo "7,8,9";
                break;
            case "广东省" :
                echo "0,0,8";
                break;
        }
    }
?>
