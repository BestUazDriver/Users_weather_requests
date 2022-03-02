<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Surveys page</title>
</head>
<body>
<table>
    <tr>
        <th>Id</th>
        <th>User key</th>
        <th>City</th>
    </tr>
    <#list requests as request>
        <tr>
            <td>${request.id}</td>
            <td>${request.userId}</td>
            <td>${request.city}</td>
        </tr>
    </#list>
</table>
</body>