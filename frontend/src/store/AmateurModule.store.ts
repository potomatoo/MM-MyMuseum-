import { Module } from "vuex";
import { RootState } from "./index";
import { AmateurModule, Amateur } from "@/store/AmateurInterface.ts";
import { Axios } from "@/service/axios.service";

const module: Module<AmateurModule, RootState> = {
  namespaced: true,

  state: {
    amateurs: [],
    scrollEnd: false,
    searchText: ""
  },

  getters: {},

  mutations: {
    SET_AMATEUR(state, amateurs: Amateur[]) {
      if (state.amateurs === null) {
        state.amateurs = amateurs;
      } else if (amateurs.length && !state.scrollEnd) {
        state.amateurs = state.amateurs?.concat(amateurs);
      } else if (!amateurs.length) {
        state.scrollEnd = true;
      }
    },

    SET_AMATEUR_SEARCHTEXT(state, searchText) {
      state.searchText = searchText;
    },

    SET_AMATEUR_ZERO(state) {
      state.scrollEnd = false;
      state.amateurs = null;
    }
  },

  actions: {
    FETCH_AMATEUR({ commit }, start) {
      Axios.instance
        .get("/api/public/myart/artist", { params: { start } })
        .then(({ data }) => {
          commit("SET_AMATEUR", data.data);
          console.log(data.data);
        })
        .catch(err => console.error(err));
    },

    FETCH_SERCH_AMATEUR({ commit }, { artistName, start }) {
      Axios.instance
        .get("", { params: { artistName, start } })
        .then(({ data }) => {
          commit("SET_AMATEUR", data.data);
          commit("SET_AMATEUR_SEARCHTEXT", artistName);
        })
        .catch(err => console.error(err));
    }
  }
};

export default module;
