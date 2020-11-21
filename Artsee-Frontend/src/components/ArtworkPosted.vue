<template>
    <div>
        <head>
            <title>Artwork Posted</title>
        </head>
        <body>
            <div class="navbarContainer">
                <Navbar navMode="false" />
            </div>
            <div class="card-container">
                <div class="card w-50">
                    <div class="card-body card-style">
                        <div class="top-container">
                            <h1 class="title-style">Artwork added to the store.</h1>
                            <span class="check-style">
                                <svg width="5em" height="5em" viewBox="0 0 16 16" class="bi bi-check2-circle" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                                    <path fill-rule="evenodd" d="M15.354 2.646a.5.5 0 0 1 0 .708l-7 7a.5.5 0 0 1-.708 0l-3-3a.5.5 0 1 1 .708-.708L8 9.293l6.646-6.647a.5.5 0 0 1 .708 0z"/>
                                    <path fill-rule="evenodd" d="M8 2.5A5.5 5.5 0 1 0 13.5 8a.5.5 0 0 1 1 0 6.5 6.5 0 1 1-3.25-5.63.5.5 0 1 1-.5.865A5.472 5.472 0 0 0 8 2.5z"/>
                                </svg>
                            </span>
                        </div>
                        <form>

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

    .navbarConatiner {
        margin-bottom: 20px;
    }

    .card-container {
        margin-top: 50px;
        display: flex;
        justify-content: center;
        align-items: center;
        margin-bottom: 50px;
    }
    .top-container {
        display: flex;
        align-items: center;
        flex-direction: column;
    }
    .check-style {
        color: #08B202
    }
    .card-style {
        height: 400px;
        display: flex;
        flex-direction: column;
        align-items: center;
        justify-content: space-between;
    }
    .title-style{
        margin-bottom: 30px;
    }
</style>