import Vue from "vue";
import VueRouter, { RouteConfig } from "vue-router";
import Home from "@/views/Home.vue";
import LoginView from "@/views/accounts/LoginView.vue";
import SignupView from "@/views/accounts/SignupView.vue";
import DetailArtistView from "@/views/detail/DetailArtistView.vue";

Vue.use(VueRouter);

const routes: Array<RouteConfig> = [
  {
    path: "/",
    name: "Home",
    component: Home
  },
  {
    path: "/accounts/login",
    name: "Login",
    component: LoginView
  },
  {
    path: "/accounts/signup",
    name: "Signup",
    component: SignupView
  },
  {
    path: "/detail/artist",
    name: "DetailArtist",
    component: DetailArtistView
  }
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes
});

export default router;
