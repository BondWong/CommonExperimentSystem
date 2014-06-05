// JavaScript Document
var code ; //在全局 定义验证码    
function createCode(){
code = ""; 
var codeLength = 4;//验证码的长度    
var checkCode = document.getElementById("checkCode");
checkCode.value = "";
var selectChar = new Array(1,2,3,4,5,6,7,8,9,'a','b','c','d','e','f','g','h','j','k','l','m','n','p','q','r','s','t','u','v','w','x','y','z','A','B','C','D','E','F','G','H','J','K','L','M','N','P','Q','R','S','T','U','V','W','X','Y','Z'); 

for(var i=0;i<codeLength;i++) {
	var charIndex = Math.floor(Math.random()*60);
	code +=selectChar[charIndex];
} 
if(code.length != codeLength){
	createCode();
}
checkCode.value = code;
}
function validate() { 
	//validate username
	var username = document.getElementById("id").value;
	if(username.length <=0){
		alert("请输入账号！");
		return false;
	}
	if(isNaN(username)){
		alert("请输入正确账号！");
		return false;
	}
	//validate password
	var password = document.getElementById("password").value;
	if(password.length <=0){
		alert("请输入密码！");
		return false;
	}
	
	//validate img code
	var inputCode = document.getElementById("input1").value.toUpperCase();
	var codeToUp=code.toUpperCase();
	if(inputCode.length <=0) {
		alert("请输入验证码！");
		return false;
	}
	else if(inputCode != codeToUp ){
		alert("验证码输入错误！");
		createCode();
		return false; 
}
	else {
		return true;
	}
	
}
function courseValidate(name,classTime,major,duration,description){
	if(name.length <= 0){
		alert("课程名不能为空！");
		return 0;
	}
	if(classTime.length <= 0){
		alert("上课时间不能为空！");
		return 0;
	}
	if(major.length <= 0){
		alert("专业不能为空！");
		return 0;
	}
	if(duration.length <= 0){
		alert("开课时间不能为空！");
		return 0;
	}
	if(description.length <= 0){
		alert("描述不能为空！");
		return 0;
	}
}
function courseEditValidate(name,classTime,duration){
	if(name.length <= 0){
		alert("课程名不能为空！");
		return 0;
	}
	if(classTime.length <= 0){
		alert("上课时间不能为空！");
		return 0;
	}
	if(duration.length <= 0){
		alert("开课时间不能为空！");
		return 0;
	}
}
function experimentValidate(name,type,duration,purpose,demand,description,courseName){
	if(name.length <= 0){
		alert("实验标题不能为空！");
		return 0;
	}
	if(type.length <= 0){
		alert("类型不能为空！");
		return 0;
	}
	if(duration.length <= 0){
		alert("截至时间不能为空！");
		return 0;
	}
	if(purpose.length <= 0){
		alert("实验目的不能为空！");
		return 0;
	}
	if(demand.length <= 0){
		alert("实验要求不能为空！");
		return 0;
	}
	if(description.length <= 0){
		alert("实验描述不能为空！");
		return 0;
	}
	
}
function userInfoValidate(){
	var userName = document.getElementById("name").value;
	if(userName.length <=0){
		alert("请输入姓名！");
		return false;
	}
	var oldPassword = document.getElementById("oldPassword").value;
	if(oldPassword.length <=0){
		alert("修改信息请输入密码！");
		return false;
	}
}