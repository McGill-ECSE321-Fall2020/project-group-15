import Vuex from 'vuex';
import Vue from 'vue';
import login from './modules/Login'
import artworkGallery from './modules/ArtworkGallery'

//load vuex
Vue.use(Vuex);

//create store
export default new Vuex.Store({
    modules: {
        login,
        artworkGallery
    }
})