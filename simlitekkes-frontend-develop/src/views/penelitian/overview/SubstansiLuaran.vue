<template>
  <v-container fluid>
    <substansi-usulan />
    <luaran-usulan />
  </v-container>
</template>

<script>
  export default {
    components: {
      SubstansiUsulan: () => import('@/views/penelitian/overview/Substansi.vue'),
      LuaranUsulan: () => import('@/views/penelitian/overview/Luaran.vue')
    }
  }
</script>
