import { Module } from "vuex";
import { RootState } from "./index";
import { MuseumModule, Museum } from "@/store/MuseumInterface.ts";
import { Axios } from "@/service/axios.service";

const module: Module<MuseumModule, RootState> = {
  namespaced: true,

  state: {
    museums: [],
    scrollEnd: false
  },

  getters: {},

  mutations: {
    SET_MUSEUM(state, museums: Museum[]) {
      if (state.museums === null) {
        state.museums = museums;
      } else if (museums.length && !state.scrollEnd) {
        state.museums = state.museums?.concat(museums);
      } else if (!museums.length) {
        state.scrollEnd = true;
      }
    },

    SET_MUSEUM_ZERO(state) {
      state.scrollEnd = false;
      state.museums = null;
    }
  },

  actions: {
    FETCH_MUSEUM({ commit }, start) {
      Axios.instance
        .get("api/public/museum/list", { params: { start } })
        .then(({ data }) => {
          commit("SET_MUSEUM", data.data);
        })
        .catch(err => console.error(err));
    },

    FETCH_SERCH_MUSEUM({ commit }, { museumName, start }) {
      console.log(museumName + " " + start);
      Axios.instance
        .get("api/public/museum/find", { params: { museumName, start } })
        .then(({ data }) => {
          if (data != null) {
            commit("SET_MUSEUM", data.data);
          }
        })
        .catch(err => console.error(err));
    }
  }
};

export default module;
