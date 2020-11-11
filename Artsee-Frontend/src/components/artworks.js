import {axios} from './utils'


export default {
  name: 'artworks',
  data () {
    return {
      artworks: [],
      errorArtwork = '',
      response: []
    }
  },
  created: function () {
    // Initializing artworks from backend
    axios.get('/artworks')
    .then(response => {
      // JSON responses are automatically parsed.
      this.artworks = response.data
    })
    .catch(e => {
      this.errorArtwork = e
    })
  }
}