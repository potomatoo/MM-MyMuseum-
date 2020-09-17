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
    <!-- 아랫공간 -->
    <v-responsive class="mx-auto mb-12" width="56">
      <v-divider class="mb-1"></v-divider>
      <v-divider></v-divider>
    </v-responsive>
    <!-- 전시공간 -->
    <v-row style="padding:20px" cols="12" sm="6" offset-sm="3">
      <v-container fluid cols="12">
        <v-row>
          <v-col
            v-for="(value, n) in articles"
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
                :to="{ name: link }"
              >
                <v-img
                  :src="require(`@/assets/dummydata/articles/${value.hero}`)"
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
                      {{ value.title }}
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
import { Article } from "../../../store/ArticleInterface";

const articleModule = namespace("articleModule");

@Component
export default class StyleList extends Vue {
  @articleModule.State articles!: Article[] | null;
  @articleModule.Mutation SET_ARTICLE: any;

  created() {
    this.SET_ARTICLE();
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
