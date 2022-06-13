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
        { text: 'Kelompok TKT', width: '30%', value: 'nama' },
        { text: 'Level 1', align: 'center', value: 'level1' },
        { text: 'Level 2', align: 'center', value: 'level2' },
        { text: 'Level 3', align: 'center', value: 'level3' },
        { text: 'Level 4', align: 'center', value: 'level4' },
        { text: 'Level 5', align: 'center', value: 'level5' },
        { text: 'Level 6', align: 'center', value: 'level6' },
        { text: 'Level 7', align: 'center', value: 'level7' },
        { text: 'Level 8', align: 'center', value: 'level8' },
        { text: 'Level 9', align: 'center', value: 'level9' },
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
          .get('/dashboard/proposal-per-kelompok-tkt/tabel/' + this.$store.state.user.organizationId)
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
