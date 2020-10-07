import { Module } from "vuex";
import { RootState } from "./index";
import {
  AmateurModule,
  Amateur,
  AmateurArt
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
    detail: null
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

    SET_USERINFO(state, { userName }) {
      state.userName = userName;
    },

    SET_AMATEUR_ART_ZERO(state) {
      state.scrollEnd = false;
      state.amateurArts = null;
    },

    SET_DETAIL_AMATEUR_ART(state, detail: AmateurArt) {
      state.detail = detail;
    },

    SET_DETAIL_AMATEUR_ART_ZERO(state) {
      state.detail = null;
    }
  },

  actions: {
    FETCH_AMATEUR({ commit }, start) {
      Axios.instance
        .get("/api/public/myart/artist", { params: { start } })
        .then(({ data }) => {
          commit("SET_AMATEUR", data.data);
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

    UPLOAD_AMATEUR_ART({ commit }, formData) {
      Axios.instance
        .post("/api/private/myart/upload", formData, {
          headers: { "Content-Type": "multipart/form-data" }
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

    FETCH_AMATEUR_ART({ commit }, { start, userId }) {
      Axios.instance
        .get("/api/public/myart/list", { params: { start, userId } })
        .then(({ data }) => {
          commit("SET_AMATEUR_ART", data.data);
        })
        .catch(err => console.error(err));
    },

    FETCH_AMATEUR_NAME({ commit }, { userId }) {
      Axios.instance
        .get("/api/public/user/userinfo", { params: { userId } })
        .then(({ data }) => {
          commit("SET_USERINFO", data.data);
        })
        .catch(err => console.error(err));
    },

    FETCH_DETAIL_AMATEUR_ART({ commit }, myartNo) {
      Axios.instance
        .get("/api/public/myart/detail", { params: { myartNo } })
        .then(({ data }) => {
          commit("SET_DETAIL_AMATEUR_ART", data.data);
        })
        .catch(err => console.error(err));
    }
  }
};

export default module;
