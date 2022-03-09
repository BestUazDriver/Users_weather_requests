<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
</head>
<body>
<h1>Account Page</h1>
<form action="/sign_in" method="post" id="signInForm">
    <input placeholder=" Name" name="name" id="name" type="text"/>
    <span id="name_fail" style="color: red"></span> <br>
    <br>
    <br>
    <input placeholder=" Email" name="email" id="email" type="text"/>
    <span id="email_fail" style="color: red"></span> <br>
    <br>
    <br>
    <input placeholder=" Password" name="password" id="password" type="text"/>
    <span id="password_fail" style="color: red"></span> <br>
    <br>
    <br>
    <input type="submit" value="Sign in"/>
</form>
</body>
</html>
