<template>
  <nav class="navbar fixed-top navbar-expand-lg navbar-dark">
    <link
      rel="stylesheet"
      href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css"
      integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2"
      crossorigin="anonymous"
    />
    <a class="navbar-brand">
      <img
        src="@/assets/logo.png"
        width="100"
        height="30"
        alt=""
        loading="lazy"
      />
    </a>
    <div class="navbar-style">
      <ul class="navbar-nav mr-auto">
        <li v-if="isCustomer" class="nav-item active">
          <router-link to="/artwork-gallery">
            <button class="btn nav-link" id="nav-bar-link-style">Artwork Gallery</button>
          </router-link>
        </li>
        <li v-if="isCustomer" class="nav-item active">
          <router-link to="/artists/all">
            <button class="btn nav-link" id="nav-bar-link-style">Artists</button>
          </router-link>
        </li>
        <li v-if="isArtist" class="nav-item">
          <router-link to="/artist-dashboard">
            <button class="btn nav-link" id="nav-bar-link-style">Dashboard</button>
          </router-link>
        </li>
      </ul>

      <button v-if="isArtist" type="button" class="btn btn-light add-artwork-btn">
        + Add Artwork
      </button>

      <router-link to="/cart">
        <button v-if="isCustomer" type="button" class="btn cart-btn-style navbar-btn-style">
          <svg width="1.5em" height="1.5em" viewBox="0 0 16 16" class="bi bi-cart-fill" fill="white" xmlns="http://www.w3.org/2000/svg">
            <path fill-rule="evenodd" d="M0 1.5A.5.5 0 0 1 .5 1H2a.5.5 0 0 1 .485.379L2.89 3H14.5a.5.5 0 0 1 .491.592l-1.5 8A.5.5 0 0 1 13 12H4a.5.5 0 0 1-.491-.408L2.01 3.607 1.61 2H.5a.5.5 0 0 1-.5-.5zM5 12a2 2 0 1 0 0 4 2 2 0 0 0 0-4zm7 0a2 2 0 1 0 0 4 2 2 0 0 0 0-4zm-7 1a1 1 0 1 0 0 2 1 1 0 0 0 0-2zm7 0a1 1 0 1 0 0 2 1 1 0 0 0 0-2z"/>
          </svg>
        </button>
      </router-link>
        <b-dropdown class="navbar-btn-style" size="lg" variant="link" toggle-class="text-decoration-none" no-caret id="dropdown1">
          <template #button-content>
            <svg width="1.5em" height="1.5em" viewBox="0 0 16 16" class="bi bi-person-fill" fill="white" xmlns="http://www.w3.org/2000/svg">
              <path fill-rule="evenodd" d="M3 14s-1 0-1-1 1-4 6-4 6 3 6 4-1 1-1 1H3zm5-6a3 3 0 1 0 0-6 3 3 0 0 0 0 6z"/>
            </svg>
          </template>
          <b-dropdown-item v-if="isCustomer" @click="routeToOrders()">Your Orders</b-dropdown-item>
          <b-dropdown-item v-if="isCustomer" @click="routeToReviews()">Your Reviews</b-dropdown-item>
          <b-dropdown-item @click="routeToSetting()">Settings</b-dropdown-item>
          <b-dropdown-divider></b-dropdown-divider>
          <b-dropdown-item @click="logoutAction()">Logout</b-dropdown-item>
        </b-dropdown>
    </div>
  </nav>
</template>

<script>
  import { mapActions, mapGetters } from 'vuex';

  export default {
    name: "navbar",
    computed: mapGetters(['userType']),
    data() {
      return {
        isCustomer: false,
        isArtist: false,
      };
    },
    methods: {
      ...mapActions(['logoutUser']),
      logoutAction() {
        window.location.replace("/");
        this.logoutUser();
      },
      routeToSetting() {
        if(this.userType == "Customer"){
          window.location.replace("#/settings/customer");
        } else if(this.userType == "Artist"){
          window.location.replace("#/settings/artist");
        }
      },
      routeToCart() {
        window.location.replace("#/cart");
      },
      routeToOrders() {
        window.location.replace("#/settings/orderlist");
      },
      routeToReviews() {
         window.location.replace("#/reviews");
      }
    },
    created() {
      if(this.userType == "Customer"){
        this.isArtist = false;
        this.isCustomer = true;
      } else if(this.userType == "Artist") {
        console.log(this.userType)
        this.isCustomer = false;
        this.isArtist = true;
      }
    }

  };
</script>
<style scoped>
.logo {
  width: 20%;
  color: white;
  padding: 0;
}

.navbar-dark {
  background-color: rgb(60, 60, 60);
}

.navbar-btn-style {
  width: 100px;
}

.cart-btn-style {
  background-color: none
}

.navbar-style {
  width: 100%;
  display: flex;
  justify-content: space-between;
  align-items: center;
}

#nav-bar-link-style {
  color: white;
}

.add-artwork-btn {
  color: #037BFF;
}
.add-artwork-btn:hover {
  color: #037BFF;
}
</style>