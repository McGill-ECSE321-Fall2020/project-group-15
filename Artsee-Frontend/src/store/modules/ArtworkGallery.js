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
    // Artwork States
    artistArtworks: [],

    // Cart States
    customerCart : [],
    cartTotal: 0,
};

const getters = {
    // Artwork Getters
    artistArtworks: (state) => state.artistArtworks,

    // Cart Getters
    customerCart: (state) => state.customerCart,
    cartTotal: (state) => state.cartTotal,
};

const actions = {
    // Artwork Actions
    async fetchArtworks({ commit }) {
        const response = await AXIOS.get("/artworks");
        commit('setArtworks', response.data);
    },
    
    // Cart Actions
    addArtworkToCart({ commit }, artworkIndex) {
        var totalPrice = state.cartTotal;
        let copiedArtistArtworks = JSON.parse(JSON.stringify(state.artistArtworks));
        copiedArtistArtworks[artworkIndex].numInStock--;
        commit('setArtworks', copiedArtistArtworks);
        totalPrice += copiedArtistArtworks[artworkIndex].price;

        let copiedCustomerCart = JSON.parse(JSON.stringify(state.customerCart));
        var artworkID = copiedArtistArtworks[artworkIndex].id
        var isInCart = false
        for(var i=0; i < copiedCustomerCart.length; i++){
            if(copiedCustomerCart[i].artworkID == artworkID){
                isInCart = true;
                copiedCustomerCart[i].artworkQuantity++;
                copiedCustomerCart[i].orderSubtotal += copiedCustomerCart[i].artworkPrice;
                copiedCustomerCart[i].artworkNumInStock = copiedArtistArtworks[artworkIndex].numInStock;
                break;
            }
        }

        if(!isInCart){
            var artwork = {
                artworkImageURL: copiedArtistArtworks[artworkIndex].imageURL,
                artworkID: copiedArtistArtworks[artworkIndex].id,
                artworkName: copiedArtistArtworks[artworkIndex].name,
                artistFirstName: copiedArtistArtworks[artworkIndex].artist.firstName,
                artistLastName: copiedArtistArtworks[artworkIndex].artist.lastName,
                artworkQuantity: 1,
                artworkNumInStock: copiedArtistArtworks[artworkIndex].numInStock,
                artworkPrice: copiedArtistArtworks[artworkIndex].price,
                orderSubtotal: copiedArtistArtworks[artworkIndex].price,
            }
            copiedCustomerCart.push(artwork);
        }
        commit('setCustomerCart', copiedCustomerCart);

    },
    incrementArtwork({ commit }, artworkData) {
        var totalPrice = state.cartTotal;
        var artworkID = artworkData.artworkID
        var customerCartIndex = artworkData.cartIndex
        let copiedArtistArtworks = JSON.parse(JSON.stringify(state.artistArtworks));
        var artworkIndex = 0;
        for(var i=0; i<copiedArtistArtworks.length; i++){
            if(copiedArtistArtworks[i].id == artworkID){
                copiedArtistArtworks[i].numInStock--;
                totalPrice += copiedArtistArtworks[i].price;
                artworkIndex = i;
                break;
            }
        }
        commit('setArtworks', copiedArtistArtworks);

        let copiedCustomerCart = JSON.parse(JSON.stringify(state.customerCart));
        copiedCustomerCart[customerCartIndex].artworkQuantity++;
        copiedCustomerCart[customerCartIndex].orderSubtotal += copiedCustomerCart[customerCartIndex].artworkPrice;
        copiedCustomerCart[customerCartIndex].artworkNumInStock = copiedArtistArtworks[artworkIndex].numInStock;
        commit('setCustomerCart', copiedCustomerCart);
    },
    addToCart({ commit }, artworkID) {
        var totalPrice = state.cartTotal;
        let copiedArtistArtworks = JSON.parse(JSON.stringify(state.artistArtworks));
        var artworkIndex = 0;
        for(var i=0; i<copiedArtistArtworks.length; i++){
            if(copiedArtistArtworks[i].id == artworkID){
                copiedArtistArtworks[i].numInStock--;
                totalPrice += copiedArtistArtworks[i].price;
                artworkIndex = i;
                break;
            }
        }
        commit('setArtworks', copiedArtistArtworks);

        let copiedCustomerCart = JSON.parse(JSON.stringify(state.customerCart));
        var isInCart = false
        for(var i=0; i < copiedCustomerCart.length; i++){
            if(copiedCustomerCart[i].artworkID == artworkID){
                isInCart = true;
                copiedCustomerCart[i].artworkQuantity++;
                copiedCustomerCart[i].orderSubtotal += copiedCustomerCart[i].artworkPrice;
                copiedCustomerCart[i].artworkNumInStock = copiedArtistArtworks[artworkIndex].numInStock;
                break;
            }
        }

        if(!isInCart){
            var artwork = {
                artworkImageURL: copiedArtistArtworks[artworkIndex].imageURL,
                artworkID: copiedArtistArtworks[artworkIndex].id,
                artworkName: copiedArtistArtworks[artworkIndex].name,
                artistFirstName: copiedArtistArtworks[artworkIndex].artist.firstName,
                artistLastName: copiedArtistArtworks[artworkIndex].artist.lastName,
                artworkQuantity: 1,
                artworkNumInStock: copiedArtistArtworks[artworkIndex].numInStock,
                artworkPrice: copiedArtistArtworks[artworkIndex].price,
                orderSubtotal: copiedArtistArtworks[artworkIndex].price,
            }
            copiedCustomerCart.push(artwork);
        }
        commit('setCustomerCart', copiedCustomerCart);

    },
    decrementArtwork({ commit }, artworkData) {
        var totalPrice = state.cartTotal;
        var artworkID = artworkData.artworkID
        var customerCartIndex = artworkData.cartIndex
        let copiedArtistArtworks = JSON.parse(JSON.stringify(state.artistArtworks));
        var artworkIndex = 0;
        for(var i=0; i<copiedArtistArtworks.length; i++){
            if(copiedArtistArtworks[i].id == artworkID){
                copiedArtistArtworks[i].numInStock++;
                totalPrice -= copiedArtistArtworks[i].price;
                artworkIndex = i;
                break;
            }
        }
        commit('setArtworks', copiedArtistArtworks);

        let copiedCustomerCart = JSON.parse(JSON.stringify(state.customerCart));
        copiedCustomerCart[customerCartIndex].artworkQuantity--;
        copiedCustomerCart[customerCartIndex].orderSubtotal -= copiedCustomerCart[customerCartIndex].artworkPrice;
        copiedCustomerCart[customerCartIndex].artworkNumInStock = copiedArtistArtworks[artworkIndex].numInStock
        copiedCustomerCart[customerCartIndex].artworkStockIsEmpty = false;
        if(copiedCustomerCart[customerCartIndex].artworkQuantity == 0) {
            copiedCustomerCart[customerCartIndex].artworkQuantityIsEmpty = true;
        }
        commit('setCustomerCart', copiedCustomerCart);
    },
    removeFromCart({ commit }, customerCartIndex) {
        var totalPrice = state.totalPrice;
        let copiedCustomerCart = JSON.parse(JSON.stringify(state.customerCart));
        totalPrice -= copiedCustomerCart[customerCartIndex].orderSubtotal
        copiedCustomerCart.splice(customerCartIndex, 1);
        commit('setCustomerCart', copiedCustomerCart);
    },
    emptyCart({ commit }) {
        commit('setCustomerCart', []);
    }
};

const mutations = {
    setArtworks: (state, artistArtworks) => (state.artistArtworks = artistArtworks),
    setCustomerCart: (state, customerCart) => (state.customerCart = customerCart),
};

export default {
    state,
    getters, 
    actions, 
    mutations
};