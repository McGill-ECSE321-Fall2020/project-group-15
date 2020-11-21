<template>
    <div>
        <head>
        <title>New Artwork</title>
        </head>
        <body>
            <div class="navbarContainer">
                <Navbar navMode="false" />
            </div>
            <div class="card-container">
                <div class="card w-50">
                    <div class="card-body">
                      <div class="header-container">
                          <h3>Artwork Form</h3>
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
                                    <button type="submit" class="btn btn-primary" @click="addArtworkToStore($event)">Post Artwork</button>
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
    </div>
</template>

<script>
import Navbar from '@/components/Navbar'
import axios from 'axios'
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
    baseURL: backendUrl
    // headers: { 'Access-Control-Allow-Origin': "*" }
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

function ArtworkDTO(name, description, price, dateOfCreation, numInStock, artist, imageURL) {
    this.name = name;
    this.description = description;
    this.price = price;
    this.dateOfCreation = dateOfCreation;
    this.numInStock = numInStock;
    this.artist = artist;
    this.imageURL = imageURL;
}

export default {
    computed: mapGetters(['userName']),
    components: {
        Navbar
    },
    data() {
      return {
        name: "",
        price: "",
        description: "",
        dateOfCreation: '',
        numInStock: "",
        imageURL: "",
        artist : {},
        userID: "",
        artworkDto : {},
        error: [],
      };
    },
    methods: {
        async addArtworkToStore(event) {
            if (event) {
                event.preventDefault()
            }
            var error = this.checkError();
            if(error == ""){
                var artistDto = new ArtistDTO(this.userName);
                var artworkDto = new ArtworkDTO(this.name, this.description, parseInt(this.price)*100, this.dateOfCreation, parseInt(this.numInStock), artistDto, this.imageURL)
                await AXIOS.post("/artworks", artworkDto)
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
    }
}
</script>

<style scoped>
  .card-container {
      margin-top: 100px;
      display: flex;
      justify-content: center;
      align-items: center;
      margin-bottom: 50px;
  }

  .header-container {
      width: 100%;
      display: flex;
      justify-content: center;
  }

  .button-container {
        display: flex;
        justify-content: space-around;
  }

  button {
      height: 40px;
      width: 170px;
  }

  .navbarConatiner {
    margin-bottom: 20px;
  }

  .date-text-style {
      width: 200px;
  }

  .date-container {
      display: flex;
      align-items: center;
  }

  .alert-style {
      margin: 5px;
  }
</style>