<template>
  <div>
    <v-responsive class="mx-auto mb-12" width="56">
      <v-divider class="mb-1"></v-divider>
      <v-divider></v-divider>
    </v-responsive>
    <h2
      class="display-2 font-weight-bold mb-3 text-uppercase text-center"
      style="color:white"
    >
      아마추어 작가
    </h2>
    <v-row
      cols="12"
      sm="6"
      offset-sm="3"
      align="center"
      justify="end"
      style="margin : 1px 20%"
    >
      <v-btn
        v-if="userArtist === 3"
        width="20%"
        color="rgb(137,120,104)"
        dark
        large
        @click="moveAmateurArtUpload()"
      >
        작품 등록
      </v-btn>
    </v-row>
    <div :scrollHeight="scrollHeight">
      <v-row style="margin: 10px 10%">
        <v-row>
          <v-col
            v-for="(value, n) in amateurs"
            :key="n"
            class="d-flex child-flex"
            cols="12"
            md="3"
            sm="6"
          >
            <!--- -->
            <v-hover v-slot:default="{ hover }">
              <v-card
                flat
                tile
                class="d-flex"
                :elevation="hover ? 12 : 2"
                :class="{ 'on-hover': hover }"
                @click="moveAmateurart(value.userId)"
              >
                <v-img
                  :src="
                    require(`@/assets/dummydata/category/amateurartist.jpg`)
                  "
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
                      {{ value.userName }}
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
import { Component, Vue, Watch } from "vue-property-decorator";

import { namespace } from "vuex-class";

import { Amateur } from "../../../store/AmateurInterface";
const amateurModule = namespace("amateurModule");

import { User } from "../../../store/Accounts.interface";
const AccountsModule = namespace("AccountsModule");

@Component
export default class AmateurArtistView extends Vue {
  @amateurModule.State amateurs!: Amateur[] | null;
  @amateurModule.Action FETCH_AMATEUR: any;
  @amateurModule.Action FETCH_SERCH_AMATEUR: any;
  @amateurModule.State scrollEnd!: boolean;
  @amateurModule.Mutation SET_AMATEUR_ZERO: any;
  @amateurModule.State searchText: any;
  @amateurModule.Mutation SET_AMATEUR_SEARCHTEXT: any;

  @AccountsModule.State user!: User;
  @AccountsModule.Action FETCH_USER_INFO: any;

  inputText = "";
  start = 0;
  scrollHeight = 0;
  searchstart = 0;
  key = "";

  userArtist = 0;

  @Watch("user", { immediate: true, deep: true })
  setUserInfo() {
    if (this.user) {
      this.userArtist = this.user.userArtist!;
    }
  }

  created() {
    this.FETCH_USER_INFO();

    if (this.searchText) {
      this.FETCH_SERCH_AMATEUR({
        artistName: this.searchText,
        start: this.searchstart
      });
    } else {
      this.FETCH_AMATEUR(this.start);
    }
  }

  searchArtist() {
    this.SET_AMATEUR_ZERO();
    if (this.inputText) {
      this.SET_AMATEUR_SEARCHTEXT(this.inputText);
      if (this.searchText) {
        this.searchstart = 0;
      }
      this.FETCH_SERCH_AMATEUR({
        artistName: this.searchText,
        start: this.searchstart
      });
    }
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
          this.FETCH_SERCH_AMATEUR({
            artistName: this.searchText,
            start: this.searchstart
          });
        } else {
          ++this.start;
          this.FETCH_AMATEUR(this.start);
        }
      }
    };
  }

  mounted() {
    this.scroll();
    this.scrollHeight = window.innerHeight;
  }

  moveAmateurArtUpload() {
    this.SET_AMATEUR_ZERO();
    this.$router.push({
      name: "AmateurArtUpload"
    });
  }

  moveAmateurart(userId: string) {
    this.$router.push({
      name: "AmateurArtView",
      params: { userId: userId }
    });
  }

  destroyed() {
    this.SET_AMATEUR_ZERO();
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
