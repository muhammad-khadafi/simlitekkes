<template>
  <v-container fluid>
    <div v-if="hasAccess === false">
      <v-card>
        <v-toolbar>
          <v-toolbar-title>Dashboard</v-toolbar-title>
        </v-toolbar>
        <v-row>
          <v-col class="align">
            <page403 />
          </v-col>
        </v-row>
      </v-card>
    </div>
    <div v-else>
      <material-card
        class="card-tabs"
        color="green"
        title="Laporan Anggaran"
      >
        <v-row>
          <div class="flex-grow-1" />
          <v-col cols="2">
            <v-select
              v-model="selectTahunUsulan"
              :items="daftarTahunUsulan"
              item-text="tahunUsulan"
              item-value="tahunUsulan"
              label="Tahun Usulan"
            />
          </v-col>
          <v-col cols="6">
            <v-btn
              large
              color="success"
              @click="exportToExcel()"
            >
              Unduh Excel
            </v-btn>
          </v-col>
        </v-row>
      </material-card>
    </div>
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
  import MaterialCard from '../../components/material/Card'
  import { apiServer, fileUrl } from '../../setup-axios'
  import Page403 from '@/views/403.vue'

  export default {
    components: { MaterialCard, Page403 },
    data: () => ({
      hasAccess: null,
      daftarTahunUsulan: [],
      selectTahunUsulan: '',
      tahunIni: '',
      snackbar: false,
      snackbarText: '',
      snackbarColor: '',
    }),

    created () {
      this.checkValidasi()
    },

    methods: {
      checkValidasi () {
        var url = this.$store.state.user.role.roleCode === 'KAPUS_PPM' ? 'checkUserIsKapusPpm' : 'checkUserIsPengelolaPusat'
        apiServer
          .get('/validasi/' + url + '/' + this.$store.state.user.userId + '/' + this.$store.state.user.role.roleCode)
          .then(response => {
            this.hasAccess = response.data
            if (this.hasAccess) {
              this.tahunIni = new Date().getFullYear()

              this.getTahunUsulan()
            }
          })
          .catch(error => {
            console.log('error when checkValidasi: ', error.response)
          })
      },
      getTahunUsulan () {
        apiServer
          .get('/laporan-anggaran/tahun-usulan/' + this.$store.state.user.organizationId)
          .then(response => {
            this.daftarTahunUsulan = response.data

            var selectedIndex = this.daftarTahunUsulan.indexOf('' + this.tahunIni)
            if (selectedIndex !== -1) {
              this.selectTahunUsulan = this.daftarTahunUsulan[selectedIndex]
            }
          })
          .catch(error => {
            console.log('There was an error:', error.response) // Logs out the error
          })
      },
      exportToExcel () {
        if (this.daftarTahunUsulan.length !== 0) {
          apiServer
            .post('/laporan-anggaran/download/' + this.selectTahunUsulan + '/' + this.$store.state.user.organizationId)
            .then(response => {
              const ticket = response.data.ticket
              window.location = fileUrl + '/download/laporan-anggaran/' + this.selectTahunUsulan + '/' + this.$store.state.user.organizationId + '?ticket=' + ticket
            })
            .catch(error => {
              console.log('There was an error:', error.response) // Logs out the error
              if (error.response.status === 500) {
                this.showSnackbar('error', 'File tidak ditemukan.')
              } else {
                this.showSnackbar('error', 'Error, Harap hubungi Administrator!')
              }
            })
        } else {
          this.showSnackbar('error', 'Harap pilih tahun usulan.')
        }
      },
      showSnackbar (color, message) {
        this.snackbar = true
        this.snackbarColor = color
        this.snackbarText = message
      },
    },

  }
</script>

<style>

</style>
