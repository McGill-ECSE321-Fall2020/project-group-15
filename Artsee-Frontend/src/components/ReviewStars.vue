<template>
  <div>
    <link
      href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css"
      rel="stylesheet"
    />
    <i class="fa fa-star" v-bind:class="{'text-warning': this.rating>=1, 'text-muted': this.rating<1}"></i>
    <i class="fa fa-star" v-bind:class="{'text-warning': this.rating>=2, 'text-muted': this.rating<2}"></i>
    <i class="fa fa-star" v-bind:class="{'text-warning': this.rating>=3, 'text-muted': this.rating<3}"></i>
    <i class="fa fa-star" v-bind:class="{'text-warning': this.rating>=4, 'text-muted': this.rating<4}"></i>
    <i class="fa fa-star" v-bind:class="{'text-warning': this.rating>=5, 'text-muted': this.rating<5}"></i>
  </div>
</template>

<script>
export default {
  props: {
      rating: {
          default: -1,
      }
  },
};
</script>
<style scoped>

.fa-star {
    font-size: 0.75em;
}
</style>