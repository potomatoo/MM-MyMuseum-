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
      Museum
    </h2>
    <v-row cols="12" align="center" justify="center" style="margin : 1px 20%">
      <v-text-field
        outlined
        dark
        placeholder="전시공간 검색"
        type="text"
        clearable
        prepend-inner-icon="mdi-magnify"
        v-model="inputText"
        color="white"
        background-color="rgb(80, 70, 60)"
        @keypress.enter="searchMuseum($event)"
      >
      </v-text-field>
    </v-row>

    <!-- 전시공간 별 -->
    <v-row style="margin: 10px 10%">
      <v-container fluid cols="12">
        <v-row>
          <v-col
            v-for="(value, n) in museums"
            :key="n"
            class="d-flex child-flex"
            cols="6"
            md="3"
            style="padding: 20px"
          >
            <v-hover v-slot:default="{ hover }">
              <v-card
                flat
                tile
                class="d-flex"
                :elevation="hover ? 12 : 2"
                :class="{ 'on-hover': hover }"
                :to="{ name: null }"
              >
                <!-- 임시 이미지 입력 -->
                <v-img
                  v-if="value.museumUrl == null"
                  :src="require(`@/assets/dummydata/category/museum.jpg`)"
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
                      {{ value.museumName }}
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
import { Museum } from "../../../store/MuseumInterface";

const museumModule = namespace("museumModule");

@Component
export default class MuseumList extends Vue {
  @museumModule.State museums!: Museum[] | null;
  @museumModule.Action FETCH_MUSEUM: any;
  @museumModule.Action FETCH_SERCH_MUSEUM: any;

  inputText!: "";
  start = 0;

  created() {
    this.FETCH_MUSEUM(this.start);
  }

  searchMuseum($event: KeyboardEvent) {
    this.FETCH_SERCH_MUSEUM({ museumName: this.inputText, start: this.start });
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
