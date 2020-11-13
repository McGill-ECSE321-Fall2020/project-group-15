import Vuex from 'vuex';
import Vue from 'vue';
import login from './modules/Login'

//load vuex
Vue.use(Vuex);

//create store
export default new Vuex.Store({
    modules: {
        login
    }
})