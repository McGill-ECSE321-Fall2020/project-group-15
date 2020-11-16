<template>
  <section class="py-5" id="itemSection">
    <link
      href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.3/css/font-awesome.min.css"
      rel="stylesheet"
      integrity="sha384-T8Gy5hrqNKT+hzMclPo118YTQO6cYprQmhrYwIiQ/3axmI1hQomh7Ud2hPOy8SP1"
      crossorigin="anonymous"
    />
    <Navbar />
    <div v-if="customerCart.length == 0" class="title-style">
      <h1>Your Shopping Cart is Empty</h1>
      <img class="empty-cart-style" src="@/assets/emptycart.png">
      <p class="subtext-style">Head to the Artwork Gallery to add Artowrks to your cart.</p>
    </div>
    <div v-else class="cart-container">
      <div class="title-style">
        <h1>Cart</h1>
      </div>
      <div v-for="(artwork, index) in customerCart" v-bind:key="index">
        <div class="card-container">
            <div class="card shadow">
              <div class="card-body">
                <div class="row text-center">
                  <img v-if="artwork.artworkImageURL" :src="artwork.artworkImageURL" class="cart-img-style" alt="" loading="lazy" />
                  <img v-else src="@/assets/no-image.png" class="cart-img-style" alt="" loading="lazy" />
                  <div class="middle-container">
                    <div>
                      <h4>{{ artwork.artworkName }}</h4>
                      <h6>{{ artwork.artistFirstName }}</h6>
                    </div>
                    <div class="quantity-style">
                      <h5>Quantity: </h5>
                      <button :disabled='artwork.artworkQuantity <= 0' class="remove-button-style" @click="removeOneFromCart(artwork.artworkID, index)">
                        <b>-</b>
                      </button>
                      <h5>{{ artwork.artworkQuantity }}</h5>
                      <button :disabled='artwork.artworkNumInStock <= 0' class="add-button-style" @click="addOneToCart(artwork.artworkID, index)">
                        <b>+</b>
                      </button>
                    </div> 
                  </div>
                  <div class="col">
                    <h4>{{ "$" + (artwork.artworkPrice / 100).toFixed(2) }}</h4>
                    <p>per Item</p>
                    <div class="sub-row">
                      <!-- <button type="button" class="btn btn-warning btn-sm btn-block">
                        View Details
                      </button> -->
                      <button class="btn btn-danger btn-sm btn-block" @click="removeArtworkFromCart(index)">
                        <svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-trash" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                          <path d="M5.5 5.5A.5.5 0 0 1 6 6v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm2.5 0a.5.5 0 0 1 .5.5v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm3 .5a.5.5 0 0 0-1 0v6a.5.5 0 0 0 1 0V6z" />
                          <path fill-rule="evenodd" d="M14.5 3a1 1 0 0 1-1 1H13v9a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2V4h-.5a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1H6a1 1 0 0 1 1-1h2a1 1 0 0 1 1 1h3.5a1 1 0 0 1 1 1v1zM4.118 4L4 4.059V13a1 1 0 0 0 1 1h6a1 1 0 0 0 1-1V4.059L11.882 4H4.118zM2.5 3V2h11v1h-11z" />
                        </svg>
                      </button>
                    </div>
                  </div>
                  <div id="subTotal">
                    <h3>
                      {{ "SubTotal: $" + (artwork.orderSubtotal / 100).toFixed(2) }}
                    </h3>
                  </div>
                </div>
              </div>
            </div>
        </div>
      </div>
      <div>
        <p>The Total Price will appear in the checkout window</p>
        <router-link to="/cart/checkout">
          <button
            :disabled='customerCart.length==0' 
            class="btn btn-success btn-sm btn-block"
            id="Checkout">
            Checkout
          </button>
        </router-link>
      </div>
    </div>
  </section>
</template>

<script>
import Navbar from "@/components/Navbar";
import axios from "axios";
import { mapActions, mapGetters } from 'vuex';

var config = require("../../config");

// configure the backend url
var backendConfigurer = function () {
  switch (process.env.NODE_ENV) {
    case "development":
      return "http://" + config.dev.backendHost + ":" + config.dev.backendPort;
    case "production":
      return (
        "https://" + config.build.backendHost + ":" + config.build.backendPort
      );
  }
};

// configure the frontend url
var frontendConfigurer = function () {
  switch (process.env.NODE_ENV) {
    case "development":
      return "http://" + config.dev.host + ":" + config.dev.port;
    case "production":
      return "https://" + config.build.host + ":" + config.build.port;
  }
};
var backendUrl = backendConfigurer();
var frontendUrl = frontendConfigurer();

var AXIOS = axios.create({
  baseURL: backendUrl,
  //headers: { "Access-Control-Allow-Origin": frontendUrl },
});

export default {
  components: {
    Navbar
  },
  name: "Cart",
  computed: mapGetters(['customerCart']),
  methods: {
    ...mapActions(['incrementArtwork', 'decrementArtwork', 'removeFromCart']),
    addOneToCart(artworkID, cartIndex) {
      var data = {
        "artworkID" : artworkID,
        "cartIndex" : cartIndex
      }
      this.incrementArtwork(data)
    },
    removeOneFromCart(artworkID, cartIndex) {
      var data = {
        "artworkID" : artworkID,
        "cartIndex" : cartIndex
      }
      this.decrementArtwork(data)
    },
    removeArtworkFromCart(cartIndex) {
      this.removeFromCart(cartIndex)
    }
  }
};
</script>

<style scoped>
#itemSection {
  background-color: white;
}

.cart-container {
  display: flex;
  flex-direction: column;
  align-items: center;
}

.title-style {
  margin-top: 40px;
  margin-bottom: 20px;
  font-size: 200%;
}

#priceButton {
  margin-top: 20px;
}

#AddToCart {
  background-color: red;
  border-color: red;
  color: white;
}

#subTotal {
  display: flex;
  justify-content: flex-end;
  width: 100%;
  margin-top: 20px;
  margin-right: 15px;
}

  .card-container {
    width: 600px;
    margin: 10px;
  }
  .cart-img-style {
    margin-left: 10px;
    margin-right: 10px;
    width: 33%;
  }
  .middle-container {
    display: flex;
    flex-direction: column;
    justify-content: space-between;
    width: 30%;
  }
  .quantity-style {
    display: flex;
    justify-content: space-between;
  }
  .add-button-style {
    color: white;
    height: 30px;
    width: 30px;
    background-color: #27A745;
    border-radius: 5px ;
    border: none;
    box-shadow: 2px 2px 4px #000000;
    margin-left: 2px;
    margin-right: 2px;
  }
  .remove-button-style {
    color: white;
    height: 30px;
    width: 30px;
    background-color: #DC3445;
    border-radius: 5px ;
    border: none;
    box-shadow: 2px 2px 4px #000000;
    margin-left: 2px;
    margin-right: 2px;
  }
  .empty-cart-style {
    height: 300px
  }
  .subtext-style {
    font-size: 18px
  }
</style>