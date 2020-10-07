<template>
  <div v-if="arts">
    <h2 class="display-2 my-10 text-center rec-title">
      {{ userName }} 님의 전시회
    </h2>
    <v-row style="margin: 10px 10%" cols="12" sm="6" offset-sm="3">
      <v-container fluid cols="12">
        <v-row>
          <v-col
            v-for="art in arts"
            :key="art.artNo"
            class="d-flex child-flex"
            cols="3"
          >
            <router-link
              class="router-link"
              :to="{
                name: 'DetailArtView',
                params: {
                  artNo: art.artNo
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
                    :src="art.artUrl"
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
                        {{ art.artTitle }}
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
import { Vue, Component, Prop } from "vue-property-decorator";
import { namespace } from "vuex-class";
import { Art } from "../../store/Recommendation.interface";

const AccountsModule = namespace("AccountsModule");

@Component
export default class ArtListGallery extends Vue {
  @AccountsModule.Getter userName!: string;
  @Prop() private arts!: Art[];

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
