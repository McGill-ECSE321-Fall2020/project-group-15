<template>

<div>
    <head>
        <title>Artist List</title>
    </head>
    <body>
      <div class="navbarContainer">
        <Navbar navMode="true"/>
      </div>

      <ArtistRow 
      v-for="(artist, i) in artists" v-bind:key="`artist-${i}`" v-bind:artistData = "artist" />
    </body>
</div>
  
</template>


<script>
import ArtistRow from '@/components/ArtistRow'
import Navbar from '@/components/Navbar'

export default {
  components: {
    ArtistRow,
    Navbar
  },

  data () {
    return {
      artists: [
        {
          userID: "",
          email: "",
          firstName: "Gareth",
          lastName: "Baley",
          phoneNumber: "",
          artistDescription: "Inspirational artworks only",
          rating: 3
        },
        {
          userID: "",
          email: "",
          firstName: "John",
          lastName: "Smith",
          phoneNumber: "",
          artistDescription: "A classic era artist",
          rating: 2
        }
      ]
    }
  }
  
}
</script>

<style scoped>

.navbarContainer {
  margin-bottom: 40px;
}
</style>