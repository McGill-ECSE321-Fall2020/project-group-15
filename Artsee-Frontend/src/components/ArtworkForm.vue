<template>
    <div>
        <head>
        <title>New Artwork</title>
        </head>
        <body>
            <div class="navbarContainer">
                <Navbar navMode="false" />
            </div>
            <div class="card-container">
                <div class="card w-25">
                    <div class="card-body">
                      <div class="header-container">
                          <h3>Artwork Form</h3>
                        </div>
                        <form>
                            <div class="form-group">
                                <input type="text" class="form-control" id="name" placeholder="Artwork Name">
                            </div>
                            <div class="form-group">
                                <input type="text" class="form-control" id="description" placeholder="Description">
                            </div>
                            <div class="form-group">
                                <input type="text" class="form-control" id="price" placeholder="Price">
                            </div>
                            <div class="form-group">
                                <input type="date" class="form-control" id="date" placeholder="Date of Creation">
                            </div>
                            <div class="form-group">
                                <input type="text" class="form-control" id="num" placeholder="Number in Stock">
                            </div>
                            <div class="button-container">
                                <div>
                                    <button type="submit" class="btn btn-primary">Post Artwork</button>
                                </div>
                            </div>
                        </form>
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
    }
}
</script>

<style scoped>
  .card-container {
      display: flex;
      justify-content: center;
      align-items: center;
      margin-bottom: 50px;
  }

  .header-container {
      width: 100%;
      display: flex;
      justify-content: center;
  }

  .button-container {
        display: flex;
        justify-content: space-around;
  }

  button {
      height: 40px;
      width: 170px;
  }

  .navbarConatiner {
    margin-bottom: 20px;
  }
</style>