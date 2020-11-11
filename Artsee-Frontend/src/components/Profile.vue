<template>
    <div>
        <head>
        <title>Update Profile</title>
        </head>
        <body>
            <div id="navbarContainer">
                <Navbar navMode="true"/>
            </div>
            <div class="card-container">
                <div class="card w-25">
                    <div class="card-body">
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
                                <input type="text" class="form-control" aria-describedby="emailHelp" placeholder="Username">
                            </div>
                            <div class="form-group">
                                <input type="password" class="form-control" id="exampleInputPassword1" placeholder="Password">
                            </div>
                            <div class="form-group">
                                <input type="password" class="form-control" id="exampleInputPassword1" placeholder="Re-enter Password">
                            </div>
                            <div class="form-group">
                                <input type="text" class="form-control" placeholder="Phone number">
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
                                    <button type="submit" class="btn btn-primary">Update</button>
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
import Navbar from '@/components/Navbar'

export default {
    components: {
        Navbar
    }
}
    
</script>

<style>
    body {
      margin: 0;
      padding: 0 0 0 0;
    }
    .card-container {
        display: flex;
        justify-content: center;
        align-items: center;
    }
    .row {
        margin-bottom: 10px;
    }
    .navbar {
      margin-bottom: 50px;
    }
    .logo {
        width: 60%;
        height: auto;
        margin: 10px 0 25px 0
    }
    .button-container {
      width: 100%;
    }
    .image-container {
        width: 25%;
        height: 10%;
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

    #navbarContainer {
        margin-bottom: 100px;
    }
</style>