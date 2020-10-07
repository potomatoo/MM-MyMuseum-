import Vue from "vue";
import VueRouter, { RouteConfig } from "vue-router";
import Home from "@/views/Home.vue";
import LoginView from "@/views/accounts/LoginView.vue";
import SignupView from "@/views/accounts/SignupView.vue";
import MyPageView from "@/views/accounts/MyPageView.vue";
import DetailArtistView from "@/views/detail/DetailArtistView.vue";
import DetailMuseumView from "@/views/detail/DetailMuseumView.vue";
import DetailGenreView from "@/views/detail/DetailGenreView.vue";
import DetailArtView from "@/views/detail/DetailArtView.vue";
import MyGalleryListView from "@/views/mygallery/MyGalleryListView.vue";
import MygalleryView from "@/views/mygallery/MygalleryView.vue";

//recommendation
import RecommendationPage from "@/views/recommendations/RecommendationPage.vue";
import ArtListView from "@/views/recommendations/ArtListView.vue";
import ArtListByColorView from "@/views/recommendations/ArtListByColorView.vue";

//categorys
import CategoryList from "@/views/categorys/CategoryList.vue";
import ArtistList from "@/views/categorys/artists/ArtistList.vue";
import MuseumList from "@/views/categorys/museums/MuseumList.vue";
import StyleList from "@/views/categorys/styles/StyleList.vue";
//userartview
import AmateurArtistView from "@/views/categorys/amateurart/AmateurArtistView.vue";
import AmateurArtView from "@/views/categorys/amateurart/AmateurArtView.vue";
import AmateurArtUpload from "@/views/categorys/amateurart/AmateurArtUpload.vue";
import DetailAmateurArt from "@/views/categorys/amateurart/DetailAmateurArt.vue";

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
    path: "/accounts/mypage",
    name: "MyPage",
    component: MyPageView
  },
  {
    path: "/artist/:artist",
    name: "DetailArtistView",
    component: DetailArtistView
  },
  {
    path: "/museum/:museum",
    name: "DetailMuseumView",
    component: DetailMuseumView
  },
  {
    path: "/genre/:genre",
    name: "DetailGenreView",
    component: DetailGenreView
  },
  {
    path: "/art/:artNo",
    name: "DetailArtView",
    component: DetailArtView
  },
  {
    path: "/mygallery",
    name: "MyGalleryListView",
    component: MyGalleryListView
  },
  {
    path: "/mygallery/:artNo",
    name: "MyGalleryView",
    component: MygalleryView
  },
  //category
  {
    path: "/categorys/categorylist",
    name: "CategoryList",
    component: CategoryList
  },
  {
    path: "/categorys/artistlist",
    name: "ArtistList",
    component: ArtistList
  },
  {
    path: "/categorys/museumlist",
    name: "MuseumList",
    component: MuseumList
  },
  {
    path: "/categorys/stylelist",
    name: "StyleList",
    component: StyleList
  },
  {
    path: "/recommend",
    name: "RecommendView",
    component: RecommendationPage
  },
  {
    path: "/recommend/arts",
    name: "RecArtList",
    component: ArtListView
  },
  {
    path: "/recommend/weather",
    name: "WeatherArtsList",
    component: ArtListView
  },
  {
    path: "/recommend/time",
    name: "TimeArtsList",
    component: ArtListView
  },
  {
    path: "/color/:color",
    name: "ArtListByColor",
    component: ArtListByColorView
  },
  {
    path: "/categorys/amateurartist",
    name: "AmateurArtistView",
    component: AmateurArtistView
  },
  {
    path: "/categorys/amateurart/:userId",
    name: "AmateurArtView",
    component: AmateurArtView
  },
  {
    path: "/amateur/upload",
    name: "AmateurArtUpload",
    component: AmateurArtUpload
  },
  {
    path: "/amateur/detail/:myartNo",
    name: "DetailAmateurArt",
    component: DetailAmateurArt
  }
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes
});

router.beforeEach((to, from, next) => {
  const authRequiredPages = ["MyPage", "MyGalleryView"];
  const authRequired = authRequiredPages.includes(to.name!);
  const isLoggedIn = !!window.sessionStorage.getItem("jwt-token");
  const unAuthRequiredPages = ["Signup", "Login"];
  const unAuthRequired = unAuthRequiredPages.includes(to.name!);
  console.log(authRequired, isLoggedIn);

  if (authRequired && !isLoggedIn) {
    console.log("로그인으로");
    next({ name: "Login" });
  } else {
    console.log("그대로");
    next();
  }
  unAuthRequired && isLoggedIn ? next({ name: "Home" }) : next();
});

export default router;
