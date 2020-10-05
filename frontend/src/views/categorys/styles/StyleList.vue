<template>
  <div>
    <!-- 윗공간 -->
    <v-responsive class="mx-auto mb-12" width="56">
      <v-divider class="mb-1"></v-divider>
      <v-divider></v-divider>
    </v-responsive>
    <!-- 제목 -->
    <h2
      class="display-2 font-weight-bold mb-3 text-uppercase text-center"
      style="color:white"
    >
      Style
    </h2>
    <v-row
      cols="12"
      sm="6"
      offset-sm="3"
      align="center"
      justify="center"
      style="margin : 1px 20%"
    >
      <v-text-field
        outlined
        dark
        placeholder="화풍 검색"
        type="text"
        clearable
        prepend-inner-icon="mdi-magnify"
        v-model="inputText"
        color="white"
        background-color="rgb(80, 70, 60)"
        @keypress.enter="searchStyle($event)"
      >
      </v-text-field>
    </v-row>
    <!-- 화풍별 -->
    <v-row style="margin: 10px 10%" cols="12" sm="6" offset-sm="3">
      <v-container fluid cols="12">
        <v-row>
          <v-col
            v-for="(genre, n) in styles"
            :key="n"
            class="d-flex child-flex"
            cols="3"
          >
            <v-hover v-slot:default="{ hover }">
              <v-card
                flat
                tile
                class="d-flex"
                :elevation="hover ? 12 : 2"
                :class="{ 'on-hover': hover }"
                @click="moveDetail(genre.genreName, 0)"
              >
                <v-img
                  :src="genre.genreUrl"
                  aspect-ratio="1"
                  class="grey lighten-2 artist-card"
                >
                  <template v-slot:placeholder>
                    <v-row
                      class="fill-height ma-0"
                      align="center"
                      justify="center"
                    >
                      <v-progress-circular
                        indeterminate
                        color="grey lighten-5"
                      ></v-progress-circular>
                    </v-row>
                  </template>

                  <v-expand-transition>
                    <div
                      v-if="hover"
                      class="d-flex transition-fast-in-fast-out darken-2 v-card--reveal display-1 white--text black text-center"
                      style="width: 100%; height: 100%;"
                    >
                      {{ genre.genreName }}
                    </div>
                  </v-expand-transition>
                </v-img>
              </v-card>
            </v-hover>
          </v-col>
        </v-row>
      </v-container>
    </v-row>
  </div>
</template>

<script lang="ts">
import { Component, Vue } from "vue-property-decorator";

import { namespace } from "vuex-class";
import { Style } from "../../../store/StyleInterface";

const styleModule = namespace("styleModule");
const DetailModule = namespace("DetailModule");

@Component
export default class StyleList extends Vue {
  @styleModule.State styles!: Style[] | null;
  @DetailModule.Mutation SET_GENRE!: any;
  @styleModule.Action FETCH_STYLE: any;
  @styleModule.Action FETCH_SERCH_STYLE: any;

  inputText = "";

  created() {
    this.FETCH_STYLE();
  }

  searchStyle($event: KeyboardEvent) {
    this.FETCH_SERCH_STYLE(this.inputText);
  }

  moveDetail(genre: Style) {
    this.SET_GENRE(genre);
    this.$router.push({
      name: "DetailGenreView",
      params: { genre: genre.genreName }
    });
  }
}
</script>

<style scoped>
.v-card {
  transition: opacity 0.4s ease-in-out;
}

.v-card:not(.on-hover) {
  opacity: 0.9;
}

.v-card--reveal {
  align-items: center;
  bottom: 0;
  justify-content: center;
  opacity: 0.5;
  position: relative;
  width: 100%;
}

.artist-card {
  box-shadow: 0px 0px 10px 5px;
}
</style>
