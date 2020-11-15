<template>
    <body>
        <NavBar />
        <div class="artworks-container">
            <div v-for="(artwork, index) in artistArtworks" :key="index">
                <div v-if="artwork.numInStock" class="card card-style card-container-style" style="width: 18rem;">
                    <img v-if="artwork.imageURL" class="card-img-top" :src= "artwork.imageURL" alt="Card image cap">
                    <img v-else class="card-img-top" src="@/assets/no-image.png" alt="Card image cap">
                    <div class="card-body">
                        <h5 class="card-title">{{artwork.name}}</h5>
                        <p class="card-text">{{artwork.description}}</p>
                    </div>
                    <ul class="list-group list-group-flush">
                        <li class="list-group-item"><b>Price:</b> $ <span>{{(artwork.price/10).toFixed(2)}}</span></li>
                        <li class="list-group-item"><b>Artist: </b><span>{{artwork.artist.userID}}</span></li>
                    </ul>
                    <div class="card-body button-container-style">
                        <button type="button" class="btn btn-primary" @click="addToCart(index)">Add to Cart</button>
                        <p class="card-text"><b>Quantity left: </b>{{artwork.numInStock}}</p>
                    </div>
                </div>
            </div>
        </div>
    </body>
</template>

<script>
import NavBar from "./Navbar"
import { mapActions, mapGetters } from 'vuex';

    export default {
        name: "gallery",
        components: {
            NavBar
        },
        computed: mapGetters(['artistArtworks']),
        data() {
            return {
                artworks: [
                    {
                        "imageURL" : "https://homepages.cae.wisc.edu/~ece533/images/airplane.png",
                        "name" : "Mona Lisa",
                        "artist" : "John Smith",
                        "description" : "Some random description about the artwork will go here.",
                        "price" : 100,
                        "quantity" : 1,
                    },
                    {
                        "imageURL" : "https://homepages.cae.wisc.edu/~ece533/images/airplane.png",
                        "name" : "Mona Lisa",
                        "artist" : "John Smith",
                        "description" : "Some random description about the artwork will go here.",
                        "price" : 100,
                        "quantity" : 1,
                    },
                    {
                        "imageURL" : "https://homepages.cae.wisc.edu/~ece533/images/airplane.png",
                        "name" : "Mona Lisa",
                        "artist" : "John Smith",
                        "description" : "Some random description about the artwork will go here.",
                        "price" : 100,
                        "quantity" : 1,
                    },
                    {
                        "imageURL" : "https://homepages.cae.wisc.edu/~ece533/images/airplane.png",
                        "name" : "Mona Lisa",
                        "artist" : "John Smith",
                        "description" : "Some random description about the artwork will go here.",
                        "price" : 100,
                        "quantity" : 1,
                    },
                    {
                        "imageURL" : "https://homepages.cae.wisc.edu/~ece533/images/airplane.png",
                        "name" : "Mona Lisa",
                        "artist" : "John Smith",
                        "description" : "Some random description about the artwork will go here.",
                        "price" : 100,
                        "quantity" : 1,
                    },
                    {
                        "imageURL" : "https://homepages.cae.wisc.edu/~ece533/images/airplane.png",
                        "name" : "Mona Lisa",
                        "artist" : "John Smith",
                        "description" : "Some random description about the artwork will go here.",
                        "price" : 100,
                        "quantity" : 1,
                    }
                ]
            };
        },
        methods: {
            ...mapActions(['fetchArtworks', 'addArtworkToCart']),
            addToCart: function(index) {
                this.addArtworkToCart(index);
            }
        },
        created() {
            this.fetchArtworks();
        }
    }
</script>

<style scoped>
    body {
        height: 100%;
        width: 100%;
        background-color: white;
    }
    .card-style {
        margin: 30px;
    }
    .card-container-style {
        box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19);
    }
    .button-container-style {
        display: flex;
        justify-content: space-between;
        align-items: center;
    }
    .artworks-container {
        margin-top: 100px;
        display: flex;
        flex-wrap: wrap;
    }
</style>