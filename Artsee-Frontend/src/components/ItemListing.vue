<template>
  <section class="py-5" id="itemSection">
      <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.3/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-T8Gy5hrqNKT+hzMclPo118YTQO6cYprQmhrYwIiQ/3axmI1hQomh7Ud2hPOy8SP1" crossorigin="anonymous">
        <div class="container">
      <div class="row xl-6">
        <div class="col-md-8">
          <div class="card shadow">
            <div class="card-body">
              <div class="row text-center">
                <div class="col-ml-1 mx-2">
                  <img src="https://cdn.mos.cms.futurecdn.net/SSsejZ9krDiV9N8TExmRGZ-320-80.jpg" width="100" alt="" loading="lazy">
                </div>
                <div class="col-md-6" id="infoBox">
                  <h4>Mona Lisa</h4> <h6> Leonardo </h6>
                  <p>This is pretty alright i guess. i mean idk about you but seems kinda overpriced...</p>
                </div>
                <div class="col-md-3" id="priceButton">
                  <h3>$3</h3>
                  <p> </p>
                  <p> </p>
                  <div class="sub-row">
                    <button
                      type="button"
                      class="btn btn-warning btn-sm btn-block"
                    >
                      View Details
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
export default {};
</script>

<style scoped>
.container {
    background: transparent;
    max-width: 1000px;
}

#itemSection {
    background-color: white;
}

#priceButton {
    margin-top: 20px;
}
</style>