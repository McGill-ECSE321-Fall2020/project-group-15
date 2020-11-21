<template>
  <div>
    <head>
      <title>MyArtworks</title>
    </head>
    <body>
      <div id="navbarContainer">
        <Navbar navMode="false" />
      </div>
      <main class="container">
        <button
          class="btn btn-primary"
        >New Artwork
        </button>
      </main>
      <table class="table">
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
          <tr v-for="(artwork, i) in artworks" v-bind:key="`artwork-${i}`">
            <td>{{ artwork.name }}</td>
            <td>{{ artwork.price }}</td>
            <td>{{ artwork.description }}</td>
            <td>{{artwork.dateOfCreation}}</td>
            <td>{{ artwork.numInStock }}</td>
            <td>{{ artwork.artist.firstName + " " + artwork.artist.lastName }}</td>
          </tr>
        </tbody>
      </table>
    </body>
  </div>
</template>

<script src="./artwork.js">
import Navbar from '@/components/Navbar'

export default {
  components: {
    Navbar
  }
}
</script>

<style scoped>

#navbarContainer {
  margin-bottom: 90px;
}

.btn {
  margin-bottom: 20px;
}

</style>