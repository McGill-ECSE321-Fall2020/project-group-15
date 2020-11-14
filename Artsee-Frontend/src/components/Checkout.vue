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
                            <div class="row">
                                <div class="col">
                                    <input type="text" class="form-control" placeholder="Cardholder First name">
                                </div>
                                <div class="col">
                                    <input type="text" class="form-control" placeholder="Cardholder Last name">
                                </div>
                            </div>
                              <div class="form-group">
                                <input type="CreditCard" class="form-control" id="cardNumber" placeholder="Credit Card Number">
                            </div>
                            <div class="form-group">
                                <input type="CVV/CVC" class="form-control" id="CVV" placeholder="CVV/CVC">
                            </div>
                             <div class="form-group">
                                <input type="Expiry Month" class="form-control" id="month" placeholder="Expiry Month: MM">
                            </div>
                            <div class="form-group">
                                <input type="Expiry Year" class="form-control" id="year" placeholder="Expiry Year: YYYY">
                            </div>
                            <div class="form-group">
                                <input type="email" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="Email Address">
                            </div>
                            <div class="form-group">
                                <input type="text" class="form-control" placeholder="Billing Address Line 1">
                            </div>
                            <div class="form-group">
                                <input type="text" class="form-control" placeholder="Billing Address Line 2">
                            </div>
                            <div class="form-group">
                                <input type="text" class="form-control" placeholder="City">
                            </div>
                            <div class="form-group">
                                <input type="text" class="form-control" placeholder="Province">
                            </div>
                            <div class="form-group">
                                <input type="text" class="form-control" placeholder="Postal Code">
                            </div>
                            <div class="form-group">
                                <input type="text" class="form-control" placeholder="Country">
                            </div>
                            <div class="button-container">
                                <div>
                                    <button type="submit" class="btn btn-success">Place Order</button>
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

export default {
    components: {
        Navbar
    }
}
    
</script>

<style>
    body {
      margin: 0;
      padding: 0 0 0 0;
    }
    .card-container {
        display: flex;
        justify-content: center;
        align-items: center;
    }
    .row {
        margin-bottom: 10px;
    }
    .button-container {
      width: 100%;
    }
    .signin-btn-style {
        color: #037BFF;
    }
    .signin-btn-style:hover {
        color: #037BFF;
    }
    .button-container {
        display: flex;
        justify-content: space-around;
    }
    button {
        height: 40px;
        width: 170px;
    }

    .navbarContainer {
        margin-bottom: 100px;
    }
</style>