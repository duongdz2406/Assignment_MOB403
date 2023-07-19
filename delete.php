<?php
if( !empty($_POST['id_product'])){
    $id_product = $_POST['id_product'] ;
 $con = mysqli_connect('localhost','root','','crud');
    if($con){
        $sql = "DELETE FROM products WHERE id_product=".$id_product;
        if(mysqli_query($con,$sql)){
            echo "Success";
        }else 
            echo "Failed to delete data" ;
    }else echo "Fail to connect to database";
}

   

?>