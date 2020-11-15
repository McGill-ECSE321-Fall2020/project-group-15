<template>
  <div>
    <link
      href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css"
      rel="stylesheet"
    />
        <div v-if="false">
            Rating: {{rating}}
            
        </div>
        <i class="fa fa-star" v-bind:class="{'text-warning': this.star1, 'text-muted': !this.star1}"></i>
        <i class="fa fa-star" v-bind:class="{'text-warning': this.star2, 'text-muted': !this.star2}"></i>
        <i class="fa fa-star" v-bind:class="{'text-warning': this.star3, 'text-muted': !this.star3}"></i>
        <i class="fa fa-star" v-bind:class="{'text-warning': this.star4, 'text-muted': !this.star4}"></i>
        <i class="fa fa-star" v-bind:class="{'text-warning': this.star5, 'text-muted': !this.star5}"></i>
  </div>
</template>

<script>
export default {
    props: {
        rating: {
            default: 0,
            type: Number
        }
    },

  data() {
    return {
      star1: false,
      star2: false,
      star3: false,
      star4: false,
      star5: false,
    };
  },

  created: function () {
    this.updateStars(this.rating);
    // this.star1 = true;
  },

  methods: {
    updateStars: function () {
      if (this.rating >= 1) {
        this.star1 = true;
      } 

      if (this.rating >= 2) {
        this.star2 = true;
      }

      if (this.rating >= 3) {
        this.star3 = true;
      }

      if (this.rating >= 4) {
        this.star4 = true;
      }

      if (this.rating >= 5) {
        this.star5 = true;
      }
    },
  },
};
</script>
<style scoped>

.fa-star {
    font-size: 0.75em;
}
</style>