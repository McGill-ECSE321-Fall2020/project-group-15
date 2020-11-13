import Navbar from '@/components/Navbar'
import axios from 'axios'

var config = require('../../config')

let frontendUrlConfig = function () {
    if (process.env.NODE_ENV === 'production') {
        return 'https://' + config.build.host + ':' + config.build.port
    }
    else {
        return 'http://' + config.dev.host + ':' + config.dev.port
    }
}
let backendUrlConfig = function () {
    if (process.env.NODE_ENV === 'production') {
        return 'https://' + config.build.backendHost + ':' + config.build.backendPort
    }
    else {
        return 'http://' + config.dev.backendHost + ':' + config.dev.backendPort
    }
}
var frontendUrl = frontendUrlConfig()
var backendUrl = backendUrlConfig()

var AXIOS = axios.create({
    baseURL: backendUrl,
    headers: { 'Access-Control-Allow-Origin': frontendUrl }
})

export default {
  name: 'reviews',
  components: {
    Navbar
  },
  data () {
    return {
      reviews: [],
      errorReview: '',
      response: []
    }
  },
  created: function () {
    // Initializing artworks from backend
    AXIOS.get('/reviews')
    .then(response => {
      // JSON responses are automatically parsed.
      this.reviews = response.data
    })
    .catch(e => {
      this.errorReview = e
    })
  }
}