<template>
    <body>
            <div class="navbarContainer">
                <Navbar navMode="false" />
            </div>
            <div class="card-container">
                <div class="card w-50">
                    <div class="card-body">
                      <div class="header-container">
                          <h3>Update Artwork Form</h3>
                        </div>
                        <form>
                            <div class="form-group add-artwork-container">
                                <input type="text" class="form-control" placeholder="Artwork Name" v-model="name">
                            </div>
                            <div class="form-group add-artwork-container">
                                <input type="text" class="form-control" placeholder="Description" v-model="description">
                            </div>
                            <div class="form-group add-artwork-container">
                                <input type="text" class="form-control" placeholder="imageURL" v-model="imageURL">
                            </div>
                            <div class="form-group add-artwork-container">
                                <div class="input-group">
                                    <div class="input-group-prepend">
                                        <span class="input-group-text">$</span>
                                    </div>
                                    <input type="text" class="form-control" aria-label="Amount (to the nearest dollar)" v-model="price">
                                    <div class="input-group-append">
                                        <span class="input-group-text">.00</span>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group add-artwork-container">
                                <input type="text" class="form-control" id="num" placeholder="Quantity" v-model="numInStock">
                            </div>
                            <div class="form-group date-container">
                                <h5 class="date-text-style">Date Created: </h5>
                                <input type="date" class="form-control" id="date" placeholder="Date of Creation" v-model="dateOfCreation">
                            </div>
                            <div class="button-container">
                                <div>
                                    <button type="submit" class="btn btn-primary" @click="updateArtwork($event)">Update Artwork</button>
                                </div>
                            </div>
                            <div v-if="error.length != 0">
                                <div v-for="(errorMsg, index) in error" :key="index" class="alert alert-danger alert-style" role="alert">
                                    {{errorMsg}}
                                </div>    
                            </div>
                        </form>
                    </div>
                </div>
            </div>
    </body>
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
  //headers: { 'Access-Control-Allow-Origin': frontendUrl }
})


function isNormalInteger(str) {
    str = str.trim();
    if (!str) {
        return false;
    }
    str = str.replace(/^0+/, "") || "0";
    var n = Math.floor(Number(str));
    return n !== Infinity && String(n) === str && n >= 0;
}

function ArtistDTO(userID){
    this.userID = userID
}

function ArtworkDTO(id, name, description, price, dateOfCreation, numInStock, artist, imageURL) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.price = price;
    this.dateOfCreation = dateOfCreation;
    this.numInStock = numInStock;
    this.artist = artist;
    this.imageURL = imageURL;
}

export default {

  props: {
    artworkID: {
      default: -1,
      type: Number
    }
  },
  name: "ArtworkRow",
    computed: mapGetters(['userName']),
  data() {
    return {
        id: "",
	    name : "NotFound",
        description: "NotFound",
        price: "0",
        dateOfCreation: "",
        numInStock: "",
        artistName: "NotFound",
        imageURL: "",
        artworkError: "",
        artist : {},
        userID: "",
        artworkDto : {},
        error: [],
    };
  },
  method: {
        async updateArtwork(event) {
            if (event) {
                event.preventDefault()
            }
            var error = this.checkError();
            if(error == ""){
                var artistDto = new ArtistDTO(this.userName);
                var artworkDto = new ArtworkDTO(this.artworkID.toString(), this.name, this.description, parseInt(this.price)*100, this.dateOfCreation, parseInt(this.numInStock), artistDto, this.imageURL)
                await AXIOS.put("/artworks", artworkDto)
                        .then(response => {
                            console.log(response)
                            window.location.replace("#/artwork/new/success");
                        })
                        .catch(e => {
                            console.log(e.response.data)
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
        checkError() {
            var error = "";
            if(!this.name){
                error += "Artwork name cannot be blank."
            }
            if(!this.price){
                error += "Artwork price cannot be blank."
            }
            if(!this.description){
                error += "Artwork description cannot be blank."
            }
            if(!this.numInStock){
                error += "Artwork quantity cannot be blank."
            }
            if(!isNormalInteger(this.price)){
                error += "Enter an positive integer for price."
            }
            if(!isNormalInteger(this.numInStock)){
                error += "Enter an positive integer for quantity."
            }
            return error;
        }
  },
  created: function () {
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

<style>

</style>