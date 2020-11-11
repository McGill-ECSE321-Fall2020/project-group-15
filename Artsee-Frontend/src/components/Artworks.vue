<template>
    <div>
      <head>
      <title>Artworks</title>
      </head>
      <body>
          <div id="navbarContainer">
            <Navbar />
          </div>
          <main class="container">
            <button
              margin-bottom= "20"
              class="btn btn-primary"
            >New Artwork
            </button>
          </main>
          <table className="table">
            <thead>
              <tr>
                <th>Name</th>
                <th>Price</th>
                <th>Description</th>
                <th>Date of creation</th>
                <th>Number in stock</th>
                <th>Artist</th>
              </tr>
            </thead>
            <tbody>
              <tr v-for="(artwork, i) in artworks" v-bind:key="`artwork-${i}`"></tr>
              <td>{{artwork.name}}</td>
              <td>{{artwork.price}}</td>
              <td>{{artwork.description}}</td>
              <td>{{artwork.dateOfCreation}}</td>
              <td>{{artwork.artist.name}}</td>
              
            </tbody>
          </table>
      </body>
</template>


<script src='./artworks.js'>
import Navbar from '@/components/Navbar'

export default {
    components: {
        Navbar
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