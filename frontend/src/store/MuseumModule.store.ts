import { Module } from "vuex";
import { RootState } from "./index";
import { MuseumModule, Museum } from "@/store/MuseumInterface.ts";
import { Axios } from "@/service/axios.service";
import qs from "qs";

const module: Module<MuseumModule, RootState> = {
  namespaced: true,

  state: {
    museums: []
  },

  getters: {},

  mutations: {
    SET_MUSEUM(state, museums: Museum[]) {
      state.museums = museums;
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
        //불러온 데이터가 null이 아니라면 할당
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
