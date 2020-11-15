<template>
    <div>
        <head>
        <title>Checkout</title>
        </head>
        <body>
            <div class="navbarContainer">
                <Navbar navMode="false" />
            </div>
            <div class="card-container">
                <div class="card w-50">
                    <div class="card-body">
                        <form>
                            <div class="form-group text-style">
                                <input type="text" class="form-control" v-model="firstName" placeholder="Cardholder First name">
                            </div>
                            <div class="form-group  text-style">
                                <input type="text" class="form-control" v-model="lastName" placeholder="Cardholder Last name">
                            </div>
                            <div class="form-group  text-style">
                                <input type="CreditCard" class="form-control" v-model="cardNumber" placeholder="Credit Card Number">
                            </div>
                            <div class="form-group text-style">
                                <input type="CVV/CVC" class="form-control" v-model="cvv" placeholder="CVV/CVC">
                            </div>
                             <div class="form-group text-style">
                                <input type="Expiry Month" class="form-control" v-model="month" placeholder="Expiry Month: MM">
                            </div>
                            <div class="form-group text-style">
                                <input type="Expiry Year" class="form-control"  v-model="year" placeholder="Expiry Year: YYYY">
                            </div>
                            <div class="form-group delivery-method-style">
                                <label class="label-style" for="inputState">Delivery Method: </label>
                                <select id="deliveryMethod" class="form-control">
                                    <option selected>SHIP</option>
                                    <option>PICKUP</option>
                                </select>
                            </div>
                            <hr>
                            <div class="button-container">
                                <div class="price-container">
                                    <h4> Total Price: $</h4>
                                    <h4> {{ cartTotal }} </h4>
                                </div>
                                <div>
                                    <button type="submit" class="btn btn-success" @click="placeOrder()">Place Order</button>
                                </div>
                            </div>
                            <div v-for="(errorMsg, index) in error" :key="index">
                                    <div class="alert alert-danger" role="alert">
                                    {{errorMsg}}
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </body>
    </div>
</template>

<script>
import Navbar from '@/components/Navbar'
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

function CustomerDTO(userID) {
    this.userID = userID;
}

function ArtworkOrderDTO(customer, artworks, deliveryMethodDto) {
    this.customer = customer
    this.artworks = artworks
    this.deliveryMethodDto = deliveryMethodDto
}

export default {
    computed: mapGetters(['cartTotal', 'userData', 'customerCart']),
    components: {
        Navbar
    },
    data () {
        return {
            firstName: '',
            lastName: '',
            cardNumber: '',
            cvv: '',
            month: '',
            year: '',
            error : [],
            // DTO fields
            artworks: [],
            artworkDto: {},
            deliveryMethodDto: '',
            customer: {},
        }
    },
    methods: {
        ...mapActions(['emptyCart']),
        async placeOrder() {
            var error = this.checkError();
            if(error == "") {
                var deliveryMethod = document.getElementById("deliveryMethod").value
                var customerDto = new CustomerDTO(this.userData.userID)
                var artworks = []
                for(var i=0; i<this.customerCart.length; i++){
                    for(var j=0; j<this.customerCart[i].artworkQuantity; j++)
                    var artwork = {
                        "id" : this.customerCart[i].artworkID
                    }
                    artworks.push(artwork)
                }
                var artworkDto = new ArtworkOrderDTO(customerDto, artworks, deliveryMethod)
                await AXIOS.post("/artworkOrders", artworkDto)
                    .then(response => {
                        console.log(response)
                        window.location.replace("#/order/thankyou");
                        this.emptyCart();
                    })
                    .catch(e => {
                        console.log(e)
                    })
            } else {
                var errorParts = error.split(".")
                errorParts.pop()
                this.error = errorParts
            }
        },
        checkError() {
            var errorMsg = ""
            if(!this.firstName){
                errorMsg += "First name cannot be empty."
            }
            if(!this.lastName){
                errorMsg += "Last name cannot be empty."
            }
            if(!this.cardNumber){
                errorMsg += "Credit card number cannot be empty."
            }
            if(!this.cvv){
                errorMsg += "CVV cannot be empty."
            }
            if(!this.month){
                errorMsg += "Month cannot be empty."
            }
            if(!this.year){
                errorMsg += "Year cannot be empty."
            }
            if(!(/^\d+$/.test(this.cardNumber)) || this.cardNumber.length!=16 ){
                console.log(this.cardNumber)
                console.log(this.cardNumber.length)
                console.log(/^\d+$/.test(this.cardNumber))
                errorMsg += "Credit Card needs to be 16 digits long with no space."
            }
            if(!(/^\d+$/.test(this.cvv)) || this.cvv.length!=3){
                console.log(this.cvv)
                console.log(this.cvv.length)
                console.log(/^\d+$/.test(this.cvv))
                errorMsg += "CVV needs to be 3 digits long with no space."
            }
            if(!(/^\d+$/.test(this.month)) || parseInt(this.month)<0 || parseInt(this.month)>12 ){
                errorMsg += "Month needs to be a value between 1 to 12."
            }
            if(!(/^\d+$/.test(this.year)) || this.year.length!=4 || parseInt(this.year)<2020) {
                errorMsg += "Year needs to be 4 digits long with no space and greater than 2020."
            }
            return errorMsg
        }
    }
}
    
</script>

<style>
    .card-container {
        display: flex;
        width: 100%;
        justify-content: center;
        align-items: center;
    }
    .button-container {
      width: 100%;
      margin-bottom: 10px;
    }
    .button-container {
        display: flex;
        justify-content: space-between;
    }
    button {
        height: 40px;
        width: 170px;
    }

    .navbarContainer {
        margin-bottom: 100px;
    }
    .text-style {
        margin: 10px;
    }
    .price-container {
        display: flex;
    }
    .error-style {
        color: red;
    }
    .delivery-method-style {
        display: flex;
        justify-content: space-between;
        align-items: flex-end;
    }
    .label-style {
        width: 30%
    }
</style>