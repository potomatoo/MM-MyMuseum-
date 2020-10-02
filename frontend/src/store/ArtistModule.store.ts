import { Module } from "vuex";
import { RootState } from "./index";
import { ArtistModule, Artist } from "@/store/ArtistInterface.ts";
import { Axios } from "@/service/axios.service";

const module: Module<ArtistModule, RootState> = {
  namespaced: true,

  state: {
    artists: []
  },

  getters: {},

  mutations: {
    SET_ARTIST(state, artists: Artist[]) {
      state.artists = artists;
    }
  },

  actions: {
    FETCH_ARTIST({ commit }, start) {
      Axios.instance
        .get("api/public/artist/list", { params: { start } })
        .then(({ data }) => {
          commit("SET_ARTIST", data.data);
        })
        .catch(err => console.error(err));
    },

    FETCH_SERCH_ARTIST({ commit }, { artistName, start }) {
      console.log(artistName + " " + start);
      Axios.instance
        .get("api/public/artist/find", { params: { artistName, start } })
        //불러온 데이터가 null이 아니라면 할당
        .then(({ data }) => {
          commit("SET_ARTIST", data.data);
        })
        .catch(err => console.error(err));
    }
  }
};

export default module;
