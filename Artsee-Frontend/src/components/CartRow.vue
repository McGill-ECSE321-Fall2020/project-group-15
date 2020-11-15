<template>
  <div class="card-container">
      <div class="card shadow">
        <div class="card-body">
          <div class="row text-center">
            <img :src="imageURL" class="cart-img-style" width="170" alt="" loading="lazy" />
            <div>
              <h4>{{ name }}</h4>
              <h6>{{ artistName }}</h6>
              <p>{{ description }}</p>
              <div class="col">
                <input
                  type="int"
                  class="quantity"
                  v-model="quantity"
                  placeholder="Qty"
                />
              </div>
            </div>
            <div class="col">
              <h4>{{ "$" + (price / 100).toString() }}</h4>
              <p>per Item</p>
              <p></p>
              <div class="sub-row">
                <button type="button" class="btn btn-warning btn-sm btn-block">
                  View Details
                </button>
                <button
                  class="btn btn-danger btn-sm btn-block"
                  id="RemoveFromCart"
                >
                  <svg
                    width="1em"
                    height="1em"
                    viewBox="0 0 16 16"
                    class="bi bi-trash"
                    fill="currentColor"
                    xmlns="http://www.w3.org/2000/svg"
                  >
                    <path
                      d="M5.5 5.5A.5.5 0 0 1 6 6v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm2.5 0a.5.5 0 0 1 .5.5v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm3 .5a.5.5 0 0 0-1 0v6a.5.5 0 0 0 1 0V6z"
                    />
                    <path
                      fill-rule="evenodd"
                      d="M14.5 3a1 1 0 0 1-1 1H13v9a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2V4h-.5a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1H6a1 1 0 0 1 1-1h2a1 1 0 0 1 1 1h3.5a1 1 0 0 1 1 1v1zM4.118 4L4 4.059V13a1 1 0 0 0 1 1h6a1 1 0 0 0 1-1V4.059L11.882 4H4.118zM2.5 3V2h11v1h-11z"
                    />
                  </svg>
                </button>
              </div>
            </div>
            <div id="subTotal">
              <h3>
                {{ "SubTotal: $" + ((price * quantity) / 100).toString() }}
              </h3>
            </div>
          </div>
        </div>
      </div>
  </div>
</template>

<script>
export default {
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
          name: "Mona Lisa",
          description: "A classic",
          price: "400",
          dateOfCreation: "1503",
          numInStock: "1",
          artistName: "Da Vinci",
          imageURL:"https://homepages.cae.wisc.edu/~ece533/images/airplane.png",
          artworkError: "",
    };
  },
}
</script>

<style>
  .card-container {
    width: 600px;
  }
  .cart-img-style {
    margin-left: 10px;
    width: 170;
  }
</style>