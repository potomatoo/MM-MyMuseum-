import Vue from "vue";
import Vuex, { StoreOptions } from "vuex";
import AccountsModule from "./AccountsModule.store";
import styleModule from "./StyleModule.store";
import museumModule from "./MuseumModule.store";
import artistModule from "./ArtistModule.store";
import DetailModule from "./DetailModule.store";
import amateurModule from "./AmateurModule.store";

//삭제예정
import articleModule from "./ArticleModule.store";
Vue.use(Vuex);

export interface RootState {
  version: string;
}

const store: StoreOptions<RootState> = {
  state: {
    version: "1.0.0" // a simple property
  },
  modules: {
    AccountsModule,
    DetailModule,
    styleModule,
    museumModule,
    artistModule,
    amateurModule,
    //삭제예정
    articleModule
  }
};

export default new Vuex.Store<RootState>(store);
