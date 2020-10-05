import { Module } from "vuex";
import { RootState } from ".";
import { RecommendationModule } from "./Recommendation.interface";
import { Axios } from "@/service/axios.service";

const module: Module<RecommendationModule, RootState> = {
  namespaced: true,
  state: {
    arts: null
  },

  getters: {
    userRecArts(state) {
      return state.arts?.slice(0, 10);
    }
  },

  mutations: {
    SET_ART_LIST(state, arts) {
      console.log(arts);
      state.arts = arts;
    }
  },

  actions: {
    FETCH_ART_LIST({ commit }) {
      Axios.instanceRec
        .get("/api/recommend/user/")
        .then(({ data }) => commit("SET_ART_LIST", data))
        .catch(err => console.error(err));
    }
  }
};

export default module;
