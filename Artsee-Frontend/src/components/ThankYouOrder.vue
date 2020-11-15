<template>
  <section class="py-5" id="itemSection">
    <div class="navbarContainer">
      <Navbar navMode="true" />
    </div>
    <div class="container">
      <div class="title">
        <div>
          <h1>Thank you for shopping at </h1>
          <img src="@/assets/logo.png" width="275" alt="" loading="lazy"/>
        </div>
        <router-link to="/artwork-gallery">
          <button class="btn btn-default btn-sm btn-block btn-subscript-style">
              Back to Artsee Gallery
          </button>
        </router-link>
      </div>
    </div>
  </section>
</template>

<script>
  import Navbar from "@/components/Navbar";

  export default {
    components: {
      Navbar
    },
  };
</script>

<style scoped>
  .container {
    background: transparent;
    display:flex;
    flex-direction: column;
  }

  .btn-subscript-style {
    margin-top: 50px;
  }

  .title {
    margin-top: 40px;
    margin-bottom: 20px;
  }

  #itemSection {
    background-color: white;
  }
</style>