<!DOCTYPE html>
<template>
  <div>
    <head>
      <title>Login Page</title>
    </head>
    <body>
      <div class="card-container">
        <div class="image-container" id="login_logo">
            <img class="image-style" src="@/assets/logo.png" />
        </div>
        <div class="card w-50" id="loginCard">
          <div class="card-body">
            <div class="text-container">
              <h1>Login</h1>
            </div>
            <form>
              <div class="input-container">
                <div class="form-group">
                  <input
                    type="username"
                    class="form-control input-style LoginInput"
                    v-model="username"
                    placeholder="Username"
                  />
                </div>
                <div class="form-group">
                  <input
                    type="password"
                    class="form-control input-style"
                    v-model="password"
                    placeholder="Password"
                  />
                </div>
              </div>
              <div id="btn-container">
                <div>
                  <div class="login-btn-container">
                    <button
                      type="submit"
                      class="btn btn-success"
                      v-bind:disabled="!password"
                      @click="submitLogin(username, password)"
                    >
                      Login
                    </button>
                    <p class="login-error-style" v-if="loginError">
                      Sorry, your username or password was incorrect.
                    </p>
                  </div>
                </div>
                <div class="signup-btn-container">
                  <router-link to="/signup">
                    <button
                      type="submit"
                      class="btn btn-light signup-btn-style"
                      @click="throwLoginError()"
                      id="login_signup_button"
                    >
                      Create your Account
                      <svg
                        width="2em"
                        height="2em"
                        viewBox="0 0 16 16"
                        class="bi bi-arrow-right-short"
                        fill="currentColor"
                        xmlns="http://www.w3.org/2000/svg"
                      >
                        <path
                          fill-rule="evenodd"
                          d="M4 8a.5.5 0 0 1 .5-.5h5.793L8.146 5.354a.5.5 0 1 1 .708-.708l3 3a.5.5 0 0 1 0 .708l-3 3a.5.5 0 0 1-.708-.708L10.293 8.5H4.5A.5.5 0 0 1 4 8z"
                        />
                      </svg>
                    </button>
                  </router-link>
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
  this.username = username;
  this.password = password;
  this.success = false;
}

export default {
  name: "login",
  data() {
    return {
      username: "",
      password: "",
      loginDto: "",
      response: [],
      loginError: false,
    };
  },

  methods: {
    submitLogin: function (userName, passWord) {
      this.loginError = false;
      var l = new LoginDto(userName, passWord);
      this.loginDto = l;
      this.username = "";
      this.password = "";
    },
    throwLoginError: function () {
      this.loginError = !this.loginError;
      this.username = "";
      this.password = "";
    },
  },
};
</script>
<style>

#login_signup_button {
    background: white;
    border-width: 0;
}

#login_logo {
    width: 45%;
    position: absolute;
    left: 10px;
    top:250px;
}
#loginCard {
    position: absolute;
    right: 50px;
    padding-bottom: 400px;
    padding-top: 150px;
}  
body {
  background-image: linear-gradient(to right, #5160a0, #9e9e9e);
}
.image-style {
  width: 60%;
  margin: 10px 0 50px 0;
}
.image-container {
  width: 100%;
  display: flex;
  justify-content: center;
}
.card-container {
  display: flex;
  justify-content: center;
  margin-bottom: 50px;
}
#btn-container {
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  height: 300px;
}
.login-btn-container {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  margin: 30px 0 10px 0;
}
.signup-btn-container {
  display: flex;
  justify-content: center;
  margin-bottom: 15px;
}
.signup-btn-style {
  color: #037bff;
}
.signup-btn-style:hover {
  color: #037bff;
}
.text-container {
  display: flex;
  justify-content: center;
}
button {
  height: 40px;
  width: 250px;
}
.input-container {
  display: flex;
  justify-content: center;
  align-items: center;
  flex-direction: column;
}
.input-style {
  border-radius: 25px;
  width: 350px;
  height: 50px;
  margin: 5px;
}
.login-error-style {
  color: red;
  margin-top: 10px;
}

</style>

