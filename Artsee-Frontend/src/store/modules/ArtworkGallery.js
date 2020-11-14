import axios from 'axios'

//Setup axios to Artsee Backend
var config = require('../../../config')

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


const state = {
    artistArtworks: []
};

const getters = {
    artistArtworks: (state) => state.artistArtworks,
};

const actions = {
    async fetchArtworks({ commit }) {
        const response = await AXIOS.get("/artworks");
        commit('setArtworks', response.data);
    },
    setArtworks({ commit }, userType) {
        commit('setArtworks', artistArtworks);
        console.log(artistArtworks);
    }  
};

const mutations = {
    setArtworks: (state, artistArtworks) => (state.artistArtworks = artistArtworks),
};

export default {
    state,
    getters, 
    actions, 
    mutations
};