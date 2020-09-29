import { Module } from "vuex";
import { Axios } from "@/service/axios.service";
import { DetailModule } from "./Detail.interface";
import { RootState } from "./index";

const module: Module<DetailModule, RootState> = {
  namespaced: true,
  state: {},
  getters: {},
  mutations: {},
  actions: {}
};

export default module;
