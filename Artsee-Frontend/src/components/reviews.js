import AXIOS from './utils'


export default {
  name: 'reviews',
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