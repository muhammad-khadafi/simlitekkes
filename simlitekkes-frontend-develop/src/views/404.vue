<template>
  <v-container
    class="text-center"
    fill-height
    style="height: calc(100vh - 58px);"
  >
    <v-row align="center">
      <v-col>
        <h1 class="display-2 primary--text">Error 404</h1>

        <p>Halaman yang anda cari tidak tersedia</p>

        <v-btn
          :to="`/`"
          color="primary"
          outlined
        >
          Kembali
        </v-btn>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
  export default {
    name: 'FourOhFour',
    props: {
      lang: {
        type: String,
        default: 'en',
      },
    },
    created () {
      this.$ssrContext && this.$ssrContext.res.status(404)
    },
  }
</script>