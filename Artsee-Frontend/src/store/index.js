import Vuex from 'vuex';
import Vue from 'vue';
import login from './modules/Login'
import artworkGallery from './modules/ArtworkGallery'
import cart from './modules/Cart'

//Add Vuex to the Application
Vue.use(Vuex);

//Create store by binding all the modules
export default new Vuex.Store({
    modules: {
        login,
        artworkGallery,
        cart
    }
})