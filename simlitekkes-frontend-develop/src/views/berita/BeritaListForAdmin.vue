<template>
  <v-container>
    <material-card
      color="green"
      title=" Berita"
    >
      <v-data-table
        :headers="headers"
        :items="daftar"
        class="elevation-1"
        :loading="isLoading"
        loading-text="Loading... Please wait"
        :search="search"
        no-data-text="Tidak ada berita"
      >
        <template v-slot:top>
          <v-toolbar dense>
            <v-toolbar-title>Daftar Berita</v-toolbar-title>
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
              color="green"
              class="white--text"
              @click="addBerita"
            >
              Tambah
            </v-btn>
          </v-toolbar>
        </template>
        <template v-slot:item.isPublished="{ item }">
          <v-icon>{{ item.isPublished ? "mdi-check" : "mdi-minus" }}</v-icon>
        </template>
        <template v-slot:no-data />
        <template v-slot:item.action="{ item }">
          <div v-if="isAdminPpsdm || (idOrgUser === item.idOrganisasi)">
            <v-btn
              small
              color="blue darken-1"
              text
              @click="updateBerita(item)"
            >
              Ubah
            </v-btn>
            <v-btn
              small
              color="blue darken-1"
              text
              @click="deleteBerita(item)"
            >
              Hapus
            </v-btn>
          </div>
          <div>
            <v-btn
              small
              color="blue darken-1"
              text
              @click="detailBerita(item)"
            >
              Detail
            </v-btn>
          </div>
        </template>
      </v-data-table>
    </material-card>
    <v-snackbar
      v-model="snackbar"
      :color="snackbarColor"
      :timeout="3000"
      :top="true"
    >
      {{ snackbarText }}
      <v-btn
        dark
        text
        @click="snackbar = false"
      >
        Tutup
      </v-btn>
    </v-snackbar>
  </v-container>
</template>

<script>
  import { apiServer } from '../../setup-axios'

  export default {

    data: () => ({
      headers: [
        { text: 'Judul', width: '40%', value: 'judul' },
        { text: 'Institusi', align: 'center', value: 'namaOrganisasi' },
        { text: 'Sudah Terbit?', width: '5%', align: 'center', value: 'isPublished' },
        { text: 'Diterbitkan Oleh', width: '10%', align: 'center', value: 'namaUser' },
        { text: 'Waktu', align: 'center', value: 'publishTimeStr' },
        { text: 'Aksi', width: '15%', align: 'center', value: 'action', sortable: false },
      ],
      daftar: [],
      isLoading: false,
      search: '',
      snackbar: false,
      snackbarText: '',
      snackbarColor: '',
      idOrgUser: '',
      isAdminPpsdm: null,
      role: '',
    }),

    created () {
      this.idOrgUser = this.$store.state.user.organizationId
      this.role = this.$store.state.user.role.roleCode
      if (this.idOrgUser === 1 && this.role === 'ADMINISTRATOR') {
        this.isAdminPpsdm = true
      } else {
        this.isAdminPpsdm = false
      }
      this.getData()
    },

    methods: {
      getData () {
        if (this.$store.state.berita.snackbarText != null) {
          this.showMessage()
        }
        this.isLoading = true
        apiServer
          .get('/berita/' + this.$store.state.user.userId + '/' + this.role)
          .then(response => {
            this.isLoading = false
            this.daftar = response.data
            console.log('isi daftar berita:', this.daftar)
          })
          .catch(error => {
            console.log('There was an error:', error.response) // Logs out the error
            this.isLoading = false
          })
      },
      addBerita () {
        this.$router.push('/berita/0')
      },
      updateBerita (item) {
        this.$router.push('/berita/' + item.id)
      },
      deleteBerita (item) {
        if (confirm('Apakah Anda yakin ingin menghapus berita ' + item.judul + '?')) {
          apiServer
            .delete('/berita/' + item.id + '/' + this.$store.state.user.name)
            .then(response => {
              this.getData()
              this.showSnackbar('success', 'Berita ' + item.judul + ' berhasil dihapus.')
            })
            .catch(error => {
              console.log('error at deleteBerita:', error.response)
              this.showSnackbar('error', 'Error, Harap hubungi Administrator!')
            })
        }
      },
      detailBerita (item) {
        this.$router.push('/berita/detail/' + item.id)
      },
      showMessage () {
        this.showSnackbar(
          this.$store.state.berita.snackbarColor,
          this.$store.state.berita.snackbarText
        )
        this.$store.dispatch('setBerita', {
          snackbarColor: null,
          snackbarText: null,
        })
      },
      showSnackbar (color, message) {
        this.snackbar = true
        this.snackbarText = message
        this.snackbarColor = color
      },
    },

  }
</script>

<style>

</style>
