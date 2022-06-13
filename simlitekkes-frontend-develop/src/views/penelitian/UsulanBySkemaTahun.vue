<template>
  <v-container fluid>
    <div v-if="validated == true">
      <v-row>
        <v-data-table
          :headers="headers"
          :items="listUsulan"
          class="elevation-1"
          :loading="isLoading"
          loading-text="Loading... Please wait"
          :search="search"
        >
          <template v-slot:top>
            <v-toolbar>
              <v-toolbar-title>Daftar Usulan</v-toolbar-title>
            </v-toolbar>
            <v-toolbar>
              <v-label>Skema Penelitian: {{ namaSkema }}</v-label>
            </v-toolbar>
            <v-toolbar>
              <v-label>Tahun Usulan: {{ tahunUsulan }}</v-label>
            </v-toolbar>
            <v-divider />
            <v-toolbar
              flat
              class="mt-1"
            >
              <v-text-field
                v-model="search"
                append-icon="mdi-magnify"
                label="Search"
                single-line
                hide-details
                class="search"
              />
              <div class="flex-grow-1" />
            </v-toolbar>
          </template>

          <template
            v-slot:item.action="{ item }"
            style="width: 10%"
          >
            <v-btn
              class="pl-0"
              small
              color="blue darken-1"
              text
              @click="showItem(item)"
            >
              Lihat Detail
            </v-btn>
          </template>
        </v-data-table>
      </v-row>
      <v-row>
        <v-toolbar>
          <div class="flex-grow-1" />
          <v-btn
            color="red"
            class="mb-2 white--text"
            @click="back()"
          >
            Kembali
          </v-btn>
        </v-toolbar>
      </v-row>

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
    <div v-if="validated == false">
      <v-card>
        <v-toolbar>
          <v-toolbar-title>Daftar Usulan</v-toolbar-title>
        </v-toolbar>
        <v-toolbar>
          <v-label>Skema Penelitian: {{ namaSkema }}</v-label>
        </v-toolbar>
        <v-toolbar>
          <v-label>Tahun Usulan: {{ tahunUsulan }}</v-label>
        </v-toolbar>
        <v-row>
          <v-col class="align">
            <page403 />
          </v-col>
        </v-row>
      </v-card>
    </div>
  </v-container>
</template>

<script>
  import axios from 'axios'
  import { apiServer } from '../../setup-axios'

  export default {
    components: {
      Page403: () => import('@/views/403.vue'),
    },
    data: () => ({
      search: '',
      snackbar: false,
      snackbarText: '',
      snackbarColor: '',
      isLoading: true,
      dialog: false,
      headers: [
        { text: 'Pengusul', value: 'pengusul' },
        { text: 'Judul', value: 'judul' },
        { text: 'Tahun Mulai Pelaksanaan', value: 'tahunMulaiPelaksanaan' },
        { text: 'Tahun Selesai Pelaksanaan', value: 'tahunSelesaiPelaksanaan' },
        { text: 'Tahap', value: 'tahap' },
        { text: 'Status', value: 'status' },
        { text: '', value: 'action', sortable: false },
      ],
      listUsulan: [],
      namaSkema: '',
      tahunUsulan: '',
      ketuaTimPeneliti: [],
      validated: null,
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
        this.tahunUsulan = this.$route.params.tahunUsulan === 'null' ? '-' : this.$route.params.tahunUsulan
        console.log('tahunUsulan:', this.tahunUsulan)
        this.isLoading = true
        console.log(this.isLoading)
        var idOrganisasi = this.$store.state.user.roleCode !== 'PENGELOLA_PUSAT' ? this.$store.state.user.organizationId : 1
        console.log('idOrganisasi,', idOrganisasi)
        var sentTahunUsulan = this.tahunUsulan === '-' ? '0' : this.tahunUsulan
        var sentTahunMulai = this.$store.state.rekapPenelitian.tahunMulaiPelaksanaan === null ? '0' : this.$store.state.rekapPenelitian.tahunMulaiPelaksanaan
        var sentTahunSelesai = this.$store.state.rekapPenelitian.tahunSelesaiPelaksanaan === null ? '0' : this.$store.state.rekapPenelitian.tahunSelesaiPelaksanaan
        var roleCode = this.$store.state.user.role.roleCode
        // console.log('testestes', idOrganisasi + ',' + this.$route.params.idSkemaPenelitian + ',' + sentTahunMulai + ',' + sentTahunSelesai + ',' + sentTahunUsulan)
        apiServer
          .get('/validasi/' + roleCode + '/' + idOrganisasi)
          .then(validateResponse => {
            this.validated = validateResponse.data
            if (this.validated) {
              axios.all([
                apiServer.get('skemaPenelitian/findById/' + this.$route.params.idSkemaPenelitian),
                apiServer.get('penelitian/getDaftarBySkemaTahun/' + idOrganisasi + '/' + this.$route.params.idSkemaPenelitian + '/' + sentTahunUsulan + '/' + sentTahunMulai + '/' + sentTahunSelesai),
              ]).then(axios.spread((response1, response2) => {
                this.namaSkema = response1.data.nama
                this.listUsulan = response2.data
                this.isLoading = false
              }))
                .catch(error => {
                  console.log('There was an error:', error.response) // Logs out the error
                  this.isLoading = false
                })
            }
          })
          .catch(error => {
            console.log('There was an error:', error.response) // Logs out the error
            this.isLoading = false
          })
      },
      showItem (item) {
        console.log('item:', item)
        this.$router.replace('/penelitian/' + item.id + '/view-usulan')
        /*     apiServer
          .get('/timPeneliti/getKetuaTimPeneliti/' + item.id)
          .then(response => {
            console.log('response:', response.data)
            this.ketuaTimPeneliti = response.data
            var orgId = this.$store.state.user.organizationId == null ? 0 : this.$store.state.user.organizationId
            if (orgId === 0 || this.ketuaTimPeneliti.idOrganisasi === orgId) {
              this.$router.replace('/penelitian/' + item.id + '/view-usulan')
            } else {
              this.showSnackbar('error', 'Anda Tidak Memiliki Akses ke Proposal Penelitian Tersebut!')
            }
          })
          .catch(error => {
            console.log('There was an error:', error.response) // Logs out the error
          }) */
      },
      back () {
        this.$router.replace('../../rekap')
      },
      showSnackbar (color, message) {
        console.log('snack ' + color + ' - ' + message)
        this.snackbar = true
        this.snackbarText = message
        this.snackbarColor = color
      },
    },
  }
</script>
