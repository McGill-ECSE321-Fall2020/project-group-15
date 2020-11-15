const state = {
   customerCart : [],
   cartTotal: 0,
};

const getters = {
    customerCart: (state) => state.customerCart,
};

const actions = {
    addItemToCart({ commit }, itemDetails) {
        var cartItem = {
            imageURL: itemDetails.imageURL,
            itemName: itemDetails.itemName,
            itemArtist: itemDetails.itemArtist,
            quantityInCart: itemDetails.quantityInCart,
            itemQuantityLeft: itemDetails.itemQuantityLeft,
            itemPrice: itemDetails.price,
            itemSubtotal: itemDetails.itemSubtotal
        }
    }
};

const mutations = {
    
};

export default {
    state,
    getters, 
    actions, 
    mutations
};