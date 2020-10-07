import { Module } from "vuex";
import { RootState } from ".";
import { RecommendationModule } from "./Recommendation.interface";
import { Axios } from "@/service/axios.service";

const module: Module<RecommendationModule, RootState> = {
  namespaced: true,
  state: {
    arts: null,
    artsByColor: null,
    artsByWeather: null,
    artsByTime: null
  },

  getters: {
    userRecArts(state) {
      return state.arts?.slice(0, 10);
    },
    weatherRecArts(state) {
      return state.artsByWeather?.data.slice(0, 10);
    },
    timeRecArts(state) {
      return state.artsByTime?.data.slice(0, 10);
    },
    weatherTitle(state) {
      return state.artsByWeather?.title;
    },
    timeTitle(state) {
      return state.artsByTime?.title;
    }
  },

  mutations: {
    SET_ART_LIST(state, arts) {
      state.arts = arts;
    },
    SET_ART_LIST_BY_COLOR(state, arts) {
      state.artsByColor = arts;
    },
    REMOVE_ART_LIST_BY_COLOR(state) {
      state.artsByColor = null;
    },
    SET_ART_LIST_BY_WEATHER(state, arts) {
      state.artsByWeather = arts;
    },
    SET_ART_LIST_BY_TIME(state, arts) {
      state.artsByTime = arts;
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
    },
    FETCH_ART_LIST_BY_WEATHER({ commit }) {
      Axios.instanceRec
        .get("/api/recommend/weather/")
        .then(({ data }) => {
          commit("SET_ART_LIST_BY_WEATHER", data.data.weather);
          commit("SET_ART_LIST_BY_TIME", data.data.time);
        })
        .catch(err => console.error(err));
    }
  }
};

export default module;
