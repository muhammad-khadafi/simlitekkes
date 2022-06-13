<template>
  <v-container
    class="pt-10 pb-10"
  >
    <v-card>
      <v-card-title>Preview Usulan</v-card-title>
      <detail-usulan />
      <v-row>
        <v-col
          class="text-right mb-2"
        >
          <div class="flex-grow-1" />
          <v-dialog
            v-model="dialog"
            max-width="600px"
          >
            <template v-slot:activator="{ on }">
              <v-col class="text-right">
                <v-card-actions>
                  <div class="flex-grow-1" />
                  <v-btn
                    color="blue darken-1 white--text"
                    v-on="on"
                  >
                    {{ labelBtn }}
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
                      <p class="title">
                        Anda yakin ingin mengirimkan usulan penelitian?
                      </p>
                      <p class="subtitle-1">
                        <b>Terdapat bagian yang belum anda isikan.</b>
                      </p>
                      <li
                        v-for="info in infoPerTab"
                        :key="info"
                        style="margin-bottom: 5px; color:red"
                      >
                        {{ info }}
                      </li>
                    </v-col>
                  </v-row>
                  <v-row>
                    <v-col class="text-right">
                      <v-card-actions>
                        <div class="flex-grow-1" />
                        <v-btn
                          color="blue darken-1 white--text"
                          @click="sendUsulan"
                        >
                          Kirim
                        </v-btn>
                        <v-btn
                          color="red darken-1 white--text"
                          @click="close"
                        >
                          Batal
                        </v-btn>
                      </v-card-actions>
                    </v-col>
                  </v-row>
                </v-container>
              </v-card-text>

              <v-card-actions />
            </v-card>
          </v-dialog>
        </v-col>
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
    </v-card>
  </v-container>
</template>

<script>
  import { apiServer } from '../../../setup-axios'
  import axios from 'axios'

  export default {
    components: {
      DetailUsulan: () => import('@/views/penelitian/overview/DetailUsulan.vue'),
    },
    data: () => ({
      tab: null,
      penelitian: null,
      statusTahapPenelitian: null,
      editedItem: {
        idPenelitian: null,
        idStatusTahapPenelitian: null,
        timestampUpdate: null,
        username: '',
      },
      dialog: false,
      snackbar: false,
      snackbarText: '',
      snackbarColor: '',
      labelBtn: '',
      infoPerTab: [],
    }),
    created () {
      this.getListPerTab()
      apiServer
        .get('/penelitian/detailStatus/' + this.$route.params.id)
        .then(response => {
          this.penelitian = response.data
          apiServer
            .get('/penelitian/getStatusBaru/' + this.penelitian.idStatusTahapPenelitian + '/' + this.$store.state.user.role.roleCode)
            .then(response => {
              this.statusTahapPenelitian = response.data
              this.labelBtn = this.statusTahapPenelitian.labelButton
              this.editedItem.idPenelitian = this.$route.params.id
              this.editedItem.idStatusTahapPenelitian = this.statusTahapPenelitian.id
              this.editedItem.timestampUpdate = new Date()
              this.editedItem.username = this.$store.state.user.username
              this.editedItem.isLast = true
            })
            .catch(error => {
              console.log('There was an error:', error.response) // Logs out the error
            })
        })
        .catch(error => {
          console.log('There was an error:', error.response) // Logs out the error
        })
    },
    methods: {
      getListPerTab () {
        axios.all([
          apiServer.get('/jadwal-kegiatan/all/' + this.$route.params.id),
          apiServer.get('/mitra-penelitian/all/' + this.$route.params.id),
          apiServer.get('/rencana-anggaran/' + this.$route.params.id),
          apiServer.get('/penelitian/' + this.$route.params.id),
          apiServer.get('/target-output-penelitian/' + this.$route.params.id),
          apiServer.get('/tim-peneliti/anggota/' + this.$route.params.id),
        ]).then(axios.spread((listJadwal, listMitra, listRab, penelitian, listTarget, listAnggotaTim) => {
          this.infoPerTab = []
          var countListJadwal = listJadwal.data.length
          var countListMitra = listMitra.data.length
          var countListRab = listRab.data.length
          var countListTarget = listTarget.data.length
          var countListAnggotaTim = listAnggotaTim.data.length
          var penelitianIdentitasPengusul = penelitian.data.idRumpunIlmu
          var penelitianTkt = penelitian.data.idKelompokTkt
          var penelitianSubstansi = penelitian.data.ringkasan
          if (penelitianTkt === null) this.infoPerTab.push('Identitas Usulan - Belum melakukan pengukuran TKT')
          if (penelitianIdentitasPengusul === null) this.infoPerTab.push('Identitas Usulan - Belum memasukkan identitas pengusul')
          if (countListAnggotaTim < 1) this.infoPerTab.push('Identitas Usulan - Belum memasukkan anggota tim peneliti')
          if (penelitianSubstansi === null) this.infoPerTab.push('Substansi Usulan - Belum memasukkan deskripsi substansi usulan')
          if (countListTarget < 1) this.infoPerTab.push('Substansi Usulan - Belum memasukkan luaran dan target usulan')
          if (countListRab < 1) this.infoPerTab.push('RAB - Belum memasukkan rencana anggaran')
          if (countListJadwal < 1) this.infoPerTab.push('Jadwal Kegiatan - Belum memasukkan jadwal kegiatan')
          if (countListMitra < 1) this.infoPerTab.push('Dokumen Pendukung - Belum memasukkan mitra penelitian')
        })).catch(error => {
          console.log('Terdapat error:', error.response) // Logs out the error
        })
      },
      sendUsulan () {
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
              apiServer
                .get('/penelitian/' + this.$route.params.id)
                .then(response => {
                  var penelitian = response.data
                  penelitian.tahunUsulan = (new Date()).getFullYear()
                  apiServer
                    .put('/penelitian', penelitian)
                    .then(response => {
                      console.log('success update penelitian')
                      apiServer
                        .put('/workflow-penelitian', this.editedItem)
                        .then(response => {
                          apiServer
                            .post('/workflow-penelitian', this.editedItem)
                            .then(response => {
                              this.$router.replace('/usulan')
                              this.showSnackbar('success', 'Data Berhasil Dikirimkan')
                            })
                            .catch(error => {
                              console.log('There was an error:', error.response)
                              this.showSnackbar('error', 'Error, please contact your administrator!')
                            })
                        })
                        .catch(error => {
                          console.log('There was an error:', error.response)
                          this.showSnackbar('error', 'Error, please contact your administrator!')
                        })
                    })
                    .catch(error => {
                      console.log('There was an error:', error.response)
                      this.showSnackbar('error', 'Terdapat Kesalahan')
                    })
                })
                .catch(error => {
                  console.log('There was an error:', error.response)
                  this.showSnackbar('error', 'Terdapat Kesalahan')
                })
            }
          })
          .catch(error => {
            console.log('There was an error:', error.response) // Logs out the error
          })
      },
      batal () {
        this.$router.replace('/usulan')
      },
      close () {
        this.dialog = false
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

<style lang="scss">
  // .search{
  //   margin-left: 20%;
  // }
</style>
