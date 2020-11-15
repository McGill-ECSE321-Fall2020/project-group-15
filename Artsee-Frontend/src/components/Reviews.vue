<template>
    <div>
        <head>
        <title>Reviews</title>
        </head>
        <body>
            <div class="navbarContainer">
                <Navbar navMode="false" />
            </div>
            <main className="container">
              <ReviewRow v-for="(review, i) in reviews" v-bind:key="`review-${i}`" v-bind:reviewID = "review.reviewID" />
            </main>
        </body>
    </div>
</template>

<script>
import Navbar from '@/components/Navbar'
import ReviewRow from '@/components/ReviewRow'
import { mapGetters, mapActions} from 'vuex'
import axios from 'axios'

var config = require('../../config')

let frontendUrlConfig = function () {
    if (process.env.NODE_ENV === 'production') {
        return 'https://' + config.build.host + ':' + config.build.port
    }
    else {
        return 'http://' + config.dev.host + ':' + config.dev.port
    }
}
let backendUrlConfig = function () {
    if (process.env.NODE_ENV === 'production') {
        return 'https://' + config.build.backendHost + ':' + config.build.backendPort
    }
    else {
        return 'http://' + config.dev.backendHost + ':' + config.dev.backendPort
    }
}
var frontendUrl = frontendUrlConfig()
var backendUrl = backendUrlConfig()

var AXIOS = axios.create({
    baseURL: backendUrl,
    headers: { 'Access-Control-Allow-Origin': frontendUrl }
})

export default {
  name: 'reviews',
  computed: mapGetters(["userName","userType"]),
  components: {
    Navbar,
    ReviewRow
  },

  data () {
    return {
      reviews: [],
      errorReview: '',
    }
  },  

  created: function () {
      console.log(this.userName)
      this.fetch()
  },

  methods: {
    fetch (){
      if (this.userType == "Customer"){
        AXIOS.get('/reviewsByCustomer/' + this.userName.toString())
          .then(response => {
          // JSON responses are automatically parsed.
            this.reviews = response.data
          })
          .catch(e => {
            var errorMsg = e.response.data
            console.log(errorMsg)
            this.reviewError = errorMsg
          })
      }
      else{
        AXIOS.get('/reviewsOnArtist/' + this.userName.toString())
          .then(response => {
            this.reviews = response.data
          })
          .catch(e => {
            var errorMsg = e.response.data
            console.log(errorMsg)
            this.reviewError = errorMsg
          })
      }
    },
  }
}
</script>

<style>
 
 .navbarContainer {
   margin-bottom: 100px;
 }

 .container {
   display: flex;
   margin: 10 10 10 10;
 }

 .card {
   padding: 30 30 30 30;
   margin-bottom: 20px;
 }

 body {
   margin: 10 10 10 10;
 }

</style>