<template>
  <v-container
    class="text-center"
    fill-height
    style="height: calc(100vh - 58px);"
  >
    <v-row align="center">
      <v-col>
        <h1 class="display-2 primary--text">Error 403 - FORBIDDEN</h1>

        <p>Anda tidak memiliki akses untuk membuka halaman ini</p>

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
      console.log("403 Forbidden")
      this.$ssrContext && this.$ssrContext.res.status(404)
    },
  }
</script>