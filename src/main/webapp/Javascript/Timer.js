function login() {
	var xhr = new XMLHttpRequest();
	var mail = document.getElementById("mailId").value;
	var pass = document.getElementById("Password").value;
//	var mailformat = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
	
	if(mail.length == 0)
	{
		document.getElementById("loginResult").innerHTML = "<p style = 'color : red'>*Mail ID field should not be empty</p>";
	}
	
	else if(pass.length ==0){
		document.getElementById("loginResult").innerHTML = "<p style = 'color : red'>*Password field should not be empty</p>";
	}
	
//	else if(!mail.match(mailformat))
//	{
//		document.getElementById("loginResult").innerHTML = "<p style = 'color : red'>*Invalid Mail Id format</p>";
//	}
	else{	
	var logDetails = {
		mailId : mail,
		password : pass
	};

	var jsonLogDetails = JSON.stringify(logDetails);

	xhr.open('POST', '/login', true);
	xhr.setRequestHeader('Content-Type', 'application/json');
	xhr.send(jsonLogDetails);

	xhr.onreadystatechange = function() {

		if (this.readyState == 4 && this.status == 400) {
			document.getElementById("loginResult").innerHTML = this.responseText;
		}

		if (this.readyState == 4 && this.status == 200) {
			window.location.href = "/Dashboard";
		}
	};
}
}

function signUp() {
	var xhr = new XMLHttpRequest();

	var name = document.getElementById("userName").value;
	var mail = document.getElementById("mailId").value;
	var pass = document.getElementById("password").value;
	var mailformat = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;

	if(name.length == 0)
	{
		document.getElementById("signupResult").innerHTML = "<p style = 'color : red'>*Username field should not be empty</p>";
	}
	
	else if(mail.length == 0)
	{
		document.getElementById("signupResult").innerHTML = "<p style = 'color : red'>*Mail ID field should not be empty</p>";
	}
	
	else if(pass.length ==0){
		document.getElementById("signupResult").innerHTML = "<p style = 'color : red'>*Password field should not be empty</p>";
	}
	
	else if(pass.length <8){
		document.getElementById("signupResult").innerHTML = "<p style = 'color : red'>*Password should have at least 8 Characters</p>";
	}
	
	else if(!(mail.match(mailformat)))
	{
		document.getElementById("signupResult").innerHTML = "<p style = 'color : red'>*Invalid Mail format</p>";
	}
	
	
	else{	

	var logDetails = {
		userName : name,
		mailId : mail,
		password : pass
	};

	var jsonLogDetails = JSON.stringify(logDetails);

	xhr.open('POST', '/SignUp', true);
	xhr.setRequestHeader('Content-Type', 'application/json');
	xhr.send(jsonLogDetails);

	xhr.onreadystatechange = function() {

		if (this.readyState == 4 && this.status == 400) {
			if (this.response == null) {
				document.getElementById("signupResult").innerHTML = "User mail already exists";
			} else {
				console.log("invoked");
				document.getElementById("signupResult").innerHTML = this.response;
			}
		}
		if (this.readyState == 4 && this.status == 200) {
			window.location.replace("/Login.html");
			document.getElementById("loginResult").innerHTML = this.responseText;
			//    	document.getElementById("message").innerHTML = this.responseText; 
		}
	};
}

// Login Page Functionalities

var x;
var onOff = 0;

var sec = 0;
var min = 0;
var hour = 0;

var secOut = 0;
var minOut = 0;
var hourOut = 0;

//var time = new Date();
//
//var curHour = time.getHours()<10 ?"0" + time.getHours() : time.getHours();
//var curMin = time.getMinutes()<10 ?"0" + time.getMinutes() : time.getMinutes();
//var curSec = time.getSeconds()<10 ?"0" + time.getSeconds() : time.getSeconds();
//
//var currentTime = curHour + ":" + curMin + ":" + curSec;

var myTable = document.getElementById("clockTable");
var row;
var cell1;
var cell2;
var cell3;
var cell4;
var cell5;

var startTime;
var endTime;

function set() {

	//	var tSwitcher = document.getElementById('check');
	//	let element = document.getElementById('check');
	//	element.classList.add(onpageLoad);
	//
	//	  if(tSwitcher.checked){
	//	      localStorage.setItem('theme', 'dark-mode');
	//	        element.classList.add('dark-mode');
	//	    } else {
	//	      localStorage.setItem('theme', '');
	//	        element.classList.remove('dark-mode');
	//	    }
	//	}	
	//	

	onOff = onOff + 1;
	if (onOff % 2 == 1) {
		if (onOff == 1) {
			var xhr = new XMLHttpRequest();

			startTime = Date.now();
			console.log(startTime);
			endTime = 0;

			xhr.open('GET', '/ClockIn', true);
			xhr.send();
			console.log("redirecting to start");
			start();

			xhr.onreadystatechange = function() {

				if (this.readyState == 4 && this.status == 400) {
					document.getElementById("message").innerHTML = this.response;
				}

				if (this.readyState == 4 && this.status == 200) {
					document.getElementById("timeStarted").innerHTML = this.response;
				}
			};

		}

		else {
			var xhr = new XMLHttpRequest();

			startTime = Date.now();
			console.log(startTime);
			endTime = 0;
			xhr.open('GET', '/ClockIn', true);
			xhr.send();
			console.log("redirecting to addStart");
			addStart();
			xhr.onreadystatechange = function() {

				if (this.readyState == 4 && this.status == 400) {
					document.getElementById("message").innerHTML = this.response;
				}

				if (this.readyState == 4 && this.status == 200) {
					cell3.innerHTML = this.response;
				}
			};
		}
	} else {
		if (onOff == 2) {
			var xhr = new XMLHttpRequest();

			endTime = Date.now();
			console.log(endTime);

			xhr.open('GET', '/ClockOut', true);
			xhr.send();
			console.log("redirecting to stop");
			stop();
			xhr.onreadystatechange = function() {
				if (this.readyState == 4 && this.status == 400) {
					document.getElementById("message").innerHTML = this.response;
				}

				if (this.readyState == 4 && this.status == 200) {
					document.getElementById("timeEnded").innerHTML = this.response;
				}
			};
		} else {
			var xhr = new XMLHttpRequest();

			endTime = Date.now();
			console.log(endTime);

			xhr.open('GET', '/ClockOut', true);
			xhr.send();
			console.log("redirecting to addStop");
			addStop();
			xhr.onreadystatechange = function() {
				if (this.readyState == 4 && this.status == 400) {
					document.getElementById("message").innerHTML = this.response;
				}

				if (this.readyState == 4 && this.status == 200) {
					cell4.innerHTML = this.response;
				}
			};
		}
	}

	function start() {

		console.log("function call start");
		x = setInterval(timer, 1000);
	}

	function timer() {

		secOut = check(sec);
		minOut = check(min);
		hourOut = check(hour);

		sec = ++sec;

		if (sec == 60) {
			min = ++min;
			sec = 0;
		}

		if (min == 60) {
			hour = ++hour;
			min = 0;
		}

		document.getElementById("totalTime").innerHTML = hourOut + "h "
				+ minOut + "m";
		document.getElementById("timer").innerHTML = hourOut + ":" + minOut
				+ ":" + secOut;
		//		document.getElementById("timeStarted").innerHTML = currentTime;
		//		document.getElementById("timeEnded").innerHTML = "Ongoing";
		console.log("hi");
	}

	function check(i) {
		if (i < 10) {
			i = "0" + i;
		}
		return i;

	}

	function stop() {
		clearInterval(x);
		//	document.getElementById("timeEnded").innerHTML = currentTime;
	}

	function addStart() {
		console.log("function call addStart");
		var myTable = document.getElementById("clockTable");
		row = myTable.insertRow(2);
		cell1 = row.insertCell(0);
		cell2 = row.insertCell(1);
		cell3 = row.insertCell(2);
		cell4 = row.insertCell(3);
		cell5 = row.insertCell(4);
		x = setInterval(timer2, 1000);
	}

	function timer2() {

		secOut = check(sec);
		minOut = check(min);
		hourOut = check(hour);

		sec = ++sec;

		if (sec == 60) {
			min = ++min;
			sec = 0;
		}

		if (min == 60) {
			hour = ++hour;
			min = 0;
		}

		cell1.innerHTML = "Add Task Description";
		cell2.innerHTML = "Project Working";
		//	cell3.innerHTML = currentTime;
		//	cell4.innerHTML = "Ongoing";
		cell5.innerHTML = hourOut + "h " + minOut + "m";

		document.getElementById("timer").innerHTML = hourOut + ":" + minOut
				+ ":" + secOut;
		console.log("hi");
	}

	function check(i) {
		if (i < 10) {
			i = "0" + i;
		}
		return i;

	}

	function addStop() {
		clearInterval(x);
		//		cell4.innerHTML = currentTime;
	}

}

function signOut() {
	var xhr = new XMLHttpRequest();

	xhr.open('GET', '/Signout', true);
	xhr.send();

	xhr.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
			//    	document.getElementById("loginResult").innerHTML = this.responseText;
			window.location.href = "/Login.html";
			//    	document.getElementById("message").innerHTML = this.responseText; 
		}
	};
}
}