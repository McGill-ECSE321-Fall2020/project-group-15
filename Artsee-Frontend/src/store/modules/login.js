const state = {
    userType: 'NoUserType'
};

const getters = {
    userType: (state) => state.userType
};

const actions = {
    setUserType({ commit }, userType) {
        commit('setUserType', userType);
        console.log(userType);
    }
};

const mutations = {
    setUserType: (state, userType) => (state.userType = userType),
};

export default {
    state,
    getters, 
    actions, 
    mutations
};