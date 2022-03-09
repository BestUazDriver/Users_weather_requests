<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Sign Up</title>
</head>
<body>
<form method="post" action="/sign_up">
    <span style="color: red">${err_message}</span>
    <br>
    <input id="email" name="email" type="text" placeholder="login"/>
    <br>
    <br>
    <input type="password" id="password" name="password" placeholder="password"/>
    <br>
    <br>
    <input type="submit" value="sign up">
</form>
</body>
</html>