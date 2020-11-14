<template>
  <section class="py-5" id="itemSection">
      <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.3/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-T8Gy5hrqNKT+hzMclPo118YTQO6cYprQmhrYwIiQ/3axmI1hQomh7Ud2hPOy8SP1" crossorigin="anonymous">
        <div class="container">
      <div class="row xl-6">
        <div class="col-md-8">
          <div class="card shadow">
            <div class="card-body">
              <div class="row text-center">
                <div class="col-ml-1 mx-2">
                  <img v-if = "imageURL" :src= "imageURL" width="100" alt="" loading="lazy">
                  <img v-else src= "@/assets/no-image.png" width="100" alt="" loading="lazy">
                </div>
                <div class="col-md-6" id="infoBox">
                  <h4>{{name}}</h4> <h6> {{artistName}} </h6>
                  <p>{{description}}</p>
                </div>
                <div class="col-md-3" id="priceButton">
                  <h3> {{"$" + (price/100).toString()}}</h3>
                  <p> </p>
                  <p> </p>
                  <div class="sub-row">
                    <button
                      type="button"
                      class="btn btn-warning btn-sm btn-block"
                      @click="$router.push({name: 'DetailedArtwork', params: {artworkID: artworkID }})">View Details
                    >
                    </button>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </section>
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

export default {

  props: {
    artworkID: {
      default: -1,
      type: Number
    }
  },
  name: "ArtworkRow",
  data() {
    return {
		      name : "NotFound",
          description: "NotFound",
          price: "0",
          dateOfCreation: "",
          numInStock: "",
          artistName: "NotFound",
          imageURL: "",
          artworkError: "",
    };
  },
  created: function () {
    console.log(this.artworkID)
    this.fetch()
  },

  methods: {
    fetch (){
      AXIOS.get('/artworks/' + this.artworkID.toString())
        .then(response => {
        // JSON responses are automatically parsed.
          this.name = response.data.name
          this.description = response.data.description
          this.price = response.data.price
          this.dateOfCreation = response.data.dateOfCreation
          this.numInStock = response.data.numInStock
          this.artistName = response.data.artist.firstName + " " + response.data.artist.lastName
          this.imageURL = response.data.imageURL.toString()
        })
        .catch(e => {
          var errorMsg = e.response
          this.artworkError = errorMsg
        })
    },
  }
};
</script>

<style scoped>
.container {
    background: transparent;
    max-width: 1000px;
}

#itemSection {
    background-color: white;
}

#priceButton {
    margin-top: 20px;
}
</style>