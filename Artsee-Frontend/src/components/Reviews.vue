<template>
    <div>
        <head>
        <title>Reviews</title>
        </head>
        <body>
            <div class="navbarContainer">
                <Navbar navMode="false" />
            </div>
            <main className="container">
              <div v-for="(review, i) in reviews" v-bind:key="`review-${i}`" class="card" style="width: 18rem;">
                <div class="card-body">
                  <h5 class="card-title">{{ review.customer.name }}</h5>
                  <p class="card-text">{{ review.comment }}</p>
                </div>
                <ul class="list-group list-group-flush">
                  <li class="list-group-item">Rating: {{ review.rating }}</li>
                </ul>
              </div>
            </main>
        </body>
    </div>
</template>

<script src="./reviews.js">
import Navbar from '@/components/Navbar'

export default {
    components: {
        Navbar
    }
}
    
</script>

<style>
  .navbarContainer {
    margin-bottom: 100px;
  }
</style>
  
  


