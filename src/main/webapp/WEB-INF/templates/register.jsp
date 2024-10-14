<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">
        <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
        <title>Registration-module</title>
    </head>
    <body style="background: url('<%= request.getContextPath() %>/images/VQC.jpg'); background-size:cover; background-attachment:fixed; ">
        <div class="container">
            <div class="row">
                <div class="col m6 offset-m3">
                    <div class="card">
                        <div class="card-content">
                            <h2 class="center-align">Register here</h2>
                            <h5 id="success-msg" class="center-align"></h5>
                        </div>
                        <div class="form center-align">
                            <form action="submitRegistrationDetails" id="registrationForm" method="post">
                                <input type = "text" name="userName" placeholder="Enter your name"/>
                                <input type = "password" name="password" placeholder="Enter your password"/>
                                <input type = "email" name="email" placeholder="Enter your email"/>
                                <button type = "submit" class="btn red">Submit</button>
                            </form>
                        </div>
                        <div class="loader center-align"style="margin-top:10px; display:none;">
                            <div class="preloader-wrapper big active">
                                <div class="spinner-layer spinner-blue-only">
                                    <div class="circle-clipper left">
                                        <div class="circle"></div>
                                    </div>
                                    <div class="gap-patch">
                                        <div class="circle"></div>
                                    </div>
                                    <div class="circle-clipper right">
                                        <div class="circle"></div>
                                    </div>
                                </div>
                            </div>
                            <div>
                                 <h5>Please wait ... </h5>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <script src="https://code.jquery.com/jquery-3.7.1.min.js"
        integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo="
        crossorigin="anonymous"></script>
        <script>
            $(document).ready(function() {
                console.log("Page is ready!");

                $("#registrationForm").on("submit", function(event) {
                    event.preventDefault();
                    // Serializes into string with key-value pairs
                    var f = $(this).serialize();
                    console.log(f);

                    $(".loader").show();
                    $(".form").hide();

                    $.ajax({
                        url: "submitRegistrationDetails",
                        data: f,
                        type: "POST",
                        success: function(data, textStatus, jqXHR) {
                            console.log(data);
                            console.log("Inside success Ajax method");
                            $(".loader").hide();
                            $(".form").show();
                            if(data.trim() === 'success'){
                                $('#success-msg').html("Successfully registered");
                                $('#success-msg').addClass('green-text');
                            }else{
                                $('#success-msg').html("Something went wrong try again");
                                $('#success-msg').addClass('red-text');
                            }
                        },
                        error: function(jqXHR, textStatus, errorThrown) {
                            console.log(jqXHR.responseText); // Corrected to log the error response
                            console.log("Inside error Ajax method");
                            $(".loader").hide();
                            $(".form").show();
                        }
                    });
                });
            });
        </script>
    </body>
</html>
