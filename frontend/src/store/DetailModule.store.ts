import { Module } from "vuex";
import { RootState } from "./index";
import { Axios } from "@/service/axios.service";
import { DetailModule, Art } from "./Detail.interface";

const module: Module<DetailModule, RootState> = {
  namespaced: true,
  state: {
    artList: null
  },
  getters: {},
  mutations: {
    SET_ART_LIST(state, artList: Art[]) {
      state.artList = artList;
    }
  },
  actions: {
    FETCH_ART_LIST(
      { commit },
      { artist, start }: { artist: string; start: number }
    ) {
      Axios.instance
        .get("/api/public/art/artist", { params: { artist, start } })
        .then(({ data }) => commit("SET_ART_LIST", data.data))
        .catch(err => console.error(err));
    }
  }
};

export default module;
