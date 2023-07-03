<html>
    <head>
        <meta charset="ISO-8859-1">
        <title>Signup Page of Travel Gig</title>
        <script src="https://code.jquery.com/jquery-3.6.3.min.js" integrity="sha256-pvPw+upLPUjgMXY0G+8O0xUf+/Im1MZjXxxgOcBQBXU=" crossorigin="anonymous"></script>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <script src="./js/hotel.js"></script>
    </head>
    <body>
        <h1>Please Signup Below, and Welcome!</h1>

        <form>
            Enter your email here.<br>
            <input type="text" id="userEmail"></input><br><br>
            
            Enter your username here.<br>
            <input type="text" id="userName"></input><br><br>
            
            Enter your password here.<br>
            <input type="text" id = "userPassword"></input><br>
            <input class='btn btn-primary mt-5' type = 'submit' value='Signup' id="signUpButton"/>
        </form>

        <a href="/login">Go Back</a>
    </body>
</html>