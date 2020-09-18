import Vue from "vue";
import App from "./App.vue";
import router from "./router";
import store from "./store";
import vuetify from "./plugins/vuetify";
import GAuth from "vue-google-oauth2";

const gauthOption = {
  clientId:
    "476248660063-e2gk89ukcim2la7mbttisi10pq9ck5r6.apps.googleusercontent.com",
  scope: "profile email",
  prompt: "select_account"
};

Vue.use(GAuth, gauthOption);

Vue.config.productionTip = false;

new Vue({
  router,
  store,
  vuetify,
  render: h => h(App)
}).$mount("#app");
