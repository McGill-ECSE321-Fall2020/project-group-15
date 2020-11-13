<template>
  <div>
    <div class="navbarContainer">
      <Navbar navMode="true" />
    </div>
    <div class="container">
      <div class="row">
        <div class="col-xs-8">
          <div class="panel panel-info">
            <div class="panel-heading">
              <div class="panel-title">
                <div class="row">
                  <div class="col-xs-6">
                    <h5>
                      <span class="glyphicon glyphicon-shopping-cart"></span>
                      ArtSee Cart
                    </h5>
                  </div>

                  <div class="col-xs-6">
                    <button
                      type="button"
                      class="btn btn-primary btn-sm btn-block"
                      id="ContinueShopping"
                    >
                      <span class="glyphicon glyphicon-share-alt"></span>
                      Continue shopping
                    </button>
                  </div>
                </div>
              </div>
            </div>
            <div class="panel-body">
              <div class="row">
                <div class="col-xs-2">
                  <img
                    class="img-responsive"
                    src="http://placehold.it/100x70"
                  />
                </div>
                <div class="col-xs-4">
                  <h4 class="product-name"><strong>Artwork name</strong></h4>
                  <h4><small>Artwork description</small></h4>
                </div>
                <div class="col-xs-6">
                  <div class="col-xs-6 text-right">
                    <h6>
                      <strong>25.00 <span class="text-muted">x</span></strong>
                    </h6>
                  </div>
                  <div class="col-xs-4">
                    <input
                      type="text"
                      class="form-control input-sm"
                      value="1"
                    />
                  </div>
                  <div class="col-xs-2">
                    <button type="button" class="btn btn-link btn-xs">
                      <span class="glyphicon glyphicon-trash"> </span>
                    </button>
                  </div>
                </div>
              </div>
              <hr />
              <div class="row">
                <div class="col-xs-2">
                  <img
                    class="img-responsive"
                    src="http://placehold.it/100x70"
                  />
                </div>
                <div class="col-xs-4">
                  <h4 class="product-name"><strong>Artwork name</strong></h4>
                  <h4><small>Artwork description</small></h4>
                </div>
                <div class="col-xs-6">
                  <div class="col-xs-6 text-right">
                    <h6>
                      <strong>25.00 <span class="text-muted">x</span></strong>
                    </h6>
                  </div>
                  <div class="col-xs-4">
                    <input
                      type="text"
                      class="form-control input-sm"
                      value="1"
                    />
                  </div>
                  <div class="col-xs-2">
                    <button type="button" class="btn btn-link btn-xs">
                      <span class="glyphicon glyphicon-trash"> </span>
                    </button>
                  </div>
                </div>
              </div>
              <hr />
              <div class="row">
                <div class="text-center">
                  <div class="col-xs-9">
                    <h6 class="text-right">Added items?</h6>
                  </div>
                  <div class="col-xs-3" id="UpdateCart">
                    <button
                      type="button"
                      class="btn btn-default btn-sm btn-block"
                    >
                      Update cart
                    </button>
                  </div>
                </div>
              </div>
            </div>
            <div class="panel-footer">
              <div class="row text-center">
                <div class="col-xs-9">
                  <h4 class="text-right">Total <strong>$50.00</strong></h4>
                </div>
                <div class="col-xs-3">
                  <button type="button" class="btn btn-success btn-block">
                    Checkout
                  </button>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import Navbar from "@/components/Navbar";

export default {
  components: {
    Navbar,
  },
};
</script>

<style>
body {
  background-image: linear-gradient(to right, #5160a0, #9e9e9e);
}
.container {
  display: flex;
  justify-content: center;
  align-items: center;
}

.navbarContainer {
  margin-bottom: 100px;
}

#UpdateCart {
  background-color:royalblue;
  color: white;
  border-width: 0;
}
.panel-heading {
  display: flex;
  justify-content: center;
}

.col-xs-6 {
  display: flex;
  justify-content: center;
  align-content: right;
}
</style>