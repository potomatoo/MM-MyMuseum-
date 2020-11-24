import Vue from "vue";
import App from "./App.vue";
import router from "./router";
import store from "./store";
import vuetify from "./plugins/vuetify";
import GAuth from "vue-google-oauth2";
import Vuelidate from "vuelidate";

const gauthOption = {  
  clientId:
    `${process.env.APP_VUE_CLIENT_ID}.apps.googleusercontent.com`,
  scope: "profile email",
  prompt: "select_account"
};

Vue.use(GAuth, gauthOption);
Vue.use(Vuelidate);
Vue.config.productionTip = false;

new Vue({
  router,
  store,
  vuetify,
  render: h => h(App)
}).$mount("#app");
