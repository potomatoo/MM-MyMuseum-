import { Module } from "vuex";
import { RootState } from "./index";
import { AccountsModule } from "./Accounts.interface";
import { Axios } from "@/service/axios.service";
import router from "@/router";

const module: Module<AccountsModule, RootState> = {
  namespaced: true,
  state: {
    user: null,
    token: null || window.sessionStorage.getItem("jwt-token")
  },

  getters: {
    isLoggedIn(state) {
      return !!state.token;
    }
  },

  mutations: {
    SET_TOKEN(state, token) {
      state.token = token;
      window.sessionStorage.setItem("jwt-token", token);
      Axios.instance.defaults.headers.common["Authorization"] = token;
      // Axios.instanceRec.defaults.headers.common["Authorization"] = token
    },
    REMOVE_TOKEN(state) {
      state.token = null;
      window.sessionStorage.removeItem("jwt-token");
    }
  },

  actions: {
    SIGNUP(_, { userId, userPassword }) {
      Axios.instance
        .post("/api/public/signup", { userId, userPassword })
        .then(res => router.push({ name: "Login" }))
        .catch(err => console.error(err));
    },
    LOGIN({ commit }, { userId, userPassword }) {
      const userInfo = {
        params: {
          userId,
          userPassword
        }
      };
      Axios.instance
        .post("/api/public/login", null, userInfo)
        .then(({ data }) => {
          commit("SET_TOKEN", data.data.userPassword);
          router.go(-1);
        })
        .catch(err => {
          console.error(err);
          alert("가입하지 않은 아이디이거나, 잘못된 비밀번호입니다.");
        });
    },
    LOGOUT({ commit }) {
      Axios.instance
        .get("/api/public/logout")
        .then(res => console.log(res))
        .catch(err => console.error(err));
    }
  }
};

export default module;
