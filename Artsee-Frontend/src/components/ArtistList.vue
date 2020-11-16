<template>

<div>
    <head>
        <title>Artist List</title>
    </head>
    <body>
      <div class="navbarContainer">
        <Navbar navMode="true"/>
      </div>

      <ArtistRow 
      v-for="(artist, i) in artists" v-bind:key="`artist-${i}`" v-bind:artistID = "artist.userID" />
    </body>
</div>
  
</template>


<script>
import ArtistRow from '@/components/ArtistRow'
import Navbar from '@/components/Navbar'
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
    ArtistRow,
    Navbar
  },

  data () {
    return {
      artists: []
    }
  },

  created: function () {
    this.fetch()
  },

  methods: {
    fetch (){
      // get the artists to display
      AXIOS.get('/artists/')
        .then(response => {
          this.artists = response.data
        })
        .catch(e => {
          var errorMsg = e.response.data
          console.log(errorMsg)
          this.artworkError = errorMsg
        })
    },
  }
};
</script>

<style scoped>

.navbarContainer {
  margin-bottom: 40px;
}
</style>