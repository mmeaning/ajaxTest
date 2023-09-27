<?php
header('Access-Control-Allow-Origin: *');
echo json_encode(array('result'=>true, 'msg'=>$_REQUEST['msg'])); 
?>