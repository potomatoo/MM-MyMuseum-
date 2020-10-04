import { Module } from "vuex";
import { RootState } from "./index";
import { AccountsModule } from "./Accounts.interface";
import { Axios } from "@/service/axios.service";

const module: Module<AccountsModule, RootState> = {
  namespaced: true,
  state: {
    user: null
  },

  getters: {},

  mutations: {},

  actions: {
    SIGNUP(_, { userId, userPassword }) {
      Axios.instance
        .post("/api/public/signup", { userId, userPassword })
        .then(res => console.log(res))
        .catch(err => console.error(err));
    }
  }
};

export default module;
