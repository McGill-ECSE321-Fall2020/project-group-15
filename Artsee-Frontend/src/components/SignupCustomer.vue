<template>
    <div>
        <head>
            <title>Sign Up Page</title>
        </head>
        <body>
            <div class="card-container">
                <div class="card w-50">
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
                                <input class="form-control" type="tel" id="example-tel-input" placeholder="1-(555)-555-5555">
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
                                <router-link to="/signup">
                                    <div>
                                        <button type="submit" class="btn btn-primary">Back</button>
                                    </div>
                                </router-link>
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
    export default {
        data () {
            return {
                firstName: '',
                lastName: '',
                email: '',
                username: '',
                password: '',
                reenterPassword: '',
                phoneNumber: 0,
                addressLine1: '',
                addressLine2: '',
                city: '',
                province: '',
                postalCode: '',
                country: '',
            }
        },

        methods: {

        }
    }
</script>

<style>
    body {
        background-image: linear-gradient(to right, #5160a0, #9e9e9e);
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