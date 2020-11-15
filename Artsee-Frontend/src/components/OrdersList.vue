<template>
  <div>
    <head>
      <title>Artworks</title>
    </head>
    <body>
      <div id="navbarContainer">
        <Navbar navMode="false" />
      </div>
      <table class="table">
        <thead>
          <tr>
            <th>Name</th>
            <th>Total</th>
            <th>Date ordered</th>
            <th>Shipping Status</th>
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

<script>
import Navbar from '@/components/Navbar'
import OrderRow from '@/components/OrderRow'

export default {
  components: {
    Navbar,
    OrderRow
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