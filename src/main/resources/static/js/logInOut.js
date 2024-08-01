function updatePass(event){
	const newInput = '<input type="password" name="newPass" placeholder="New Password">';
	const newPass = document.querySelector(".form button");
	if(newPass.childElementCount == 0) {
		newPass.insertAdjacentHTML("beforebegin", newInput);
		event.target.remove();
	}
}

function logout(){
	document.logoutform.submit();
}