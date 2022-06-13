<template>
  <v-container fluid>
    <material-card v-if="hasAccess == false">
      <page403 />
    </material-card>
    <material-card
      color="blue"
      title="Detail Usulan"
      class="font-weight-bold"
      v-if="hasAccess"
    >
      <v-app>
        <v-row v-if="snackbar">
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
        </v-row>

        <v-container>
          <v-row class="mt-6">
            <v-col>
              <v-label>Judul Usulan: {{ judul }}</v-label>
            </v-col>
          </v-row>

          <v-row>
            <v-col>
              <v-card raised>
                <v-tabs
                  v-model="tab"
                  background-color="primary"
                  fixed-tabs
                  dark
                >
                  <v-tab :to="'/penelitian/'+$route.params.id+'/identitas-usulan'">
                    Identitas Usulan
                  </v-tab>
                  <v-tab :to="'/penelitian/'+$route.params.id+'/substansi-usulan'">
                    Substansi Usulan
                  </v-tab>
                  <v-tab :to="'/penelitian/'+$route.params.id+'/rab'">
                    RAB
                  </v-tab>
                  <v-tab :to="'/penelitian/'+$route.params.id+'/rencana-kegiatan'">
                    Jadwal Kegiatan
                  </v-tab>
                  <v-tab :to="'/penelitian/'+$route.params.id+'/mitra-penelitian'">
                    Dokumen Pendukung
                  </v-tab>
                  <v-tab :to="'/penelitian/'+$route.params.id+'/kirim-usulan'">
                    Kirim Usulan
                  </v-tab>
                </v-tabs>

                <v-tabs-items v-model="tab">
                  <v-tab-item :id="tab">
                    <router-view />
                  </v-tab-item>
                </v-tabs-items>
              </v-card>
            </v-col>
          </v-row>
          <v-card v-if="hasAccess && showReview">
            <v-expansion-panels>
              <v-expansion-panel>
                <v-expansion-panel-header>
                  <v-card-title>Penilaian Usulan Penelitian</v-card-title>
                </v-expansion-panel-header>
                <v-expansion-panel-content>
                  <hasil-review />
                </v-expansion-panel-content>
              </v-expansion-panel>
            </v-expansion-panels>
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
          </v-card>
          <v-row>
            <v-toolbar flat>
              <v-dialog
                v-model="dialog"
                max-width="600px"
              >
                <template v-slot:activator="{ on }">
                  <v-col class="text-right">
                    <v-card-actions>
                      <div class="flex-grow-1" />
                      <v-btn
                        color="blue"
                        class="mb-2 mr-1 white--text"
                        @click="back()"
                      >
                        Simpan Sebagai Draft
                      </v-btn>
                      <v-btn
                        color="red"
                        class="mb-2 white--text"
                        v-if="isDraft === true"
                        v-on="on"
                      >
                        Batalkan Usulan
                      </v-btn>
                    </v-card-actions>
                  </v-col>
                </template>
                <v-card>
                  <v-card-title>
                    <span class="headline">Konfirmasi Aksi</span>
                  </v-card-title>
                  <v-divider />
                  <v-card-text>
                    <v-container>
                      <v-row>
                        <v-col>
                          <p class="title">Apakah anda yakin ingin membatalkan usulan penelitian ini? <i>(Data yang sudah dibatalkan akan terhapus dan tidak bisa dikembalikan)</i> </p>
                        </v-col>
                      </v-row>
                      <v-row>
                        <v-col class="text-right">
                          <v-card-actions>
                            <div class="flex-grow-1" />
                            <v-btn
                              color="blue darken-1 white--text"
                              :loading="loading"
                              :disabled="loading"
                              @click="hapusPenelitian"
                            >
                              Batalkan Usulan
                            </v-btn>
                            <v-btn
                              color="red darken-1 white--text"
                              @click="close"
                            >
                              Kembali
                            </v-btn>
                          </v-card-actions>
                        </v-col>
                      </v-row>
                    </v-container>
                  </v-card-text>

                  <v-card-actions />
                </v-card>
              </v-dialog>
            </v-toolbar>
          </v-row>
        </v-container>
      </v-app>
    </material-card>
  </v-container>
</template>

<script>
  import axios from 'axios'
  import { apiServer } from '../../../setup-axios'

  export default {
    data: () => ({
      tab: null,
      dialog: false,
      snackbar: false,
      snackbarText: '',
      snackbarColor: '',
      ketuaTimPeneliti: [],
      hasAccess: null,
      judul: '',
      idSkemaPenelitian: '',
      tahunUsulan: '',
      showReview: null,
      isDraft: null,
      loading: false,
    }),

    components: {
      Page403: () => import('@/views/403.vue'),
      HasilReview: () => import('@/views/penelitian/overview/HasilReview.vue'),
    },

    created () {
      apiServer
        .get('/validasi/date/isAllowed')
        .then(response => {
          var pesanValidasi = response.data
          if (pesanValidasi !== 'Allowed') {
            this.$store.dispatch('setStatus', {
              snackbarColor: 'danger',
              snackbarText: pesanValidasi,
            })
            this.$router.replace('/usulan')
          } else {
            axios.all([
              apiServer.get('/timPeneliti/getKetuaTimPeneliti/' + this.$route.params.id),
              apiServer.get('/validasi/isPoltekkes/' + this.$store.state.user.organizationId),
              apiServer.get('/penelitian/detailStatus/' + this.$route.params.id),
              apiServer.get('/validasi/isDosenAktif/' + this.$store.state.user.userId),
            ]).then(axios.spread((response, response1, response2, response3) => {
              console.log('response /timPeneliti/getKetuaTimPeneliti/:', response.data)
              this.penelitian = response2.data
              this.ketuaTimPeneliti = response.data
              var orgId = this.$store.state.user.organizationId == null ? 0 : this.$store.state.user.organizationId
              var isPoltekkes = response1.data
              var isDraft = (this.penelitian.status === 'DRAFT' || this.penelitian.status === 'DRAFT_REVISION')
              var isDosen = this.$store.state.user.role.roleCode === 'DOSEN'
              var isKetua = orgId === 0 || this.ketuaTimPeneliti.idOrganisasi === orgId
              var isAktif = response3.data
              this.isDraft = this.penelitian.status === 'DRAFT'
              this.showReview = this.penelitian.status === 'DRAFT_REVISION'
              this.hasAccess = isPoltekkes && isDraft && isDosen && isKetua && isAktif
              console.log('isPoltekkes : ' + isPoltekkes + ', isDraft : ' + isDraft + ', isDosen : ' + isDosen + ', isKetua : ' + isKetua + ', isAktif/nidn!=null : ' + isAktif)
              if (this.hasAccess) {
                this.getData()
              }
            })).catch(error => {
              console.log('Terdapat error:', error.response) // Logs out the error
            })
          }
        })
        .catch(error => {
          console.log('There was an error:', error.response) // Logs out the error
        })
    },

    methods: {
      getData () {
        apiServer
          .get('/penelitian/' + this.$route.params.id)
          .then(response => {
            console.log('response: /penelitian/' + this.$route.params.id, this.ketuaTimPeneliti)
            this.$store.dispatch('setPenelitian', response.data)
            this.judul = response.data.judul
            this.idSkemaPenelitian = response.data.idSkemaPenelitian
            this.tahunUsulan = response.data.tahunUsulan == null ? 'null' : response.data.tahunUsulan
          })
          .catch(error => {
            console.log('There was an error:', error.response) // Logs out the error
          })
      },
      back () {
        if (this.hasAccess) {
          this.$router.replace('/usulan/')
        }
      },
      showSnackbar (color, message) {
        console.log('snack ' + color + ' - ' + message)
        this.snackbar = true
        this.snackbarText = message
        this.snackbarColor = color
      },
      hapusPenelitian () {
        this.loading = true
        setTimeout(() => (this.loading = false), 10000)
        apiServer.delete('/penelitian/' + this.$route.params.id + '/' + this.$store.state.user.name)
          .then((resp) => {
            this.$router.replace('/usulan-baru/penelitian')
            this.showSnackbar('success', 'Data berhasil dihapus')
          })
          .catch(error => {
            console.log('There was an error:', error.response) // Logs out the error
          })
      },
      close () {
        this.dialog = false
      },
    },
  }
</script>

<style lang="scss" scoped>
    // .search{
    //   margin-left: 20%;
    // }
    #tblPokok td{
        border: 1px dotted black;
    }

</style>
