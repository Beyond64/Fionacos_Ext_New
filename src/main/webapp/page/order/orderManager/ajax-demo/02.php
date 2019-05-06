<?php
    $user = $_POST['user'];
    $pwd = $_POST["pwd"];

    // echo $user." successful";

    if($user=='admin' && $pwd=='admin'){
        echo 'successful';
    }else{
        echo 'error';
    }
?>
