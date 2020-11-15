<template>
  <section class="py-5" id="itemSection">
    <link
      href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.3/css/font-awesome.min.css"
      rel="stylesheet"
      integrity="sha384-T8Gy5hrqNKT+hzMclPo118YTQO6cYprQmhrYwIiQ/3axmI1hQomh7Ud2hPOy8SP1"
      crossorigin="anonymous"
    />
    <div class="navbarContainer">
      <Navbar navMode="true" />
    </div>
    <div class="container">
      <div class="title">
          <router-link to="/artwork-gallery">
          <button class="btn btn-default btn-sm btn-block">
              Back to Artsee Gallery
          </button>
          </router-link>
        <h1>Thank you for shopping at </h1>
            <img
        src="@/assets/logo.png"
        width="275"
        height="125"
        alt=""
        loading="lazy"
      />
      </div>

      
      </div>

  </section>
</template>

<script>
import Navbar from "@/components/Navbar";
import axios from "axios";
var config = require("../../config");

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
  headers: { "Access-Control-Allow-Origin": frontendUrl },
});

export default {
  components: {
    Navbar,
  },

  
  };


</script>

<style scoped>
.container {
  background: transparent;
  max-width: 2000px;
  border-top: 500px;
}

.title {
  margin-top: 40px;
  margin-bottom: 20px;
}
#itemSection {
  background-color: white;
}

.quantity {
  width: 30%;
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
  margin-right: 5px;
}
#Checkout {
 width:150%;
}

.bottom {
  display: flex;
  justify-content: center;
}
</style>