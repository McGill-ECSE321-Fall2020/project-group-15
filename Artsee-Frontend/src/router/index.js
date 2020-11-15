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
import Cart from '@/components/Cart'
import OrdersList from '@/components/OrdersList'
import ReviewRow from '@/components/ReviewRow'
import OrderRow from '@/components/OrderRow'
import ArtistRow from '@/components/ArtistRow'
import Checkout from '@/components/Checkout'
import Order from '@/components/Order'
import ArtworkRow from '@/components/ArtworkRow'
import ArtistProfile from '@/components/ArtistProfile';
import ArtworkGallery  from "@/components/ArtworkGallery"
import ArtistList from '@/components/ArtistList'
import EndSignup from '@/components/EndSignup'
import ErrorPage from '@/components/ErrorPage'
import DetailedArtwork from '@/components/DetailedArtwork'
import ReviewStars from '@/components/ReviewStars'

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
      path: '/artworkrow',
      name: 'ArtworkRow',
      component: ArtworkRow
    },
    {
      path: '/profile/artist',
      name: 'ArtistProfile',
      component: ArtistProfile,
      props: true,
    },
    {

      path: '/cart',
      name: 'Cart',
      component: Cart
    },
    {
      path: '/orderlist',
      name: 'OrdersList',
      component: OrdersList
    },
    {

      path: '/reviewrow',
      name: 'ReviewRow',
      component: ReviewRow,
      props: true
    },
    {
      path: '/orderrow',
      name: 'OrderRow',
      component: OrderRow,
      props: true
    },

    {
      path: '/artistrow',
      name: 'artistrow',
      component: ArtistRow,
      props: true
    },

  {
    path: '/cart/checkout',
    name: 'Checkout',
    component: Checkout,
  },

  {
    path:'/settings/order',
    name: 'Order',
    component: Order,
  },

    {
      path: '/artwork-gallery',
      name: 'ArtworkGallery',
      component: ArtworkGallery
    },
    {
      path: '/artists/all',
      name: 'ArtistList',
      component: ArtistList
    },
    {
      path: '/signup/thank-you',
      name: 'EndSignup',
      component: EndSignup
    },
    {
      path: '/404',
      name: 'ErrorPage',
      component: ErrorPage
    },
    {
      path: '/artwork/item',
      name: 'DetailedArtwork',
      component: DetailedArtwork,
      props: true,
    },
    {
      path: '/stars',
      name: 'ReviewStars',
      component: ReviewStars,
      props: true
    }

  ]
})
