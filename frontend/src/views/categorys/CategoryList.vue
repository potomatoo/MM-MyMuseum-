<template>
  <div>
    <!-- 윗공간 -->
    <v-responsive class="mx-auto mb-12" width="56">
      <v-divider class="mb-1"></v-divider>
      <v-divider></v-divider>
    </v-responsive>
    <!-- 제목 -->
    <v-container>
      <h2
        class="display-2 font-weight-bold mb-3 text-uppercase text-center"
        style="color:white"
      >
        Category
      </h2>
      <!-- 아랫공간 -->
      <v-responsive class="mx-auto mb-12" width="56">
        <v-divider class="mb-1"></v-divider>
        <v-divider></v-divider>
      </v-responsive>
      <!-- 분류 -->
      <v-row>
        <v-col
          v-for="({ title, link, hero }, i) in articles"
          :key="i"
          cols="12"
          md="4"
          style="padding: 20px"
        >
          <v-hover v-slot:default="{ hover }">
            <v-card
              flat
              tile
              :elevation="hover ? 12 : 2"
              :class="{ 'on-hover': hover }"
              :to="{ name: link }"
            >
              <v-img
                :src="require(`@/assets/dummydata/category/${hero}`)"
                class="mb-4 category-card"
                height="420"
                max-width="100%"
              >
                <v-expand-transition>
                  <div
                    v-if="hover"
                    class="d-flex transition-fast-in-fast-out darken-2 v-card--reveal display-3 white--text black"
                    style="height: 100%;"
                  >
                    {{ title }}
                  </div>
                </v-expand-transition>
              </v-img>
            </v-card>
          </v-hover>
        </v-col>
      </v-row>
    </v-container>
  </div>
</template>

<script lang="ts">
import { Component, Vue, Watch } from "vue-property-decorator";

@Component({
  components: {}
})
export default class CategoryView extends Vue {
  data() {
    return {
      articles: [
        {
          title: "Artist",
          link: "ArtistList",
          hero: "artist.jpg"
        },
        {
          title: "Museum",
          link: "MuseumList",
          hero: "museum.jpg"
        },
        {
          title: "Style",
          link: "StyleList",
          hero: "style.jpg"
        }
      ]
    };
  }
}
</script>

<style scoped>
.v-card {
  transition: opacity 0.4s ease-in-out;
  transform: perspective(800px) rotateY(0deg);
}

.v-card:not(.on-hover) {
  opacity: 0.9;
  transition: 0.5s;
  transform-origin: center;
  transform: perspective(800px) rotateY(20deg);
  -webkit-box-reflect: below 1px liner-gradient(transparent, transparent, #000f);
}

.v-card--reveal {
  align-items: center;
  bottom: 0;
  justify-content: center;
  opacity: 0.5;
  position: absolute;
  width: 100%;
}

.category-card {
  box-shadow: 0px 0px 10px 10px;
}
</style>
