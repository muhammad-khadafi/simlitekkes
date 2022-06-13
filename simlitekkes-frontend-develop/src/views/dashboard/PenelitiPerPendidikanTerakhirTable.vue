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
        { text: 'Pendidikan Terakhir', value: 'name' },
        { text: 'Jumlah', align: 'center', value: 'y' },
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
          .get('/dashboard/peneliti-per-pendidikan-terakhir/' + this.$store.state.user.organizationId)
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
