<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Index</title>

<!-- Call favicon for page-->
<link rel="icon" href="images/ico/fb.png" type="image/x-icon" />

<!-- Call css for page -->
<link rel="stylesheet" type="text/css" href="css/styletest.css">

<!-- Other -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- Call favicon for page-->
<link rel="icon" href="images/ico/logo.png" type="image/x-icon" />

</head>
<body>
	<div id="maincontainer">

		<div id="headlogin">
			<div id="header">
				<div id="leftheaderbox">
					<a href="home.jsp"><img alt="logo"
						src="images/login/fblogo.png"></a>
				</div>
				<div id="rightheaderbox">
					<form action="LogSignIn" method="post">
						<input type="text" name="email" class="loginparam"
							placeholder="email"> <input type="password"
							name="password" class="loginparam" placeholder="password">
						<input type="submit" value="Log In" id="loginbutton"> <input
							type="hidden" name="type" value="log">

					</form><br>
					<a>${msglog }</a>
				</div>
			</div>

		</div>
		<div id="contentcontainer">
			<div id="content">
				<div class="contentloginpage">
					<h2>Connect with friends and the world around you on
						Flezbooks.</h2>
				</div>
				<div class="contentloginpage">
					<h1>Sign Up</h1>
					<h3>It's free and always will be.</h3>
					<form action="LogSignIn" method="post">
						<input type="text" name="firstname" class="signupfield"
							id="firstname" placeholder="First name"> <input
							type="text" name="lastname" class="signupfield"
							placeholder="Last name"> <input type="text" name="email"
							class="signupfield" placeholder="Email"> <input
							type="text" name="reenteremail" class="signupfield"
							placeholder="Re-enter email"> <input type="password"
							name="newpassword" class="signupfield" placeholder="New password">
						<input type="hidden" name="type" value="sign">

						<fieldset>
							<legend> &nbsp Sex &nbsp</legend>
							<input name="sex" value="male" type="radio">Male <input
								name="sex" value="female" type="radio">Female
						</fieldset>

						<fieldset>
							<legend>&nbsp Birdthday &nbsp</legend>
							<input type="date" name="birdthday">
						</fieldset>

						<input type="submit" value="Sign Up" id="signupbutton">
					</form><br>
					<p align="center">${msgsign}</p>
				</div>

			</div>
		</div>
	</div>

</body>
</html>