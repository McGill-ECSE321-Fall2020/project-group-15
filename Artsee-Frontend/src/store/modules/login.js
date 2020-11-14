const state = {
    userType: 'NoUserType',
    userName: "NoUserName",
};

const getters = {
    userType: (state) => state.userType,
    userName: (state) => state.userName
};

const actions = {
    setUserType({ commit }, userType) {
        commit('setUserType', userType);
        console.log(userType);
    },
    setUserName({ commit }, userName) {
        commit('setUserName', userName);
        console.log(userName);
    }
    
};

const mutations = {
    setUserType: (state, userType) => (state.userType = userType),
    setUserName: (state, userName) => (state.userName = userName),
};

export default {
    state,
    getters, 
    actions, 
    mutations
};