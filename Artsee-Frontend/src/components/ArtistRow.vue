<template>
  <section class="py-5" id="itemSection">
    <link
      href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.3/css/font-awesome.min.css"
      rel="stylesheet"
      integrity="sha384-T8Gy5hrqNKT+hzMclPo118YTQO6cYprQmhrYwIiQ/3axmI1hQomh7Ud2hPOy8SP1"
      crossorigin="anonymous"
    />
    <link rel="icon" href="favicon.ico" />
    <div class="container">
      <div class="row mb-3">
        <div class="col-md-8">
          <div class="card shadow">
            <div class="card-body">
              <div class="row text-center">
                <div class="col-ml-1 mx-2" id="image-cropper">
                  <img
                    v-if = "profilePictureURL"
                    :src="profilePictureURL"
                    width="100"
                    alt=""
                    loading="lazy"
                    id="profile-pic"
                  />
                  <img
                    v-else 
                    src= "https://i0.wp.com/www.beerleagueheroes.com/wp-content/uploads/2019/04/mystery-person-png-mystery-customer-person-9LKwzI-clipart.png?fit=750%2C481&ssl=1"
                    width="100"
                    alt=""
                    loading="lazy"
                    id="profile-pic"
                  />
                </div>
                <div class="col-md-3">
                  <h4>{{firstName}} {{lastName}}</h4>
                  <div class="rating-block">
                    <ReviewStars v-bind:rating="rating"/>
                  </div>
                </div>
                <div class="col-md-4">
                  <p>{{ artistDescription }}</p>
                </div>

                <div class="col-md-3">
                  <h3></h3>
                  <div class="sub-row">
                    <button
                      type="button"
                      class="btn btn-warning btn-sm btn-block"
                      id="view-more-button"
                      @click="$router.push({name: 'ArtistProfile', params: {artistID: artistID },})">View profile
                    </button>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </section>
</template>

<script>
import ReviewStars from '@/components/ReviewStars'
import axios from 'axios'
var config = require('../../config')

var backendConfigurer = function(){
  switch(process.env.NODE_ENV){
      case 'development':
          return 'http://' + config.dev.backendHost + ':' + config.dev.backendPort;
      case 'production':
          return 'https://' + config.build.backendHost + ':' + config.build.backendPort ;
  }
};

var frontendConfigurer = function(){
  switch(process.env.NODE_ENV){
      case 'development':
          return 'http://' + config.dev.host + ':' + config.dev.port;
      case 'production':
          return 'https://' + config.build.host + ':' + config.build.port ;
  }
};
var backendUrl = backendConfigurer();
var frontendUrl = frontendConfigurer();

var AXIOS = axios.create({
  baseURL: backendUrl,
  //headers: { 'Access-Control-Allow-Origin': frontendUrl }
})

export default {
  props: {
    artistID: {
      default: "notfound",
      type: String
    }
  },
  data() {
    return {
      profilePictureURL: null,
      artistDescription: "Description not found",
      firstName: 'first name',
      lastName: 'last name',
      rating: 0,
      artistError: "",
    };
  },

  created: function () {
      this.fetch()
      this.updateStars(this.rating)
      this.star1 = true  
  },

  methods: {
    fetch (){
      AXIOS.get('/artists/' + this.artistID.toString())
        .then(response => {
        // JSON responses are automatically parsed.
          this.profilePictureURL = response.data.profilePictureURL

          if (response.data.artistDescription != "" && response.data.artistDescription !=null) {
            this.artistDescription = response.data.artistDescription
          }

          this.firstName = response.data.firstName
          this.lastName = response.data.lastName
          this.rating = response.data.rating
        })
        .catch(e => {
          var errorMsg = e.response.data
          console.log(errorMsg)
          this.artistError = errorMsg
        })
    },

    updateStars: function (starRating) {
      if (starRating >= 1) {
        this.star1 = true;
      }

      if (starRating >= 2) {
        this.star2 = true;
      }

      if (starRating >= 3) {
        this.star3 = true;
      }

      if (starRating >= 4) {
        this.star4 = true;
      }

      if (starRating >= 5) {
        this.star5 = true;
      }
    },
  },
  components: {
    ReviewStars
  }
};
</script>

<style scoped>
.container {
  background: transparent;
  max-width: 1200px;
}

#itemSection {
  background-color: white;
}

#view-more-button {
  position: relative;
  top: 30px;
}
#image-cropper {
  width: 100px;
  height: 100px;
  position: relative;
  overflow: hidden;
  border-radius: 50%;
}
#profile-pic {
  display: inline;
  margin: 0 auto;
  margin-left: -30%;
  height: 100%;
  width: auto;
}

.btn-grey {
  background-color: #D8D8D8;
  color: #FFF;
}
.rating-block {
  background-color: #ffffff;
  border: 1px solid #ffffff;
  border-radius: 3px;
}
.bold {
  font-weight: 700;
}
.padding-bottom-7 {
  padding-bottom: 7px;
}

.review-block {
  background-color: #FAFAFA;
  border: 1px solid #EFEFEF;
  padding: 15px;
  border-radius: 3px;
  margin-bottom: 15px;
}
.review-block-name {
  font-size: 12px;
  margin: 10px 0;
}
.review-block-date {
  font-size: 12px;
}
.review-block-rate {
  font-size: 13px;
  margin-bottom: 15px;
}
.review-block-title {
  font-size: 15px;
  font-weight: 700;
  margin-bottom: 10px;
}
.review-block-description {
  font-size: 13px;
}

.py-5 {
  padding-bottom: 0!important;
}
</style>