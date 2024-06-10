<?php
include_once"conn.php";
$user_name=$_GET['u_name'];
$user_password=$_GET['u_password'];
$qry="insert into employee_data(username,password) values('$user_name','$user_password')";
$result=mysqli_query($conn,$qry);
if($result){
	echo "registered";
}
else{
	echo "not registered";
}
?>