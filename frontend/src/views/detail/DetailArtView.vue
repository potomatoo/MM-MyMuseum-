<template>
  <DetailArtImage />
</template>

<script lang="ts">
import { Component, Vue } from "vue-property-decorator";
import DetailArtImage from "@/components/detail/DetailArtImage.vue";

@Component({
  components: {
    DetailArtImage
  }
})
export default class DetailArtView extends Vue {}
</script>
