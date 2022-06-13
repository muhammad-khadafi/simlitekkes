<template>
  <v-container fluid>
    <v-data-table
      :headers="headers"
      :items="daftar"
      class="elevation-1"
      :loading="isLoading"
      loading-text="Loading... Please wait"
      :search="search"
    >
      <template v-slot:no-data />
    </v-data-table>
  </v-container>
</template>

<script>
  import { apiServer } from '../../setup-axios'

  export default {
    data: () => ({
      headers: [
        { text: 'Topik', width: '30%', value: 'nama2' },
        { text: 'Tema', width: '30%', value: 'nama' },
        { text: 'Diajukan', align: 'center', value: 'diajukan' },
        { text: 'Dalam Proses', align: 'center', value: 'dalamProses' },
        { text: 'Diterima', align: 'center', value: 'diterima' },
        { text: 'Ditolak', align: 'center', value: 'ditolak' },
        { text: 'Telat', align: 'center', value: 'telat' },
      ],
      daftar: [],
      isLoading: false,
      search: '',
    }),

    created () {
      this.getData()
    },

    methods: {
      getData () {
        this.isLoading = true
        apiServer
          .get('/dashboard/proposal-per-topik/' + this.$store.state.user.organizationId)
          .then(response => {
            this.daftar = response.data
            this.isLoading = false
          })
          .catch(error => {
            console.log('There was an error:', error.response) // Logs out the error
            this.isLoading = false
          })
      },
    },

  }
</script>

<style>

</style>
