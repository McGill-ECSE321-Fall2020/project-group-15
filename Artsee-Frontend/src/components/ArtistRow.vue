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
                    :src="imageURL"
                    width="100"
                    alt=""
                    loading="lazy"
                    id="profile-pic"
                  />
                </div>
                <div class="col-md-3">
                  <h4>John Doe</h4>
                  <div class="rating-block">
                    <button
                      type="button"
                      class="btn btn-sm"
                      v-bind:class="{
                        'btn-warning': this.star1,
                        'btn-default btn-grey': !this.star1,
                      }"
                      aria-label="Left Align"
                    >
                      <span
                        class="glyphicon glyphicon-star"
                        aria-hidden="true"
                      ></span>
                    </button>
                    <button
                      type="button"
                      class="btn btn-sm"
                      v-bind:class="{
                        'btn-warning': this.star2,
                        'btn-default btn-grey': !this.star2,
                      }"
                      aria-label="Left Align"
                    >
                      <span
                        class="glyphicon glyphicon-star"
                        aria-hidden="true"
                      ></span>
                    </button>
                    <button
                      type="button"
                      class="btn btn-sm"
                      v-bind:class="{
                        'btn-warning': this.star3,
                        'btn-default btn-grey': !this.star3,
                      }"
                      aria-label="Left Align"
                    >
                      <span
                        class="glyphicon glyphicon-star"
                        aria-hidden="true"
                      ></span>
                    </button>
                    <button
                      type="button"
                      class="btn btn-sm"
                      v-bind:class="{
                        'btn-warning': this.star4,
                        'btn-default btn-grey': !this.star4,
                      }"
                      aria-label="Left Align"
                    >
                      <span
                        class="glyphicon glyphicon-star"
                        aria-hidden="true"
                      ></span>
                    </button>
                    <button
                      type="button"
                      class="btn btn-sm"
                      v-bind:class="{
                        'btn-warning': this.star5,
                        'btn-default btn-grey': !this.star5,
                      }"
                      aria-label="Left Align"
                    >
                      <span
                        class="glyphicon glyphicon-star"
                        aria-hidden="true"
                      ></span>
                    </button>
                  </div>
                </div>
                <div class="col-md-4">
                  <p>{{ description }}</p>
                </div>

                <div class="col-md-3">
                  <h3></h3>
                  <div class="sub-row">
                    <button
                      type="button"
                      class="btn btn-warning btn-sm btn-block"
                      id="view-more-button"
                    >
                      View profile
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
export default {
  data() {
    return {
      imageURL: "https://c8.alamy.com/comp/KTHWN4/man-has-a-poker-face-KTHWN4.jpg",
      description: "some description here",
      star1: false,
      star2: false,
      star3: false,
      star4: false,
      star5: false,
      rating: 3
    };
  },

  created: function () {
      this.updateStars(this.rating)
      this.star1 = true
  },

  methods: {
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
};
</script>

<style>
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
  margin-left: -10%;
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
</style>