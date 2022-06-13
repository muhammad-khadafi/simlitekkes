<template>
  <v-container
    v-if="kelasPoltekkes !== 3"
    fluid
  >
    <material-card
      color="green"
      title=" Penugasan Reviewer Internal"
    >
      <div>
        <v-row justify="center">
          <v-col cols="11">
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
            </v-row>
          </v-col>
        </v-row>

        <v-data-table
          :headers="headers"
          :items="daftarReviewer"
          :items-per-page="5"
          class="elevation-1"
          :loading="isLoading"
          loading-text="Loading... Please wait"
          :search="search"
        >
          <template v-slot:top>
            <v-toolbar>
              <v-toolbar-title>Daftar Reviewer Internal</v-toolbar-title>
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
              <v-dialog
                v-model="dialog"
                max-width="50%"
              >
                <template v-slot:activator="{ on }">
                  <v-btn
                    v-show="!isEksternal"
                    color="blue"
                    dark
                    class="mb-2"
                    v-on="on"
                  >
                    Tambah
                  </v-btn>
                </template>
                <v-card>
                  <v-card-title>
                    <span class="headline">{{ formTitle }}</span>
                  </v-card-title>
                  <v-card-text>
                    <v-container>
                      <v-row>
                        <v-col cols="6">
                          <v-text-field
                            v-if="!isEditing"
                            v-model="$v.editedReviewer.tahunUsulan.$model"
                            label="Tahun Usulan*"
                            type="number"
                            :error-messages="tahunUsulanErrors"
                          />
                          <v-text-field
                            v-else
                            v-model="editedReviewer.tahunUsulan"
                            label="Tahun Usulan"
                            outlined
                            disabled
                          />
                        </v-col>
                        <v-col cols="6">
                          <v-text-field
                            v-if="!isEditing"
                            v-model="$v.editedReviewer.tahunPelaksanaan.$model"
                            label="Tahun Pelaksanaan*"
                            type="number"
                            :error-messages="tahunPelaksanaanErrors"
                          />
                          <v-text-field
                            v-else
                            v-model="editedReviewer.tahunPelaksanaan"
                            label="Tahun Pelaksanaan"
                            outlined
                            disabled
                          />
                        </v-col>
                      </v-row>
                      <v-row>
                        <v-col cols="10">
                          <v-autocomplete
                            v-if="!isEditing"
                            v-model="$v.editedReviewer.idPeneliti.$model"
                            label="Peneliti*"
                            :items="daftarPeneliti"
                            :loading="loadingPeneliti"
                            :search-input.sync="searchPeneliti"
                            hide-no-data
                            item-text="nama"
                            item-value="id"
                            placeholder="Harap masukkan nama atau nidn minimal 3 karakter"
                            clearable
                            :error-messages="$v.editedReviewer.idPeneliti.$error ? ['Wajib Diisi'] : []"
                          >
                            <template
                              slot="selection"
                              slot-scope="data"
                            >
                              {{ data.item.nama }}
                            </template>
                          </v-autocomplete>
                          <v-text-field
                            v-else
                            v-model="editedReviewer.namaPeneliti"
                            label="Peneliti"
                            outlined
                            disabled
                          />
                        </v-col>
                      </v-row>
                      <v-row>
                        <v-col cols="10">
                          <v-autocomplete
                            v-model="editedReviewer.idDokumenOrganisasi"
                            label="SK Penugasan"
                            :items="daftarDokumen"
                            :loading="loadingDokumen"
                            :search-input.sync="searchDokumen"
                            hide-no-data
                            item-text="judul"
                            item-value="id"
                            placeholder="Harap masukkan judul atau nomor dokumen minimal 3 karakter"
                            clearable
                          >
                            <template
                              slot="selection"
                              slot-scope="data"
                            >
                              {{ data.item.judul }}
                            </template>
                          </v-autocomplete>
                        </v-col>
                      </v-row>
                    </v-container>
                  </v-card-text>
                  <v-card-actions>
                    <div class="flex-grow-1" />
                    <v-btn
                      color="red"
                      class="mb-2 white--text mr-2"
                      @click="close"
                    >
                      Batal
                    </v-btn>
                    <v-btn
                      color="blue"
                      class="mb-2 white--text mr-2"
                      @click="saveReviewer"
                    >
                      Simpan
                    </v-btn>
                  </v-card-actions>
                </v-card>
              </v-dialog>
            </v-toolbar>
          </template>
          <template v-slot:item.judulDokumen="{ item }">
            <a
              v-show="item.judulDokumen !== null"
              class="tertiary--text footer-links"
              @click="downloadDokumen(item)"
            >{{ item.judulDokumen }}</a>
          </template>
          <template v-slot:item.action="{ item }">
            <v-btn
              small
              color="blue darken-1"
              text
              @click="riwayatReview(item)"
            >
              Riwayat Review
            </v-btn>
            <v-btn
              v-show="!isEksternal"
              small
              color="blue darken-1"
              text
              @click="updateReviewer(item)"
            >
              Ubah
            </v-btn>
            <v-btn
              v-show="item.jumlahProposalDiPlot === 0 && !isEksternal"
              small
              color="blue darken-1"
              text
              @click="deleteReviewer(item)"
            >
              Hapus
            </v-btn>
          </template>
          <template v-slot:no-data />
        </v-data-table>
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
  import { apiServer, fileUrl } from '../../setup-axios'
  import { required } from 'vuelidate/lib/validators'

  export default {
    components: { MaterialCard },
    data: () => ({
      headers: [
        { text: 'Tahun Usulan', value: 'tahunUsulan' },
        { text: 'Tahun Pelaksanaan', value: 'tahunPelaksanaan' },
        { text: 'Institusi', value: 'namaInstitusi' },
        { text: 'Nama Peneliti', value: 'namaPeneliti' },
        { text: 'Dokumen Penugasan', value: 'judulDokumen' },
        { text: 'Jumlah Proposal di Plot', align: 'center', value: 'jumlahProposalDiPlot' },
        { text: 'Aksi', value: 'action', align: 'center', sortable: false },
      ],
      daftarReviewer: [],
      daftarTahunUsulan: [],
      selectTahunUsulan: '',
      idOrganisasi: null,
      isLoading: false,
      isEksternal: true,
      search: '',
      tahunIni: '',
      dialog: false,
      isEditing: false,
      daftarPeneliti: [],
      daftarDokumen: [],
      defaultReviewer: {
        tahunUsulan: '',
        tahunPelaksanaan: '',
        idPeneliti: '',
        namaPeneliti: '',
        idDokumenOrganisasi: '',
        nomorDokumen: '',
        username: '',
        idPemberiTugas: '',
      },
      editedReviewer: {
        tahunUsulan: '',
        tahunPelaksanaan: '',
        idPeneliti: '',
        namaPeneliti: '',
        idDokumenOrganisasi: '',
        nomorDokumen: '',
        username: '',
        idPemberiTugas: '',
      },
      loadingPeneliti: false,
      searchPeneliti: '',
      loadingDokumen: false,
      searchDokumen: '',
      snackbar: false,
      snackbarText: '',
      snackbarColor: '',
      errors: false,
      kelasPoltekkes: null,
    }),

    validations: {
      editedReviewer: {
        tahunUsulan: {
          required,
          minYear: value => value >= new Date().getFullYear(),
        },
        tahunPelaksanaan: {
          required,
          minYear: value => value >= new Date().getFullYear(),
          greaterEqual: (value, component) => value >= component.tahunUsulan,
        },
        idPeneliti: { required },
      },
    },

    computed: {
      formTitle () {
        return this.isEditing ? 'Ubah Reviewer Internal' : 'Tambah Reviewer Internal'
      },
      tahunUsulanErrors () {
        const errorsForTahunUsulan = []

        if (!this.$v.editedReviewer.tahunUsulan.$dirty) {
          return errorsForTahunUsulan
        }

        if (!this.$v.editedReviewer.tahunUsulan.minYear) {
          errorsForTahunUsulan.push('Tahun Usulan minimal tahun ini')
        }

        if (!this.$v.editedReviewer.tahunUsulan.required) {
          errorsForTahunUsulan.push('Wajib Diisi')
        }

        return errorsForTahunUsulan
      },
      tahunPelaksanaanErrors () {
        const errorsForTahunPelaksanaan = []

        if (!this.$v.editedReviewer.tahunPelaksanaan.$dirty) {
          return errorsForTahunPelaksanaan
        }

        if (!this.$v.editedReviewer.tahunPelaksanaan.minYear) {
          errorsForTahunPelaksanaan.push('Tahun Pelaksanaan minimal tahun ini')
        }

        if (!this.$v.editedReviewer.tahunPelaksanaan.greaterEqual) {
          errorsForTahunPelaksanaan.push('Tahun Pelaksanaan tidak boleh sebelum Tahun Usulan')
        }

        if (!this.$v.editedReviewer.tahunPelaksanaan.required) {
          errorsForTahunPelaksanaan.push('Wajib Diisi')
        }

        return errorsForTahunPelaksanaan
      },
    },

    watch: {
      selectTahunUsulan (value) {
        this.getData(value)
      },

      searchPeneliti (value) {
        if (value !== null && value !== undefined) {
          if (value.length > 2) {
            this.getDaftarPeneliti(value)
          }
        }
      },

      searchDokumen (value) {
        if (value !== null && value !== undefined) {
          if (value.length > 2) {
            this.getDaftarDokumen(value)
          }
        }
      },

    },

    created () {
      this.kelasPoltekkes = this.kelasPoltekkes = this.$store.state.user.organisasi.klasifikasi
      if ((this.$store.state.user.role.roleCode).includes('PPM')) {
        this.idOrganisasi = this.$store.state.user.organizationId
        this.isEksternal = false
      } else {
        this.idOrganisasi = '1'
        this.isEksternal = true
      }

      this.tahunIni = new Date().getFullYear()
      this.editedReviewer.tahunUsulan = this.tahunIni
      this.editedReviewer.tahunPelaksanaan = this.tahunIni + 1
      this.defaultReviewer.tahunUsulan = this.tahunIni
      this.defaultReviewer.tahunPelaksanaan = this.tahunIni + 1

      this.getDaftarTahunUsulan()
      this.getData(this.tahunIni)
    },

    methods: {
      getData (year) {
        apiServer
          .get('/reviewer-internal/' + this.idOrganisasi + '/' + year)
          .then(response => {
            this.daftarReviewer = response.data
            this.isLoading = false
          })
          .catch(error => {
            console.log('There was an error:', error.response) // Logs out the error
            this.isLoading = false
          })
      },
      getDaftarTahunUsulan () {
        apiServer
          .get('/tahun-usulan/in/' + this.idOrganisasi)
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
      getDaftarPeneliti (keyword) {
        apiServer
          .get('/peneliti-reviewer-internal/' + this.idOrganisasi + '/' + keyword)
          .then(response => {
            this.daftarPeneliti = response.data
          })
          .catch(error => {
            console.log('There was an error:', error.response) // Logs out the error
          })
      },
      getDaftarDokumen (keyword) {
        apiServer
          .get('/dokumen-penugasan-reviewer/' + this.idOrganisasi + '/' + this.editedReviewer.tahunUsulan + '/' + keyword)
          .then(response => {
            this.daftarDokumen = response.data
          })
          .catch(error => {
            console.log('There was an error:', error.response) // Logs out the error
          })
      },
      saveReviewer () {
        this.$v.$touch()
        this.errors = this.$v.editedReviewer.$anyError

        this.editedReviewer.username = this.$store.state.user.name
        this.editedReviewer.idOrganisasi = this.$store.state.user.organizationId

        if (this.errors === false) {
          if (this.isEditing) {
            // update
            console.log('update reviewer: ', this.editedReviewer)
            // untuk handling clearable dari v-autocomplete
            if (this.editedReviewer.idDokumenOrganisasi === undefined) {
              this.editedReviewer.idDokumenOrganisasi = null
            }

            apiServer
              .put('/reviewer-internal', this.editedReviewer)
              .then(response => {
                this.showSnackbar('success', 'Reviewer Internal berhasil diubah')
                this.getData(this.tahunIni)
                this.close()
              })
              .catch(error => {
                console.log('error', error.response)
                this.showSnackbar('error', 'Error, Harap hubungi Administrator!')
              })
          } else {
            // add new
            this.editedReviewer.idPemberiTugas = this.$store.state.user.userId

            apiServer
              .post('/reviewer-internal', this.editedReviewer)
              .then(response => {
                this.showSnackbar('success', 'Reviewer Internal berhasil ditambah')
                this.getData(this.tahunIni)
                this.getDaftarTahunUsulan()
                this.close()
              })
              .catch(error => {
                if (error.response.status === 409) {
                  this.showSnackbar('error', 'Reviewer sudah ditugaskan.')
                } else {
                  this.showSnackbar('error', 'Error, Harap hubungi Administrator!')
                }
              })
          }
        } else {
          this.showSnackbar('error', 'Harap perbaiki data terlebih dahulu')
        }
      },
      close () {
        this.dialog = false
        this.$v.$reset()
        setTimeout(() => {
          this.editedReviewer = Object.assign({}, this.defaultReviewer)
          this.isEditing = false
        }, 300)
      },
      riwayatReview (item) {
        console.log('isi item riwayatReview: ', item)
        this.$store.dispatch('setRiwayatReview', item)
        this.$router.push('/riwayat-review')
      },
      updateReviewer (item) {
        this.isEditing = true
        this.editedReviewer = Object.assign({}, item)
        this.daftarPeneliti = [{ id: item.idPeneliti, nama: item.namaPeneliti }]
        if (item.judulDokumen !== null) {
          this.daftarDokumen = [{ id: item.idDokumenOrganisasi, judul: item.judulDokumen }]
        }
        this.dialog = true
      },
      deleteReviewer (item) {
        if (confirm('Apakah Anda yakin ingin menghapus penugasan reviewer ' + item.namaPeneliti + '?')) {
          apiServer
            .delete('/reviewer/' + item.id + '/' + this.$store.state.user.name)
            .then(response => {
              this.getDaftarTahunUsulan()
              this.getData(this.tahunIni)
              this.showSnackbar('success', 'Penugasan Reviewer Internal ' + item.namaPeneliti + ' berhasil dihapus.')
            })
            .catch(error => {
              if (error.response.status === 409) {
                this.showSnackbar('error', 'Gagal menghapus karena data ' + item.namaPeneliti + ' digunakan oleh data lain!')
              } else {
                this.showSnackbar('error', 'Error, Harap hubungi Administrator!')
              }
            })
        }
      },
      downloadDokumen (item) {
        apiServer
          .post('/dokumen-organisasi/download/' + item.idDokumenOrganisasi)
          .then(response => {
            const ticket = response.data.ticket
            window.location = fileUrl + '/download/dokumen-organisasi/' + item.idDokumenOrganisasi + '?ticket=' + ticket
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

<style>

</style>
