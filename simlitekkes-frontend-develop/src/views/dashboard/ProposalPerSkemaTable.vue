<template>
  <v-container fluid>
    <v-data-table
      :headers="headers"
      :items="daftar"
      class="elevation-1"
      :loading="isLoading"
      loading-text="Loading... Please wait"
      :search="search"
      :items-per-page="5"
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
        { text: 'Skema', value: 'nama' },
        { text: 'Singkatan', value: 'singkatan' },
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
          .get('/dashboard/proposal-per-skema/tabel/' + this.$store.state.user.organizationId)
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
