import Vue from 'vue'
import Router from 'vue-router'
import Login from '@/components/Login'
import Signup from '@/components/Signup'
import CustomerSettings from '@/components/CustomerSettings'
import ArtistSettings from '@/components/ArtistSettings'
import Navbar from '@/components/Navbar'
import SignupCustomer from '@/components/SignupCustomer'
import SignupArtist from '@/components/SignupArtist'
import SignupAdministrator from '@/components/SignupAdministrator'
import Reviews from '@/components/Reviews'
import ArtworkForm from '@/components/ArtworkForm'
import MyArtworks from '@/components/MyArtworks'
import ItemListing from '@/components/ItemListing'
import ReviewRow from '@/components/ReviewRow'
import OrderRow from '@/components/OrderRow'
import ArtistRow from '@/components/ArtistRow'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'Login',
      component: Login
    },
    {
      path: '/signup',
      name: 'Signup',
      component: Signup
    },
    {
      path: '/settings/customer',
      name: 'CustomerSettings',
      component: CustomerSettings
    },
    {
      path: '/settings/artist',
      name: 'ArtistSettings',
      component: ArtistSettings
    },
    {
      path: '/nav',
      name: 'Navbar',
      component: Navbar
    },
    {
      path: '/signup/customer',
      name: 'SignupCustomer',
      component: SignupCustomer
    },
    {
      path: '/signup/artist',
      name: 'SignupArtist',
      component: SignupArtist
    },
    {
      path: '/signup/administrator',
      name: 'SignupAdministrator',
      component: SignupAdministrator
    },
    {
      path: '/reviews',
      name: 'Reviews',
      component: Reviews
    },
    {
      path: '/artwork/new',
      name: 'ArtworkForm',
      component: ArtworkForm
    },
    {
      path: '/me/artworks',
      name: 'MyArtworks',
      component: MyArtworks
    },
    {
      path: '/item',
      name: 'itemList',
      component: ItemListing
    },
    {
      path: '/reviewitem',
      name: 'reviewRow',
      component: ReviewRow
    },
    {
      path: '/orderitem',
      name: 'orderRow',
      component: OrderRow
    },
    {
      path: '/artistrow',
      name: 'artistrow',
      component: ArtistRow
    }


  ]
})
