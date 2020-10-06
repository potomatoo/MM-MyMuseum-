<template>
  <div v-if="artList">
    <h2
      v-if="currentPage === 'RecArtList'"
      class="display-2 my-10 text-center rec-title"
    >
      {{ userName }} 님을 위한 추천 작품
    </h2>
    <h2
      v-else-if="currentPage === 'WeatherArtsList'"
      class="display-2 my-10 text-center rec-title"
    >
      {{ artsByWeather.title }}
    </h2>
    <v-row style="margin: 10px 10%" cols="12" sm="6" offset-sm="3">
      <v-container fluid cols="12">
        <v-row>
          <v-col
            v-for="art in artList"
            :key="art.art_no"
            class="d-flex child-flex"
            cols="3"
          >
            <router-link
              class="router-link"
              :to="{
                name: 'DetailArtView',
                params: {
                  artNo: art.art_no
                }
              }"
            >
              <v-hover v-slot:default="{ hover }">
                <v-card
                  flat
                  tile
                  class="d-flex"
                  :elevation="hover ? 12 : 2"
                  :class="{ 'on-hover': hover }"
                >
                  <v-img
                    :src="art.art_url"
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
                        {{ art.art_title }}
                      </div>
                    </v-expand-transition>
                  </v-img>
                </v-card>
              </v-hover>
            </router-link>
          </v-col>
        </v-row>
      </v-container>
    </v-row>
  </div>
</template>

<script lang="ts">
import { Vue, Component, Watch } from "vue-property-decorator";
import { namespace } from "vuex-class";
import { User } from "../../store/Accounts.interface";
import {
  Art,
  ArtsByTime,
  ArtsByWeather
} from "../../store/Recommendation.interface";

const AccountsModule = namespace("AccountsModule");
const RecommendationModule = namespace("RecommendationModule");

@Component
export default class ArtListView extends Vue {
  @AccountsModule.State user?: User | null;
  @AccountsModule.Getter userName!: string;
  @RecommendationModule.State arts?: Art[] | null;
  @RecommendationModule.State artsByWeather?: ArtsByWeather;
  @RecommendationModule.Action FETCH_ART_LIST: any;

  get currentPage() {
    return this.$route.name;
  }

  get artList() {
    if (this.currentPage === "RecArtList") {
      return this.arts;
    } else if (this.currentPage === "WeatherArtsList") {
      return this.artsByWeather?.data;
    }
    return null;
  }

  // artList: Art[] | [] = [];
  // start = 0;

  // @Watch("arts", { immediate: true })
  // init_artList() {
  //   if (
  //     this.artList &&
  //     this.arts &&
  //     !this.artList.length &&
  //     this.arts!.length
  //   ) {
  //     this.artList = this.arts!.slice(0, 20);
  //     this.start = this.start + 20;
  //   }
  // }

  // set_artList(start: number) {
  //   const addArts = this.arts!.slice(this.start, this.start + 20);
  //   this.artList = [...this.artList, ...addArts] as Art[];
  // }

  // scroll() {
  //   window.onscroll = () => {
  //     const ceilBottomOfWindow =
  //       Math.ceil(window.pageYOffset) + window.innerHeight ===
  //       document.documentElement.offsetHeight;

  //     const plusBottomOfWindow =
  //       Math.ceil(window.pageYOffset) + window.innerHeight + 1 ===
  //       document.documentElement.offsetHeight;

  //     if ((ceilBottomOfWindow || plusBottomOfWindow) && this.start < 100) {
  //       this.set_artList(this.start);
  //       this.start += 20;
  //     }
  //   };
  // }

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

  created() {
    if (!this.arts) {
      this.FETCH_ART_LIST();
    }
  }

  mounted() {
    window.scrollTo(0, 0);
  }
}
</script>

<style scoped>
.rec-title {
  color: white;
  font-family: "Do Hyeon", sans-serif !important;
}
.router-link {
  text-decoration: none;
  color: inherit;
  border: 0;
  outline: none;
}
.v-card {
  transition: opacity 0.4s ease-in-out;
  border-radius: 5px !important;
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
