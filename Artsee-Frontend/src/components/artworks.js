import AXIOS from './utils'


export default {
  name: 'artworks',
  data () {
    return {
      artworks: [],
      errorArtwork: '',
      response: []
    }
  },
  created: function () {
    // Initializing artworks from backend
    AXIOS.get('/artworks')
    .then(response => {
      // JSON responses are automatically parsed.
      this.artworks = response.data
    })
    .catch(e => {
      this.errorArtwork = e
    })
  }
}