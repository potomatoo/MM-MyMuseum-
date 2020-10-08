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
        @keypress.enter="searchMuseum()"
      >
      </v-text-field>
    </v-row>

    <!-- 전시공간 별 -->
    <div :scrollHeight="scrollHeight">
      <v-row style="margin: 10px 10%">
        <v-row>
          <v-col
            v-for="(museum, n) in museums"
            :key="n"
            class="d-flex child-flex"
            cols="12"
            md="3"
            sm="6"
          >
            <v-hover v-slot:default="{ hover }">
              <v-card
                flat
                tile
                class="d-flex"
                :elevation="hover ? 12 : 2"
                :class="{ 'on-hover': hover }"
                @click="moveDetail(museum.museumName)"
              >
                <v-img
                  :src="museum.museumUrl"
                  aspect-ratio="1"
                  class="grey lighten-2 artist-card"
                  @mouseenter="zoomIn"
                  @mouseleave="zoomOut"
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
                      {{ museum.museumName }}
                    </div>
                  </v-expand-transition>
                </v-img>
              </v-card>
            </v-hover>
          </v-col>
        </v-row>
      </v-row>
    </div>
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
  @museumModule.State scrollEnd!: boolean;
  @museumModule.Mutation SET_MUSEUM_ZERO: any;
  @museumModule.State searchText: any;
  @museumModule.Mutation SET_MUSEUM_SEARCHTEXT: any;

  inputText = "";
  start = 0;
  scrollHeight = 0;
  searchstart = 0;

  key = "";

  created() {
    if (this.searchText) {
      this.FETCH_SERCH_MUSEUM({
        museumName: this.searchText,
        start: this.searchstart
      });
    } else {
      this.FETCH_MUSEUM(this.start);
    }
  }

  searchMuseum() {
    this.SET_MUSEUM_ZERO();
    if (this.inputText) {
      this.SET_MUSEUM_SEARCHTEXT(this.inputText);
      if (this.searchText) {
        this.searchstart = 0;
      }
      this.FETCH_SERCH_MUSEUM({
        museumName: this.inputText,
        start: this.searchstart
      });
    } else {
      this.FETCH_MUSEUM(this.start);
    }
  }

  moveDetail(museumName: string) {
    this.$router.push({
      name: "DetailMuseumView",
      params: { museum: museumName }
    });
  }

  scroll() {
    window.onscroll = () => {
      const ceilBottomOfWindow =
        Math.ceil(window.pageYOffset) + window.innerHeight ===
        document.documentElement.offsetHeight;

      const plusBottomOfWindow =
        Math.ceil(window.pageYOffset) + window.innerHeight + 1 ===
        document.documentElement.offsetHeight;

      if (
        (ceilBottomOfWindow || plusBottomOfWindow) &&
        !this.scrollEnd &&
        this.$route.name === "MuseumList"
      ) {
        if (this.searchText) {
          ++this.searchstart;
          this.FETCH_SERCH_MUSEUM({
            museumName: this.searchText,
            start: this.searchstart
          });
        } else {
          ++this.start;
          this.FETCH_MUSEUM(this.start);
        }
      }
    };
  }

  mounted() {
    this.scroll();
    this.scrollHeight = window.innerHeight;
  }

  destroyed() {
    this.SET_MUSEUM_ZERO();
    this.searchstart = 0;
    this.start = 0;
  }

  zoomIn(event: any) {
    event.target.style.transform = "scale(1.1)";
    event.target.style.zIndex = 1;
    event.target.style.transition = "all 0.5s";
  }

  zoomOut(event: any) {
    event.target.style.transform = "scale(1)";
    event.target.style.zIndex = 0;
    event.target.style.transition = "all 0.5s";
  }
}
</script>

<style scoped>
.v-card {
  border-radius: 5px !important;
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
