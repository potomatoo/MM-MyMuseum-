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
    },
    SET_USER_INFO(state, userInfo) {
      state.user = userInfo;
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
          commit("SET_USER_INFO", data.data);
          router.go(-1);
        })
        .catch(err => {
          console.error(err);
          alert("가입하지 않은 아이디이거나, 잘못된 비밀번호입니다.");
        });
    },
    GOOGLE_LOGIN({ commit }, authToken) {
      Axios.instance
        .post("/api/public/google/login", authToken)
        .then(({ data }) => {
          commit("SET_TOKEN", data.data.userPassword);
          commit("SET_USER_INFO", data.data);
          router.go(-1);
        })
        .catch(err => console.error(err));
    },
    LOGOUT({ commit }) {
      Axios.instance
        .get("/api/public/logout")
        .then(res => console.log(res))
        .catch(err => console.error(err));
    },
    FETCH_USER_INFO({ commit }) {
      Axios.instance
        .get("/api/private/user/detail")
        .then(({ data }) => commit("SET_USER_INFO", data.data))
        .catch(err => console.error(err));
    },
    CHECK_EMAIL(_, email) {
      return Axios.instance
        .get("/api/public/user/checkemail", { params: { email } })
        .then(({ data }) => data.data)
        .catch(err => console.error(err));
    }
  }
};

export default module;
