<?php
if(!empty($_POST['name_product'])){
    $name_product = $_POST['name_product'] ;
    $price_product = $_POST['price_product'] ;
    $img_product = $_POST['img_product'] ;
    $soluong_product = $_POST['soluong_product'] ;
    $categorie_product = $_POST['categorie_product'] ;
    $con = mysqli_connect('localhost','root','','crud');
    if($con){
        // if(isset($_POST['img_product'])){
        //     $target_dir = "Images/";
        //     $imageStore = rand()."_".time().".jpeg";
        //     $target_dir = $target_dir."/".$imageStore;
        //     file_put_contents($target_dir,base64_decode($img_product));
        //     $result = array();
            $sql = "insert into products (name_product,price_product,img_product,soluong_product,categorie_product)
            values ('".$name_product."' , '".$price_product."' , '".$img_product."' , '".$soluong_product."' , '".$categorie_product."') ";
            if(mysqli_query($con,$sql)){
                echo "Success";
            }else 
                echo "Failed to insert data" ;
        
       
    }
    else echo "Fail to connect to database";
}
   

?>