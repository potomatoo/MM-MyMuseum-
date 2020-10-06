import { Module } from "vuex";
import { RootState } from ".";
import { RecommendationModule } from "./Recommendation.interface";
import { Axios } from "@/service/axios.service";

const module: Module<RecommendationModule, RootState> = {
  namespaced: true,
  state: {
    arts: null,
    artsByColor: null
  },

  getters: {
    userRecArts(state) {
      return state.arts?.slice(0, 10);
    }
  },

  mutations: {
    SET_ART_LIST(state, arts) {
      console.log("추천작품");
      state.arts = arts;
    },
    SET_ART_LIST_BY_COLOR(state, arts) {
      console.log("컬러별");
      state.artsByColor = arts;
    },
    REMOVE_ART_LIST_BY_COLOR(state) {
      state.artsByColor = null;
    }
  },

  actions: {
    FETCH_ART_LIST({ commit }) {
      Axios.instanceRec
        .get("/api/recommend/user/")
        .then(({ data }) => commit("SET_ART_LIST", data))
        .catch(err => console.error(err));
    },
    FETCH_ART_LIST_BY_COLOR({ commit }, color) {
      Axios.instanceRec
        .get(`/api/recommend/color/${color}/`)
        .then(({ data }) => commit("SET_ART_LIST_BY_COLOR", data))
        .catch(err => console.error(err));
    }
  }
};

export default module;
