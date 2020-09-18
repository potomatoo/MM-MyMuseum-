import { Module } from "vuex";
import { RootState } from "./index";
import { ArticleModule, Article } from "@/store/ArticleInterface.ts";

const module: Module<ArticleModule, RootState> = {
  namespaced: true,

  state: {
    articles: []
  },

  getters: {},

  mutations: {
    SET_ARTICLE(state) {
      state.articles = require("@/data/articles.json");
      console.log(state.articles);
    }
  },

  actions: {}
};

export default module;
