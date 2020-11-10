<!DOCTYPE html>
<template>
    <div>
        <head>
        <title>Login Page</title>
        </head>
        <body>
            <div class="card-container">
                <div class="card w-25">
                    <div class="card-body">
                        <div class="image-container">
                            <img class="image-style" src="@/assets/logo.png">
                        </div>
                        <div class="text-container">
                            <h1>Welcome to ArtSee</h1>
                        </div>
                        <form>
                            <div class="form-group">
                                <input type="username" class="form-control" v-model="username" placeholder="Username">
                            </div>
                            <div class="form-group">
                                <input type="password" class="form-control" v-model="password" placeholder="Password">
                            </div>
                            <div id="btn-container">
                                <div>
                                    <div class="login-btn-container">
                                        <button type="submit" class="btn btn-success" v-bind:disabled="!password" @click="submitLogin(username, password)">Login</button>
                                    </div>
                                    <p class="login_error" v-if="loginError">Sorry, your username or password was incorrect.</p>
                                </div>
                                <div class="signup-btn-container">
                                    <button type="submit" class="btn btn-light signup-btn-style" @click="throwLoginError()">
                                        Create your Account 
                                        <svg width="2em" height="2em" viewBox="0 0 16 16" class="bi bi-arrow-right-short" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                                            <path fill-rule="evenodd" d="M4 8a.5.5 0 0 1 .5-.5h5.793L8.146 5.354a.5.5 0 1 1 .708-.708l3 3a.5.5 0 0 1 0 .708l-3 3a.5.5 0 0 1-.708-.708L10.293 8.5H4.5A.5.5 0 0 1 4 8z"/>
                                        </svg>
                                    </button>
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
    function LoginDto(username, password) {
        this.username = username
        this.password = password
        this.success = false
    }

    export default {
        name: 'login',
        data () {
            return {
                username: '',
                password: '',
                loginDto: '',
                response: [],
                loginError: false
            }
        },

        methods: {
            submitLogin: function(userName, passWord) {
                this.loginError = false
                var l = new LoginDto(userName, passWord)
                this.loginDto = l
                this.username = ''
                this.password = ''
            },
            throwLoginError: function() {
                this.loginError = true
                this.username = ''
                this.password = ''
            }
        }
    }
</script>
<style>
    body {
        background-image: linear-gradient(to right, #BC54C2, #5056CD);
    }
    .image-style {
        width: 60%;
        margin: 10px 0 50px 0
    }
    .image-container {
        width: 100%;
        display: flex;
        justify-content: center;
    }
    .card-container {
        display: flex;
        justify-content: center;
    }
    #btn-container {
        display: flex;
        flex-direction: column;
        justify-content: space-between;
        height: 300px;
    }
    .login-btn-container{
        display: flex;
        justify-content: center;
        margin: 30px 0 10px 0;
    }
    .signup-btn-container{
        display: flex;
        justify-content: center;
        margin-bottom: 15px;
    }
    .signup-btn-style {
        color: #037BFF;
    }
    .signup-btn-style:hover {
        color: #037BFF;
    }
    .text-container {
        display: flex;
        justify-content: center;
    }
    button {
        height: 40px;
        width: 350px;
    }
</style>

