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
    userData: {
        userType: 'NoUserType',
        userName: 'NoUserName',
    },
    userType: 'NoUserType',
    userName: 'NoUserName',
};

const getters = {
    userData: (state) => state.userData,
    userType: (state) => state.userType,
    userName: (state) => state.userName
};

const actions = {
    async setUserData({ commit }, userData) {
        if(state.userType == "Customer"){
            const tempUserData = await AXIOS.get("/customers/" + userData.userID)
            commit('setUserData', tempUserData.data);
            console.log(tempUserData.data)
        } else if(state.userType == "Artist") {
            const tempUserData = await AXIOS.get("/artists/" + userData.userID)
            commit('setUserData', tempUserData.data);
            console.log(tempUserData.data)
        }
    },
    async setUserType({ commit }, userType) {
        console.log(userType)
        commit('setUserType', userType);
    },
    setUserName({ commit }, userName) {
        console.log(userType)
        commit('setUserName', userName);
    },
    logoutUser({ commit }) {
        var data = {
            userType: 'NoUserType',
            userName: 'NoUserName',
        }
        commit('setUserData', data);
        commit('setUserType', 'NoUserType');
        commit('setUserName', 'NoUserName');
    }
};

const mutations = {
    setUserData: (state, userData) => (state.userData = userData),
    setUserType: (state, userType) => (state.userType = userType),
    setUserName: (state, userName) => (state.userName = userName),
};

export default {
    state,
    getters, 
    actions, 
    mutations
};