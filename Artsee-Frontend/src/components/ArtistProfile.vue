<template>
  <div>
    <head>
        <title>Artist Profile</title>
    </head>
    <body>
      <div class="navbarContainer">
        <Navbar navMode="true"/>
      </div>
      <div class="row py-5 px-4">
        <div class="col-md-5 mx-auto">
            <!-- Profile widget -->
            <div class="bg-white shadow rounded overflow-hidden">
                <div class="px-4 pt-0 pb-4 cover">
                    <div class="media align-items-end profile-head">
                        <div class="profile mr-3">
                          <img src="https://images.unsplash.com/photo-1522075469751-3a6694fb2f61?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=500&q=80" alt="..." width="130" class="rounded mb-2 img-thumbnail">
                          <!-- Should be a vue-router and conditionally rendered depednding on artist or customer view-->
                          <a href="#" class="btn btn-outline-dark btn-sm btn-block">Edit profile</a>
                        </div>
                        <div class="media-body mb-5 text-white">
                            <h4 class="mt-0 mb">{{ artist.firstName + " " + artist.lastName }}</h4>
                        </div>
                    </div>
                </div>
                <div class="bg-light p-4 d-flex justify-content-end text-center">
                    <ul class="list-inline mb-0">
                        <li class="list-inline-item">
                            <h5 class="font-weight-bold mb-0 d-block">{{ artworks.length }}</h5><small class="text-muted"> <i class="fas fa-image mr-1"></i>Artworks</small>
                        </li>
                        <li class="list-inline-item">
                            <h5 class="font-weight-bold mb-0 d-block">{{ artist.rating }}</h5><small class="text-muted"> <i class="fas fa-user mr-1"></i>Rating</small>
                        </li>
                    </ul>
                </div>
                <div class="px-4 py-3">
                    <h5 class="mb-0">About</h5>
                    <div class="p-4 rounded shadow-sm bg-light">
                        <p class="font-italic mb-0">{{ artist.artistDescription }}</p>
                        <!-- <p class="font-italic mb-0">Lives in New York</p>
                        <p class="font-italic mb-0">Photographer</p> -->
                    </div>
                </div>
                <div class="py-4 px-4"> 
                    <div class="d-flex align-items-center justify-content-between mb-3">
                        <h5 class="mb-0">Artworks</h5>
                    </div>
                    <!-- NEED TO ADD v-for AND RENDER AN ITEMLISTING COMPONENT PASSING THE DETAILS OF  ITEM 
                    THROUGH PROPS (DIDNT DO BECAUSE ITEMLISTING HAD TO BE MODIFIED BY ADDING MORE PROPS) -->
                    <div class="row">
                        
                    </div>
                </div>
                <div class="py-4 px-4">
                    <div class="d-flex align-items-center justify-content-between mb-3">
                        <h5 class="mb-0">Reviews</h5>
                    </div>
                    <!-- COULD BE REPLACED BY REVIEW ROW COMPONENT -->
                    <div v-for="(review, i) in reviews" v-bind:key="`review-${i}`" class="row">
                        <div class="card" style="width: 90%">
                          <div class="card-body">
                            <h5 class="card-title">{{ "Customer: " + review.customer.firstName + " " + review.customer.lastName }}</h5>
                            <p class="card-text">{{"Comment: " + review.comment }}</p>
                          </div>
                          <ul class="list-group list-group-flush">
                            <li class="list-group-item">{{"Rating: " + review.rating }}</li>
                          </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>
      </div>
    </body>
  </div>
</template>

<script>
import Navbar from '@/components/Navbar'

export default {
  components: {
    Navbar
  },
  data () {
    return {
      artist: {
        userID: "",
        email: "",
        firstName: "Gareth",
        lastName: "Baley",
        phoneNumber: "",
        artistDescription: "Inspirational artworks only",
        rating: 3
      },
      artworks: [
        {
          id: 1,
          name: "newart2",
          description: "latest",
          price: 9,
          dateOfCreation: "2020-03-09",
          numInStock: 5,
          artist: {}
        }
      ],
      reviews: [
        {
          comment: "Best artist",
          rating: 5,
          customer: {
            userID: "",
            email: "",
            firstName: "John",
            lastName: "Smith"
          }
        }
      ]
    }
  },

  methods: {
    getArtist: function(id) {
      // GET THE ARTIST BY ID IN CACHE USING AXIOS, SET ARTIST OBJECT AND RENDER THE DETAILS 
    }
  }
}
</script>

<style>
.profile-head {
    transform: translateY(5rem)
}

.cover {
    background-image: url(https://images.unsplash.com/photo-1530305408560-82d13781b33a?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1352&q=80);
    background-size: cover;
    background-repeat: no-repeat
}

body {
    background: #654ea3;
    background: linear-gradient(to right, #e96443, #904e95);
    min-height: 100vh
}

.navbarContainer {
  margin-bottom: 80px;
}

.mb {
  margin-bottom: 20px;
}

</style>