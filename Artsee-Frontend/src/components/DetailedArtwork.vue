<template>
  <div class="col-sm-12 col-md-12 col-lg-12" id="main">
        <div class="navbarContainer">
              <Navbar :navMode="true"/>
        </div>
    <link
      href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css"
      rel="stylesheet"
    />
    <!-- product -->
    <div
      class="product-content product-wrap clearfix product-deatil"
      id="product-card"
    >
      <div class="row">
        <div class="col-md-5 col-sm-12 col-xs-12">
          <div class="product-image">
            <div id="myCarousel-2" class="carousel slide">
              <div class="carousel-inner">
                <!-- Slide 1 -->
                <div class="item active">
                  <img
                    v-if = "artwork.imageURL"
                    :src="artwork.imageURL"
                    id="img"
                    class="img-responsive"
                    alt=""
                  />
                  <img
                    v-else
                    src="@/assets/no-image.png"
                    id="img"
                    class="img-responsive"
                    alt=""
                  />
                </div>
              </div>
            </div>
          </div>
        </div>

        <div class="col-md-6 col-md-offset-1 col-sm-12 col-xs-12">
          <h4 class="name">
            <h2>{{ artwork.name }}</h2>
            <small
              >By
              <a @click="$router.push({name: 'ArtistProfile', params: {artistID: artwork.artist.userID },})" class="hyperlinkstyle">
                {{ artwork.artist.firstName }} {{ artwork.artist.lastName }}
              </a>
            </small
            >
            <div id="stars">
              <ReviewStars v-bind:rating= "artwork.artist.rating"/>
            </div>
          </h4>
          <hr />
          <div class="row">
            <div class="col">
              <div class="btn-group pull-right">
                <ul id="inlineList">
                <li>
                <button class="btn btn-white btn-default">
                  <i class="fa fa-envelope"></i>
                </button>
                </li>
                <li>
                    Contact Seller
                </li>
                </ul>
              </div>
            </div>
            <div class="col">
              <h3 class="price-container" id="price">
                ${{artwork.price / 100}}
              </h3>
            </div>
            <div class="card-body button-container-style">
                <button type="button" class="btn btn-primary" @click="addDetailedArtToCart()">Add to Cart</button>
            </div>
          </div>
          <hr />
        </div>
      </div>
      <!-- end product -->
      <div class="description description-tabs">
        <ul id="myTab" class="nav nav-pills">
          <li>
            <h6 id="descriptionTitle">{{artwork.name}}</h6>
            <h6 id="descriptionTitle">Date <small> {{artwork.dateOfCreation}} </small></h6>
            <h6 id="descriptionTitle">Description</h6>
            <p id="descriptionTitle">{{ artwork.description }}</p>
          </li>
        </ul>
      </div>
      <hr />
      <div class="description description-tabs">
        <ul class="nav nav-pills">
          <li>
            <h6 id="descriptionTitle">Artist <small> {{artwork.artist.firstName}} {{artwork.artist.lastName}} </small></h6>
            <p id="descriptionTitle">{{ artwork.artist.artistDescription }}</p>
          </li>
        </ul>
      </div>
    </div>
  </div>
</template>

<script>
import Navbar from '@/components/Navbar'
import ReviewStars from '@/components/ReviewStars'
import { mapActions, mapGetters } from 'vuex';

import axios from 'axios'
var config = require('../../config')

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
export default {
  props: {
    artworkID: {
      default: -1,
      type: Number
    }
  },
  data() {
    return {
      artwork: {
        name: String,
        price: Number,
        description: String,
        dateOfCreation: Date,
        imageURL: String,
        artist: {
          firstName: String,
          lastName: String,
          rating: Number,
          artistDescription: String,
        }
      }
    };
  },
  components: {
      Navbar,        
      ReviewStars
  },
    created: function () {
    this.fetch()
  },
    methods: {
    fetch (){
      AXIOS.get('/artworks/' + this.artworkID.toString())
        .then(response => {
          // Pull artwork object
          this.artwork = response.data
        })
        .catch(e => {
          var errorMsg = e.response
          this.artworkError = errorMsg
        })
    },
    routeToArtist(){
      $router.push({name: 'ArtistProfile', params: {artistID: this.artistID },})
    },
    ...mapActions(['addToCart']),
    addDetailedArtToCart: function() {
        this.addToCart(this.artworkID);
    }
  }
   
};
</script>

<style scoped>

#stars {
    font-size: 1em;
}

#price {
    margin-top: 25px;
}

#addToCart {
    margin-top: 15px;
}

#inlineList {
    display: inline;
}

ul {
  list-style-type: none;
}

    .navbarContainer {
        margin-bottom: 100px;
    }

#img {
  width: 550px;
}

#product-card {
  border-width: 0px;
}

#artistDiv {
  background-color: white;
  text-align: left;
  margin-top: -70px;
  margin-left: -100px;
  padding-right: 100px;
}

#descriptionTitle {
    text-align: left;
  width: 500px;
}
body {
  margin-top: 20px;
  background: #eee;
}

.product-content {
  border: 1px solid #dfe5e9;
  margin-bottom: 20px;
  margin-top: 12px;
  background: #fff;
}

.product-content .carousel-control.left {
  margin-left: 0;
}

.product-content .product-image {
  background-color: #fff;
  display: block;
  min-height: 238px;
  overflow: hidden;
  position: relative;
}

.product-content .product-deatil {
  border-bottom: 1px solid #dfe5e9;
  padding-bottom: 17px;
  padding-left: 16px;
  padding-top: 16px;
  position: relative;
  background: #fff;
}

.product-content .product-deatil h5 a {
  color: #2f383d;
  font-size: 15px;
  line-height: 19px;
  text-decoration: none;
  padding-left: 0;
  margin-left: 0;
}

.product-content .product-deatil h5 a span {
  color: #9aa7af;
  display: block;
  font-size: 13px;
}

.product-content .product-deatil span.tag1 {
  border-radius: 50%;
  color: #fff;
  font-size: 15px;
  height: 50px;
  padding: 13px 0;
  position: absolute;
  right: 10px;
  text-align: center;
  top: 10px;
  width: 50px;
}

.product-content .product-deatil span.sale {
  background-color: #21c2f8;
}

.product-content .product-deatil span.discount {
  background-color: #71e134;
}

.product-content .product-deatil span.hot {
  background-color: #fa9442;
}

.product-content .description {
  font-size: 12.5px;
  line-height: 20px;
  padding: 10px 14px 16px 19px;
  background: #fff;
}

.product-content .product-info {
  padding: 11px 19px 10px 20px;
}

.product-content .product-info a.add-to-cart {
  color: #2f383d;
  font-size: 13px;
  padding-left: 16px;
}

.product-content name.a {
  padding: 5px 10px;
  margin-left: 16px;
}

.product-info.smart-form .btn {
  padding: 6px 12px;
  margin-left: 12px;
  margin-top: -10px;
}

.product-entry .product-deatil {
  border-bottom: 1px solid #dfe5e9;
  padding-bottom: 17px;
  padding-left: 16px;
  padding-top: 16px;
  position: relative;
}

.product-entry .product-deatil h5 a {
  color: #2f383d;
  font-size: 15px;
  line-height: 19px;
  text-decoration: none;
}

.product-entry .product-deatil h5 a span {
  color: #9aa7af;
  display: block;
  font-size: 13px;
}

.load-more-btn {
  background-color: #21c2f8;
  border-bottom: 2px solid #037ca5;
  border-radius: 2px;
  border-top: 2px solid #0cf;
  margin-top: 20px;
  padding: 9px 0;
  width: 100%;
}

.product-block .product-deatil p.price-container span,
.product-content .product-deatil p.price-container span,
.product-entry .product-deatil p.price-container span,
.shipping table tbody tr td p.price-container span,
.shopping-items table tbody tr td p.price-container span {
  color: #21c2f8;
  font-family: Lato, sans-serif;
  font-size: 24px;
  line-height: 20px;
}

.product-info.smart-form .rating label {
  margin-top: 0;
}

.product-wrap .product-image span.tag2 {
  position: absolute;
  top: 10px;
  right: 10px;
  width: 36px;
  height: 36px;
  border-radius: 50%;
  padding: 10px 0;
  color: #fff;
  font-size: 11px;
  text-align: center;
}

.product-wrap .product-image span.sale {
  background-color: #57889c;
}

.product-wrap .product-image span.hot {
  background-color: #a90329;
}

.shop-btn {
  position: relative;
}

.shop-btn > span {
  background: #a90329;
  display: inline-block;
  font-size: 10px;
  box-shadow: inset 1px 1px 0 rgba(0, 0, 0, 0.1),
    inset 0 -1px 0 rgba(0, 0, 0, 0.07);
  font-weight: 700;
  border-radius: 50%;
  padding: 2px 4px 3px !important;
  text-align: center;
  line-height: normal;
  width: 19px;
  top: -7px;
  left: -7px;
}

.description-tabs {
  padding: 30px 0 5px !important;
}

.description-tabs .tab-content {
  padding: 10px 0;
}

.product-deatil {
  padding: 30px 30px 50px;
}

.product-deatil hr + .description-tabs {
  padding: 0 0 5px !important;
}

.product-deatil .carousel-control.left,
.product-deatil .carousel-control.right {
  background: none !important;
}

.product-deatil .glyphicon {
  color: #3276b1;
}

.product-deatil .product-image {
  border-right: none !important;
}

.product-deatil .name {
  margin-top: 0;
  margin-bottom: 0;
}

.product-deatil .name small {
  display: block;
}

.product-deatil .name a {
  margin-left: 0;
}

.product-deatil .price-container {
  font-size: 24px;
  margin: 0;
  font-weight: 300;
}

.product-deatil .price-container small {
  font-size: 12px;
}

.product-deatil .fa-2x {
  font-size: 16px !important;
}

.product-deatil .fa-2x > h5 {
  font-size: 12px;
  margin: 0;
}

.product-deatil .fa-2x + a,
.product-deatil .fa-2x + a + a {
  font-size: 13px;
}

.profile-message ul {
  list-style: none;
}

.product-deatil .certified {
  margin-top: 10px;
}

.product-deatil .certified ul {
  padding-left: 0;
}

.product-deatil .certified ul li:not(first-child) {
  margin-left: -3px;
}

.product-deatil .certified ul li {
  display: inline-block;
  background-color: #f9f9f9;
  border: 1px solid #ccc;
  padding: 13px 19px;
}

.product-deatil .certified ul li:first-child {
  border-right: none;
}

.product-deatil .certified ul li a {
  text-align: left;
  font-size: 12px;
  color: #6d7a83;
  line-height: 16px;
  text-decoration: none;
}

.product-deatil .certified ul li a span {
  display: block;
  color: #21c2f8;
  font-size: 13px;
  font-weight: 700;
  text-align: center;
}

.product-deatil .message-text {
  width: calc(100% - 70px);
}

@media only screen and (min-width: 1024px) {
  .product-content div[class*="col-md-4"] {
    padding-right: 0;
  }
  .product-content div[class*="col-md-8"] {
    padding: 0 13px 0 0;
  }
  .product-wrap div[class*="col-md-5"] {
    padding-right: 0;
  }
  .product-wrap div[class*="col-md-7"] {
    padding: 0 13px 0 0;
  }
  .product-content .product-image {
    border-right: 1px solid #dfe5e9;
  }
  .product-content .product-info {
    position: relative;
  }
}

.message img.online {
  width: 40px;
  height: 40px;
}

.hyperlinkstyle{
  color: blue;
  text-decoration: underline;
}
</style>