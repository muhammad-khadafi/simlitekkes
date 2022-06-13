<template>
  <v-container fluid>
    <div>
      <v-data-table
        :headers="headers"
        :items="listPenelitian"
        sort-by="judul"
        class="elevation-1"
        :loading="isLoading"
        loading-text="Loading... Please wait"
        :search="search"
      >
        <template v-slot:top>
          <v-toolbar>
            <v-toolbar-title>Riwayat Penelitian</v-toolbar-title>
          </v-toolbar>
          <v-divider />
          <v-toolbar
            flat
            class="mt-1"
          >
            <v-text-field
              v-model="search"
              append-icon="mdi-magnify"
              label="Cari"
              single-line
              hide-details
              class="search"
            />
            <div class="flex-grow-1" />
          </v-toolbar>
        </template>
      </v-data-table>

      <v-snackbar
        v-model="snackbar"
        :color="snackbarColor"
        :timeout="2000"
        :top="true"
      >
        {{ snackbarText }}
        <v-btn
          dark
          text
          @click="snackbar = false"
        >
          Close
        </v-btn>
      </v-snackbar>
    </div>
  </v-container>
</template>

<script>
  import { apiServer } from '../../../setup-axios'

  export default {
    data: () => ({
      search: '',
      snackbar: false,
      snackbarText: '',
      snackbarColor: '',
      isLoading: true,
      dialog: false,
      listPenelitian: [],
      headers: [
        { text: 'Judul', value: 'judul' },
        { text: 'Tahun Pelaksanaan', value: 'tahunPelaksanaan' },
        { text: 'Peran', value: 'namaPeran' }
      ]
    }),

    watch: {
      dialog (val) {
        val || this.close()
      }
    },

    created () {
      this.getData()
    },

    methods: {
      getData () {
        this.isLoading = true
        console.log(this.isLoading)
        console.log('userId: ', this.$store.state.user.userId)
        apiServer
          .get('/penelitian/listByIdPeneliti/' + this.$store.state.user.userId)
          .then(response => {
            this.listPenelitian = response.data
            console.log('response:', response.data)
            this.isLoading = false
          })
          .catch(error => {
            console.log('There was an error:', error.response) // Logs out the error
            this.isLoading = false
          })
      },
      showSnackbar (color, message) {
        console.log('snack ' + color + ' - ' + message)
        this.snackbar = true
        this.snackbarText = message
        this.snackbarColor = color
      }
    }
  }
</script>
