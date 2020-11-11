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
                            <div class="form-group">
                                <input type="text" class="form-control" placeholder="Address Line 1">
                            </div>
                            <div class="form-group">
                                <input type="text" class="form-control" placeholder="Address Line 2">
                            </div>
                            <div class="form-group">
                                <input type="text" class="form-control" placeholder="City">
                            </div>
                            <div class="form-group">
                                <input type="text" class="form-control" placeholder="Province">
                            </div>
                            <div class="form-group">
                                <input type="text" class="form-control" placeholder="Postal Code">
                            </div>
                            <div class="form-group">
                                <input type="text" class="form-control" placeholder="Country">
                            </div>
                            <div class="button-container">
                                <div>
                                    <button type="submit" class="btn btn-light signin-btn-style">
                                        <svg width="2em" height="2em" viewBox="0 0 16 16" class="bi bi-arrow-left-short" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                                            <path fill-rule="evenodd" d="M12 8a.5.5 0 0 1-.5.5H5.707l2.147 2.146a.5.5 0 0 1-.708.708l-3-3a.5.5 0 0 1 0-.708l3-3a.5.5 0 1 1 .708.708L5.707 7.5H11.5a.5.5 0 0 1 .5.5z"/>
                                        </svg>
                                        Sign In Instead
                                    </button>
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