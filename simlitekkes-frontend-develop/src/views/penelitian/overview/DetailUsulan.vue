<template>
  <v-card>
    <v-tabs
      v-model="tab"
      background-color="transparent"
      color="green"
    >
      <v-tab :key="'/identitas-usulan'">
        Identitas Usulan
      </v-tab>
      <v-tab :key="'/substansi-usulan'">
        Substansi Usulan
      </v-tab>
      <v-tab :key="'/rab'">
        RAB
      </v-tab>
      <v-tab :key="'/rencana-kegiatan'">
        Jadwal Kegiatan
      </v-tab>
      <v-tab :key="'/mitra-penelitian'">
        Dokumen Pendukung
      </v-tab>
      <v-tab
        v-if="!isDraft"
        :key="'/history-workflow'"
      >
        Riwayat Status
      </v-tab>
    </v-tabs>

    <v-tabs-items v-model="tab">
      <v-tab-item :key="'/identitas-usulan'">
        <identitas-usulan />
      </v-tab-item>
      <v-tab-item :key="'/substansi-usulan'">
        <substansi-usulan />
      </v-tab-item>
      <v-tab-item :key="'/rab'">
        <rab />
      </v-tab-item>
      <v-tab-item :key="'/rencana-kegiatan'">
        <rencana-kegiatan />
      </v-tab-item>
      <v-tab-item :key="'/mitra-penelitian'">
        <mitra-penelitian />
      </v-tab-item>
      <v-tab-item :key="'/history-workflow'">
        <history-workflow />
      </v-tab-item>
    </v-tabs-items>
  </v-card>
</template>

<script>
  import { apiServer } from '../../../setup-axios'

  export default {
    components: {
      IdentitasUsulan: () => import('@/views/penelitian/overview/preview/IdentitasUsulan.vue'),
      SubstansiUsulan: () => import('@/views/penelitian/overview/preview/SubstansiUsulan.vue'),
      Rab: () => import('@/views/penelitian/overview/preview/RAB.vue'),
      MitraPenelitian: () => import('@/views/penelitian/overview/preview/MitraPenelitian.vue'),
      RencanaKegiatan: () => import('@/views/penelitian/overview/preview/RencanaKegiatan.vue'),
      HistoryWorkflow: () => import('@/views/penelitian/overview/Riwayat.vue'),
    },
    data: () => ({
      tab: null,
      isDraft: null,
    }),
    created () {
      apiServer
        .get('/penelitian/detailStatus/' + this.$route.params.id)
        .then(response => {
          var penelitian = response.data
          if (penelitian != null) {
            this.isDraft = penelitian.status === 'DRAFT'
          }
        })
        .catch(error => {
          console.log('There was an error:', error.response) // Logs out the error
        })
    },
  }
</script>

<style lang="scss">
  // .search{
  //   margin-left: 20%;
  // }
</style>
