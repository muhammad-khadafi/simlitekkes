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
        { text: 'Bidang Ilmu', width: '50%', value: 'nama' },
        { text: 'Jumlah Peneliti', align: 'center', value: 'jumlahPeneliti' },
        { text: 'Jumlah Reviewer', align: 'center', value: 'jumlahReviewer' },
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
          .get('/dashboard/peneliti-reviewer-per-bidang-ilmu/' + this.$store.state.user.organizationId)
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
