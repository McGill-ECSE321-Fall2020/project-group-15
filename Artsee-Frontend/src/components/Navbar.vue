<template>
<nav class="navbar fixed-top navbar-expand-lg navbar-light">
  <div v-if= "false">
    NavMode: {{navMode}}
  </div>
    <a class="navbar-brand" id="logoBox">
    <img src="@/assets/logo.png" width="100" height="30" alt="" loading="lazy">
  </a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>

  <div class="collapse navbar-collapse" id="navbarSupportedContent">
    <ul class="navbar-nav mr-auto">
      <li class="nav-item active">
        <a class="nav-link" href="?/profile">Profile <span class="sr-only">(current)</span></a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="#">Artwork</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="#">Artists</a>
      </li>
      <li class="nav-item" v-if="!navMode">
        <a class="nav-link" href="#">Orders</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="?/reviews">Reviews</a>
      </li>
    </ul>
    <li class="navbar-text">
      <a class="nav-link" href="#">Settings</a>
    </li>
    <form class="form-inline my-2 my-lg-0" href="/">
      <button class="btn btn-outline-success my-2 my-sm-0" type="submit" id="logoutButton">Logout</button>
    </form>
  </div>
</nav>
</template>

<script>
export default {
  props: {
    navMode: {
      type: Boolean,
      default: false
    }
  }
}

</script>

<style>

.logo {
  width: 20%;
  color: white;
  padding: 0;
}

.navbar-light {
  background-color: rgb(122, 137, 160);
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

</style>