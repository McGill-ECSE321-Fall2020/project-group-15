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
                                    <input type="text" class="form-control" v-model="firstName" placeholder="First name">
                                </div>
                                <div class="col">
                                    <input type="text" class="form-control" v-model="lastName" placeholder="Last name">
                                </div>
                            </div>
                            <div class="form-group">
                                <input type="email" class="form-control" v-model="email" aria-describedby="emailHelp" placeholder="Email Address">
                            </div>
                            <div class="form-group">
                                <input type="email" class="form-control" v-model="username" aria-describedby="emailHelp" placeholder="Username">
                            </div>
                            <div class="form-group">
                                <input type="password" class="form-control" v-model="password" placeholder="Password">
                            </div>
                            <div class="form-group">
                                <input type="password" class="form-control" v-model="reenterPassword" placeholder="Re-enter Password" @change="checkPasswordMatch()">
                            </div>
                            <div class="form-group">
                                <input class="form-control" type="tel" v-model="phoneNumber" placeholder="(123) 456-7890" @change="checkPhoneNumber()">
                            </div>
                            <div class="button-container">
                                <router-link to="/signup">
                                    <div>
                                        <button type="submit" class="btn btn-primary">Back</button>
                                    </div>
                                </router-link>
                                <div>
                                    <button type="submit" class="btn btn-primary" @click="submitSignup()">Submit</button>
                                </div>
                            </div>
                        <p class="login-error-style" v-if="error">{{error}}</p>
                        </form>
                    </div>
                </div>
            </div>
        </body>
    </div>
</template>

<script>
    function AdministratorDto(username, email, password, firstName, lastName, phoneNumber) {
        this.username = username
        this.email = email
        this.password = password
        this.firstName = firstName
        this.lastName = lastName
        this.phoneNumber = phoneNumber
    }

    export default {
        data () {
            return {
                firstName: '',
                lastName: '',
                email: '',
                username: '',
                password: '',
                reenterPassword: '',
                phoneNumber: '',
                passwordError: false,
                phoneNumberError: false,
                error: '',
            }
        },

        methods: {
            submitSignup: function() {

            },
            checkPasswordMatch: function() {
                if(this.password != this.reenterPassword){
                    this.error = "Passwords do not match"
                    this.passwordError = true
                    console.log(this.error)
                } else {
                    this.passwordError = false
                    if(this.phoneNumberError) {
                        this.error = "Invalid phone number"
                        console.log(this.error) 
                    } else {
                        this.error = ''
                    }
                }
            },
            checkPhoneNumber: function() {
                var isPhoneNumber = (/^[\+]?[(]?[0-9]{3}[)]?[-\s\.]?[0-9]{3}[-\s\.]?[0-9]{4,6}$/im.test(this.phoneNumber))
                if(!isPhoneNumber){
                    this.error = "Invalid phone number"
                    this.phoneNumberError = true
                    console.log(this.error)
                } else {
                    this.phoneNumberError = false
                    if(this.passwordError){
                        this.error = "Passwords do not match"
                    } else {
                        this.error = ''
                    }
                }
            }
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