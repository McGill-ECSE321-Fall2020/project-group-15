<template>
  <div>
    <body>
      <table class="table">
        <thead>
          <tr>
            <th>{{orderID}}</th>
            <th>{{datePlaced}}</th>
            <th>{{totalPrice}}</th>
            <th>{{orderStatus}}</th>
          </tr>
        </thead>
      </table>
    </body>
  </div>
</template>

<script>
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
    orderID: {
      default: -1,
      type: Number
    }
  },
  name: "orderRow",
  data() {
    return {
		orderStatus: "Not Found",
		totalPrice: 0,
        datePlaced: "Not Found",
        orderError: "",
    };
  },
  created: function () {
    this.fetch()
  },

  methods: {
    fetch (){
      AXIOS.get('/orders/' + this.orderID.toString())
        .then(response => {
        // JSON responses are automatically parsed.
          this.orderStatus = response.data.orderStatusDto.toString()
          this.totalPrice = response.data.totalPrice
          this.datePlaced = response.data.datePlaced.toString()
          console.log(response.data)
        })
        .catch(e => {
          var errorMsg = e.response.data
          console.log(errorMsg)
          this.orderError = errorMsg
        })
    },
  }
};
</script>