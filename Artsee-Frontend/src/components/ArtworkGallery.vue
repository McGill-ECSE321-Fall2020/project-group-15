<template>
    <section class="py-5" id="gallery-container">
        <NavBar />
        <div v-if="artistArtworks.length == 0" class="title-style">
            <h1> The Gallery is Empty</h1>
        </div>
        <div v-else class="artworks-container">
            <div v-for="(artwork, index) in artistArtworks" :key="index">
                <div v-if="artwork.numInStock" class="card card-style card-container-style" style="width: 18rem;">
                    <img v-if="artwork.imageURL" class="card-img-top" :src= "artwork.imageURL" alt="Card image cap">
                    <img v-else class="card-img-top" src="@/assets/no-image.png" alt="Card image cap">
                    <div class="card-body">
                        <h5 class="card-title">{{artwork.name}}</h5>
                        <p class="card-text">{{artwork.description}}</p>
                    </div>
                    <ul class="list-group list-group-flush">
                        <li class="list-group-item"><b>Price:</b> $ <span>{{(artwork.price/100).toFixed(2)}}</span></li>
                        <li class="list-group-item"><b>Artist: </b><span>{{artwork.artist.userID}}</span></li>
                    </ul>
                    <div class="details-body details-container-style">
                        <button type="button" class="btn btn-primary detailbutton" @click="$router.push({name: 'DetailedArtwork', params: {artworkID: artwork.id },})">View Details</button>
                    </div>
                    <div class="card-body button-container-style">
                        <button type="button" class="btn btn-primary" @click="addToCart(index)">Add to Cart</button>
                        <p class="card-text"><b>Quantity left: </b>{{artwork.numInStock}}</p>
                    </div>
                </div>
            </div>
        </div>
    </section>
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
    #gallery-container {
        background-color: #FAFAFA;
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
        margin-top: 10px;
        display: flex;
        flex-wrap: wrap;
    }
    .title-style {
        margin-top: 100px;
    }
    .details-container-style{
        margin-top: 10px;
    }
    .detailbutton{
        width: 85%;
    }
</style>