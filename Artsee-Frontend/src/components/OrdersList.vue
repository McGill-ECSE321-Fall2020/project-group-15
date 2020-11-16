<template>
  <div>
    <head>
      <title>Artworks</title>
    </head>
    <body>
      <div id="navbarContainer">
        <Navbar navMode="false" />
      </div>
      <table class="table">
        <thead>
          <tr>
            <th>ID</th>
            <th>Total</th>
            <th>Date ordered</th>
            <th>Order Status</th>
            <th>View Details</th>
          </tr>
        </thead>

        <tbody>
          <tr v-for="(order, i) in orders" v-bind:key="`artwork-${i}`">
            <td>{{ order.orderID }}</td>
            <td>{{ "$" + order.totalPrice / 100 }}</td>
            <td>{{ order.datePlaced }}</td>
            <td>{{order.orderStatusDto}}</td>
            <td>
                <button
                  type="button"
                  class="btn btn-warning btn-sm btn-block"
                  @click="$router.push({name: 'Order', params: {orderID: order.orderID },})">View Details
                ></button>
            </td>
          </tr>
        </tbody>
      </table>
    </body>
  </div>
</template>

<script>
import Navbar from '@/components/Navbar'
import OrderRow from '@/components/OrderRow'
import { mapGetters, mapActions} from 'vuex'

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
  components: {
    Navbar,
    OrderRow
  },

    computed: mapGetters(["userName","userType"]),

    data () {
    return {
      orders: []
    }
  },

  created: function () {
    this.fetch()
  },

  methods: {
    fetch (){
      AXIOS.get('/artworkOrdersByCustomer/' + this.userName.toString())
        .then(response => {
          this.orders = response.data
        })
        .catch(e => {
          var errorMsg = e.response.data
          console.log(errorMsg)
          this.artworkError = errorMsg
        })
    },
  }
}

</script>

<style scoped>

#navbarContainer {
  margin-bottom: 90px;
}

.btn {
  margin-bottom: 20px;
}

</style>