<template>
  <div class>
    <p>카테고리 테스트</p>
    <div style="width: 50%" class="mx-auto mt-15">
      <v-card class="d-inline-block mx-auto" :to="{ name: 'ArtistList' }">
        <v-row justify="space-between">
          <v-col cols="auto">
            <v-img height="200" width="200" src="@/assets/artist.jpg"></v-img>
          </v-col>
        </v-row>
      </v-card>

      <v-card class="d-inline-block mx-auto" :to="{ name: 'MuseumList' }">
        <v-row justify="space-between">
          <v-col cols="auto">
            <v-img height="200" width="200" src="@/assets/museum.jpg"></v-img>
          </v-col>
        </v-row>
      </v-card>

      <v-card class="d-inline-block mx-auto" :to="{ name: 'StyleList' }">
        <v-row justify="space-between">
          <v-col cols="auto">
            <v-img height="200" width="200" src="@/assets/style.jpg"></v-img>
          </v-col>
        </v-row>
      </v-card>
    </div>
  </div>
</template>

<script lang="ts">
import { Component, Vue, Watch } from "vue-property-decorator";

@Component({
  components: {}
})
export default class CategoryView extends Vue {}
</script>

<style scoped></style>
