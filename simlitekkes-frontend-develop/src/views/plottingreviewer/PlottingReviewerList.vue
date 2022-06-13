<template>
  <v-container fluid>
    <material-card
      color="green"
      title="Plotting Reviewer"
    >
      <div v-if="hasAccess === false">
        <v-card>
          <v-row>
            <v-col class="align">
              <page403 />
            </v-col>
          </v-row>
        </v-card>
      </div>
      <div v-else>
        <v-row justify="center">
          <v-col cols="12">
            <v-row>
              <div class="flex-grow-1" />
              <v-col cols="3">
                <v-select
                  v-model="selectTahunUsulan"
                  :items="daftarTahunUsulan"
                  item-text="tahunUsulan"
                  item-value="tahunUsulan"
                  label="Tahun Usulan"
                />
              </v-col>
            </v-row>
          </v-col>
          <v-col cols="12">
            <v-data-table
              :items="daftarReviewer"
              :items-per-page="5"
              :headers="headersReviewer"
              class="elevation-1"
              :loading="isLoadingReviewer"
              loading-text="Loading... Please wait"
              :search="searchReviewer"
            >
              <template v-slot:top>
                <v-toolbar dense>
                  <v-toolbar-title>Daftar Reviewer</v-toolbar-title>
                </v-toolbar>
                <v-divider />
                <v-toolbar
                  flat
                  class="mt-1"
                >
                  <v-text-field
                    v-model="searchReviewer"
                    append-icon="mdi-magnify"
                    label="Cari"
                    single-line
                    hide-details
                    class="search"
                  />
                  <div class="flex-grow-1" />
                </v-toolbar>
              </template>

              <template v-slot:no-data />
            </v-data-table>
          </v-col>
          <v-col cols="12">
            <v-data-table
              :items="daftarPlotting"
              :items-per-page="5"
              :headers="headersPlotting"
              class="elevation-1"
              :loading="isLoadingPlotting"
              loading-text="Loading... Please wait"
              :search="searchPlotting"
            >
              <template v-slot:top>
                <v-toolbar dense>
                  <v-toolbar-title>Daftar Plotting Reviewer - Penelitian</v-toolbar-title>
                </v-toolbar>
                <v-divider />
                <v-toolbar
                  flat
                  class="mt-1"
                >
                  <v-text-field
                    v-model="searchPlotting"
                    append-icon="mdi-magnify"
                    label="Cari"
                    single-line
                    hide-details
                    class="search"
                  />
                  <div class="flex-grow-1" />
                </v-toolbar>
              </template>
              <template
                v-if="kelasPoltekkes !== 3"
                v-slot:item.action="{ item }"
              >
                <v-btn
                  v-if="item.namaReviewer !== null"
                  small
                  color="blue darken-1"
                  text
                  @click="deletePlotting(item)"
                >
                  Hapus Plotting
                </v-btn>
              </template>
              <template v-slot:no-data />
            </v-data-table>
          </v-col>
        </v-row>
        <v-card
          v-if="kelasPoltekkes !== 3"
          outlined
        >
          <v-card-title>Plotting</v-card-title>
          <v-card-text>
            <v-row justify="center">
              <v-col cols="6">
                <v-autocomplete
                  v-model="$v.plottedReviewer.idReviewerSkema.$model"
                  label="Reviewer*"
                  :items="daftarReviewer"
                  hide-no-data
                  item-text="namaPeneliti"
                  item-value="id"
                  placeholder="Harap masukkan nama reviewer"
                  clearable
                  :error-messages="$v.plottedReviewer.idReviewerSkema.$error ? ['Wajib Diisi'] : []"
                >
                  <template
                    slot="selection"
                    slot-scope="data"
                  >
                    {{ data.item.namaPeneliti }}
                  </template>
                </v-autocomplete>
              </v-col>
              <v-col cols="6">
                <v-autocomplete
                  v-model="$v.plottedReviewer.idPenelitian.$model"
                  label="Penelitian*"
                  :items="daftarPlotting"
                  hide-no-data
                  item-text="judulPenelitian"
                  item-value="idPenelitian"
                  placeholder="Harap masukkan judul penelitian"
                  clearable
                  :error-messages="$v.plottedReviewer.idPenelitian.$error ? ['Wajib Diisi'] : []"
                >
                  <template
                    slot="selection"
                    slot-scope="data"
                  >
                    {{ data.item.judulPenelitian }}
                  </template>
                </v-autocomplete>
              </v-col>
            </v-row>
          </v-card-text>
          <v-card-actions>
            <div class="flex-grow-1" />
            <v-btn
              color="blue"
              class="mb-2 white--text mr-2"
              @click="plotReviewer"
            >
              Plot
            </v-btn>
          </v-card-actions>
        </v-card>
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
    </material-card>
  </v-container>
</template>

<script>
  import MaterialCard from '../../components/material/Card'
  import { apiServer } from '../../setup-axios'
  import { required } from 'vuelidate/lib/validators'
  import Page403 from '@/views/403.vue'

  export default {
    components: { MaterialCard, Page403 },
    data: () => ({
      headersReviewer: [
        { text: 'Tahun Usulan', value: 'tahunUsulan' },
        { text: 'Institusi', value: 'namaInstitusi' },
        { text: 'Nama Reviewer', value: 'namaPeneliti' },
        { text: 'Beban Review', align: 'center', value: 'bebanReviewMaksimal' },
        { text: 'Jumlah Plot', align: 'center', value: 'jumlahProposalDiPlot' },
      ],
      headersPlotting: [
        { text: 'Tahun Usulan', value: 'tahunUsulan' },
        { text: 'Judul', width: '30%', value: 'judulPenelitian' },
        { text: 'Institusi Ketua Tim', value: 'institusiKetuaTim' },
        { text: 'Ketua Tim', value: 'namaKetuaTim' },
        { text: 'Institusi Reviewer', value: 'institusiReviewer' },
        { text: 'Reviewer', value: 'namaReviewer' },
        { text: 'Aksi', value: 'action', align: 'center', sortable: false },
      ],
      daftarTahunUsulan: [],
      selectTahunUsulan: '',
      searchReviewer: '',
      searchPlotting: '',
      daftarReviewer: [],
      daftarPlotting: [],
      isLoadingReviewer: true,
      isLoadingPlotting: true,
      tahunIni: '',
      username: '',
      idOrganisasi: null,
      isEksternal: null,
      plottedReviewer: {
        idReviewerSkema: null,
        idPenelitian: null,
        username: '',
      },
      emptyPlotted: {
        idReviewerSkema: null,
        idPenelitian: null,
        username: '',
      },
      errors: false,
      snackbar: false,
      snackbarText: '',
      snackbarColor: '',
      hasAccess: null,
      kelasPoltekkes: null,
    }),

    validations: {
      plottedReviewer: {
        idReviewerSkema: { required },
        idPenelitian: { required },
      },
    },

    watch: {
      selectTahunUsulan (value) {
        this.getDaftarReviewer(value)
        this.getDaftarPlotting(value)
      },
    },

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
              this.idOrganisasi = this.$store.state.user.organizationId
              this.kelasPoltekkes = this.$store.state.user.organisasi.klasifikasi

              if ((this.$store.state.user.role.roleCode).includes('PPM') && this.kelasPoltekkes !== 3) {
                this.isEksternal = false
              } else {
                this.isEksternal = true
              }

              this.getDaftarTahunUsulan()
              this.getDaftarReviewer(this.tahunIni)
              this.getDaftarPlotting(this.tahunIni)
            }
          })
          .catch(error => {
            console.log('error when checkValidasi: ', error.response)
          })
      },
      getDaftarTahunUsulan () {
        var url = this.isEksternal ? '/tahun-usulan/eks/' : '/tahun-usulan/in/'

        apiServer
          .get(url + this.idOrganisasi)
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
      getDaftarReviewer (year) {
        apiServer
          .get('/reviewer/' + this.idOrganisasi + '/' + year)
          .then(response => {
            this.daftarReviewer = response.data
            this.isLoadingReviewer = false
          })
          .catch(error => {
            console.log('There was an error:', error.response) // Logs out the error
            this.isLoadingReviewer = false
          })
      },
      getDaftarPlotting (year) {
        apiServer
          .get('/plotting-reviewer/' + this.idOrganisasi + '/' + year)
          .then(response => {
            this.daftarPlotting = response.data
            this.isLoadingPlotting = false
          })
          .catch(error => {
            console.log('There was an error:', error.response) // Logs out the error
            this.isLoadingPlotting = false
          })
      },
      plotReviewer () {
        this.$v.$touch()
        this.errors = this.$v.plottedReviewer.$anyError

        this.plottedReviewer.username = this.$store.state.user.name
        console.log('plottedReviewer: ', this.plottedReviewer)

        if (this.errors === false) {
          apiServer
            .put('/plotting-reviewer', this.plottedReviewer)
            .then(response => {
              this.showSnackbar('success', 'Plotting Reviewer berhasil dilakukan')
              this.getDaftarReviewer(this.selectTahunUsulan)
              this.getDaftarPlotting(this.selectTahunUsulan)
              this.$v.$reset()
              this.plottedReviewer = Object.assign({}, this.emptyPlotted)
            })
            .catch(error => {
              if (error.response.status === 409) {
                this.showSnackbar('error', 'Plotting tidak bisa dilakukan karena reviewer dan ketua tim adalah peneliti yang sama.')
              } else {
                this.showSnackbar('error', 'Error, Harap hubungi Administrator!')
              }
            })
        }
      },
      deletePlotting (item) {
        if (confirm('Apakah Anda yakin ingin menghapus plotting reviewer [' + item.namaReviewer + '] dari penelitian [' + item.judulPenelitian + '] ?')) {
          apiServer
            .put('/remove-plotting-reviewer/' + item.idPenelitian + '/' + this.$store.state.user.name)
            .then(response => {
              this.showSnackbar('success', 'Hapus Plotting Reviewer berhasil dilakukan')
              this.getDaftarReviewer(this.selectTahunUsulan)
              this.getDaftarPlotting(this.selectTahunUsulan)
            })
            .catch(error => {
              if (error.response.status === 409) {
                this.showSnackbar('error', 'Hapus Plotting Reviewer tidak bisa dilakukan karena Reviewer sedang/sudah melakukan review terhadap penelitian')
              } else {
                this.showSnackbar('error', 'Error, Harap hubungi Administrator!')
              }
            })
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
