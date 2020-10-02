import { Module } from "vuex";
import { RootState } from "./index";
import { StyleModule, Style } from "@/store/StyleInterface.ts";
import { Axios } from "@/service/axios.service";

const module: Module<StyleModule, RootState> = {
  namespaced: true,

  state: {
    styles: []
  },

  getters: {},

  mutations: {
    SET_STYLE(state, styles: Style[]) {
      state.styles = styles;
    }
  },

  actions: {
    FETCH_STYLE({ commit }) {
      Axios.instance
        .get("api/public/genre/list")
        .then(({ data }) => {
          commit("SET_STYLE", data.data);
        })
        .catch(err => console.error(err));
    },

    FETCH_SERCH_STYLE({ commit }, GenreName) {
      Axios.instance
        .get("/api/public/genre/find", { params: { GenreName } })
        .then(({ data }) => {
          commit("SET_STYLE", data.data);
        })
        .catch(err => console.error(err));
    }
  }
};

export default module;
