<template>
    <div>
        
        <head>
            <title>Review Form</title>
        </head>
        <body>
        <div id="navbarContainer">
            <Navbar navMode="false" />
        </div>
            <div class="card-container">
                <div class="card w-50">
                    <div class="card-body">
                        <form>
                            <div class="form-group">
                                <input type="text" class="form-control" v-model="comment"  rows="3" placeholder="Comment">
                            </div>
                            <div class="form-group">
                                <input type="number" class="form-control" v-model="rating" placeholder="Enter a rating between 1 and 5">
                            </div>
                            <div class="button-container">
                                <div>
                                    <button type="submit" class="btn btn-primary" @click="addReview($event)">Save</button>
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
    import Navbar from '@/components/Navbar'

    import { mapActions, mapGetters } from 'vuex';

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
    //headers: { 'Access-Control-Allow-Origin': frontendUrl }
    })

    function checkError(comment, rating){
        var errorMsg = ""
        if(!comment){
            errorMsg += "You need to add a comment."
        }
        if(!Number.isInteger(rating)){
            errorMsg += "The rating needs to a whole number."
        }
        if(rating < 1 || rating > 5){
            errorMsg += "The rating should be between 1 and 5."
        }
        return errorMsg
    }

    // function CustomerDto(userID) {
    //     userID: userID;
    // }
    // function ArtistDto(userID) {
    //     userID: userID;
    // }
    function ReviewDto(ArtistDto,CustomerDto,rating,comment) {
        this.rating = rating;
        this.wouldRecommend = true;
        this.comment = comment;
        this.artist = ArtistDto;
        this.customer = CustomerDto;
    }

    export default {
        name: "reviewForm",
          props: {
            artistID: {
                default: "notfound",
                type: String
        }
  },
    components: {
    Navbar
      },
      computed: mapGetters(['userType', 'userName', 'userData']),

        data () {
            return {
                comment: '',
                rating: 0,
                wouldRecommend: true,
                artist: {},
                customer: {},
                reviewDto:{},
                error: [],
            }
        },

  methods: {
    async addReview (event){
        console.log(this.userName)
        console.log(this.artistID)
        console.log(this.rating)
        console.log(this.comment)

        if (event) {
          event.preventDefault()
        }
        // var customerDto = new CustomerDto(this.userName)
        // var artistDto = new ArtistDto(this.artistID)
        var customerDto = {"userID": this.userName}
        var artistDto = {"userID": this.artistID}
        console.log(artistDto)
        console.log(customerDto)
        var reviewDto = new ReviewDto(artistDto,customerDto,this.rating,this.comment)
      await AXIOS.post('/reviews/', reviewDto)
        .then(response => {
        // JSON responses are automatically parsed.
          console.log(response.data)
        })
        .catch(e => {
          var errorMsg = e.response.data
          console.log(errorMsg)
          this.error = errorMsg
        })
    },
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
        margin-bottom: 20px;
    }
    button {
        height: 40px;
        width: 170px;
    }
    .login-error-style {
        color: red;
    }
    #navbarContainer {
        margin-bottom: 90px;
    }   
</style>