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
      Artist
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
        placeholder="아티스트 검색"
        type="text"
        clearable
        prepend-inner-icon="mdi-magnify"
        v-model="inputText"
        color="white"
        background-color="rgb(80, 70, 60)"
        @keypress.enter="searchArtist()"
      >
      </v-text-field>
    </v-row>

    <!-- 작가 별 -->
    <div :scrollHeight="scrollHeight">
      <v-row style="margin: 10px 10%" cols="12" sm="6" offset-sm="3">
        <v-container fluid cols="12">
          <v-row>
            <v-col
              v-for="(artist, n) in artists"
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
                  @click="moveDetail(artist.artistName)"
                >
                  <v-img
                    :src="artist.artistImg"
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
                        {{ artist.artistName }}
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
  </div>
</template>

<script lang="ts">
import { Component, Vue } from "vue-property-decorator";

import { namespace } from "vuex-class";
import { Artist } from "../../../store/ArtistInterface";

const artistModule = namespace("artistModule");

@Component
export default class ArtistList extends Vue {
  @artistModule.State artists!: Artist[] | null;
  @artistModule.Action FETCH_ARTIST: any;
  @artistModule.Action FETCH_SERCH_ARTIST: any;
  @artistModule.State scrollEnd!: boolean;
  @artistModule.Mutation SET_ARTIST_ZERO: any;
  @artistModule.Mutation SET_ARTIST_SEARCHTEXT: any;
  @artistModule.State searchText: any;

  inputText = "";
  start = 0;
  scrollHeight = 0;
  searchstart = 0;

  key = "";

  created() {
    if (this.searchText) {
      this.FETCH_SERCH_ARTIST({
        artistName: this.searchText,
        start: this.searchstart
      });
    } else {
      this.FETCH_ARTIST(this.start);
    }
  }

  searchArtist() {
    this.SET_ARTIST_ZERO();
    if (this.inputText) {
      this.SET_ARTIST_SEARCHTEXT(this.inputText);
      this.FETCH_SERCH_ARTIST({
        artistName: this.searchText,
        start: this.searchstart
      });
    }
  }

  moveDetail(artistName: string) {
    this.$router.push({
      name: "DetailArtistView",
      params: { artist: artistName }
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
        this.$route.name === "ArtistList"
      ) {
        if (this.searchText) {
          ++this.searchstart;
          this.FETCH_SERCH_ARTIST({
            artistName: this.searchText,
            start: this.searchstart
          });
        } else {
          ++this.start;
          this.FETCH_ARTIST(this.start);
        }
      }
    };
  }

  mounted() {
    this.scroll();
    this.scrollHeight = window.innerHeight;
  }

  destroyed() {
    this.SET_ARTIST_ZERO();
    this.searchstart = 0;
    this.start = 0;
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
