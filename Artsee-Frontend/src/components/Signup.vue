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
                            <button type="button" class="btn user-btn-style" @click="changeUser('Customer')" v-bind:class="{ active: isCustomer }">
                                <div class="btn-container">                                        
                                    <div class="image-profile-container">
                                        <img class="image-style" src="@/assets/customer.svg">
                                    </div>
                                    <p class="user-text-style">Customer</p>
                                </div>
                            </button>
                            <button type="button" class="btn user-btn-style" @click="changeUser('Artist')" v-bind:class="{ active: isArtist }">
                                <div class="btn-container">    
                                    <div class="image-profile-container">
                                        <img class="image-style" src="@/assets/artist.svg">
                                    </div>
                                    <p class="user-text-style">Artist</p>
                                </div>
                            </button>
                            <button type="button" class="btn user-btn-style" @click="changeUser('Administrator')" v-bind:class="{ active: isAdministrator }">
                                <div class="btn-container">    
                                    <div class="image-profile-container">
                                        <img class="image-style" src="@/assets/admin.svg">
                                    </div>
                                    <p class="user-text-style">Administrator</p>
                                </div>
                            </button>
                            <div class="button-container">
                                <div>
                                    <button type="submit" class="btn btn-light signin-btn-style navigation-btn">
                                        <svg width="2em" height="2em" viewBox="0 0 16 16" class="bi bi-arrow-left-short" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                                            <path fill-rule="evenodd" d="M12 8a.5.5 0 0 1-.5.5H5.707l2.147 2.146a.5.5 0 0 1-.708.708l-3-3a.5.5 0 0 1 0-.708l3-3a.5.5 0 1 1 .708.708L5.707 7.5H11.5a.5.5 0 0 1 .5.5z"/>
                                        </svg>
                                        Sign In Instead
                                    </button>
                                </div>
                                <div>
                                    <button type="submit" class="btn btn-primary navigation-btn" v-bind:disabled="!userType">Next</button>
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
                userType: '',
                isCustomer: false,
                isArtist: false,
                isAdministrator: false,
            }
        },

        methods: {
            changeUser: function(user) {
                this.userType = user
                if(user=='Customer'){
                    this.isCustomer = true;
                    this.isArtist = false;
                    this.isAdministrator = false;
                } else if(user=='Artist'){
                    this.isCustomer = false;
                    this.isArtist = true;
                    this.isAdministrator = false;
                } else if(user=='Administrator'){
                    this.isCustomer = false;
                    this.isArtist = false;
                    this.isAdministrator = true;
                }
            }
        }
    }
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
    .navigation-btn {
        height: 40px;
        width: 170px;
    }
    .image-profile-container {
        height: 60px;
        width: 60px;
    }
    .btn-container {
        display: flex;
    }
    .user-btn-style{
        display: flex;
        width: 300px;
        height: 80px;
        margin-bottom: 20px;
    }
    .user-text-style {
        font-size: 30px;
    }
    .active {
        border-color: lightblue;
    }
</style>