import { Module } from "vuex";
import { RootState } from "./index";
import { ArtistModule, Artist } from "@/store/ArtistInterface.ts";
import { Axios } from "@/service/axios.service";

const module: Module<ArtistModule, RootState> = {
  namespaced: true,

  state: {
    artists: [],
    scrollEnd: false,
    searchText: ""
  },

  getters: {},

  mutations: {
    SET_ARTIST(state, artists: Artist[]) {
      if (state.artists === null) {
        state.artists = artists;
      } else if (artists.length && !state.scrollEnd) {
        state.artists = state.artists?.concat(artists);
      } else if (!artists.length) {
        state.scrollEnd = true;
      }
    },

    SET_ARTIST_SEARCHTEXT(state, searchText) {
      state.searchText = searchText;
    },

    SET_ARTIST_ZERO(state) {
      state.scrollEnd = false;
      state.artists = null;
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
        .then(({ data }) => {
          commit("SET_ARTIST", data.data);
          commit("SET_ARTIST_SEARCHTEXT", artistName);
        })
        .catch(err => console.error(err));
    }
  }
};

export default module;
