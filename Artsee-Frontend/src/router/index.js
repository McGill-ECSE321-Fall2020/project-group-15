import Vue from 'vue'
import Router from 'vue-router'
import Hello from '@/components/Hello'
import Login from '@/components/Login'
import Signup from '@/components/Signup'
import Profile from '@/components/Profile'
import ArtistProfile from '@/components/ArtistProfile'
import Navbar from '@/components/Navbar'
import SignupCustomer from '@/components/SignupCustomer'
import SignupArtist from '@/components/SignupArtist'
import SignupAdministrator from '@/components/SignupAdministrator'
import ItemListing from '@/components/ItemListing'

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
      path: '/profile',
      name: 'Profile',
      component: Profile
    },
    {
      path: '/artistprofile',
      name: 'ArtistProfile',
      component: ArtistProfile
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
      path: '/item',
      name: 'itemList',
      component: ItemListing
    }

  ]
})
