<template>
  <v-container
    fluid
    class="home"
  >
    <v-row>
      <v-col
        cols="12"
        lg="6"
      >
        <v-card
          outlined
        >
          <v-toolbar
            color="primary"
            dark
          >
            <v-app-bar-nav-icon />

            <v-toolbar-title>Berita Terkini</v-toolbar-title>

            <v-spacer />
          </v-toolbar>
          <v-col
            v-if="daftarBerita.length === 0"
            cols="12"
          >
            <v-card>
              <v-card-text>Tidak ada Berita Terkini</v-card-text>
            </v-card>
          </v-col>
          <v-col
            v-for="berita in daftarBerita"
            v-else
            :key="berita.id"
            cols="12"
          >
            <v-card
              class="mx-auto"
            >
              <v-card-title>
                <v-icon
                  large
                  left
                >
                  mdi-newspaper-variant-outline
                </v-icon>
              </v-card-title>
              <v-card-subtitle class="font-weight-bold">
                {{ berita.judul }}
              </v-card-subtitle>
              <v-card-text>
                {{ berita.isi }}
                <span class="font-weight-bold"><a @click="viewBerita(berita.id)">selanjutnya ...</a></span>
              </v-card-text>
              <v-card-actions>
                <v-list-item
                  three-line
                  class="grow"
                >
                  <v-list-item-avatar color="grey darken-3">
                    <v-img
                      class="elevation-6"
                      src="../assets/avatar.svg"
                    />
                  </v-list-item-avatar>
                  <v-list-item-content>
                    <v-list-item-title>
                      {{ berita.userPublish }}
                    </v-list-item-title>
                    <v-list-item-subtitle>
                      {{ berita.namaOrganisasi }}
                    </v-list-item-subtitle>
                    <v-list-item-subtitle>
                      {{ berita.publishTimeStr }}
                    </v-list-item-subtitle>
                  </v-list-item-content>
                </v-list-item>
              </v-card-actions>
            </v-card>
          </v-col>
          <v-col cols="12">
            <v-btn
              text
              @click="viewAllBerita()"
            >
              Berita Lainnya
            </v-btn>
          </v-col>
        </v-card>
      </v-col>
      <v-col
        cols="12"
        lg="6"
      >
        <v-card
          outlined
        >
          <v-toolbar
            color="primary"
            dark
          >
            <v-app-bar-nav-icon />

            <v-toolbar-title>Dokumen Terkini</v-toolbar-title>

            <v-spacer />
          </v-toolbar>
          <v-data-table
            :headers="headers"
            :items="daftarDokumen"
            class="elevation-1"
            :loading="isLoading"
            loading-text="Loading... Please wait"
            :search="search"
          >
            <template v-slot:no-data />
            <template v-slot:item.action="{ item }">
              <v-btn
                small
                color="blue darken-1"
                text
                @click="downloadDokumen(item)"
              >
                Unduh
              </v-btn>
            </template>
          </v-data-table>
        </v-card>
      </v-col>
    </v-row>
    <v-snackbar
      v-model="snackbar"
      :color="snackbarColor"
      :timeout="4000"
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
  import { apiServer, fileUrl } from '../setup-axios'

  export default {
    data: () => ({
      daftarBerita: [],
      daftarDokumen: [],
      headers: [
        { text: 'Judul', width: '35%', value: 'judul' },
        { text: 'Institusi', value: 'namaOrganisasi' },
        { text: 'Tahun', align: 'center', value: 'tahunTerbit' },
        { text: 'Aksi', value: 'action', align: 'center', sortable: false },
      ],
      isLoading: false,
      search: '',
      snackbar: false,
      snackbarText: '',
      snackbarColor: '',
    }),

    created () {
      this.getBerita()
      this.getDokumen()
    },

    methods: {
      getBerita () {
        apiServer
          .get('/berita-terkini/' + this.$store.state.user.organizationId)
          .then(response => {
            this.daftarBerita = response.data
          })
          .catch(error => {
            console.log('There was an error:', error.response) // Logs out the error
          })
      },
      viewBerita (id) {
        var role = this.$store.state.user.role.roleCode
        if ((this.$store.state.user.organizationId === 1 && role !== 'ADMIN') || role === 'OPERATOR_PPM') {
          this.$router.push('/berita/detail/' + id)
        } else {
          this.$router.push('/berita/detail-nonadmin/' + id)
        }
      },
      viewAllBerita () {
        this.$router.replace('/berita')
      },
      getDokumen () {
        this.isLoading = true
        apiServer
          .get('/dokumen-terkini/' + this.$store.state.user.organizationId)
          .then(response => {
            this.isLoading = false
            this.daftarDokumen = response.data
          })
          .catch(error => {
            console.log('There was an error:', error.response) // Logs out the error
            this.isLoading = false
          })
      },
      downloadDokumen (item) {
        apiServer
          .post('/dokumen-organisasi/download/' + item.id)
          .then(response => {
            const ticket = response.data.ticket
            window.location = fileUrl + '/download/dokumen-organisasi/' + item.id + '?ticket=' + ticket
          })
          .catch(error => {
            console.log('There was an error:', error.response) // Logs out the error
            if (error.response.status === 500) {
              this.showSnackbar('error', 'File tidak ditemukan.')
            } else {
              this.showSnackbar('error', 'Error, Harap hubungi Administrator!')
            }
          })
      },
      showSnackbar (color, message) {
        this.snackbar = true
        this.snackbarColor = color
        this.snackbarText = message
      },
    },

  }
</script>

<style lang="scss">
.home{
    background-color: white;
    padding-bottom: 16%;
}

.selamat-home{
    margin-top: 10%;
}
.pointerCursor {
    cursor:pointer;
}
</style>
