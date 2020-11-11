<template>
    <div>
        <head>
            <title>Sign Up Page</title>
        </head>
        <body>
            <div class="card-container">
                <div class="card w-25">
                    <div class="card-body">
                        <div class="image-container">
                            <img class="image-style" src="@/assets/logo.png">
                        </div>
                        <form>
                            <div class="row">
                                <div class="col">
                                    <input type="text" class="form-control" placeholder="First name">
                                </div>
                                <div class="col">
                                    <input type="text" class="form-control" placeholder="Last name">
                                </div>
                            </div>
                            <div class="form-group">
                                <input type="email" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="Email Address">
                            </div>
                            <div class="form-group">
                                <input type="email" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="Username">
                            </div>
                            <div class="form-group">
                                <input type="password" class="form-control" id="exampleInputPassword1" placeholder="Password">
                            </div>
                            <div class="form-group">
                                <input type="password" class="form-control" id="exampleInputPassword1" placeholder="Re-enter Password">
                            </div>
                            <div class="button-container">
                                <div>
                                    <button type="submit" class="btn btn-primary">Back</button>
                                </div>
                                <div>
                                    <button type="submit" class="btn btn-primary">Submit</button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </body>
    </div>
</template>

<script>
    
</script>

<style>
    body {
        background-image: linear-gradient(to right, #BC54C2, #5056CD);
    }
    .card-container {
        display: flex;
        justify-content: center;
        align-items: center;
        margin-bottom: 50px;
    }
    .row {
        margin-bottom: 10px;
    }
    .image-style {
        width: 60%;
        margin: 10px 0 25px 0
    }
    .image-container {
        width: 100%;
        display: flex;
        justify-content: center;
    }
    .signin-btn-style {
        color: #037BFF;
    }
    .signin-btn-style:hover {
        color: #037BFF;
    }
    .button-container {
        display: flex;
        justify-content: space-around;
    }
    button {
        height: 40px;
        width: 170px;
    }
</style>