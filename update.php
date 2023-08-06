<?php
if(!empty($_POST['name_product']) &&  !empty($_POST['id_product'])){
    $id_product = $_POST['id_product'] ;
    $name_product = $_POST['name_product'] ;
    $price_product = $_POST['price_product'] ;
    $img_product = $_POST['img_product'] ;
    $soluong_product = $_POST['soluong_product'] ;
    $categorie_product = $_POST['categorie_product'] ;
 $con = mysqli_connect('localhost','root','','crud');
    if($con){
        $sql = "UPDATE products set name_product = '".$name_product."', price_product = '".$price_product."',img_product = '".$img_product."',soluong_product = '".$soluong_product."',categorie_product = '".$categorie_product."' WHERE id_product=".$id_product;
                 
        if(mysqli_query($con,$sql)){
            echo "Success";
        }else
            echo "Update Failed";
    }else echo "Fail to connect to database";
}

   

?>