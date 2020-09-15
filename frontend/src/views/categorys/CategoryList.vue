<template>
  <div>
    <p style="color:white">카테고리 테스트</p>
    <v-col cols="12">
      <v-row :align="center" :justify="center">
        <!-- 작가 -->
        <v-hover v-slot:default="{ hover }">
          <v-card
            class="d-inline-block mx-auto"
            :to="{ name: 'ArtistList' }"
            max-width="400"
            max-height="400"
            :elevation="hover ? 12 : 2"
            :class="{ 'on-hover': hover }"
          >
            <v-img
              class="white--text align-end"
              src="@/assets/artist.jpg"
              width="100%"
              height="100%"
            >
              <v-expand-transition>
                <div
                  v-if="hover"
                  class="d-flex transition-fast-in-fast-out black darken-2 v-card--reveal display-3 white--text"
                  style="height: 100%;"
                >
                  Artist
                </div>
              </v-expand-transition>
            </v-img>
          </v-card>
        </v-hover>

        <!-- 박물관 -->
        <v-hover v-slot:default="{ hover }">
          <v-card
            class="d-inline-block mx-auto"
            :to="{ name: 'MuseumList' }"
            max-width="400"
            max-height="400"
            :elevation="hover ? 12 : 2"
            :class="{ 'on-hover': hover }"
          >
            <v-img
              class="white--text align-end"
              src="@/assets/museum.jpg"
              width="100%"
              height="100%"
            >
              <v-expand-transition>
                <div
                  v-if="hover"
                  class="d-flex transition-fast-in-fast-out brown darken-2 v-card--reveal display-3 white--text"
                  style="height: 100%;"
                >
                  Museum
                </div>
              </v-expand-transition>
            </v-img>
          </v-card>
        </v-hover>

        <!-- 화풍 -->
        <v-hover v-slot:default="{ hover }">
          <v-card
            class="d-inline-block mx-auto"
            :to="{ name: 'StyleList' }"
            max-width="400"
            max-height="400"
            :elevation="hover ? 12 : 2"
            :class="{ 'on-hover': hover }"
          >
            <v-img
              class="white--text align-end"
              src="@/assets/style.jpg"
              width="100%"
              height="100%"
            >
              <v-expand-transition>
                <div
                  v-if="hover"
                  class="d-flex transition-fast-in-fast-out grey darken-2 v-card--reveal display-3 white--text"
                  style="height: 100%;"
                >
                  Style
                </div>
              </v-expand-transition>
            </v-img>
          </v-card>
        </v-hover>
      </v-row>
    </v-col>
  </div>
</template>

<script lang="ts">
import { Component, Vue, Watch } from "vue-property-decorator";

@Component({
  components: {}
})
export default class CategoryView extends Vue {}
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
  position: absolute;
  width: 100%;
}
</style>
