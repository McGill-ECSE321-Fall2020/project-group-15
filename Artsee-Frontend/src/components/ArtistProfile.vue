<template>
  <div>
    <head>
        <title>Artist Profile</title>
    </head>
    <body>
      <div class="navbarContainer">
        <Navbar navMode="true"/>
      </div>
      <div class="row py-5 px-4">
        <div class="col-md-9 mxa-auto">
            <!-- Profile widget -->
            <div class="bg-white shadow rounded overflow-hidden">
                <div class="px-4 pt-0 pb-4 cover">
                    <div class="media align-items-end profile-head">
                        <div class="profile mr-3">
                          <img v-if = "artist.profilePictureURL" :src= "artist.profilePictureURL" alt="..." width="130" class="rounded mb-2 img-thumbnail">
                          <img v-else src="https://images.unsplash.com/photo-1522075469751-3a6694fb2f61?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=500&q=80" alt="..." width="130" class="rounded mb-2 img-thumbnail">

                          <!-- Should be a vue-router and conditionally rendered depednding on artist or customer view
                          <a href="#" class="btn btn-outline-dark btn-sm btn-block">Edit profile</a> -->
                          
                        </div>
                        <div class="media-body mb-5 text-white">
                            <h4 class="mt-0 mb">{{ artist.firstName + " " + artist.lastName }}</h4>
                        </div>
                    </div>
                </div>
                <div class="bg-light p-4 d-flex justify-content-end text-center">
                    <ul class="list-inline mb-0">
                        <li class="list-inline-item">
                            <h5 class="font-weight-bold mb-0 d-block">{{ artworks.length }}</h5><small class="text-muted"> <i class="fas fa-image mr-1"></i>Artworks</small>
                        </li>
                        <li class="list-inline-item">
                            <h5 class="font-weight-bold mb-0 d-block">{{ artist.rating }}</h5><small class="text-muted"> <i class="fas fa-user mr-1"></i>Rating</small>
                        </li>
                    </ul>
                    <button
                      type="button"
                      class="btn btn-warning btn-sm btn-block"
                      id="view-more-button"
                      @click="$router.push({name: 'ReviewForm', params: {artistID: artistID },})">Add a Review
                    </button>
                </div>
                <div class="px-4 py-3">
                    <h5 class="mb-0">About</h5>
                    <div class="p-4 rounded shadow-sm bg-light">
                        <p class="font-italic mb-0">{{ artist.artistDescription }}</p>
                        <!-- <p class="font-italic mb-0">Lives in New York</p>
                        <p class="font-italic mb-0">Photographer</p> -->
                    </div>
                </div>
                <div class="py-4 px-4"> 
                    <div class="d-flex align-items-center justify-content-between mb-3">
                        <h5 class="mb-0">Artworks</h5>
                    </div>
                    <div class = "artworkRow">
                      <ArtworkRow 
                          v-for="(artwork, i) in artworks" v-bind:key="`artwork-${i}`" v-bind:artworkID = "artwork.id" />
                    </div>
                </div>
                <div class="py-4 px-4">
                    <div class="d-flex align-items-center justify-content-between mb-3">
                        <h5 class="mb-0">Reviews</h5>
                    </div>
                    <ReviewRow 
                        v-for="(review, i) in reviews" v-bind:key="`review-${i}`" v-bind:reviewID = "review.reviewID" />
                </div>
            </div>
        </div>
      </div>
    </body>
  </div>
</template>

<script>
import Navbar from '@/components/Navbar'
import ArtworkRow from '@/components/ArtworkRow'
import ReviewRow from '@/components/ReviewRow'
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
  //headers: { 'Access-Control-Allow-Origin': frontendUrl }
})

export default {
  components: {
    Navbar,
    ArtworkRow,
    ReviewRow
  },
  props: {
    artistID: {
      default: "NotReceived",
      type: String
    }
  },
  data () {
    return {
      artist: "",
      artworks: [],
      reviews: [],
      artistError: "",
      artworkError: "",
      reviewError: ""
    }
  },
  created: function () {
      console.log(this.artistID)
      this.fetch()
  },

  methods: {
    fetch (){
      AXIOS.get('/artists/' + this.artistID.toString())
        .then(response => {
        // JSON responses are automatically parsed.
          this.artist = response.data
        })
        .catch(e => {
          var errorMsg = e.response.data
          console.log(response.data)
          this.artistError = errorMsg
        })
      AXIOS.get('/artworksByArtist/' + this.artistID.toString())
        .then(response => {
        // JSON responses are automatically parsed.
          this.artworks = response.data
        })
        .catch(e => {
          var errorMsg = e.response.data
          console.log(errorMsg)
          this.artworkError = errorMsg
        })
      AXIOS.get('/reviewsOnArtist/' + this.artistID.toString())
        .then(response => {
        // JSON responses are automatically parsed.
          this.reviews = response.data
        })
        .catch(e => {
          var errorMsg = e.response.data
          console.log(errorMsg)
          this.reviewError = errorMsg
        })
    },
  }
}
</script>

<style scoped>

.profile-head {
    transform: translateY(5rem)
}

.cover {
    background-image: url(https://images.unsplash.com/photo-1530305408560-82d13781b33a?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1352&q=80);
    background-size: cover;
    background-repeat: no-repeat
}

.navbarContainer {
  margin-bottom: 80px;
}

.mb {
  margin-bottom: 20px;
}

/* .artworkRow{
  margin-left: auto;
  margin-right: auto;
} */

</style>