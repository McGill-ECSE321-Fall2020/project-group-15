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
          <router-link to="/orderlist">
          <button class="btn btn-default btn-sm btn-block">
              Back to myOrders
          </button>
          </router-link>
        <h1>Previous Order</h1>
      </div>
      <div v-for="(item, index) in items" :key="index">
      <div class="row xl-6">
        <div class="col-md-8">
          <div class="card shadow">
            <div class="card-body">
              <div class="row text-center">
                <div class="col-ml-1 mx-2">
                  <img :src="item.imageURL" width="170" alt="" loading="lazy" />
                </div>
                <div class="col-md-4" id="infoBox">
                  <h4>{{ item.name }}</h4>
                  <h6>{{ item.artistName }}</h6>
                  <p>{{ item.description }}</p>
                  <div class="col">
                    <input
                      type="int"
                      class="quantity"
                      v-model="quantity"
                      placeholder="Qty"
                    />
                  </div>
                </div>
                <div class="col-md-4" id="priceButton">
                  <h4>{{ "$" + (item.price / 100).toString() }}</h4>
                  <p>per Item</p>
                  <p></p>
                  <div class="sub-row">
                    <button
                      type="button"
                      class="btn btn-warning btn-sm btn-block"
                    >
                      View Details
                    </button>
                    <button
                      class="btn btn-success btn-sm btn-block"
                      id="Review"
                    >
                    Add Review
                    </button>
                  </div>
                </div>
                <div id="subTotal">
                  <h3>
                    {{ "SubTotal: $" + ((item.price * quantity) / 100).toString() }}
                  </h3>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
      </div>
    </div>
    <div class="bottom">
    <h2> {{"Total:" + ((price*quantity)/100).toString() }}
    </h2>
    
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
  props: {
    artworkID: {
      default: -1,
      type: Number,
    },
    quantity: {
      default: 1,
      type: Number,
    },
  },
  name: "itemListing",
  data() {
    return {
      items: [
        {
          name: "Mona Lisa",
          description: "A classic",
          price: "400",
          dateOfCreation: "1503",
          numInStock: "1",
          artistName: "Da Vinci",
          imageURL:
            "https://homepages.cae.wisc.edu/~ece533/images/airplane.png",
          artworkError: "",
          quantity: "",
        },
        {
          name: "Starry Night",
          description: "a famous painting",
          price: "300",
          dateOfCreation: "1889",
          numInStock: "3",
          artistName: "Van Gogh",
          imageURL:
            "https://homepages.cae.wisc.edu/~ece533/images/airplane.png",
          artworkError: "",
          quantity: "",
        },
      ],
    };
  },
  created: function () {
    this.fetch();
  },

  methods: {
    fetch() {
      AXIOS.get("/artworks/" + this.artworkID.toString())
        .then((response) => {
          // JSON responses are automatically parsed.
          (this.name = response.data.name),
            (this.description = response.data.description),
            (this.price = response.data.price),
            (this.dateOfCreation = response.data.dateOfCreation),
            (this.numInStock = response.data.numInStock),
            (this.artistName =
              response.data.artist.firstName +
              " " +
              response.data.artist.lastName),
            (this.imageURL = response.data.imageURL.toString()),
            console.log(response.data);
        })
        .catch((e) => {
          var errorMsg = e.response.data;
          console.log(errorMsg);
          this.artworkError = errorMsg;
        });
    },
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