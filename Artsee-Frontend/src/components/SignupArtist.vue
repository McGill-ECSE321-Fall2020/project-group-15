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
                                <input class="form-control" v-model="userID" placeholder="Username">
                            </div>
                            <div class="form-group">
                                <input type="password" class="form-control" v-model="password" placeholder="Password" @change="checkPasswordMatch()">
                            </div>
                            <div class="form-group">
                                <input type="password" class="form-control" v-model="reenterPassword" placeholder="Re-enter Password" @change="checkPasswordMatch()">
                            </div>
                            <div class="form-group">
                                <input class="form-control" type="tel" v-model="phoneNumber" placeholder="(123) 456-7890" @change="checkPhoneNumber()">
                            </div>
                            <div class="form-group">
                                <input type="text" class="form-control" v-model="profilePictureURL" placeholder="Insert your profile picture url">
                            </div>
                            <div class="form-group">
                                <textarea class="form-control" v-model="artistDescription" rows="3" placeholder="Tell us about yourself..."></textarea>
                            </div>
                            <div class="button-container">
                                <router-link to="/signup">
                                    <div>
                                        <button type="submit" class="btn btn-primary">Back</button>
                                    </div>
                                </router-link>
                                <div>
                                    <button type="submit" class="btn btn-primary" @click="createArtist()">Submit</button>
                                </div>
                            </div>
                            <div v-for="(errorMsg, index) in error" :key="index">
                                <p class="login-error-style" >{{errorMsg}}</p>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </body>
    </div>
</template>

<script>
    import axios from 'axios'
    var config = require('../../config')

    var backendConfigurer = function(){
    switch(process.env.NODE_ENV){
        case 'development':
            return 'http://' + config.dev.backendHost + ':' + config.dev.backendPort;
        case 'production':
            return 'https://' + config.build.backendHost + ':' + config.build.backendPort ;
    }
    };

    var frontendConfigurer = function(){
    switch(process.env.NODE_ENV){
        case 'development':
            return 'http://' + config.dev.host + ':' + config.dev.port;
        case 'production':
            return 'https://' + config.build.host + ':' + config.build.port ;
    }
    };
    var backendUrl = backendConfigurer();
    var frontendUrl = frontendConfigurer();

    var AXIOS = axios.create({
    baseURL: backendUrl,
    headers: { 'Access-Control-Allow-Origin': frontendUrl }
    })

    function ArtistDto(userID, email, password, firstName, lastName, phoneNumber, artistDescription, profilePictureURL) {
        this.userID = userID
        this.email = email
        this.password = password
        this.firstName = firstName
        this.lastName = lastName
        this.phoneNumber = phoneNumber
        this.artistDescription = artistDescription
        this.profilePictureURL = profilePictureURL
    }

    function checkError(userID, email, password, firstName, lastName, phoneNumber, passwordError, phoneNumberError){
        var errorMsg = ""
        if(!userID){
            errorMsg += "Username cannot be empty."
        }
        if(userID.indexOf(' ') >= 0){
            errorMsg += "Username cannot have white spaces."
        }
        if(!email){
            errorMsg += "Email cannot be empty."
        }
        if(email.indexOf(' ') >= 0){
            errorMsg += "Email cannot have white spaces."
        }
        if(email.indexOf('@') < 0){
            errorMsg += "Please include '@' in the email address."
        }
        if(!password){
            errorMsg += "Password cannot be empty."
        }
        if(!firstName){
            errorMsg += "First name cannot be empty."
        }
        if(!lastName){
            errorMsg += "Last Name cannot be empty."
        }
        if(!phoneNumber){
            errorMsg += "Phone number cannot be empty."
        }
        if(passwordError){
            errorMsg += "Passwords do not match."
        }
        if(phoneNumberError){
            errorMsg += "Invalid phone number."
        }
        return errorMsg
    }

    export default {
        data () {
            return {
                firstName: '',
                lastName: '',
                email: '',
                userID: '',
                password: '',
                reenterPassword: '',
                phoneNumber: '',
                artistDescription: '',
                profilePictureURL: '',
                error: [],
                artistDto: {}
            }
        },

        methods: {
            createArtist: function (){
                var error = checkError(this.userID, this.email, this.password, this.firstName, this.lastName, this.phoneNumber, this.passwordError, this.phoneNumberError)
                if(error == ""){
                    var artistDto = new ArtistDto(this.userID, this.email, this.password, this.firstName, this.lastName, this.phoneNumber, this.artistDescription, this.profilePictureURL);
                    this.artistDto = artistDto;
                    AXIOS.post('/artists', artistDto)
                        .then(response => {
                            window.location.replace("#/signup/thank-you");
                        })
                        .catch(e => {
                            var errorMsg = e.response.data
                            var errorParts = errorMsg.split(".")
                            errorParts.pop()
                            this.error = errorParts
                        })
                } else {
                    var errorParts = error.split(".")
                    errorParts.pop()
                    this.error = errorParts
                }
            },
            checkPasswordMatch: function() {
                if(this.password != this.reenterPassword){
                    var errorMsg = ["Passwords do not match"]
                    this.error = errorMsg
                    this.passwordError = true
                } else {
                    this.passwordError = false
                    if(this.phoneNumberError) {
                        var errorMsg = ["Invalid phone number"]
                        this.error = errorMsg
                    } else {
                        this.error = []
                    }
                }
            },
            checkPhoneNumber: function() {
                var isPhoneNumber = (/^[\+]?[(]?[0-9]{3}[)]?[-\s\.]?[0-9]{3}[-\s\.]?[0-9]{4,6}$/im.test(this.phoneNumber))
                if(!isPhoneNumber){
                    var errorMsg = ["Invalid phone number"]
                    this.error = errorMsg
                    this.phoneNumberError = true
                } else {
                    this.phoneNumberError = false
                    if(this.passwordError){
                        var errorMsg = ["Passwords do not match"]
                        this.error = errorMsg
                    } else {
                        this.error = []
                    }
                }
            }
        }
    }
</script>

<style scoped>

    .card-container {
        margin-top: 50px;
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