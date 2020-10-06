import { Module } from "vuex";
import { RootState } from "./index";
import {
  AmateurModule,
  Amateur,
  AmateurArt,
  UploadData
} from "@/store/AmateurInterface.ts";
import { Axios } from "@/service/axios.service";
import router from "@/router";

const module: Module<AmateurModule, RootState> = {
  namespaced: true,

  state: {
    amateurs: [],
    amateurArts: [],
    scrollEnd: false,
    searchText: "",
    userId: "",
    userName: "",
    uploadData: null
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
    },

    SET_AMATEUR_ART(state, amateurArts: AmateurArt[]) {
      if (state.amateurArts === null) {
        state.amateurArts = amateurArts;
      } else if (amateurArts.length && !state.scrollEnd) {
        state.amateurArts = state.amateurArts?.concat(amateurArts);
      } else if (!amateurArts.length) {
        state.scrollEnd = true;
      }
    },

    SET_USERINFO(state, { userId, userName }) {
      state.userId = userId;
      state.userName = userName;
    },

    SET_AMATEUR_ART_ZERO(state) {
      state.scrollEnd = false;
      state.amateurArts = null;
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
    },

    UPLOAD_AMATEUR_ART({ commit }, { token, description, file, title, type }) {
      Axios.instance
        .post("/api/private/myart/upload", {
          params: { token, description, file, title, type }
        })
        .then(res => {
          alert("작품 등록이 완료되었습니다.");
          router.push("/categorys/amateurartist");
        })
        .catch(err => {
          console.log("err", err);
          alert("등록 실패입니다.");
        });
    },

    FETCH_AMATEUR_ART({ commit }, { start, userId, userName }) {
      Axios.instance
        .get("/api/public/myart/list", { params: { start, userId } })
        .then(({ data }) => {
          commit("SET_AMATEUR_ART", data.data);
          commit("SET_USERINFO", { userId, userName });
        })
        .catch(err => console.error(err));
    }
  }
};

export default module;
