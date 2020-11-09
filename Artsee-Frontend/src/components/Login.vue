<!DOCTYPE html>
<template>
  <div>
    <head>
      <title>Login Page</title>
    </head>

    <body>
        <div class="theLogo">
            <img src="@/assets/logo.png">
        </div>
        <div class="loginFields">
            <h2 style="text-align: center"> Welcome! </h2>
            <p class="login_error" v-if="loginError"> Error! Wrong password and/or username </p>
            <table>
                <tr>
                    <td style="padding-top:15px">
                        <input type="text" v-model="username" placeholder="username">
                    </td>
                </tr>
                <tr>
                    <td style="padding-top:5px">
                        <input type="text" v-model="password" placeholder="password">
                    </td>
                </tr>
                <tr>
                    <td class="buttonWrapper" style="padding-top:30px">
                        <button v-bind:disabled="!password" @click="submitLogin(username, password)">Login</button>
                    </td>
                </tr>
                <tr>
                    <td class="buttonWrapper" style="padding-top:200px">
                        <button id="signup" @click="throwLoginError()">Signup</button>
                    </td>
                </tr>
            </table>
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
    height: 100%;
    background: linear-gradient(90deg, #727272 50%, #c2c2c2 50%);
}

.loginFields {
    position: absolute;
    top: 200px;
    right: 15%;
}

button {
    text-align: center;
    background: #008307;
    border-radius: 25px;
    border: 0px;
    height: 40px;
    width: 350px;
}

input {
    border-radius: 25px;
    background: #e7e7e7;
    text-align: center;
    border: 0px;
    height: 40px;
    width: 350px;
}

#signup {
    text-align: center;
    background: #c2c2c2;
    border-radius: 25px;
    border: 0px;
    height: 40px;
    width: 350px;
    color: blue;
}

.login_error {
    color: red;
    text-align: center;
}

.theLogo {
    position: absolute;
    top: 235px;
    right: 70%;
}
</style>