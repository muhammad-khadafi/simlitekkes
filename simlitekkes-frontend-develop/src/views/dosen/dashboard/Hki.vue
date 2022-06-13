<template>
  <v-container fluid>
    <div>
      <v-data-table
        :headers="headers"
        :items="listHki"
        sort-by="judul"
        class="elevation-1"
        :loading="isLoading"
        loading-text="Loading... Please wait"
        :search="search"
      >
        <template v-slot:top>
          <v-toolbar>
            <v-toolbar-title>Daftar HKI</v-toolbar-title>
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
            <v-btn
              color="blue"
              class="white--text"
              to="/hki-dosen/0"
            >
              Tambah HKI
            </v-btn>
          </v-toolbar>
        </template>
        <template v-slot:item.action="{ item }">
          <v-btn
            small
            color="blue darken-1"
            text
            @click="ubahHki(item)"
            :disabled="item.idPenelitian != null && item.idPeranPeneliti != 1"
          >
            Ubah
          </v-btn>
          <v-btn
            small
            color="blue darken-1"
            text
            @click="deleteItem(item)"
            :disabled="item.idPenelitian != null && item.idPeranPeneliti != 1"
          >
            Hapus
          </v-btn>
        </template>
        <template v-slot:item.berkas="{ item }">
          <v-icon
            large
            @click="downloadItem(item)"
          >
            mdi-file-pdf
          </v-icon>
        </template>
        <template v-slot:no-data />
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
  import { apiServer, fileUrl } from '../../../setup-axios'
  import axios from 'axios'

  export default {
    data: () => ({
      search: '',
      snackbar: false,
      snackbarText: '',
      snackbarColor: '',
      isLoading: true,
      menu: false,
      dialog: false,
      listHki: [],
      headers: [
        { text: 'Berkas', value: 'berkas', align: 'center', sortable: false },
        {
          text: 'Judul',
          align: 'left',
          value: 'judul',
        },
        {
          text: 'Jenis HKI',
          align: 'center',
          value: 'namaJenisOutput',
        },
        {
          text: 'Tahun Pelaksanaan',
          align: 'center',
          value: 'tahunPelaksanaan',
        },
        {
          text: 'No. Pendaftaran',
          align: 'center',
          value: 'noPendaftaran',
        },
        {
          text: 'Status HKI',
          align: 'center',
          value: 'statusHkiString',
        },
        {
          text: 'Nomor HKI',
          align: 'center',
          value: 'noHki',
        },
        {
          text: 'URL',
          align: 'center',
          value: 'urlOutput',
        },
        { text: 'Aksi', value: 'action', align: 'center', sortable: false },
      ],
      editedIndex: -1,
    }),

    watch: {
      dialog (val) {
        val || this.close()
      },
    },

    created () {
      this.getData()
    },

    methods: {
      getData () {
        this.isLoading = true
        console.log(this.isLoading)
        apiServer
          .get('/hki/' + this.$store.state.user.userId)
          .then(response => {
            this.listHki = response.data
            console.log('hki:', response.data) // For now, logs out the response
            // response.data.forEach(this.formattingDate);
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
      },
      ubahHki (item) {
        this.$router.push('/hki-dosen/' + item.idOutput)
      },
      deleteItem (item) {
        confirm('Anda yakin menghapus ' + item.judul + '?') &&
          this.hapusHki(item)
      },
      hapusHki (item) {
        if (item.idPenelitian) {
          var validationResult = true
          apiServer
            .get('/validasi/h16w-output/' + this.$store.state.user.userId + '/' + item.idOutput)
            .then(response => {
              validationResult = response.data
              if (validationResult) {
                axios.all([
                  apiServer.delete('/hki/' + item.idOutput + '/' + this.$store.state.user.name),
                  apiServer.delete('/penulis-output/' + item.idOutput + '/' + this.$store.state.user.name),
                  apiServer.delete('/output-file/' + item.idOutput),
                ]).then(axios.spread((response1, response2, response3) => {
                  apiServer.delete('/output/' + item.idOutput + '/' + this.$store.state.user.name)
                    .then((resp) => {
                      this.getData()
                      this.showSnackbar('success', 'Data berhasil dihapus')
                    })
                })).catch(error => {
                  console.log('Terdapat error:', error.response) // Logs out the error
                })
                this.isLoading = false
              } else {
                this.showSnackbar('danger', 'Anda bukan ketua penelitian pada publikasi ini')
              }
            })
            .catch(error => {
              console.log('There was an error:', error.response) // Logs out the error
              this.isLoading = false
            })
        } else {
          axios.all([
            apiServer.delete('/hki/' + item.idOutput + '/' + this.$store.state.user.name),
            apiServer.delete('/penulis-output/' + item.idOutput + '/' + this.$store.state.user.name),
            apiServer.delete('/output-file/' + item.idOutput),
          ]).then(axios.spread((response1, response2, response3) => {
            apiServer.delete('/output/' + item.idOutput + '/' + this.$store.state.user.name)
              .then((resp) => {
                this.getData()
                this.showSnackbar('success', 'Data berhasil dihapus')
              })
          })).catch(error => {
            console.log('Terdapat error:', error.response) // Logs out the error
          })
          this.isLoading = false
        }
      },
      downloadItem (item) {
        apiServer.post('/output/download/' + item.idOutput)
          .then((resp) => {
            const ticket = resp.data.ticket
            window.location = fileUrl + '/download/output/' + item.idOutput + '?ticket=' + ticket
          })
      },
    },
  }
</script>

<style lang="scss">
  // .search{
  //   margin-left: 20%;
  // }
</style>
