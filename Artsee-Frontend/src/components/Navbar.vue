<template>
  <nav class="navbar fixed-top navbar-expand-lg navbar-dark">
    <link
      rel="stylesheet"
      href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css"
      integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2"
      crossorigin="anonymous"
    />
    <div v-if="false">NavMode: {{ navMode }}</div>
    <a class="navbar-brand">
      <img
        src="@/assets/logo.png"
        width="100"
        height="30"
        alt=""
        loading="lazy"
      />
    </a>
    <button
      class="navbar-toggler"
      type="button"
      data-toggle="collapse"
      data-target="#navbarSupportedContent"
      aria-controls="navbarSupportedContent"
      aria-expanded="false"
      aria-label="Toggle navigation"
    >
      <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
      <ul class="navbar-nav mr-auto">
        <li class="nav-item active">
          <a class="nav-link" href="?/profile"
            >Gallery <span class="sr-only">(current)</span></a
          >
        </li>
        <li class="nav-item active">
          <a class="nav-link" href="?/profile"
            >Artists <span class="sr-only">(current)</span></a
          >
        </li>
        <li class="nav-item">
          <a class="nav-link" href="?/reviews">Reviews</a>
        </li>
      </ul>
      <div href="#">
        <svg width="1.5em" height="1.5em" viewBox="0 0 16 16" class="bi bi-cart-fill" fill="white" xmlns="http://www.w3.org/2000/svg">
          <path fill-rule="evenodd" d="M0 1.5A.5.5 0 0 1 .5 1H2a.5.5 0 0 1 .485.379L2.89 3H14.5a.5.5 0 0 1 .491.592l-1.5 8A.5.5 0 0 1 13 12H4a.5.5 0 0 1-.491-.408L2.01 3.607 1.61 2H.5a.5.5 0 0 1-.5-.5zM5 12a2 2 0 1 0 0 4 2 2 0 0 0 0-4zm7 0a2 2 0 1 0 0 4 2 2 0 0 0 0-4zm-7 1a1 1 0 1 0 0 2 1 1 0 0 0 0-2zm7 0a1 1 0 1 0 0 2 1 1 0 0 0 0-2z"/>
        </svg>
      </div>
        <b-dropdown size="lg" variant="link" toggle-class="text-decoration-none" no-caret id="dropdown1">
          <template #button-content>
            <svg width="1.5em" height="1.5em" viewBox="0 0 16 16" class="bi bi-person-fill" fill="white" xmlns="http://www.w3.org/2000/svg">
              <path fill-rule="evenodd" d="M3 14s-1 0-1-1 1-4 6-4 6 3 6 4-1 1-1 1H3zm5-6a3 3 0 1 0 0-6 3 3 0 0 0 0 6z"/>
            </svg>
          </template>
          <b-dropdown-item>Orders</b-dropdown-item>
          <b-dropdown-item>Settings</b-dropdown-item>
          <b-dropdown-divider></b-dropdown-divider>
          <b-dropdown-item>Logout</b-dropdown-item>
        </b-dropdown>
    </div>
  </nav>
</template>

<script>
export default {
  props: {
    navMode: {
      type: Boolean,
      default: false,
    },
  },
};
</script>
<style>

.logo {
  width: 20%;
  color: white;
  padding: 0;
}

.btn-secondary {
  background-color: white;
  border-color: white;
  color: white;
}
.navbar-dark {
  background-color: rgb(80, 80, 80);
}

#logoBox {
  border-style: solid;
  padding: 0;
  border-width: 7px;
  border-color: white;
  background-color: white;
  border-radius: 15px;
}

#logoutButton {
  color: white;
  border-width: 2.5px;
  width: 100px;
}

#right-nav-item {
  justify-self: right;
}

button {
  width: 100px;
}
</style>